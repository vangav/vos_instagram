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

package com.vangav.vos_instagram.common;

import java.util.UUID;

/**
 * @author mustapha
 * fb.com/mustapha.abdallah
 */
/**
 * Authenticator holds OAuth2 authentication elements for all controllers
 *   except for signup/login-related controllers
 */
public class Authenticator {

  private String deviceToken;
  private UUID userId;
  private String accessToken;
  
  /**
   * Constructor - Authenticator
   * @param deviceToken
   * @param userId
   * @param accessToken
   * @return new Authenticator Object
   * @throws Exception
   */
  public Authenticator (
    String deviceToken,
    String userId,
    String accessToken) throws Exception {
    
    this.deviceToken = deviceToken;
    this.userId = UUID.fromString(userId);
    this.accessToken = accessToken;
  }
  
  /**
   * getDeviceToken
   * @return authenticator's device token
   * @throws Exception
   */
  public String getDeviceToken () throws Exception {
    
    return this.deviceToken;
  }
  
  /**
   * getUserId
   * @return authenticator's user id
   * @throws Exception
   */
  public UUID getUserId () throws Exception {
    
    return this.userId;
  }
  
  /**
   * getAccessToken
   * @return authenticator's access token
   * @throws Exception
   */
  public String getAccessToken () throws Exception {
    
    return this.accessToken;
  }
}
