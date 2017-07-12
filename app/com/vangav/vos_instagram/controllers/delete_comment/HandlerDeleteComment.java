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

package com.vangav.vos_instagram.controllers.delete_comment;

import java.util.UUID;

import com.datastax.driver.core.BatchStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.BatchStatement.Type;
import com.vangav.backend.cassandra.Cassandra;
import com.vangav.backend.exceptions.BadRequestException;
import com.vangav.backend.exceptions.VangavException.ExceptionClass;
import com.vangav.backend.play_framework.request.Request;
import com.vangav.backend.play_framework.request.RequestJsonBody;
import com.vangav.backend.play_framework.request.response.ResponseBody;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_app_data.PostComments;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_app_data.PostCommentsCount;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_app_data.PostCommentsTime;
import com.vangav.vos_instagram.common.Authenticator;
import com.vangav.vos_instagram.controllers.CommonPlayHandler;

/**
 * GENERATED using ControllersGeneratorMain.java
 */
/**
 * HandlerDeleteComment
 *   handles request-to-response processing
 *   also handles after response processing (if any)
 * */
public class HandlerDeleteComment extends CommonPlayHandler {

  private static final String kName = "DeleteComment";

  @Override
  protected String getName () {

    return kName;
  }

  @Override
  protected RequestJsonBody getRequestJson () {

    return new RequestDeleteComment();
  }

  @Override
  protected ResponseBody getResponseBody () {

    return new ResponseDeleteComment();
  }
  
  @Override
  protected Authenticator getRequestAuthenticator (
    final RequestJsonBody requestJsonBody) throws Exception {
    
    return
      new Authenticator(
        ((RequestDeleteComment)requestJsonBody).device_token,
        ((RequestDeleteComment)requestJsonBody).user_id,
        ((RequestDeleteComment)requestJsonBody).access_token);
  }

  @Override
  protected void processRequest (final Request request) throws Exception {

    // use the following request Object to process the request and set
    //   the response to be returned
    RequestDeleteComment requestDeleteComment =
      (RequestDeleteComment)request.getRequestJsonBody();
    
    // get post comment's time
    
    UUID postId = UUID.fromString(requestDeleteComment.post_id);
    
    ResultSet resultSet =
      PostCommentsTime.i().executeSyncSelect(
        postId,
        requestDeleteComment.getUserId() );
    
    // post/comment doesn't exist?
    if (resultSet.isExhausted() == true) {
      
      throw new BadRequestException(
        402,
        1,
        "User ["
          + requestDeleteComment.user_id
          + "] didn't comment on post ["
          + requestDeleteComment.post_id
          + "], then can't delete a comment that doesn't exist",
        ExceptionClass.INVALID);
    }
    
    // extract comment time
    long commentTime =
      resultSet.one().getLong(PostCommentsTime.kCommentTimeColumnName);
    
    // all queries must succeed
    BatchStatement batchStatement = new BatchStatement(Type.LOGGED);
    
    // delete from ig_app_data.post_comments
    batchStatement.add(
      PostComments.i().getBoundStatementDelete(
        postId,
        commentTime,
        requestDeleteComment.getUserId() ) );
    
    // delete from ig_app_data.post_comments_time
    batchStatement.add(
      PostCommentsTime.i().getBoundStatementDelete(
        postId,
        requestDeleteComment.getUserId() ) );
    
    // execute batch statement
    Cassandra.i().executeSync(batchStatement);
    
    // decrement ig_app_data.post_comments_count
    PostCommentsCount.i().executeSyncDecrement(postId);
    
    // set response
    ((ResponseDeleteComment)request.getResponseBody() ).set(
      requestDeleteComment.request_tracking_id);
  }
}
