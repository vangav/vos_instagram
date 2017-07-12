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

package com.vangav.vos_instagram.controllers.follow;

import java.util.Calendar;
import java.util.Map;
import java.util.UUID;

import com.datastax.driver.core.BatchStatement;
import com.datastax.driver.core.BatchStatement.Type;
import com.google.android.gcm.server.Message;
import com.vangav.backend.cassandra.Cassandra;
import com.vangav.backend.cassandra.formatting.CalendarFormatterInl;
import com.vangav.backend.exceptions.BadRequestException;
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
import com.vangav.vos_instagram.cassandra_keyspaces.ig_app_data.Follower;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_app_data.FollowerCount;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_app_data.FollowerTime;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_app_data.Following;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_app_data.FollowingCount;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_app_data.FollowingTime;
import com.vangav.vos_instagram.common.Authenticator;
import com.vangav.vos_instagram.common.Constants;
import com.vangav.vos_instagram.common.cassandra.CheckersInl;
import com.vangav.vos_instagram.common.cassandra.GettersInl;
import com.vangav.vos_instagram.controllers.CommonPlayHandler;

/**
 * GENERATED using ControllersGeneratorMain.java
 */
/**
 * HandlerFollow
 *   handles request-to-response processing
 *   also handles after response processing (if any)
 * */
public class HandlerFollow extends CommonPlayHandler {

  private static final String kName = "Follow";

  @Override
  protected String getName () {

    return kName;
  }

  @Override
  protected RequestJsonBody getRequestJson () {

    return new RequestFollow();
  }

  @Override
  protected ResponseBody getResponseBody () {

    return new ResponseFollow();
  }
  
  @Override
  protected Authenticator getRequestAuthenticator (
    final RequestJsonBody requestJsonBody) throws Exception {
    
    return
      new Authenticator(
        ((RequestFollow)requestJsonBody).device_token,
        ((RequestFollow)requestJsonBody).user_id,
        ((RequestFollow)requestJsonBody).access_token);
  }
  
  /**
   * checkRequest
   * validates the request before processing it
   * @param request
   * @throws Exception
   */
  private void checkRequest (final Request request) throws Exception {
    
    // get request's body
    RequestFollow requestFollow =
      (RequestFollow)request.getRequestJsonBody();
    
    // can't follow self
    if (requestFollow.user_id.compareTo(requestFollow.follow_user_id) == 0) {
      
      throw new BadRequestException(
        403,
        1,
        "User ["
          + requestFollow.user_id
          + "] can't follow her/him-self",
        ExceptionClass.INVALID);
    }
    
    // make follow_user_id UUID
    UUID followUserId = UUID.fromString(requestFollow.follow_user_id);
    
    // check if follow_user_id exists
    if (CheckersInl.userIdExists(followUserId) == false) {
      
      throw new BadRequestException(
        403,
        2,
        "User ["
          + requestFollow.user_id
          + "] can't follow an non-existing user with id ["
          + requestFollow.follow_user_id
          + "]",
        ExceptionClass.INVALID);
    }
    
    // check if user is already following
    if (CheckersInl.isFollowing(
          requestFollow.getUserId(),
          followUserId) == true) {
      
      throw new BadRequestException(
        403,
        3,
        "User ["
          + requestFollow.user_id
          + "] can't follow a user she/he is already following with id ["
          + requestFollow.follow_user_id
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
    RequestFollow requestFollow =
      (RequestFollow)request.getRequestJsonBody();
    
    // make follow_user_id UUID
    UUID followUserId = UUID.fromString(requestFollow.follow_user_id);
    
    // all queries must succeed
    BatchStatement batchStatement = new BatchStatement(Type.LOGGED);

    // insert into ig_app_data.following
    batchStatement.add(
      Following.i().getBoundStatementInsert(
        requestFollow.getUserId(),
        request.getStartTime(),
        followUserId) );
    
    // insert into ig_app_data.following_time
    batchStatement.add(
      FollowingTime.i().getBoundStatementInsert(
        requestFollow.getUserId(),
        followUserId,
        request.getStartTime() ) );
    
    // insert into ig_app_data.follower
    batchStatement.add(
      Follower.i().getBoundStatementInsert(
        followUserId,
        request.getStartTime(),
        requestFollow.getUserId() ) );
    
    // insert into ig_app_data.follower_time
    batchStatement.add(
      FollowerTime.i().getBoundStatementInsert(
        followUserId,
        requestFollow.getUserId(),
        request.getStartTime() ) );
    
    // execute batch statement
    Cassandra.i().executeSync(batchStatement);
    
    // set response
    ((ResponseFollow)request.getResponseBody() ).set(
      requestFollow.request_tracking_id);
  }

  @Override
  protected void afterProcessing (
    final Request request) throws Exception {
    
    // only process this method if user's request was a success
    if (request.getState() != RequestState.OK) {
      
      return;
    }
    
    // get request's body
    RequestFollow requestFollow =
      (RequestFollow)request.getRequestJsonBody();
    
    UUID followUserId = UUID.fromString(requestFollow.follow_user_id);
    
    // increment ig_app_data.following_count
    request.getDispatcher().addDispatchMessage(
      FollowingCount.i().getQueryDispatchableIncrement(
        requestFollow.getUserId() ) );
    
    // increment ig_app_data.follower_count
    request.getDispatcher().addDispatchMessage(
      FollowerCount.i().getQueryDispatchableIncrement(
        followUserId) );
    
    // increment ig_app_data.count_per_week
    request.getDispatcher().addDispatchMessage(
      CountPerWeek.i().getQueryDispatchableIncrementFollowerCount(
        requestFollow.follow_user_id
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
    RequestFollow requestFollow =
      (RequestFollow)request.getRequestJsonBody();
    
    // get follow user's id
    UUID followUserId = UUID.fromString(requestFollow.follow_user_id);
    
    // get followed user's device tokens
    Map<String, String> deviceTokens =
      GettersInl.getUserDeviceTokens(followUserId);
    
    // get follower's name
    String followerName = GettersInl.getUserName(requestFollow.getUserId() );
    
    // dispatch notifications for each device token
    for (String deviceToken : deviceTokens.keySet() ) {
      
      if (deviceTokens.get(deviceToken).compareTo(
            Constants.kAppleDeviceTypeName) == 0) {
        
        request.getDispatcher().addDispatchMessage(
          new AppleNotificationDispatchable(
            new AppleNotificationBuilder(deviceToken)
              .alertBody(followerName + " started following you")
              .badgeNumber(1)
              .build() ) );
      } else if (deviceTokens.get(deviceToken).compareTo(
                   Constants.kAndroidDeviceTypeName) == 0) {
        
        request.getDispatcher().addDispatchMessage(
          new AndroidNotificationDispatchable(
            new AndroidNotification(
              new Message.Builder()
                .collapseKey(followerName + " started following you")
                .build(),
              deviceToken) ) );
      } else {
        
        throw new BadRequestException(
          403,
          4,
          "Invalid device type ["
            + deviceTokens.get(deviceToken)
            + "] for user_id ["
            + requestFollow.follow_user_id
            + "] and device_token ["
            + deviceToken
            + "]",
          ExceptionClass.INVALID);
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
    RequestFollow requestFollow =
      (RequestFollow)request.getRequestJsonBody();
    
    // get follow user's id
    UUID followUserId = UUID.fromString(requestFollow.follow_user_id);
    
    // get follower user's location

    String followerLocation;
    
    ReverseGeoCode reverseGeoCode =
      GettersInl.getUserLastReverseGeoCode(requestFollow.getUserId() );
    
    if (reverseGeoCode != null) {
      
      followerLocation = reverseGeoCode.getContinentCode();
    } else {
      
      followerLocation = Constants.kDefaultRegion;
    }
    
    // get follow user's location

    String followLocation;
    
    reverseGeoCode =
      GettersInl.getUserLastReverseGeoCode(followUserId);
    
    if (reverseGeoCode != null) {
      
      followLocation = reverseGeoCode.getContinentCode();
    } else {
      
      followLocation = Constants.kDefaultRegion;
    }
    
    // dispatch analytics
    
    // ig_analytics.annual_regional_counters.increment_sent_follows
    request.getDispatcher().addDispatchMessage(
      AnnualRegionalCounters.i().getQueryDispatchableIncrementSentFollows(
        CalendarFormatterInl.concatCalendarFields(
          request.getStartCalendar(),
          Calendar.YEAR)
          + Constants.kCassandraIdConcat
          + followerLocation) );
    
    // ig_analytics.annual_regional_counters.increment_sent_follows
    request.getDispatcher().addDispatchMessage(
      AnnualRegionalCounters.i().getQueryDispatchableIncrementSentFollows(
        CalendarFormatterInl.concatCalendarFields(
          request.getStartCalendar(),
          Calendar.YEAR)
          + Constants.kCassandraIdConcat
          + Constants.kWorldRegion) );
    
    // ig_analytics.annual_regional_counters.increment_received_follows
    request.getDispatcher().addDispatchMessage(
      AnnualRegionalCounters.i().getQueryDispatchableIncrementReceivedFollows(
        CalendarFormatterInl.concatCalendarFields(
          request.getStartCalendar(),
          Calendar.YEAR)
          + Constants.kCassandraIdConcat
          + followLocation) );
    
    // ig_analytics.annual_regional_counters.increment_received_follows
    request.getDispatcher().addDispatchMessage(
      AnnualRegionalCounters.i().getQueryDispatchableIncrementReceivedFollows(
        CalendarFormatterInl.concatCalendarFields(
          request.getStartCalendar(),
          Calendar.YEAR)
          + Constants.kCassandraIdConcat
          + Constants.kWorldRegion) );
    
    // ig_analytics.daily_regional_counters.increment_sent_follows
    request.getDispatcher().addDispatchMessage(
      DailyRegionalCounters.i().getQueryDispatchableIncrementSentFollows(
        CalendarFormatterInl.concatCalendarFields(
          request.getStartCalendar(),
          Calendar.YEAR,
          Calendar.MONTH,
          Calendar.DAY_OF_MONTH)
          + Constants.kCassandraIdConcat
          + followerLocation) );
    
    // ig_analytics.daily_regional_counters.increment_sent_follows
    request.getDispatcher().addDispatchMessage(
      DailyRegionalCounters.i().getQueryDispatchableIncrementSentFollows(
        CalendarFormatterInl.concatCalendarFields(
          request.getStartCalendar(),
          Calendar.YEAR,
          Calendar.MONTH,
          Calendar.DAY_OF_MONTH)
          + Constants.kCassandraIdConcat
          + Constants.kWorldRegion) );
    
    // ig_analytics.daily_regional_counters.increment_received_follows
    request.getDispatcher().addDispatchMessage(
      DailyRegionalCounters.i().getQueryDispatchableIncrementReceivedFollows(
        CalendarFormatterInl.concatCalendarFields(
          request.getStartCalendar(),
          Calendar.YEAR,
          Calendar.MONTH,
          Calendar.DAY_OF_MONTH)
          + Constants.kCassandraIdConcat
          + followLocation) );
    
    // ig_analytics.daily_regional_counters.increment_received_follows
    request.getDispatcher().addDispatchMessage(
      DailyRegionalCounters.i().getQueryDispatchableIncrementReceivedFollows(
        CalendarFormatterInl.concatCalendarFields(
          request.getStartCalendar(),
          Calendar.YEAR,
          Calendar.MONTH,
          Calendar.DAY_OF_MONTH)
          + Constants.kCassandraIdConcat
          + Constants.kWorldRegion) );
  }
}
