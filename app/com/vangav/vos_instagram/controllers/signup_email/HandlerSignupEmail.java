/**
 * "First, solve the problem. Then, write the code. -John Johnson"
 * "Or use Vangav M"
 * www.vangav.com
 * */

/**
 * MIT License
 *
 * Copyright (c) 2016 Vangav
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to
 * deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or
 * sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
 * IN THE SOFTWARE.
 * */

/**
 * Community
 * Facebook Group: Vangav Open Source - Backend
 *   fb.com/groups/575834775932682/
 * Facebook Page: Vangav
 *   fb.com/vangav.f
 * 
 * Third party communities for Vangav Backend
 *   - play framework
 *   - cassandra
 *   - datastax
 *   
 * Tag your question online (e.g.: stack overflow, etc ...) with
 *   #vangav_backend
 *   to easier find questions/answers online
 * */

package com.vangav.vos_instagram.controllers.signup_email;

import java.util.Calendar;
import java.util.UUID;

import com.datastax.driver.core.BatchStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.BatchStatement.Type;
import com.vangav.backend.cassandra.Cassandra;
import com.vangav.backend.cassandra.formatting.CalendarFormatterInl;
import com.vangav.backend.content.formatting.EncodingInl;
import com.vangav.backend.exceptions.BadRequestException;
import com.vangav.backend.exceptions.VangavException.ExceptionClass;
import com.vangav.backend.metrics.time.TimeUnitType;
import com.vangav.backend.play_framework.request.Request;
import com.vangav.backend.play_framework.request.RequestJsonBody;
import com.vangav.backend.play_framework.request.RequestState;
import com.vangav.backend.play_framework.request.response.ResponseBody;
import com.vangav.backend.security.authentication.o_auth_2.OAuth2Tokens;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_analytics.AnnualRegionalCounters;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_analytics.DailyRegionalCounters;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_app_data.UsersIndex;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_app_data.UsersInfo;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_auth.AuthCodes;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_auth.EmailCreds;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_auth.UsersCredIds;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_blobs.ProfilePicturesBlobs;
import com.vangav.vos_instagram.common.Constants;
import com.vangav.vos_instagram.controllers.CommonPlayHandler;

/**
 * GENERATED using ControllersGeneratorMain.java
 */
/**
 * HandlerSignupEmail
 *   handles request-to-response processing
 *   also handles after response processing (if any)
 * */
public class HandlerSignupEmail extends CommonPlayHandler {

  private static final String kName = "SignupEmail";

  @Override
  protected String getName () {

    return kName;
  }

  @Override
  protected RequestJsonBody getRequestJson () {

    return new RequestSignupEmail();
  }

  @Override
  protected ResponseBody getResponseBody () {

    return new ResponseSignupEmail();
  }
  
  @Override
  protected void authenticateRequest (
    final Request request) throws Exception {
    
    // TODO: implement
    
    // get request's body
    RequestSignupEmail requestSignupEmail =
      (RequestSignupEmail)request.getRequestJsonBody();
    
    ResultSet resultSet =
      EmailCreds.i().executeSyncSelect(requestSignupEmail.email);
    
    // email already signed up?
    if (resultSet.isExhausted() == false) {
      
      throw new BadRequestException(
        427,
        1,
        "Email ["
          + requestSignupEmail.email
          + "] is already signed up, request issued from device_token ["
          + requestSignupEmail.device_token
          + "]",
        ExceptionClass.AUTHENTICATION);
    }
  }

  @Override
  protected void processRequest (final Request request) throws Exception {

    // use the following request Object to process the request and set
    //   the response to be returned
    RequestSignupEmail requestSignupEmail =
      (RequestSignupEmail)request.getRequestJsonBody();
    
    // new user_id
    UUID userId = UUID.randomUUID();
    
    // set user's id
    request.setUserId(userId);

    // generate new authentication tokens
    OAuth2Tokens oAuth2Tokens = new OAuth2Tokens();
    
    // database queries
    
    // insert into ig_blobs.profile_pictures_blobs
    if (requestSignupEmail.isValidParam(
          RequestSignupEmail.kProfilePictureName) == true) {
      
      ProfilePicturesBlobs.i().executeSyncInsert(
        userId,
        EncodingInl.encodeStringIntoByteBuffer(
          requestSignupEmail.profile_picture) );
    }

    // all queries must succeed - ig_auth
    BatchStatement batchStatement = new BatchStatement(Type.LOGGED);
    
    // insert into ig_auth.auth_codes
    batchStatement.add(
      AuthCodes.i().getBoundStatementInsert(
        userId,
        requestSignupEmail.device_token,
        oAuth2Tokens.getAuthorizationCode(),
        oAuth2Tokens.getAccessToken(),
        oAuth2Tokens.getRefreshToken(),
        ((int)Constants.kAuthCodeLifeTime.getAs(
          TimeUnitType.SECOND).getValue() ) ) );
    
    // insert into ig_auth.email_creds
    batchStatement.add(
      EmailCreds.i().getBoundStatementInsert(
        requestSignupEmail.email,
        requestSignupEmail.password,
        userId) );
    
    // set email in ig_auth.users_cred_ids
    batchStatement.add(
      UsersCredIds.i().getBoundStatementSetEmail(
        requestSignupEmail.email,
        userId) );
    
    try {
    
      // execute batch statement
      Cassandra.i().executeSync(batchStatement);
    } catch (Exception e) {
      
      // failed, undo successful queries and re-throw
      
      if (requestSignupEmail.isValidParam(
            RequestSignupEmail.kProfilePictureName) == true) {
        
        ProfilePicturesBlobs.i().executeSyncDelete(userId);
      }
      
      throw e;
    }

    // all queries must succeed - ig_app_data
    batchStatement = new BatchStatement(Type.LOGGED);
    
    // insert into ig_app_data.users_index
    batchStatement.add(
      UsersIndex.i().getBoundStatementInsert(
        CalendarFormatterInl.concatCalendarFields(
          request.getStartCalendar(),
          Calendar.YEAR,
          Calendar.MONTH,
          Calendar.DAY_OF_MONTH),
        request.getStartTime(),
        userId) );
    
    // insert into ig_app_data.users_info
    batchStatement.add(
      UsersInfo.i().getBoundStatementInsert(
        userId,
        requestSignupEmail.name,
        request.getStartTime(),
        request.getStartTime() ) );
    
    // set profile_picture_id in ig_app_data.users_info
    if (requestSignupEmail.isValidParam(
          RequestSignupEmail.kProfilePictureName) == true) {
      
      batchStatement.add(
        UsersInfo.i().getBoundStatementSetProfilePictureId(
          userId,
          userId) );
    }
    
    try {
    
      // execute batch statement
      Cassandra.i().executeSync(batchStatement);
    } catch (Exception e) {
      
      // failed, undo successful queries and re-throw
      
      if (requestSignupEmail.isValidParam(
            RequestSignupEmail.kProfilePictureName) == true) {
        
        ProfilePicturesBlobs.i().executeSyncDelete(userId);
      }

      batchStatement = new BatchStatement(Type.LOGGED);
      
      batchStatement.add(
        AuthCodes.i().getBoundStatementDelete(
          userId,
          requestSignupEmail.device_token) );
      
      batchStatement.add(
        EmailCreds.i().getBoundStatementDelete(
          requestSignupEmail.email) );
      
      batchStatement.add(
        UsersCredIds.i().getBoundStatementDelete(
          userId) );
      
      // execute batch statement
      Cassandra.i().executeSync(batchStatement);
      
      throw e;
    }
    
    // set response
    ((ResponseSignupEmail)request.getResponseBody() ).set(
      requestSignupEmail.request_tracking_id,
      userId.toString(),
      oAuth2Tokens.getAuthorizationCode() );
  }

  @Override
  protected void dispatchAnalysis (
    final Request request) throws Exception {
    
    // only process this method if user's request was a success
    if (request.getState() != RequestState.OK) {
      
      return;
    }
    
    // ig_analytics.annual_regional_counters.increment_new_users
    request.getDispatcher().addDispatchMessage(
      AnnualRegionalCounters.i().getQueryDispatchableIncrementNewUsers(
        CalendarFormatterInl.concatCalendarFields(
          request.getStartCalendar(),
          Calendar.YEAR)
          + Constants.kCassandraIdConcat
          + Constants.kWorldRegion) );
    
    // ig_analytics.daily_regional_counters.increment_new_users
    request.getDispatcher().addDispatchMessage(
      DailyRegionalCounters.i().getQueryDispatchableIncrementNewUsers(
        CalendarFormatterInl.concatCalendarFields(
          request.getStartCalendar(),
          Calendar.YEAR,
          Calendar.MONTH,
          Calendar.DAY_OF_MONTH)
          + Constants.kCassandraIdConcat
          + Constants.kWorldRegion) );
  }
}
