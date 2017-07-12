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

package com.vangav.vos_instagram.controllers.get_post_comments;

import java.util.ArrayList;
import java.util.UUID;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.vangav.backend.exceptions.BadRequestException;
import com.vangav.backend.exceptions.VangavException.ExceptionClass;
import com.vangav.backend.play_framework.request.Request;
import com.vangav.backend.play_framework.request.RequestJsonBody;
import com.vangav.backend.play_framework.request.response.ResponseBody;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_app_data.PostComments;
import com.vangav.vos_instagram.common.Authenticator;
import com.vangav.vos_instagram.common.cassandra.CheckersInl;
import com.vangav.vos_instagram.controllers.CommonPlayHandler;
import com.vangav.vos_instagram.controllers.get_post_comments.response_json.ResponsePostComment;

/**
 * GENERATED using ControllersGeneratorMain.java
 */
/**
 * HandlerGetPostComments
 *   handles request-to-response processing
 *   also handles after response processing (if any)
 * */
public class HandlerGetPostComments extends CommonPlayHandler {

  private static final String kName = "GetPostComments";

  @Override
  protected String getName () {

    return kName;
  }

  @Override
  protected RequestJsonBody getRequestJson () {

    return new RequestGetPostComments();
  }

  @Override
  protected ResponseBody getResponseBody () {

    return new ResponseGetPostComments();
  }
  
  @Override
  protected Authenticator getRequestAuthenticator (
    final RequestJsonBody requestJsonBody) throws Exception {
    
    return
      new Authenticator(
        ((RequestGetPostComments)requestJsonBody).device_token,
        ((RequestGetPostComments)requestJsonBody).user_id,
        ((RequestGetPostComments)requestJsonBody).access_token);
  }

  @Override
  protected void processRequest (final Request request) throws Exception {

    // use the following request Object to process the request and set
    //   the response to be returned
    RequestGetPostComments requestGetPostComments =
      (RequestGetPostComments)request.getRequestJsonBody();
    
    // set and check post_id
    UUID postId = UUID.fromString(requestGetPostComments.post_id);
    
    if (CheckersInl.postExists(postId) == false) {
      
      throw new BadRequestException(
        410,
        1,
        "Post with post_id ["
          + requestGetPostComments.post_id
          + "] doesn't exist, request issued by user_id ["
          + requestGetPostComments.user_id
          + "] from device_token ["
          + requestGetPostComments.device_token
          + "]",
        ExceptionClass.INVALID);
    }
    
    // set count limit
    
    // CURRENTLY DISABLED - set to 30
//    int countLimit;
//    
//    if (requestGetPostComments.isValidParam(
//          RequestGetPostComments.kCountLimitName) == true) {
//      
//      countLimit =
//        NumbersInl.trim(
//          requestGetPostComments.count_limit,
//          Constants.kMinGettersCountLimit,
//          Constants.kMaxGettersCountLimit);
//    } else {
//      
//      countLimit = Constants.kDefaultGettersCountLimit;
//    }
    
    // select post's comments from database
    
    ResultSet resultSet;
    
    if (requestGetPostComments.isValidParam(
          RequestGetPostComments.kAtOrBeforeTimeName) == true) {
      
      resultSet =
        PostComments.i().executeSyncSelectAtOrBeforeTimeLimit(
          postId,
          requestGetPostComments.at_or_before_time);
    } else {
      
      resultSet =
        PostComments.i().executeSyncSelectRecentLimit(
          postId);
    }
    
    // extract response data
    
    Row row;
    ArrayList<ResponsePostComment> postComments =
      new ArrayList<ResponsePostComment>();
    
    while (resultSet.isExhausted() == false) {
      
      row = resultSet.one();
      
      postComments.add(
        new ResponsePostComment(
          row.getUUID(PostComments.kUserIdColumnName).toString(),
          row.getString(PostComments.kCommentColumnName),
          row.getLong(PostComments.kCommentTimeColumnName) ) );
    }
    
    // set response
    ((ResponseGetPostComments)request.getResponseBody() ).set(
      requestGetPostComments.request_tracking_id,
      postComments.toArray(new ResponsePostComment[0] ) );
  }
}
