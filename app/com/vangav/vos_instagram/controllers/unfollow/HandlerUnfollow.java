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

package com.vangav.vos_instagram.controllers.unfollow;

import java.util.Calendar;
import java.util.UUID;

import com.datastax.driver.core.BatchStatement;
import com.datastax.driver.core.BatchStatement.Type;
import com.datastax.driver.core.ResultSet;
import com.vangav.backend.cassandra.Cassandra;
import com.vangav.backend.cassandra.formatting.CalendarFormatterInl;
import com.vangav.backend.exceptions.BadRequestException;
import com.vangav.backend.exceptions.VangavException.ExceptionClass;
import com.vangav.backend.geo.reverse_geo_coding.ReverseGeoCode;
import com.vangav.backend.play_framework.request.Request;
import com.vangav.backend.play_framework.request.RequestJsonBody;
import com.vangav.backend.play_framework.request.RequestState;
import com.vangav.backend.play_framework.request.response.ResponseBody;
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
 * HandlerUnfollow
 *   handles request-to-response processing
 *   also handles after response processing (if any)
 * */
public class HandlerUnfollow extends CommonPlayHandler {

  private static final String kName = "Unfollow";

  @Override
  protected String getName () {

    return kName;
  }

  @Override
  protected RequestJsonBody getRequestJson () {

    return new RequestUnfollow();
  }

  @Override
  protected ResponseBody getResponseBody () {

    return new ResponseUnfollow();
  }
  
  @Override
  protected Authenticator getRequestAuthenticator (
    final RequestJsonBody requestJsonBody) throws Exception {
    
    return
      new Authenticator(
        ((RequestUnfollow)requestJsonBody).device_token,
        ((RequestUnfollow)requestJsonBody).user_id,
        ((RequestUnfollow)requestJsonBody).access_token);
  }
  
  /**
   * checkRequest
   * validates the request before processing it
   * @param request
   * @throws Exception
   */
  private void checkRequest (final Request request) throws Exception {
    
    // get request's body
    RequestUnfollow requestUnfollow =
      (RequestUnfollow)request.getRequestJsonBody();
    
    // make unfollow_user_id UUID
    UUID unfollowUserId = UUID.fromString(requestUnfollow.unfollow_user_id);
    
    // check if unfollow_user_id exists
    if (CheckersInl.userIdExists(unfollowUserId) == false) {
      
      throw new BadRequestException(
        428,
        1,
        "Can't unfollow a user that doesn't exist. unfollow_user_id ["
          + requestUnfollow.unfollow_user_id
          + "]. Request issued by user_id ["
          + requestUnfollow.user_id
          + "] from device_token ["
          + requestUnfollow.device_token
          + "]",
        ExceptionClass.INVALID);
    }
    
    // a user can't unfollow another unless she/he is following that user
    //   already
    if (CheckersInl.isFollowing(
          requestUnfollow.getUserId(),
          unfollowUserId) == false) {
      
      throw new BadRequestException(
        428,
        2,
        "Can't unfollow an unfollowed user. Request issued by user_id ["
          + requestUnfollow.user_id
          + "] from device_token ["
          + requestUnfollow.device_token
          + "]. Trying to unfollow user_id ["
          + requestUnfollow.unfollow_user_id
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
    RequestUnfollow requestUnfollow =
      (RequestUnfollow)request.getRequestJsonBody();
    
    // make unfollow_user_id UUID
    UUID unfollowUserId = UUID.fromString(requestUnfollow.unfollow_user_id);
    
    // get follow_time
    ResultSet resultSet =
      FollowingTime.i().executeSyncSelect(
        requestUnfollow.getUserId(),
        unfollowUserId);
    
    long followTime =
      resultSet.one().getLong(FollowingTime.kFollowingTimeColumnName);
    
    // all queries must succeed
    BatchStatement batchStatement = new BatchStatement(Type.LOGGED);
    
    // delete from ig_app_data.following
    batchStatement.add(
      Following.i().getBoundStatementDelete(
        requestUnfollow.getUserId(),
        followTime,
        unfollowUserId) );
    
    // delete from ig_app_data.following_time
    batchStatement.add(
      FollowingTime.i().getBoundStatementDelete(
        requestUnfollow.getUserId(),
        unfollowUserId) );
    
    // delete from ig_app_data.follower
    batchStatement.add(
      Follower.i().getBoundStatementDelete(
        unfollowUserId,
        followTime,
        requestUnfollow.getUserId() ) );
    
    // delete from ig_app_data.follower_time
    batchStatement.add(
      FollowerTime.i().getBoundStatementDelete(
        unfollowUserId,
        requestUnfollow.getUserId() ) );
    
    // execute batch statement
    Cassandra.i().executeSync(batchStatement);
    
    // set response
    ((ResponseUnfollow)request.getResponseBody() ).set(
      requestUnfollow.request_tracking_id);
  }

  @Override
  protected void afterProcessing (
    final Request request) throws Exception {
    
    // only process this method if user's request was a success
    if (request.getState() != RequestState.OK) {
      
      return;
    }
    
    // get request's body
    RequestUnfollow requestUnfollow =
      (RequestUnfollow)request.getRequestJsonBody();
    
    // make unfollow_user_id UUID
    UUID unfollowUserId = UUID.fromString(requestUnfollow.unfollow_user_id);
    
    // decrement ig_app_data.following_count
    request.getDispatcher().addDispatchMessage(
      FollowingCount.i().getQueryDispatchableDecrement(
        requestUnfollow.getUserId() ) );
    
    // decrement ig_app_data.follower_count
    request.getDispatcher().addDispatchMessage(
      FollowerCount.i().getQueryDispatchableDecrement(
        unfollowUserId) );
    
    // increment ig_app_data.count_per_week
    request.getDispatcher().addDispatchMessage(
      CountPerWeek.i().getQueryDispatchableIncrementUnfollowerCount(
        requestUnfollow.unfollow_user_id
        + Constants.kCassandraIdConcat
        + CalendarFormatterInl.concatCalendarFields(
          request.getStartCalendar(),
          Calendar.YEAR,
          Calendar.WEEK_OF_YEAR) ) );
  }

  @Override
  protected void dispatchAnalysis (
    final Request request) throws Exception {
    
    // only process this method if user's request was a success
    if (request.getState() != RequestState.OK) {
      
      return;
    }
    
    // get request's body
    RequestUnfollow requestUnfollow =
      (RequestUnfollow)request.getRequestJsonBody();
    
    // make unfollow_user_id UUID
    UUID unfollowUserId = UUID.fromString(requestUnfollow.unfollow_user_id);
    
    // get unfollower user's location
    
    String unfollowerLocation = Constants.kDefaultRegion;
    
    ReverseGeoCode reverseGeoCode =
      GettersInl.getUserLastReverseGeoCode(requestUnfollow.getUserId() );
    
    if (reverseGeoCode != null) {
     
      unfollowerLocation = reverseGeoCode.getContinentCode();
    }
    
    // get unfollowed user's location
    
    String unfollowedLocation = Constants.kDefaultRegion;
    
    reverseGeoCode =
      GettersInl.getUserLastReverseGeoCode(unfollowUserId);
    
    if (reverseGeoCode != null) {
      
      unfollowedLocation = reverseGeoCode.getContinentCode();
    }
    
    // dispatch analytics
    
    // ig_analytics.annual_regional_counters.increment_sent_unfollows
    request.getDispatcher().addDispatchMessage(
      AnnualRegionalCounters.i(
        ).getQueryDispatchableIncrementSentUnfollows(
          CalendarFormatterInl.concatCalendarFields(
            request.getStartCalendar(),
            Calendar.YEAR)
            + Constants.kCassandraIdConcat
            + unfollowerLocation) );
    
    // ig_analytics.annual_regional_counters.increment_sent_unfollows
    request.getDispatcher().addDispatchMessage(
      AnnualRegionalCounters.i(
        ).getQueryDispatchableIncrementSentUnfollows(
          CalendarFormatterInl.concatCalendarFields(
            request.getStartCalendar(),
            Calendar.YEAR)
            + Constants.kCassandraIdConcat
            + Constants.kWorldRegion) );
    
    // ig_analytics.annual_regional_counters.increment_received_unfollows
    request.getDispatcher().addDispatchMessage(
      AnnualRegionalCounters.i(
        ).getQueryDispatchableIncrementReceivedUnfollows(
          CalendarFormatterInl.concatCalendarFields(
            request.getStartCalendar(),
            Calendar.YEAR)
            + Constants.kCassandraIdConcat
            + unfollowedLocation) );
    
    // ig_analytics.annual_regional_counters.increment_received_unfollows
    request.getDispatcher().addDispatchMessage(
      AnnualRegionalCounters.i(
        ).getQueryDispatchableIncrementReceivedUnfollows(
          CalendarFormatterInl.concatCalendarFields(
            request.getStartCalendar(),
            Calendar.YEAR)
            + Constants.kCassandraIdConcat
            + Constants.kWorldRegion) );
    
    // ig_analytics.daily_regional_counters.increment_sent_unfollows
    request.getDispatcher().addDispatchMessage(
      DailyRegionalCounters.i(
        ).getQueryDispatchableIncrementSentUnfollows(
          CalendarFormatterInl.concatCalendarFields(
            request.getStartCalendar(),
            Calendar.YEAR,
            Calendar.MONTH,
            Calendar.DAY_OF_MONTH)
            + Constants.kCassandraIdConcat
            + unfollowerLocation) );
    
    // ig_analytics.daily_regional_counters.increment_sent_unfollows
    request.getDispatcher().addDispatchMessage(
      DailyRegionalCounters.i(
        ).getQueryDispatchableIncrementSentUnfollows(
          CalendarFormatterInl.concatCalendarFields(
            request.getStartCalendar(),
            Calendar.YEAR,
            Calendar.MONTH,
            Calendar.DAY_OF_MONTH)
            + Constants.kCassandraIdConcat
            + Constants.kWorldRegion) );
    
    // ig_analytics.daily_regional_counters.increment_received_unfollows
    request.getDispatcher().addDispatchMessage(
      DailyRegionalCounters.i(
        ).getQueryDispatchableIncrementReceivedUnfollows(
          CalendarFormatterInl.concatCalendarFields(
            request.getStartCalendar(),
            Calendar.YEAR,
            Calendar.MONTH,
            Calendar.DAY_OF_MONTH)
            + Constants.kCassandraIdConcat
            + unfollowedLocation) );
    
    // ig_analytics.daily_regional_counters.increment_received_unfollows
    request.getDispatcher().addDispatchMessage(
      DailyRegionalCounters.i(
        ).getQueryDispatchableIncrementReceivedUnfollows(
          CalendarFormatterInl.concatCalendarFields(
            request.getStartCalendar(),
            Calendar.YEAR,
            Calendar.MONTH,
            Calendar.DAY_OF_MONTH)
            + Constants.kCassandraIdConcat
            + Constants.kWorldRegion) );
  }
}
