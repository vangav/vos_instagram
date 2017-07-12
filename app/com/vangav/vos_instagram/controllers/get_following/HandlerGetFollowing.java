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

package com.vangav.vos_instagram.controllers.get_following;

import java.util.ArrayList;
import java.util.UUID;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.vangav.backend.exceptions.BadRequestException;
import com.vangav.backend.exceptions.VangavException.ExceptionClass;
import com.vangav.backend.play_framework.request.Request;
import com.vangav.backend.play_framework.request.RequestJsonBody;
import com.vangav.backend.play_framework.request.response.ResponseBody;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_app_data.Following;
import com.vangav.vos_instagram.common.Authenticator;
import com.vangav.vos_instagram.common.cassandra.CheckersInl;
import com.vangav.vos_instagram.controllers.CommonPlayHandler;
import com.vangav.vos_instagram.controllers.get_following.response_json.ResponseFollowing;

/**
 * GENERATED using ControllersGeneratorMain.java
 */
/**
 * HandlerGetFollowing
 *   handles request-to-response processing
 *   also handles after response processing (if any)
 * */
public class HandlerGetFollowing extends CommonPlayHandler {

  private static final String kName = "GetFollowing";

  @Override
  protected String getName () {

    return kName;
  }

  @Override
  protected RequestJsonBody getRequestJson () {

    return new RequestGetFollowing();
  }

  @Override
  protected ResponseBody getResponseBody () {

    return new ResponseGetFollowing();
  }
  
  @Override
  protected Authenticator getRequestAuthenticator (
    final RequestJsonBody requestJsonBody) throws Exception {
    
    return
      new Authenticator(
        ((RequestGetFollowing)requestJsonBody).device_token,
        ((RequestGetFollowing)requestJsonBody).user_id,
        ((RequestGetFollowing)requestJsonBody).access_token);
  }

  @Override
  protected void processRequest (final Request request) throws Exception {

    // use the following request Object to process the request and set
    //   the response to be returned
    RequestGetFollowing requestGetFollowing =
      (RequestGetFollowing)request.getRequestJsonBody();
    
    // set and check get_following_user_id
    UUID getFollowingUserId =
      UUID.fromString(requestGetFollowing.get_following_user_id);
    
    if (requestGetFollowing.get_following_user_id.compareTo(
          requestGetFollowing.user_id) != 0) {
    
      if (CheckersInl.userIdExists(getFollowingUserId) == false) {
        
        throw new BadRequestException(
          407,
          1,
          "Can't get following for non-existing user_id ["
            + requestGetFollowing.get_following_user_id
            + "], request issued by user_id ["
            + requestGetFollowing.user_id
            + "] from device_token ["
            + requestGetFollowing.device_token
            + "]",
          ExceptionClass.INVALID);
      }
    }
    
    // set count limit
    
    // CURRENTLY DISABLED - set to 30
//    int countLimit;
//    
//    if (requestGetFollowing.isValidParam(
//          RequestGetFollowing.kCountLimitName) == true) {
//      
//      countLimit =
//        NumbersInl.trim(
//          requestGetFollowing.count_limit,
//          Constants.kMinGettersCountLimit,
//          Constants.kMaxGettersCountLimit);
//    } else {
//      
//      countLimit = Constants.kDefaultGettersCountLimit;
//    }
    
    // select following from the database
    
    ResultSet resultSet;
    
    if (requestGetFollowing.isValidParam(
          RequestGetFollowing.kAtOrBeforeTimeName) == true) {
      
      resultSet =
        Following.i().executeSyncSelectAtOrBeforeTimeLimit(
          getFollowingUserId,
          requestGetFollowing.at_or_before_time);
    } else {
      
      resultSet =
        Following.i().executeSyncSelectRecentLimit(
          getFollowingUserId);
    }
    
    // extract response data
    
    Row row;
    ArrayList<ResponseFollowing> following =
      new ArrayList<ResponseFollowing>();
    
    while (resultSet.isExhausted() == false) {
      
      row = resultSet.one();
      
      following.add(
        new ResponseFollowing(
          row.getUUID(Following.kFollowingUserIdColumnName).toString(),
          row.getLong(Following.kFollowingTimeColumnName) ) );
    }
    
    // set response
    ((ResponseGetFollowing)request.getResponseBody() ).set(
      requestGetFollowing.request_tracking_id,
      following.toArray(new ResponseFollowing[0] ) );
  }
}
