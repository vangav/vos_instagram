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

package com.vangav.vos_instagram.controllers.get_profile_picture;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vangav.backend.play_framework.request.response.ResponseBodyJson;
import com.vangav.vos_instagram.common.BigResponseInt;

/**
 * GENERATED using ControllersGeneratorMain.java
 */
/**
 * ResponseGetProfilePicture represents the response's structure
 * */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseGetProfilePicture
  extends ResponseBodyJson
  implements BigResponseInt {

  @Override
  @JsonIgnore
  protected String getName () throws Exception {

    return "GetProfilePicture";
  }

  @Override
  @JsonIgnore
  protected ResponseGetProfilePicture getThis () throws Exception {

    return this;
  }

  @JsonProperty
  public String request_tracking_id;
  @JsonProperty
  public String profile_picture;

  @JsonIgnore
  public void set (
    String request_tracking_id,
    String profile_picture) {

    this.request_tracking_id = request_tracking_id;
    this.profile_picture = profile_picture;
  }
  
  @Override
  @JsonIgnore
  public String getMinResponseAsString () throws Exception {
    
    ResponseGetProfilePicture minResponseGetProfilePicture =
      new ResponseGetProfilePicture();
    
    minResponseGetProfilePicture.request_tracking_id =
      this.request_tracking_id;
    minResponseGetProfilePicture.profile_picture = "";
    
    return minResponseGetProfilePicture.toString();
  }
}
