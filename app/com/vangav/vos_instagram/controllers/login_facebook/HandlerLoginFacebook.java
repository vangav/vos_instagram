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

package com.vangav.vos_instagram.controllers.login_facebook;

import java.net.HttpURLConnection;
import java.util.Calendar;
import java.util.Map;
import java.util.UUID;

import com.datastax.driver.core.BatchStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.BatchStatement.Type;
import com.vangav.backend.cassandra.Cassandra;
import com.vangav.backend.cassandra.formatting.CalendarFormatterInl;
import com.vangav.backend.content.formatting.EncodingInl;
import com.vangav.backend.data_structures_and_algorithms.tuple.Pair;
import com.vangav.backend.exceptions.CodeException;
import com.vangav.backend.exceptions.VangavException.ExceptionClass;
import com.vangav.backend.metrics.time.TimeUnitType;
import com.vangav.backend.networks.rest_client.RestResponseJson;
import com.vangav.backend.play_framework.request.Request;
import com.vangav.backend.play_framework.request.RequestJsonBody;
import com.vangav.backend.play_framework.request.RequestState;
import com.vangav.backend.play_framework.request.response.ResponseBody;
import com.vangav.backend.public_apis.facebook.FacebookGraph;
import com.vangav.backend.public_apis.facebook.json.fields.FacebookGraphApiFieldType;
import com.vangav.backend.public_apis.facebook.json.fields.Name;
import com.vangav.backend.security.authentication.facebook.FacebookAuthInl;
import com.vangav.backend.security.authentication.o_auth_2.OAuth2Tokens;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_analytics.AnnualRegionalCounters;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_analytics.DailyRegionalCounters;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_app_data.UsersIndex;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_app_data.UsersInfo;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_auth.AuthCodes;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_auth.FacebookIds;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_auth.UsersCredIds;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_blobs.ProfilePicturesBlobs;
import com.vangav.vos_instagram.common.Constants;
import com.vangav.vos_instagram.controllers.CommonPlayHandler;

/**
 * GENERATED using ControllersGeneratorMain.java
 */
/**
 * HandlerLoginFacebook
 *   handles request-to-response processing
 *   also handles after response processing (if any)
 * */
public class HandlerLoginFacebook extends CommonPlayHandler {

  private static final String kName = "LoginFacebook";

  @Override
  protected String getName () {

    return kName;
  }

  @Override
  protected RequestJsonBody getRequestJson () {

    return new RequestLoginFacebook();
  }

  @Override
  protected ResponseBody getResponseBody () {

    return new ResponseLoginFacebook();
  }
  
  @Override
  protected void authenticateRequest (
    final Request request) throws Exception {
    
    // get request's body
    RequestLoginFacebook requestLoginFacebook =
      (RequestLoginFacebook)request.getRequestJsonBody();
    
    // authenticate using Facebook Graph API
    FacebookAuthInl.validateFacebookAccessToken(
      requestLoginFacebook.fb_access_token,
      Constants.kFacebookAppId);
  }

  private boolean isSignup = false;
  @Override
  protected void processRequest (final Request request) throws Exception {

    // use the following request Object to process the request and set
    //   the response to be returned
    RequestLoginFacebook requestLoginFacebook =
      (RequestLoginFacebook)request.getRequestJsonBody();
    
    // get user's facebook id
    FacebookGraph facebookGraph =
      new FacebookGraph(requestLoginFacebook.fb_access_token);
    
    String facebookId = facebookGraph.getUserId();
    
    // check if that's a login or signup request
    
    ResultSet resultSet =
      FacebookIds.i().executeSyncSelect(facebookId);
    
    if (resultSet.isExhausted() == false) {
      // LOGIN
      
      // get user_id
      UUID userId =
        resultSet.one().getUUID(FacebookIds.kUserIdColumnName);
      
      // set user's id
      request.setUserId(userId);

      // generate new authentication tokens
      OAuth2Tokens oAuth2Tokens = new OAuth2Tokens();
      
      // insert into ig_auth.auth_codes
      AuthCodes.i().executeSyncInsert(
        userId,
        requestLoginFacebook.device_token,
        oAuth2Tokens.getAuthorizationCode(),
        oAuth2Tokens.getAccessToken(),
        oAuth2Tokens.getRefreshToken(),
        ((int)Constants.kAuthCodeLifeTime.getAs(
          TimeUnitType.SECOND).getValue() ) );
      
      // set response and return
      ((ResponseLoginFacebook)request.getResponseBody() ).set(
        requestLoginFacebook.request_tracking_id,
        userId.toString(),
        oAuth2Tokens.getAuthorizationCode(),
        false);
      
      return;
    } else {
      // SIGN UP
      
      // get user's info from facebook
      
      // query Facebook Graph API for name
      Map<
        FacebookGraphApiFieldType,
        Pair<Integer, RestResponseJson> > facebookGraphApiResponse =
        facebookGraph.getFieldsSync(FacebookGraphApiFieldType.NAME);
      
      // error communicating with Facebook Graph API?
      if (facebookGraphApiResponse.get(
            FacebookGraphApiFieldType.NAME).getFirst() !=
            HttpURLConnection.HTTP_OK) {
        
        throw new CodeException(
          422,
          1,
          "Couldn't get user's name from Facebook Graph API. "
            + "Facebook access token ["
            + requestLoginFacebook.fb_access_token
            + "], request issued from device_token ["
            + requestLoginFacebook.device_token
            + "]. Http Status code ["
            + facebookGraphApiResponse.get(
                FacebookGraphApiFieldType.NAME).getFirst()
            + "], response ["
            + facebookGraphApiResponse.get(
                FacebookGraphApiFieldType.NAME).getSecond().toString()
            + "]",
          ExceptionClass.COMMUNICATION);
      }
      
      // extract name field
      Name nameField =
        ((Name)facebookGraphApiResponse.get(
          FacebookGraphApiFieldType.NAME).getSecond() );
      
      // get name
      String name = nameField.name;
      
      // get user's Facebook profile picture
      String profilePicture =
        facebookGraph.getProfilePictureSync(
          Constants.kFacebookProfilePictureDimension);
      
      // new user_id
      UUID userId = UUID.randomUUID();
      
      // set user's id
      request.setUserId(userId);

      // generate new authentication tokens
      OAuth2Tokens oAuth2Tokens = new OAuth2Tokens();
      
      // database queries
      
      // insert into ig_blobs.profile_pictures_blobs
      ProfilePicturesBlobs.i().executeSyncInsert(
        userId,
        EncodingInl.encodeStringIntoByteBuffer(profilePicture) );

      // all queries must succeed - ig_auth
      BatchStatement batchStatement = new BatchStatement(Type.LOGGED);
      
      // insert into ig_auth.auth_codes
      batchStatement.add(
        AuthCodes.i().getBoundStatementInsert(
          userId,
          requestLoginFacebook.device_token,
          oAuth2Tokens.getAuthorizationCode(),
          oAuth2Tokens.getAccessToken(),
          oAuth2Tokens.getRefreshToken(),
          ((int)Constants.kAuthCodeLifeTime.getAs(
            TimeUnitType.SECOND).getValue() ) ) );
      
      // insert into ig_auth.facebook_ids
      batchStatement.add(
        FacebookIds.i().getBoundStatementInsert(
          facebookId,
          userId) );
      
      // set facebook_id in ig_auth.users_cred_ids
      batchStatement.add(
        UsersCredIds.i().getBoundStatementSetFacebookId(
          facebookId,
          userId) );
      
      try {
      
        // execute batch statement
        Cassandra.i().executeSync(batchStatement);
      } catch (Exception e) {
        
        // failed, undo successful queries and re-throw
        
        ProfilePicturesBlobs.i().executeSyncDelete(userId);
        
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
          name,
          request.getStartTime(),
          request.getStartTime() ) );
      
      // set profile_picture_id in ig_app_data.users_info
      batchStatement.add(
        UsersInfo.i().getBoundStatementSetProfilePictureId(
          userId,
          userId) );
      
      try {
      
        // execute batch statement
        Cassandra.i().executeSync(batchStatement);
      } catch (Exception e) {
        
        // failed, undo successful queries and re-throw
        
        ProfilePicturesBlobs.i().executeSyncDelete(userId);

        batchStatement = new BatchStatement(Type.LOGGED);
        
        batchStatement.add(
          AuthCodes.i().getBoundStatementDelete(
            userId,
            requestLoginFacebook.device_token) );
        
        batchStatement.add(
          FacebookIds.i().getBoundStatementDelete(
            facebookId) );
        
        batchStatement.add(
          UsersCredIds.i().getBoundStatementDelete(
            userId) );
      
        // execute batch statement
        Cassandra.i().executeSync(batchStatement);
        
        throw e;
      }
      
      // set response and return
      ((ResponseLoginFacebook)request.getResponseBody() ).set(
        requestLoginFacebook.request_tracking_id,
        userId.toString(),
        oAuth2Tokens.getAuthorizationCode(),
        true);
      
      this.isSignup = true;
      return;
    } // SIGN UP
  }

  @Override
  protected void dispatchAnalysis (
    final Request request) throws Exception {
    
    // only process this method if user's request was a success
    if (request.getState() != RequestState.OK) {
      
      return;
    }
    
    // only increment new users count if this was a sign up
    if (this.isSignup == false) {
      
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
