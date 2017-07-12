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

package com.vangav.vos_instagram.controllers.get_post_info;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vangav.backend.play_framework.request.response.ResponseBodyJson;

/**
 * GENERATED using ControllersGeneratorMain.java
 */
/**
 * ResponseGetPostInfo represents the response's structure
 * */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseGetPostInfo extends ResponseBodyJson {

  @Override
  @JsonIgnore
  protected String getName () throws Exception {

    return "GetPostInfo";
  }

  @Override
  @JsonIgnore
  protected ResponseGetPostInfo getThis () throws Exception {

    return this;
  }

  @JsonProperty
  public String request_tracking_id;
  @JsonProperty
  public String posted_by_user_id;
  @JsonProperty
  public long post_time;
  @JsonProperty
  public String photo_id;
  @JsonProperty
  public long likes_count;
  @JsonProperty
  public long comments_count;
  @JsonProperty
  public String caption;
  @JsonProperty
  public String most_recent_like_user_id;
  @JsonProperty
  public String most_recent_comment_user_id;
  @JsonProperty
  public String most_recent_comment;
  @JsonProperty
  public String post_city;
  @JsonProperty
  public String post_country;

  @JsonIgnore
  public void set (
    String request_tracking_id,
    String posted_by_user_id,
    long post_time,
    String photo_id,
    long likes_count,
    long comments_count,
    String caption,
    String most_recent_like_user_id,
    String most_recent_comment_user_id,
    String most_recent_comment,
    String post_city,
    String post_country) {

    this.request_tracking_id = request_tracking_id;
    this.posted_by_user_id = posted_by_user_id;
    this.post_time = post_time;
    this.photo_id = photo_id;
    this.likes_count = likes_count;
    this.comments_count = comments_count;
    this.caption = caption;
    this.most_recent_like_user_id = most_recent_like_user_id;
    this.most_recent_comment_user_id = most_recent_comment_user_id;
    this.most_recent_comment = most_recent_comment;
    this.post_city = post_city;
    this.post_country = post_country;
  }
}
