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

package com.vangav.vos_instagram.controllers.get_post_likes;

import java.util.ArrayList;
import java.util.UUID;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.vangav.backend.exceptions.BadRequestException;
import com.vangav.backend.exceptions.VangavException.ExceptionClass;
import com.vangav.backend.play_framework.request.Request;
import com.vangav.backend.play_framework.request.RequestJsonBody;
import com.vangav.backend.play_framework.request.response.ResponseBody;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_app_data.PostLikes;
import com.vangav.vos_instagram.common.Authenticator;
import com.vangav.vos_instagram.common.cassandra.CheckersInl;
import com.vangav.vos_instagram.controllers.CommonPlayHandler;
import com.vangav.vos_instagram.controllers.get_post_likes.response_json.ResponsePostLike;

/**
 * GENERATED using ControllersGeneratorMain.java
 */
/**
 * HandlerGetPostLikes
 *   handles request-to-response processing
 *   also handles after response processing (if any)
 * */
public class HandlerGetPostLikes extends CommonPlayHandler {

  private static final String kName = "GetPostLikes";

  @Override
  protected String getName () {

    return kName;
  }

  @Override
  protected RequestJsonBody getRequestJson () {

    return new RequestGetPostLikes();
  }

  @Override
  protected ResponseBody getResponseBody () {

    return new ResponseGetPostLikes();
  }
  
  @Override
  protected Authenticator getRequestAuthenticator (
    final RequestJsonBody requestJsonBody) throws Exception {
    
    return
      new Authenticator(
        ((RequestGetPostLikes)requestJsonBody).device_token,
        ((RequestGetPostLikes)requestJsonBody).user_id,
        ((RequestGetPostLikes)requestJsonBody).access_token);
  }

  @Override
  protected void processRequest (final Request request) throws Exception {

    // use the following request Object to process the request and set
    //   the response to be returned
    RequestGetPostLikes requestGetPostLikes =
      (RequestGetPostLikes)request.getRequestJsonBody();
    
    // set/check post_id
    UUID postId = UUID.fromString(requestGetPostLikes.post_id);
    
    if (CheckersInl.postExists(postId) == false) {
      
      throw new BadRequestException(
        412,
        1,
        "Post with post_id ["
          + requestGetPostLikes.post_id
          + "] doesn't exist, request issued by user_id ["
          + requestGetPostLikes.user_id
          + "] from device_token ["
          + requestGetPostLikes.device_token
          + "]",
        ExceptionClass.INVALID);
    }
    
    // set count limit
    
    // CURRENTLY DISABLED - set to 30
//    int countLimit;
//    
//    if (requestGetPostLikes.isValidParam(
//          RequestGetPostLikes.kCountLimitName) == true) {
//      
//      countLimit =
//        NumbersInl.trim(
//          requestGetPostLikes.count_limit,
//          Constants.kMinGettersCountLimit,
//          Constants.kMaxGettersCountLimit);
//    } else {
//      
//      countLimit = Constants.kDefaultGettersCountLimit;
//    }
    
    // select post's likes from the database
    
    ResultSet resultSet;
    
    if (requestGetPostLikes.isValidParam(
          RequestGetPostLikes.kAtOrBeforeTimeName) == true) {
      
      resultSet =
        PostLikes.i().executeSyncSelectAtOrBeforeTimeLimit(
          postId,
          requestGetPostLikes.at_or_before_time);
    } else {
      
      resultSet =
        PostLikes.i().executeSyncSelectRecentLimit(
          postId);
    }
    
    // extract response data
    
    Row row;
    ArrayList<ResponsePostLike> postLikes = new ArrayList<ResponsePostLike>();
    
    while (resultSet.isExhausted() == false) {
      
      row = resultSet.one();
      
      postLikes.add(
        new ResponsePostLike(
          row.getUUID(PostLikes.kUserIdColumnName).toString(),
          row.getLong(PostLikes.kLikeTimeColumnName) ) );
    }
    
    // set response
    ((ResponseGetPostLikes)request.getResponseBody() ).set(
      requestGetPostLikes.request_tracking_id,
      postLikes.toArray(new ResponsePostLike[0] ) );
  }
}
