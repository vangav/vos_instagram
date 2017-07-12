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

package com.vangav.vos_instagram.controllers.unlike_post;

import java.util.UUID;

import com.datastax.driver.core.BatchStatement;
import com.datastax.driver.core.BatchStatement.Type;
import com.datastax.driver.core.ResultSet;
import com.vangav.backend.cassandra.Cassandra;
import com.vangav.backend.exceptions.BadRequestException;
import com.vangav.backend.exceptions.VangavException.ExceptionClass;
import com.vangav.backend.play_framework.request.Request;
import com.vangav.backend.play_framework.request.RequestJsonBody;
import com.vangav.backend.play_framework.request.response.ResponseBody;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_app_data.PostLikes;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_app_data.PostLikesCount;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_app_data.PostLikesTime;
import com.vangav.vos_instagram.common.Authenticator;
import com.vangav.vos_instagram.common.cassandra.CheckersInl;
import com.vangav.vos_instagram.controllers.CommonPlayHandler;

/**
 * GENERATED using ControllersGeneratorMain.java
 */
/**
 * HandlerUnlikePost
 *   handles request-to-response processing
 *   also handles after response processing (if any)
 * */
public class HandlerUnlikePost extends CommonPlayHandler {

  private static final String kName = "UnlikePost";

  @Override
  protected String getName () {

    return kName;
  }

  @Override
  protected RequestJsonBody getRequestJson () {

    return new RequestUnlikePost();
  }

  @Override
  protected ResponseBody getResponseBody () {

    return new ResponseUnlikePost();
  }
  
  @Override
  protected Authenticator getRequestAuthenticator (
    final RequestJsonBody requestJsonBody) throws Exception {
    
    return
      new Authenticator(
        ((RequestUnlikePost)requestJsonBody).device_token,
        ((RequestUnlikePost)requestJsonBody).user_id,
        ((RequestUnlikePost)requestJsonBody).access_token);
  }
  
  /**
   * checkRequest
   * validates the request before processing it
   * @param request
   * @throws Exception
   */
  private void checkRequest (final Request request) throws Exception {
    
    // get request's body
    RequestUnlikePost requestUnlikePost =
      (RequestUnlikePost)request.getRequestJsonBody();
    
    // set/check post_id
    UUID postId = UUID.fromString(requestUnlikePost.post_id);
    
    if (CheckersInl.postExists(postId) == false) {
      
      throw new BadRequestException(
        429,
        1,
        "Can't unlike a post that doesn't exist, post_id ["
          + requestUnlikePost.post_id
          + "]. Request issued by user_id ["
          + requestUnlikePost.user_id
          + "] from device_token ["
          + requestUnlikePost.device_token
          + "]",
        ExceptionClass.INVALID);
    }
    
    // check if user didn't like this post before
    if (CheckersInl.userLikedPost(
          postId,
          requestUnlikePost.getUserId() ) == false) {
      
      throw new BadRequestException(
        429,
        2,
        "Post with post_id ["
          + requestUnlikePost.post_id
          + "] hasn't been liked by user_id ["
          + requestUnlikePost.user_id
          + "], request issued from device_token ["
          + requestUnlikePost.device_token
          + "]",
        ExceptionClass.INVALID);
    }
  }

  @Override
  protected void processRequest (final Request request) throws Exception {
    
    // check request
    this.checkRequest(request);

    // use the following request Object to process the request and set
    //   the response to be returned
    RequestUnlikePost requestUnlikePost =
      (RequestUnlikePost)request.getRequestJsonBody();
    
    // set post_id
    UUID postId = UUID.fromString(requestUnlikePost.post_id);
    
    // get like_time
    ResultSet resultSet =
      PostLikesTime.i().executeSyncSelect(
        postId,
        requestUnlikePost.getUserId() );
    
    long likeTime =
      resultSet.one().getLong(PostLikesTime.kLikeTimeColumnName);
    
    // all queries must succeed
    BatchStatement batchStatement = new BatchStatement(Type.LOGGED);
    
    // delete from ig_app_data.post_likes
    batchStatement.add(
      PostLikes.i().getBoundStatementDelete(
        postId,
        likeTime,
        requestUnlikePost.getUserId() ) );
    
    // delete from ig_app_data.post_likes_time
    batchStatement.add(
      PostLikesTime.i().getBoundStatementDelete(
        postId,
        requestUnlikePost.getUserId() ) );
    
    // execute batch statement
    Cassandra.i().executeSync(batchStatement);
    
    // decrement post's likes count
    PostLikesCount.i().executeSyncDecrement(postId);
    
    // set response
    ((ResponseUnlikePost)request.getResponseBody() ).set(
      requestUnlikePost.request_tracking_id);
  }
}
