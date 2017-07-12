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

package com.vangav.vos_instagram.controllers.get_user_info;

import java.util.ArrayList;
import java.util.UUID;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.vangav.backend.cassandra.Cassandra;
import com.vangav.backend.exceptions.BadRequestException;
import com.vangav.backend.exceptions.VangavException.ExceptionClass;
import com.vangav.backend.play_framework.request.Request;
import com.vangav.backend.play_framework.request.RequestJsonBody;
import com.vangav.backend.play_framework.request.response.ResponseBody;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_app_data.FollowerCount;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_app_data.FollowingCount;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_app_data.UserPostsCount;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_app_data.UsersInfo;
import com.vangav.vos_instagram.common.Authenticator;
import com.vangav.vos_instagram.common.Constants;
import com.vangav.vos_instagram.common.cassandra.CheckersInl;
import com.vangav.vos_instagram.controllers.CommonPlayHandler;

/**
 * GENERATED using ControllersGeneratorMain.java
 */
/**
 * HandlerGetUserInfo
 *   handles request-to-response processing
 *   also handles after response processing (if any)
 * */
public class HandlerGetUserInfo extends CommonPlayHandler {

  private static final String kName = "GetUserInfo";

  @Override
  protected String getName () {

    return kName;
  }

  @Override
  protected RequestJsonBody getRequestJson () {

    return new RequestGetUserInfo();
  }

  @Override
  protected ResponseBody getResponseBody () {

    return new ResponseGetUserInfo();
  }
  
  @Override
  protected Authenticator getRequestAuthenticator (
    final RequestJsonBody requestJsonBody) throws Exception {
    
    return
      new Authenticator(
        ((RequestGetUserInfo)requestJsonBody).device_token,
        ((RequestGetUserInfo)requestJsonBody).user_id,
        ((RequestGetUserInfo)requestJsonBody).access_token);
  }

  @Override
  protected void processRequest (final Request request) throws Exception {

    // use the following request Object to process the request and set
    //   the response to be returned
    RequestGetUserInfo requestGetUserInfo =
      (RequestGetUserInfo)request.getRequestJsonBody();
    
    // set/check get_info_user_id
    UUID getInfoUserId = UUID.fromString(requestGetUserInfo.get_info_user_id);
    
    if (requestGetUserInfo.get_info_user_id.compareTo(
          requestGetUserInfo.user_id) != 0) {
    
      if (CheckersInl.userIdExists(getInfoUserId) == false) {
        
        throw new BadRequestException(
          418,
          1,
          "User with user_id ["
            + requestGetUserInfo.get_info_user_id
            + "] doesn't exist, request issued by user_id ["
            + requestGetUserInfo.user_id
            + "] from device_token ["
            + requestGetUserInfo.device_token
            + "]",
          ExceptionClass.INVALID);
      }
    }
    
    // store bound statements
    ArrayList<BoundStatement> boundStatements =
      new ArrayList<BoundStatement>();
    
    // (0) select name from ig_app_data.users_info
    boundStatements.add(
      UsersInfo.i().getBoundStatementSelectName(getInfoUserId) );
    
    // (1) select profile_picture_id from ig_app_data.users_info
    boundStatements.add(
      UsersInfo.i().getBoundStatementSelectProfilePictureId(getInfoUserId) );
    
    // (2) select from ig_app_data.user_posts_count
    boundStatements.add(
      UserPostsCount.i().getBoundStatementSelect(getInfoUserId) );
    
    // (3) select from ig_app_data.follower_count
    boundStatements.add(
      FollowerCount.i().getBoundStatementSelect(getInfoUserId) );
    
    // (4) select from ig_app_data.following_count
    boundStatements.add(
      FollowingCount.i().getBoundStatementSelect(getInfoUserId) );
    
    // execute bound statements
    ArrayList<ResultSet> resultSets =
      Cassandra.i().executeSync(
        boundStatements.toArray(new BoundStatement[0] ) );
    
    // (0) get name from ig_app_data.users_info
    ResultSet resultSet = resultSets.get(0);
    Row row = resultSet.one();
    
    String name =
      row.getString(UsersInfo.kNameColumnName);
    
    // (1) get profile_picture_id from ig_app_data.users_info
    resultSet = resultSets.get(1);
    row = resultSet.one();
    
    String profilePictureId = Constants.kDefaultUuid;
    
    try {
      
      profilePictureId =
        row.getUUID(UsersInfo.kProfilePictureIdColumnName).toString();
    } catch (Exception e) {
      
      // non-fatal because not all users have a profile picture set
    }
    
    // (2) get from ig_app_data.user_posts_count
    resultSet = resultSets.get(2);
    row = resultSet.one();
    
    long postsCount = 0L;
    
    try {
      
      postsCount =
        row.getLong(UserPostsCount.kPostsCountColumnName);
    } catch (Exception e) {
      
      // non-fatal because not all users have a posts
    }
    
    // (3) get from ig_app_data.follower_count
    resultSet = resultSets.get(3);
    row = resultSet.one();
    
    long followersCount = 0L;
    
    try {
      
      followersCount =
        row.getLong(FollowerCount.kFollowerCountColumnName);
    } catch (Exception e) {
      
      // non-fatal because not all users have followers
    }
    
    // (4) get from ig_app_data.following_count
    resultSet = resultSets.get(4);
    row = resultSet.one();
    
    long followingCount = 0L;
    
    try {
    
      followingCount =
        row.getLong(FollowingCount.kFollowingCountColumnName);
    } catch (Exception e) {
      
      // non-fatal because not all users are following other users
    }
    
    // set response
    ((ResponseGetUserInfo)request.getResponseBody() ).set(
      requestGetUserInfo.request_tracking_id,
      name,
      profilePictureId,
      postsCount,
      followersCount,
      followingCount);
  }
}
