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

package com.vangav.vos_instagram.controllers.get_access_token;

import java.util.Arrays;
import java.util.HashSet;
import java.util.UUID;

import com.datastax.driver.core.BatchStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.BatchStatement.Type;
import com.vangav.backend.cassandra.Cassandra;
import com.vangav.backend.exceptions.BadRequestException;
import com.vangav.backend.exceptions.VangavException.ExceptionClass;
import com.vangav.backend.play_framework.request.Request;
import com.vangav.backend.play_framework.request.RequestJsonBody;
import com.vangav.backend.play_framework.request.response.ResponseBody;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_app_data.UsersInfo;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_auth.AccessTokens;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_auth.AuthCodes;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_auth.DeviceTokens;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_auth.RefreshTokens;
import com.vangav.vos_instagram.controllers.CommonPlayHandler;

/**
 * GENERATED using ControllersGeneratorMain.java
 */
/**
 * HandlerGetAccessToken
 *   handles request-to-response processing
 *   also handles after response processing (if any)
 * */
public class HandlerGetAccessToken extends CommonPlayHandler {

  private static final String kName = "GetAccessToken";

  @Override
  protected String getName () {

    return kName;
  }

  @Override
  protected RequestJsonBody getRequestJson () {

    return new RequestGetAccessToken();
  }

  @Override
  protected ResponseBody getResponseBody () {

    return new ResponseGetAccessToken();
  }
  
  @Override
  protected void authenticateRequest (
    final Request request) throws Exception {

    // as an authentication controller, authentication happens in the
    //   processRequest method
  }

  @Override
  protected void processRequest (final Request request) throws Exception {

    // use the following request Object to process the request and set
    //   the response to be returned
    RequestGetAccessToken requestGetAccessToken =
      (RequestGetAccessToken)request.getRequestJsonBody();
    
    // select auth_code, access_token and refresh_token
    ResultSet resultSet =
      AuthCodes.i().executeSyncSelect(
        requestGetAccessToken.getUserId(),
        requestGetAccessToken.device_token);
    
    // auth_code doesn't exist?
    if (resultSet.isExhausted() == true) {
      
      throw new BadRequestException(
        404,
        1,
        "no auth_code for user_id ["
          + requestGetAccessToken.user_id
          + "] and device_token ["
          + requestGetAccessToken.device_token
          + "]",
        ExceptionClass.AUTHENTICATION);
    }
    
    // extract data from selected row
    
    Row row = resultSet.one();
    
    String dbAuthCode = row.getString(AuthCodes.kAuthCodeColumnName);
    String dbAccessToken = row.getString(AuthCodes.kAccessTokenColumnName);
    String dbRefreshToken = row.getString(AuthCodes.kRefreshTokenColumnName);
    
    // wrong auth_code?
    if (requestGetAccessToken.auth_code.compareTo(dbAuthCode) != 0) {
      
      throw new BadRequestException(
        404,
        2,
        "wrong auth_code for user_id ["
          + requestGetAccessToken.user_id
          + "] and device_token ["
          + requestGetAccessToken.device_token
          + "] expected auth_code ["
          + dbAuthCode
          + "] but got auth_code ["
          + requestGetAccessToken.auth_code
          + "]",
        ExceptionClass.AUTHENTICATION);
    }
    
    // all good, execute required queries and set response
    
    // all queries must succeed
    BatchStatement batchStatement = new BatchStatement(Type.LOGGED);
    
    // delete from ig_auth.auth_codes
    batchStatement.add(
      AuthCodes.i().getBoundStatementDelete(
        requestGetAccessToken.getUserId(),
        requestGetAccessToken.device_token) );
    
    // insert into ig_auth.access_tokens
    batchStatement.add(
      AccessTokens.i().getBoundStatementInsert(
        requestGetAccessToken.getUserId(),
        requestGetAccessToken.device_token,
        dbAccessToken,
        request.getStartTime() ) );
    
    // insert into ig_auth.refresh_tokens
    batchStatement.add(
      RefreshTokens.i().getBoundStatementInsert(
        requestGetAccessToken.getUserId(),
        requestGetAccessToken.device_token,
        dbRefreshToken) );
    
    // update ig_auth.device_tokens
    batchStatement.add(
      DeviceTokens.i().getBoundStatementAddUserId(
        new HashSet<UUID>(Arrays.asList(requestGetAccessToken.getUserId() ) ),
        requestGetAccessToken.device_token) );
    
    // execute batch statement
    Cassandra.i().executeSync(batchStatement);
    
    // update user's device tokens
    UsersInfo.i().executeSyncAddDeviceToken(
      requestGetAccessToken.device_token,
      requestGetAccessToken.device_type,
      requestGetAccessToken.getUserId());
    
    // set response
    ((ResponseGetAccessToken)request.getResponseBody() ).set(
      requestGetAccessToken.request_tracking_id,
      dbAccessToken,
      dbRefreshToken);
  }
}
