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

package com.vangav.vos_instagram.controllers.refresh_access_token;

import com.datastax.driver.core.BatchStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.BatchStatement.Type;
import com.vangav.backend.cassandra.Cassandra;
import com.vangav.backend.exceptions.BadRequestException;
import com.vangav.backend.exceptions.VangavException.ExceptionClass;
import com.vangav.backend.metrics.time.TimeUnitType;
import com.vangav.backend.play_framework.request.Request;
import com.vangav.backend.play_framework.request.RequestJsonBody;
import com.vangav.backend.play_framework.request.response.ResponseBody;
import com.vangav.backend.security.authentication.o_auth_2.OAuth2Tokens;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_auth.AccessTokens;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_auth.AuthCodes;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_auth.RefreshTokens;
import com.vangav.vos_instagram.common.Constants;
import com.vangav.vos_instagram.controllers.CommonPlayHandler;

/**
 * GENERATED using ControllersGeneratorMain.java
 */
/**
 * HandlerRefreshAccessToken
 *   handles request-to-response processing
 *   also handles after response processing (if any)
 * */
public class HandlerRefreshAccessToken extends CommonPlayHandler {

  private static final String kName = "RefreshAccessToken";

  @Override
  protected String getName () {

    return kName;
  }

  @Override
  protected RequestJsonBody getRequestJson () {

    return new RequestRefreshAccessToken();
  }

  @Override
  protected ResponseBody getResponseBody () {

    return new ResponseRefreshAccessToken();
  }
  
  @Override
  protected void authenticateRequest (
    final Request request) throws Exception {
    
    // get request's body
    RequestRefreshAccessToken requestRefreshAccessToken =
      (RequestRefreshAccessToken)request.getRequestJsonBody();
    
    // get user's access token
    ResultSet resultSet =
      AccessTokens.i().executeSyncSelect(
        requestRefreshAccessToken.getUserId(),
        requestRefreshAccessToken.device_token);
    
    // no access token?
    if (resultSet.isExhausted() == true) {
     
      throw new BadRequestException(
        425,
        1,
        "No access_token for user_id ["
          + requestRefreshAccessToken.user_id
          + "] and device_token ["
          + requestRefreshAccessToken.device_token
          + "]",
        ExceptionClass.AUTHENTICATION);
    }
   
    // extract db access token
    String dbAccessToken =
      resultSet.one().getString(AccessTokens.kAccessTokenColumnName);
    
    // wrong access token?
    if (dbAccessToken.compareTo(
          requestRefreshAccessToken.access_token) != 0) {
      
      throw new BadRequestException(
        425,
        2,
        "Wrong access token for user_id ["
          + requestRefreshAccessToken.user_id
          + "] and device_token ["
          + requestRefreshAccessToken.device_token
          + "], expected access_token ["
          + dbAccessToken
          + "] but got access_token ["
          + requestRefreshAccessToken.access_token
          + "]",
        ExceptionClass.AUTHENTICATION);
    }
    
    // get user's refresh token
    resultSet =
      RefreshTokens.i().executeSyncSelect(
        requestRefreshAccessToken.getUserId(),
        requestRefreshAccessToken.device_token);
    
    // no refresh token?
    if (resultSet.isExhausted() == true) {
     
      throw new BadRequestException(
        425,
        3,
        "No refresh_token for user_id ["
          + requestRefreshAccessToken.user_id
          + "] and device_token ["
          + requestRefreshAccessToken.device_token
          + "]",
        ExceptionClass.AUTHENTICATION);
    }
    
    // extract db refresh token
    String dbRefreshToken =
      resultSet.one().getString(RefreshTokens.kRefreshTokenColumnName);
    
    // wrong refresh token?
    if (dbRefreshToken.compareTo(
          requestRefreshAccessToken.refresh_token) != 0) {
      
      throw new BadRequestException(
        425,
        4,
        "Wrong refresh token for user_id ["
          + requestRefreshAccessToken.user_id
          + "] and device_token ["
          + requestRefreshAccessToken.device_token
          + "], expected refresh_token ["
          + dbRefreshToken
          + "] but got refresh_token ["
          + requestRefreshAccessToken.refresh_token
          + "]",
        ExceptionClass.AUTHENTICATION);
    }
  }

  @Override
  protected void processRequest (final Request request) throws Exception {

    // use the following request Object to process the request and set
    //   the response to be returned
    RequestRefreshAccessToken requestRefreshAccessToken =
      (RequestRefreshAccessToken)request.getRequestJsonBody();

    // generate new authentication tokens
    OAuth2Tokens oAuth2Tokens = new OAuth2Tokens();
    
    // all queries must succeed
    BatchStatement batchStatement = new BatchStatement(Type.LOGGED);
    
    // insert into auth_codes
    batchStatement.add(
      AuthCodes.i().getBoundStatementInsert(
        requestRefreshAccessToken.getUserId(),
        requestRefreshAccessToken.device_token,
        oAuth2Tokens.getAuthorizationCode(),
        oAuth2Tokens.getAccessToken(),
        oAuth2Tokens.getRefreshToken(),
        ((int)Constants.kAuthCodeLifeTime.getAs(
          TimeUnitType.SECOND).getValue() ) ) );
    
    // delete from access_tokens
    batchStatement.add(
      AccessTokens.i().getBoundStatementDelete(
        requestRefreshAccessToken.getUserId(),
        requestRefreshAccessToken.device_token) );
    
    // delete from refresh_tokens
    batchStatement.add(
      RefreshTokens.i().getBoundStatementDelete(
        requestRefreshAccessToken.getUserId(),
        requestRefreshAccessToken.device_token) );
    
    // execute batch statement
    Cassandra.i().executeSync(batchStatement);
    
    // set response
    ((ResponseRefreshAccessToken)request.getResponseBody() ).set(
      requestRefreshAccessToken.request_tracking_id,
      oAuth2Tokens.getAuthorizationCode() );
  }
}
