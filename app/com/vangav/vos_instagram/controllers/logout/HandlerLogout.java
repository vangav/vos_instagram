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

package com.vangav.vos_instagram.controllers.logout;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.datastax.driver.core.BatchStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.BatchStatement.Type;
import com.vangav.backend.cassandra.Cassandra;
import com.vangav.backend.exceptions.BadRequestException;
import com.vangav.backend.exceptions.VangavException.ExceptionClass;
import com.vangav.backend.play_framework.request.Request;
import com.vangav.backend.play_framework.request.RequestJsonBody;
import com.vangav.backend.play_framework.request.response.ResponseBody;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_auth.AccessTokens;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_auth.AuthCodes;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_auth.RefreshTokens;
import com.vangav.vos_instagram.common.Authenticator;
import com.vangav.vos_instagram.common.cassandra.GettersInl;
import com.vangav.vos_instagram.controllers.CommonPlayHandler;
import com.vangav.vos_instagram.controllers.logout.request_enums.LogoutType;

/**
 * GENERATED using ControllersGeneratorMain.java
 */
/**
 * HandlerLogout
 *   handles request-to-response processing
 *   also handles after response processing (if any)
 * */
public class HandlerLogout extends CommonPlayHandler {

  private static final String kName = "Logout";

  @Override
  protected String getName () {

    return kName;
  }

  @Override
  protected RequestJsonBody getRequestJson () {

    return new RequestLogout();
  }

  @Override
  protected ResponseBody getResponseBody () {

    return new ResponseLogout();
  }
  
  @Override
  protected Authenticator getRequestAuthenticator (
    final RequestJsonBody requestJsonBody) throws Exception {
    
    return
      new Authenticator(
        ((RequestLogout)requestJsonBody).device_token,
        ((RequestLogout)requestJsonBody).user_id,
        ((RequestLogout)requestJsonBody).access_token);
  }
  
  @Override
  protected void authenticateRequest (
    final Request request) throws Exception {
    
    // do normal access_token authentication first
    super.authenticateRequest(request);
    
    // get request's body
    RequestLogout requestLogout =
      (RequestLogout)request.getRequestJsonBody();
    
    // get user's refresh token
    ResultSet resultSet =
      RefreshTokens.i().executeSyncSelect(
        requestLogout.getUserId(),
        requestLogout.device_token);
    
    // no refresh token for this user/device_token
    if (resultSet.isExhausted() == true) {
      
      throw new BadRequestException(
        423,
        1,
        "No refresh token for user_id ["
          + requestLogout.user_id
          + "] and device_token ["
          + requestLogout.device_token
          + "]",
        ExceptionClass.AUTHENTICATION);
    }
    
    // extract refresh token
    String refreshToken =
      resultSet.one().getString(RefreshTokens.kRefreshTokenColumnName);
    
    // wrong refresh token
    if (requestLogout.refresh_token.compareTo(refreshToken) != 0) {
      
      throw new BadRequestException(
        423,
        2,
        "Wrong refresh token for user_id ["
          + requestLogout.user_id
          + "] and device_token ["
          + requestLogout.device_token
          + "], expected refresh_token ["
          + refreshToken
          + "] but got ["
          + requestLogout.refresh_token
          + "]",
        ExceptionClass.AUTHENTICATION);
    }
  }

  @Override
  protected void processRequest (final Request request) throws Exception {

    // use the following request Object to process the request and set
    //   the response to be returned
    RequestLogout requestLogout =
      (RequestLogout)request.getRequestJsonBody();
    
    // get logout type
    
    LogoutType logoutType;
    
    if (requestLogout.isValidParam(
          RequestLogout.kLogoutTypeName) == true) {
      
      try {
      
        logoutType = LogoutType.valueOf(requestLogout.logout_type);
      } catch (Exception e) {
        
        logoutType = LogoutType.getDefaultType();
      }
    } else {
      
      logoutType = LogoutType.getDefaultType();
    }

    // get device tokens to logout from
    
    Set<String> deviceTokensToLogoutFrom = new HashSet<String>();
    
    if (logoutType == LogoutType.THIS_DEVICE) {
      
      deviceTokensToLogoutFrom.add(requestLogout.device_token);
    } else {
      
      Map<String, String> userDeviceTokens =
        GettersInl.getUserDeviceTokens(requestLogout.getUserId() );
      
      if (logoutType == LogoutType.OTHER_DEVICES) {
        
        for (String deviceToken : userDeviceTokens.keySet() ) {
          
          if (deviceToken.compareTo(requestLogout.device_token) != 0) {
            
            deviceTokensToLogoutFrom.add(deviceToken);
          }
        }
      } else {
        
        deviceTokensToLogoutFrom.addAll(userDeviceTokens.keySet() );
      }
    }
    
    // log out
    
    // all queries must succeed
    BatchStatement batchStatement = new BatchStatement(Type.LOGGED);
    
    for (String deviceToken : deviceTokensToLogoutFrom) {
      
      // delete from ig_auth.auth_codes
      batchStatement.add(
        AuthCodes.i().getBoundStatementDelete(
          requestLogout.getUserId(),
          deviceToken) );
      
      // delete from ig_auth.access_tokens
      batchStatement.add(
        AccessTokens.i().getBoundStatementDelete(
          requestLogout.getUserId(),
          deviceToken) );
      
      // delete from ig_auth.refresh_tokens
      batchStatement.add(
        RefreshTokens.i().getBoundStatementDelete(
          requestLogout.getUserId(),
          deviceToken) );
    }
    
    // execute batch statement
    Cassandra.i().executeSync(batchStatement);
    
    // set response
    ((ResponseLogout)request.getResponseBody() ).set(
      requestLogout.request_tracking_id);
  }
}
