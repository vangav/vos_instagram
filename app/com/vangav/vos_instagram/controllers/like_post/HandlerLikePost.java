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

package com.vangav.vos_instagram.controllers.like_post;

import java.util.Calendar;
import java.util.Map;
import java.util.UUID;

import com.datastax.driver.core.BatchStatement;
import com.datastax.driver.core.BatchStatement.Type;
import com.google.android.gcm.server.Message;
import com.vangav.backend.cassandra.Cassandra;
import com.vangav.backend.cassandra.formatting.CalendarFormatterInl;
import com.vangav.backend.exceptions.BadRequestException;
import com.vangav.backend.exceptions.CodeException;
import com.vangav.backend.exceptions.VangavException.ExceptionClass;
import com.vangav.backend.geo.reverse_geo_coding.ReverseGeoCode;
import com.vangav.backend.play_framework.request.Request;
import com.vangav.backend.play_framework.request.RequestJsonBody;
import com.vangav.backend.play_framework.request.RequestState;
import com.vangav.backend.play_framework.request.response.ResponseBody;
import com.vangav.backend.push_notifications.android.AndroidNotification;
import com.vangav.backend.push_notifications.android.dispatch_message.AndroidNotificationDispatchable;
import com.vangav.backend.push_notifications.apple.AppleNotification.AppleNotificationBuilder;
import com.vangav.backend.push_notifications.apple.dispatch_message.AppleNotificationDispatchable;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_analytics.AnnualRegionalCounters;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_analytics.DailyRegionalCounters;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_app_data.CountPerWeek;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_app_data.CountTotal;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_app_data.PostLikes;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_app_data.PostLikesCount;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_app_data.PostLikesTime;
import com.vangav.vos_instagram.common.Authenticator;
import com.vangav.vos_instagram.common.Constants;
import com.vangav.vos_instagram.common.cassandra.CheckersInl;
import com.vangav.vos_instagram.common.cassandra.GettersInl;
import com.vangav.vos_instagram.controllers.CommonPlayHandler;

/**
 * GENERATED using ControllersGeneratorMain.java
 */
/**
 * HandlerLikePost
 *   handles request-to-response processing
 *   also handles after response processing (if any)
 * */
public class HandlerLikePost extends CommonPlayHandler {

  private static final String kName = "LikePost";

  @Override
  protected String getName () {

    return kName;
  }

  @Override
  protected RequestJsonBody getRequestJson () {

    return new RequestLikePost();
  }

  @Override
  protected ResponseBody getResponseBody () {

    return new ResponseLikePost();
  }
  
  @Override
  protected Authenticator getRequestAuthenticator (
    final RequestJsonBody requestJsonBody) throws Exception {
    
    return
      new Authenticator(
        ((RequestLikePost)requestJsonBody).device_token,
        ((RequestLikePost)requestJsonBody).user_id,
        ((RequestLikePost)requestJsonBody).access_token);
  }
  
  /**
   * checkRequest
   * validates the request before processing it
   * @param request
   * @throws Exception
   */
  private void checkRequest (final Request request) throws Exception {
    
    // get request's body
    RequestLikePost requestLikePost =
      (RequestLikePost)request.getRequestJsonBody();
    
    // set/check post_id
    UUID postId = UUID.fromString(requestLikePost.post_id);
    
    if (CheckersInl.postExists(postId) == false) {
      
      throw new BadRequestException(
        420,
        1,
        "Can't like a post that doesn't exist, post_id ["
          + requestLikePost.post_id
          + "]. Request issued by user_id ["
          + requestLikePost.user_id
          + "] from device_token ["
          + requestLikePost.device_token
          + "]",
        ExceptionClass.INVALID);
    }
    
    // check if user already liked this post
    if (CheckersInl.userLikedPost(
          postId,
          requestLikePost.getUserId() ) == true) {
      
      throw new BadRequestException(
        420,
        2,
        "Post with post_id ["
          + requestLikePost.post_id
          + "] has already been liked by used_id ["
          + requestLikePost.user_id
          + "], request issued from device_token ["
          + requestLikePost.device_token
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
    RequestLikePost requestLikePost =
      (RequestLikePost)request.getRequestJsonBody();
    
    // set post_id
    UUID postId = UUID.fromString(requestLikePost.post_id);
    
    // all queries must succeed
    BatchStatement batchStatement = new BatchStatement(Type.LOGGED);
    
    // insert into ig_app_data.post_likes
    batchStatement.add(
      PostLikes.i().getBoundStatementInsert(
        postId,
        request.getStartTime(),
        requestLikePost.getUserId() ) );
    
    // insert into ig_app_data.post_likes_time
    batchStatement.add(
      PostLikesTime.i().getBoundStatementInsert(
        postId,
        requestLikePost.getUserId(),
        request.getStartTime() ) );
    
    // execute batch statement
    Cassandra.i().executeSync(batchStatement);
    
    // increment post's likes count
    PostLikesCount.i().executeSyncIncrement(postId);
    
    // set response
    ((ResponseLikePost)request.getResponseBody() ).set(
      requestLikePost.request_tracking_id,
      request.getStartTime() );
  }

  @Override
  protected void afterProcessing (
    final Request request) throws Exception {
    
    // only process this method if user's request was a success
    if (request.getState() != RequestState.OK) {
      
      return;
    }
    
    // get request's body
    RequestLikePost requestLikePost =
      (RequestLikePost)request.getRequestJsonBody();
    
    // get post_id
    UUID postId = UUID.fromString(requestLikePost.post_id);
    
    // get post's owner user_id
    UUID postOwnerUserId = GettersInl.getPostOwnerId(postId);
    
    // dispatch counters updates
    
    request.getDispatcher().addDispatchMessage(
      CountTotal.i().getQueryDispatchableIncrementLikesReceivedCount(
        postOwnerUserId) );
    
    request.getDispatcher().addDispatchMessage(
      CountPerWeek.i().getQueryDispatchableIncrementLikesReceivedCount(
        postOwnerUserId.toString()
        + Constants.kCassandraIdConcat
        + CalendarFormatterInl.concatCalendarFields(
          request.getStartCalendar(),
          Calendar.YEAR,
          Calendar.WEEK_OF_YEAR) ) );
  }

  @Override
  protected void dispatchPushNotifications (
    final Request request) throws Exception {
    
    // only process this method if user's request was a success
    if (request.getState() != RequestState.OK) {
      
      return;
    }
    
    // get request's body
    RequestLikePost requestLikePost =
      (RequestLikePost)request.getRequestJsonBody();
    
    // get post_id
    UUID postId = UUID.fromString(requestLikePost.post_id);
    
    // get post's owner user_id
    UUID postOwnerUserId = GettersInl.getPostOwnerId(postId);
    
    // liked own post? don't notify
    if (postOwnerUserId.toString().compareTo(requestLikePost.user_id) == 0) {
      
      return;
    }
    
    // get post's owner's device tokens
    Map<String, String> deviceTokens =
      GettersInl.getUserDeviceTokens(postOwnerUserId);
    
    // get commenter's name
    String likerName = GettersInl.getUserName(requestLikePost.getUserId() );
    
    // dispatch notifications for each device token
    for (String deviceToken : deviceTokens.keySet() ) {
      
      if (deviceTokens.get(deviceToken).compareTo(
            Constants.kAppleDeviceTypeName) == 0) {
        
        request.getDispatcher().addDispatchMessage(
          new AppleNotificationDispatchable(
            new AppleNotificationBuilder(deviceToken)
              .alertBody(likerName + " liked your photo")
              .badgeNumber(1)
              .build() ) );
        
      } else if (deviceTokens.get(deviceToken).compareTo(
          Constants.kAndroidDeviceTypeName) == 0) {
        
        request.getDispatcher().addDispatchMessage(
          new AndroidNotificationDispatchable(
            new AndroidNotification(
              new Message.Builder()
                .collapseKey(likerName + " liked your photo")
                .build(),
              deviceToken) ) );
      } else {
        
        // non-fatal exception
        request.addVangavException(
          new CodeException(
            420,
            3,
            "Invalid device type ["
              + deviceTokens.get(deviceToken)
              + "] for user_id ["
              + postOwnerUserId.toString()
              + "] and device_token ["
              + deviceToken
              + "]",
            ExceptionClass.INVALID));
      }
    }
  }

  @Override
  protected void dispatchAnalysis (
    final Request request) throws Exception {
    
    // only process this method if user's request was a success
    if (request.getState() != RequestState.OK) {
      
      return;
    }
    
    // get request's body
    RequestLikePost requestLikePost =
      (RequestLikePost)request.getRequestJsonBody();
    
    // get post_id
    UUID postId = UUID.fromString(requestLikePost.post_id);
    
    // get post's owner user_id
    UUID postOwnerUserId = GettersInl.getPostOwnerId(postId);
    
    // get liker's last location
    
    String likerLocation;
    
    ReverseGeoCode reverseGeoCode =
      GettersInl.getUserLastReverseGeoCode(requestLikePost.getUserId() );
    
    if (reverseGeoCode != null) {
      
      likerLocation = reverseGeoCode.getContinentCode();
    } else {
      
      likerLocation = Constants.kDefaultRegion;
    }
    
    // get post owner's last location
    
    String postOwnerLocation;
    
    if (postOwnerUserId.toString().compareTo(requestLikePost.user_id) == 0) {
      
      postOwnerLocation = likerLocation;
    } else {
      
      reverseGeoCode =
        GettersInl.getUserLastReverseGeoCode(postOwnerUserId);
      
      if (reverseGeoCode != null) {
        
        postOwnerLocation = reverseGeoCode.getContinentCode();
      } else {
        
        postOwnerLocation = Constants.kDefaultRegion;
      }
    }
    
    // dispatch analytics
    
    // ig_analytics.annual_regional_counters.increment_sent_likes
    request.getDispatcher().addDispatchMessage(
      AnnualRegionalCounters.i().getQueryDispatchableIncrementSentLikes(
        CalendarFormatterInl.concatCalendarFields(
          request.getStartCalendar(),
          Calendar.YEAR)
          + Constants.kCassandraIdConcat
          + likerLocation) );
    
    // ig_analytics.annual_regional_counters.increment_sent_likes
    request.getDispatcher().addDispatchMessage(
      AnnualRegionalCounters.i().getQueryDispatchableIncrementSentLikes(
        CalendarFormatterInl.concatCalendarFields(
          request.getStartCalendar(),
          Calendar.YEAR)
          + Constants.kCassandraIdConcat
          + Constants.kWorldRegion) );
    
    // ig_analytics.annual_regional_counters.increment_received_likes
    request.getDispatcher().addDispatchMessage(
      AnnualRegionalCounters.i().getQueryDispatchableIncrementReceivedLikes(
        CalendarFormatterInl.concatCalendarFields(
          request.getStartCalendar(),
          Calendar.YEAR)
          + Constants.kCassandraIdConcat
          + postOwnerLocation) );
    
    // ig_analytics.annual_regional_counters.increment_received_likes
    request.getDispatcher().addDispatchMessage(
      AnnualRegionalCounters.i().getQueryDispatchableIncrementReceivedLikes(
        CalendarFormatterInl.concatCalendarFields(
          request.getStartCalendar(),
          Calendar.YEAR)
          + Constants.kCassandraIdConcat
          + Constants.kWorldRegion) );
    
    // ig_analytics.daily_regional_counters.increment_sent_likes
    request.getDispatcher().addDispatchMessage(
      DailyRegionalCounters.i().getQueryDispatchableIncrementSentLikes(
        CalendarFormatterInl.concatCalendarFields(
          request.getStartCalendar(),
          Calendar.YEAR,
          Calendar.MONTH,
          Calendar.DAY_OF_MONTH)
          + Constants.kCassandraIdConcat
          + likerLocation) );
    
    // ig_analytics.daily_regional_counters.increment_sent_likes
    request.getDispatcher().addDispatchMessage(
      DailyRegionalCounters.i().getQueryDispatchableIncrementSentLikes(
        CalendarFormatterInl.concatCalendarFields(
          request.getStartCalendar(),
          Calendar.YEAR,
          Calendar.MONTH,
          Calendar.DAY_OF_MONTH)
          + Constants.kCassandraIdConcat
          + Constants.kWorldRegion) );
    
    // ig_analytics.daily_regional_counters.increment_received_likes
    request.getDispatcher().addDispatchMessage(
      DailyRegionalCounters.i().getQueryDispatchableIncrementReceivedLikes(
        CalendarFormatterInl.concatCalendarFields(
          request.getStartCalendar(),
          Calendar.YEAR,
          Calendar.MONTH,
          Calendar.DAY_OF_MONTH)
          + Constants.kCassandraIdConcat
          + postOwnerLocation) );
    
    // ig_analytics.daily_regional_counters.increment_received_likes
    request.getDispatcher().addDispatchMessage(
      DailyRegionalCounters.i().getQueryDispatchableIncrementReceivedLikes(
        CalendarFormatterInl.concatCalendarFields(
          request.getStartCalendar(),
          Calendar.YEAR,
          Calendar.MONTH,
          Calendar.DAY_OF_MONTH)
          + Constants.kCassandraIdConcat
          + Constants.kWorldRegion) );
  }
}
