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

import java.util.ArrayList;
import java.util.UUID;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.vangav.backend.cassandra.Cassandra;
import com.vangav.backend.exceptions.BadRequestException;
import com.vangav.backend.exceptions.VangavException.ExceptionClass;
import com.vangav.backend.geo.reverse_geo_coding.ReverseGeoCode;
import com.vangav.backend.geo.reverse_geo_coding.ReverseGeoCoding;
import com.vangav.backend.play_framework.request.Request;
import com.vangav.backend.play_framework.request.RequestJsonBody;
import com.vangav.backend.play_framework.request.response.ResponseBody;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_app_data.PostComments;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_app_data.PostCommentsCount;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_app_data.PostLikes;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_app_data.PostLikesCount;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_app_data.Posts;
import com.vangav.vos_instagram.common.Authenticator;
import com.vangav.vos_instagram.common.Constants;
import com.vangav.vos_instagram.common.cassandra.CheckersInl;
import com.vangav.vos_instagram.controllers.CommonPlayHandler;

/**
 * GENERATED using ControllersGeneratorMain.java
 */
/**
 * HandlerGetPostInfo
 *   handles request-to-response processing
 *   also handles after response processing (if any)
 * */
public class HandlerGetPostInfo extends CommonPlayHandler {

  private static final String kName = "GetPostInfo";

  @Override
  protected String getName () {

    return kName;
  }

  @Override
  protected RequestJsonBody getRequestJson () {

    return new RequestGetPostInfo();
  }

  @Override
  protected ResponseBody getResponseBody () {

    return new ResponseGetPostInfo();
  }
  
  @Override
  protected Authenticator getRequestAuthenticator (
    final RequestJsonBody requestJsonBody) throws Exception {
    
    return
      new Authenticator(
        ((RequestGetPostInfo)requestJsonBody).device_token,
        ((RequestGetPostInfo)requestJsonBody).user_id,
        ((RequestGetPostInfo)requestJsonBody).access_token);
  }

  @Override
  protected void processRequest (final Request request) throws Exception {

    // use the following request Object to process the request and set
    //   the response to be returned
    RequestGetPostInfo requestGetPostInfo =
      (RequestGetPostInfo)request.getRequestJsonBody();
    
    // set/check post_id
    UUID postId = UUID.fromString(requestGetPostInfo.post_id);
    
    if (CheckersInl.postExists(postId) == false) {
      
      throw new BadRequestException(
        411,
        1,
        "Post with post_id ["
          + requestGetPostInfo.post_id
          + "] doesn't exist, request issued by user_id ["
          + requestGetPostInfo.user_id
          + "] from device_token ["
          + requestGetPostInfo.device_token
          + "]",
        ExceptionClass.INVALID);
    }
    
    // store bound statements
    ArrayList<BoundStatement> boundStatements =
      new ArrayList<BoundStatement>();
    
    // (0) select from ig_app_data.posts
    boundStatements.add(
      Posts.i().getBoundStatementSelect(
        postId) );
    
    // (1) select from ig_app_data.post_likes_count
    boundStatements.add(
      PostLikesCount.i().getBoundStatementSelect(
        postId) );
    
    // (2) select from ig_app_data.post_comments_count
    boundStatements.add(
      PostCommentsCount.i().getBoundStatementSelect(
        postId) );
    
    // (3) select from ig_app_data.post_likes
    boundStatements.add(
      PostLikes.i().getBoundStatementSelectRecentLimit(
        postId) );
    
    // (4) select from ig_app_data.post_comments
    boundStatements.add(
      PostComments.i().getBoundStatementSelectRecentLimit(
        postId) );
    
    // execute bound statements
    ArrayList<ResultSet> resultSets =
      Cassandra.i().executeSync(
        boundStatements.toArray(new BoundStatement[0] ) );
    
    // (0) get from ig_app_data.posts
    ResultSet resultSet = resultSets.get(0);
    Row row = resultSet.one();
    
    String postedByUserId =
      row.getUUID(Posts.kUserIdColumnName).toString();
    long postTime =
      row.getLong(Posts.kPostTimeColumnName);
    String photoId =
      row.getUUID(Posts.kPhotoIdColumnName).toString();
    String caption =
      row.getString(Posts.kCaptionColumnName);
    double latitude =
      row.getDouble(Posts.kLatitudeColumnName);
    double longitude =
      row.getDouble(Posts.kLongitudeColumnName);

    ReverseGeoCode reverseGeoCode =
      ReverseGeoCoding.i().getReverseGeoCode(latitude, longitude);
    
    String postCity = reverseGeoCode.getMajorCity();
    String postCountry = reverseGeoCode.getCountry();
    
    // (1) get from ig_app_data.post_likes_count
    resultSet = resultSets.get(1);
    row = resultSet.one();
    
    long likesCount = 0L;
    
    try {
    
      likesCount =
        row.getLong(PostLikesCount.kLikesCountColumnName);
    } catch (Exception e) {
      
      // non-fatal because not all posts have likes
    }
    
    // (2) get from ig_app_data.post_comments_count
    resultSet = resultSets.get(2);
    row = resultSet.one();
    
    long commentsCount = 0L;
    
    try {
    
      commentsCount =
        row.getLong(PostCommentsCount.kCommentsCountColumnName);
    } catch (Exception e) {
      
      // non-fatal because not all posts have comments
    }
    
    // (3) get from ig_app_data.post_likes
    resultSet = resultSets.get(3);
    row = resultSet.one();
    
    String mostRecentLikeUserId = Constants.kDefaultUuid;
    
    try {
    
      mostRecentLikeUserId =
        row.getUUID(PostLikes.kUserIdColumnName).toString();
    } catch (Exception e) {
      
      // non-fatal because not all posts have likes
    }
    
    // (4) get from ig_app_data.post_comments
    resultSet = resultSets.get(4);
    row = resultSet.one();
    
    String mostRecentCommentUserId = Constants.kDefaultUuid;
    String mostRecentComment = "";
    
    try {
    
      mostRecentCommentUserId =
        row.getUUID(PostComments.kUserIdColumnName).toString();
      mostRecentComment =
        row.getString(PostComments.kCommentColumnName);
    } catch (Exception e) {
      
      // non-fatal because not all posts have comments
    }
    
    // set response
    ((ResponseGetPostInfo)request.getResponseBody() ).set(
      requestGetPostInfo.request_tracking_id,
      postedByUserId,
      postTime,
      photoId,
      likesCount,
      commentsCount,
      caption,
      mostRecentLikeUserId,
      mostRecentCommentUserId,
      mostRecentComment,
      postCity,
      postCountry);
  }
}
