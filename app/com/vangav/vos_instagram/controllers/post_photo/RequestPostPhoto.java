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

package com.vangav.vos_instagram.controllers.post_photo;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vangav.backend.play_framework.param.ParamOptionality;
import com.vangav.backend.play_framework.param.ParamType;
import com.vangav.backend.play_framework.request.RequestJsonBodyPost;
import com.vangav.vos_instagram.common.BigRequestInt;

/**
 * GENERATED using ControllersGeneratorMain.java
 */
/**
 * RequestPostPhoto represents the request's structure
 * */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestPostPhoto
  extends RequestJsonBodyPost
  implements BigRequestInt {

  @Override
  @JsonIgnore
  protected String getName () throws Exception {

    return "PostPhoto";
  }

  @Override
  @JsonIgnore
  protected RequestPostPhoto getThis () throws Exception {

    return this;
  }

  @Override
  @JsonIgnore
  public UUID getUserId () {

    try {

      return UUID.fromString(this.user_id);
    } catch (Exception e) {

      return super.getUserId();
    }
  }

  @JsonIgnore
  public static final String kRequestTrackingIdName = "request_tracking_id";
  @JsonProperty
  public String request_tracking_id;

  @JsonIgnore
  public static final String kDeviceTokenName = "device_token";
  @JsonProperty
  public String device_token;

  @JsonIgnore
  public static final String kUserIdName = "user_id";
  @JsonProperty
  public String user_id;

  @JsonIgnore
  public static final String kAccessTokenName = "access_token";
  @JsonProperty
  public String access_token;

  @JsonIgnore
  public static final String kPhotoName = "photo";
  @JsonProperty
  public String photo;

  @JsonIgnore
  public static final String kPhotoThumbnailName = "photo_thumbnail";
  @JsonProperty
  public String photo_thumbnail;

  @JsonIgnore
  public static final String kCaptionName = "caption";
  @JsonProperty
  public String caption;

  @JsonIgnore
  public static final String kLatitudeName = "latitude";
  @JsonProperty
  public double latitude;

  @JsonIgnore
  public static final String kLongitudeName = "longitude";
  @JsonProperty
  public double longitude;

  @Override
  @JsonIgnore
  public void validate () throws Exception {

    this.validate(
      kRequestTrackingIdName,
      this.request_tracking_id,
      ParamType.UUID,
      ParamOptionality.OPTIONAL);

    this.validate(
      kDeviceTokenName,
      this.device_token,
      ParamType.DEVICE_TOKEN,
      ParamOptionality.MANDATORY);

    this.validate(
      kUserIdName,
      this.user_id,
      ParamType.UUID,
      ParamOptionality.MANDATORY);

    this.validate(
      kAccessTokenName,
      this.access_token,
      ParamType.ACCESS_TOKEN,
      ParamOptionality.MANDATORY);

    this.validate(
      kPhotoName,
      this.photo,
      ParamType.PHOTO,
      ParamOptionality.MANDATORY);

    this.validate(
      kPhotoThumbnailName,
      this.photo_thumbnail,
      ParamType.PHOTO,
      ParamOptionality.MANDATORY);

    this.validate(
      kCaptionName,
      this.caption,
      ParamType.CAPTION,
      ParamOptionality.OPTIONAL);

    this.validate(
      kLatitudeName,
      this.latitude,
      ParamType.LATITUDE,
      ParamOptionality.OPTIONAL);

    this.validate(
      kLongitudeName,
      this.longitude,
      ParamType.LONGITUDE,
      ParamOptionality.OPTIONAL);
  }
  
  @Override
  @JsonIgnore
  public String getMinRequestAsString () throws Exception {
    
    RequestPostPhoto minRequestPostPhoto = new RequestPostPhoto();
    
    minRequestPostPhoto.request_tracking_id = this.request_tracking_id;
    minRequestPostPhoto.device_token = this.device_token;
    minRequestPostPhoto.user_id = this.user_id;
    minRequestPostPhoto.access_token = this.access_token;
    minRequestPostPhoto.photo = "";
    minRequestPostPhoto.photo_thumbnail = "";
    minRequestPostPhoto.caption = this.caption;
    minRequestPostPhoto.latitude = this.latitude;
    minRequestPostPhoto.longitude = this.longitude;
    
    return minRequestPostPhoto.toString();
  }
}
