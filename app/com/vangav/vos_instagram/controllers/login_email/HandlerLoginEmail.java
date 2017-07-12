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

package com.vangav.vos_instagram.controllers.login_email;

import java.util.UUID;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.vangav.backend.exceptions.BadRequestException;
import com.vangav.backend.exceptions.VangavException.ExceptionClass;
import com.vangav.backend.metrics.time.TimeUnitType;
import com.vangav.backend.play_framework.request.Request;
import com.vangav.backend.play_framework.request.RequestJsonBody;
import com.vangav.backend.play_framework.request.response.ResponseBody;
import com.vangav.backend.security.authentication.o_auth_2.OAuth2Tokens;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_auth.AuthCodes;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_auth.EmailCreds;
import com.vangav.vos_instagram.common.Constants;
import com.vangav.vos_instagram.controllers.CommonPlayHandler;

/**
 * GENERATED using ControllersGeneratorMain.java
 */
/**
 * HandlerLoginEmail
 *   handles request-to-response processing
 *   also handles after response processing (if any)
 * */
public class HandlerLoginEmail extends CommonPlayHandler {

  private static final String kName = "LoginEmail";

  @Override
  protected String getName () {

    return kName;
  }

  @Override
  protected RequestJsonBody getRequestJson () {

    return new RequestLoginEmail();
  }

  @Override
  protected ResponseBody getResponseBody () {

    return new ResponseLoginEmail();
  }
  
  @Override
  protected void authenticateRequest (
    final Request request) throws Exception {
    
    // get request's body
    RequestLoginEmail requestLoginEmail =
      (RequestLoginEmail)request.getRequestJsonBody();
    
    // select user's password
    ResultSet resultSet =
      EmailCreds.i().executeSyncSelect(requestLoginEmail.email);
    
    // email isn't registered?
    if (resultSet.isExhausted() == true) {
      
      throw new BadRequestException(
        421,
        1,
        "Email ["
          + requestLoginEmail.email
          + "] isn't registered, request sent from device_token ["
          + requestLoginEmail.device_token
          + "]",
        ExceptionClass.AUTHENTICATION);
    }
    
    // extract password
    Row row = resultSet.one();
    
    String password = row.getString(EmailCreds.kPasswordColumnName);
    
    // wrong password?
    if (requestLoginEmail.password.compareTo(password) != 0) {
      
      throw new BadRequestException(
        421,
        2,
        "Wrong password ["
          + requestLoginEmail.password
          + "], expected password ["
          + password
          + "] for email ["
          + requestLoginEmail.email
          + "], request sent from device_token ["
          + requestLoginEmail.device_token
          + "]",
        ExceptionClass.AUTHENTICATION);
    }
  }

  @Override
  protected void processRequest (final Request request) throws Exception {

    // use the following request Object to process the request and set
    //   the response to be returned
    RequestLoginEmail requestLoginEmail =
      (RequestLoginEmail)request.getRequestJsonBody();

    // select user's password and user_id
    ResultSet resultSet =
      EmailCreds.i().executeSyncSelect(requestLoginEmail.email);
    
    // email isn't registered? -- in case authentication is disabled
    if (resultSet.isExhausted() == true) {
      
      throw new BadRequestException(
        421,
        3,
        "Email ["
          + requestLoginEmail.email
          + "] isn't registered, request sent from device_token ["
          + requestLoginEmail.device_token
          + "]",
        ExceptionClass.AUTHENTICATION);
    }
    
    // extract user_id
    Row row = resultSet.one();
    
    UUID userId = row.getUUID(EmailCreds.kUserIdColumnName);
    
    // set user's id
    request.setUserId(userId);

    // generate new authentication tokens
    OAuth2Tokens oAuth2Tokens = new OAuth2Tokens();
    
    // insert into ig_auth.auth_codes
    AuthCodes.i().executeSyncInsert(
      userId,
      requestLoginEmail.device_token,
      oAuth2Tokens.getAuthorizationCode(),
      oAuth2Tokens.getAccessToken(),
      oAuth2Tokens.getRefreshToken(),
      ((int)Constants.kAuthCodeLifeTime.getAs(
        TimeUnitType.SECOND).getValue() ) );
    
    // set response
    ((ResponseLoginEmail)request.getResponseBody() ).set(
      requestLoginEmail.request_tracking_id,
      userId.toString(),
      oAuth2Tokens.getAuthorizationCode() );
  }
}
