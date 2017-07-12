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

package com.vangav.vos_instagram.controllers.comment;

import java.util.Calendar;
import java.util.Map;
import java.util.UUID;

import com.datastax.driver.core.BatchStatement;
import com.datastax.driver.core.BatchStatement.Type;
import com.datastax.driver.core.ResultSet;
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
import com.vangav.vos_instagram.cassandra_keyspaces.ig_app_data.PostComments;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_app_data.PostCommentsCount;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_app_data.PostCommentsTime;
import com.vangav.vos_instagram.common.Authenticator;
import com.vangav.vos_instagram.common.Constants;
import com.vangav.vos_instagram.common.cassandra.CheckersInl;
import com.vangav.vos_instagram.common.cassandra.GettersInl;
import com.vangav.vos_instagram.controllers.CommonPlayHandler;

/**
 * GENERATED using ControllersGeneratorMain.java
 */
/**
 * HandlerComment
 *   handles request-to-response processing
 *   also handles after response processing (if any)
 * */
public class HandlerComment extends CommonPlayHandler {

  private static final String kName = "Comment";

  @Override
  protected String getName () {

    return kName;
  }

  @Override
  protected RequestJsonBody getRequestJson () {

    return new RequestComment();
  }

  @Override
  protected ResponseBody getResponseBody () {

    return new ResponseComment();
  }
  
  @Override
  protected Authenticator getRequestAuthenticator (
    final RequestJsonBody requestJsonBody) throws Exception {
    
    return
      new Authenticator(
        ((RequestComment)requestJsonBody).device_token,
        ((RequestComment)requestJsonBody).user_id,
        ((RequestComment)requestJsonBody).access_token);
  }

  @Override
  protected void processRequest (final Request request) throws Exception {

    // use the following request Object to process the request and set
    //   the response to be returned
    RequestComment requestComment =
      (RequestComment)request.getRequestJsonBody();
    
    UUID postId = UUID.fromString(requestComment.post_id);
    
    // post doesn't exist?
    if (CheckersInl.postExists(postId) == false) {
      
      throw new BadRequestException(
        401,
        1,
        "Can't comment on a post that doesn't exist, post_id ["
          + requestComment.post_id
          + "]. Request issued by user_id ["
          + requestComment.user_id
          + "] from device_token ["
          + requestComment.device_token
          + "]",
        ExceptionClass.INVALID);
    }
    
    // NOTE: vos_instagram intentionally allows only one comment for each
    //         user on a post
    //       in case a user comments more than once on the same post, the newer
    //         comment overwrites the older one
    
    // all queries must succeed
    BatchStatement batchStatement = new BatchStatement(Type.LOGGED);
    
    ResultSet resultSet =
      PostCommentsTime.i().executeSyncSelect(
        postId,
        requestComment.getUserId() );
    
    // user commented on this post before?
    if (resultSet.isExhausted() == false) {

      long oldCommentTime =
        resultSet.one().getLong(PostCommentsTime.kCommentTimeColumnName);
      
      // delete old user's comment
      batchStatement.add(
        PostComments.i().getBoundStatementDelete(
          postId,
          oldCommentTime,
          requestComment.getUserId() ) );
    } else {
      
      // first time for this user to comment on this post? increment comments
      //   count
      PostCommentsCount.i().executeSyncIncrement(postId);
    }
    
    // insert into ig_app_data.post_comments
    batchStatement.add(
      PostComments.i().getBoundStatementInsert(
        postId,
        request.getStartTime(),
        requestComment.getUserId(),
        requestComment.comment) );
    
    // insert into ig_app_data.post_comments_time
    batchStatement.add(
      PostCommentsTime.i().getBoundStatementInsert(
        postId,
        requestComment.getUserId(),
        request.getStartTime() ) );
    
    // execute batch statement
    Cassandra.i().executeSync(batchStatement);
    
    // set response
    ((ResponseComment)request.getResponseBody() ).set(
      requestComment.request_tracking_id,
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
    RequestComment requestComment =
      (RequestComment)request.getRequestJsonBody();
    
    // get post's id
    UUID postId = UUID.fromString(requestComment.post_id);
    
    // get post's owner user_id
    UUID postOwnerUserId = GettersInl.getPostOwnerId(postId);
    
    // dispatch counters updates
    
    request.getDispatcher().addDispatchMessage(
      CountTotal.i().getQueryDispatchableIncrementCommentsReceivedCount(
        postOwnerUserId) );
    
    request.getDispatcher().addDispatchMessage(
      CountPerWeek.i().getQueryDispatchableIncrementCommentsReceivedCount(
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
    RequestComment requestComment =
      (RequestComment)request.getRequestJsonBody();
    
    // get post's id
    UUID postId = UUID.fromString(requestComment.post_id);
    
    // get post's owner user_id
    UUID postOwnerUserId = GettersInl.getPostOwnerId(postId);
    
    // commented on own photo? don't notify
    if (postOwnerUserId.toString().compareTo(requestComment.user_id) == 0) {
      
      return;
    }
    
    // get post's owner's device tokens
    Map<String, String> deviceTokens =
      GettersInl.getUserDeviceTokens(postOwnerUserId);
      
    // get commenter's name
    String commenterName = GettersInl.getUserName(requestComment.getUserId() );
    
    // dispatch notifications for each device token
    for (String deviceToken : deviceTokens.keySet() ) {
      
      if (deviceTokens.get(deviceToken).compareTo(
            Constants.kAppleDeviceTypeName) == 0) {
        
        request.getDispatcher().addDispatchMessage(
          new AppleNotificationDispatchable(
            new AppleNotificationBuilder(deviceToken)
              .alertBody(commenterName + " commented on your photo")
              .badgeNumber(1)
              .build() ) );
      } else if (deviceTokens.get(deviceToken).compareTo(
                   Constants.kAndroidDeviceTypeName) == 0) {
        
        request.getDispatcher().addDispatchMessage(
          new AndroidNotificationDispatchable(
            new AndroidNotification(
              new Message.Builder()
                .collapseKey(commenterName + " commented on your photo")
                .build(),
              deviceToken) ) );
      } else {
        
        // non-fatal exception
        request.addVangavException(
          new CodeException(
            401,
            2,
            "Invalid device type ["
              + deviceTokens.get(deviceToken)
              + "] for user_id ["
              + postOwnerUserId.toString()
              + "] and device_token ["
              + deviceToken
              + "]",
            ExceptionClass.INVALID) );
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
    RequestComment requestComment =
      (RequestComment)request.getRequestJsonBody();
    
    // get post's id
    UUID postId = UUID.fromString(requestComment.post_id);
    
    // get post's owner user_id
    UUID postOwnerUserId = GettersInl.getPostOwnerId(postId);

    // get commenter's last location
    
    String commenterLocation;
    
    ReverseGeoCode reverseGeoCode =
      GettersInl.getUserLastReverseGeoCode(requestComment.getUserId() );
    
    if (reverseGeoCode != null) {
      
      commenterLocation = reverseGeoCode.getContinentCode();
    } else {
      
      commenterLocation = Constants.kDefaultRegion;
    }
    
    // get post owner's last location
    
    String postOwnerLocation;
    
    if (postOwnerUserId.toString().compareTo(requestComment.user_id) == 0) {
      
      postOwnerLocation = commenterLocation;
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
    
    // ig_analytics.annual_regional_counters.increment_sent_comments
    request.getDispatcher().addDispatchMessage(
      AnnualRegionalCounters.i().getQueryDispatchableIncrementSentComments(
        CalendarFormatterInl.concatCalendarFields(
          request.getStartCalendar(),
          Calendar.YEAR)
          + Constants.kCassandraIdConcat
          + commenterLocation) );
    
    // ig_analytics.annual_regional_counters.increment_sent_comments
    request.getDispatcher().addDispatchMessage(
      AnnualRegionalCounters.i().getQueryDispatchableIncrementSentComments(
        CalendarFormatterInl.concatCalendarFields(
          request.getStartCalendar(),
          Calendar.YEAR)
          + Constants.kCassandraIdConcat
          + Constants.kWorldRegion) );
    
    // ig_analytics.annual_regional_counters.increment_received_comments
    request.getDispatcher().addDispatchMessage(
      AnnualRegionalCounters.i().getQueryDispatchableIncrementReceivedComments(
        CalendarFormatterInl.concatCalendarFields(
          request.getStartCalendar(),
          Calendar.YEAR)
          + Constants.kCassandraIdConcat
          + postOwnerLocation) );
    
    // ig_analytics.annual_regional_counters.increment_received_comments
    request.getDispatcher().addDispatchMessage(
      AnnualRegionalCounters.i().getQueryDispatchableIncrementReceivedComments(
        CalendarFormatterInl.concatCalendarFields(
          request.getStartCalendar(),
          Calendar.YEAR)
          + Constants.kCassandraIdConcat
          + Constants.kWorldRegion) );
    
    // ig_analytics.daily_regional_counters.increment_sent_comments
    request.getDispatcher().addDispatchMessage(
      DailyRegionalCounters.i().getQueryDispatchableIncrementSentComments(
        CalendarFormatterInl.concatCalendarFields(
          request.getStartCalendar(),
          Calendar.YEAR,
          Calendar.MONTH,
          Calendar.DAY_OF_MONTH)
          + Constants.kCassandraIdConcat
          + commenterLocation) );
    
    // ig_analytics.daily_regional_counters.increment_sent_comments
    request.getDispatcher().addDispatchMessage(
      DailyRegionalCounters.i().getQueryDispatchableIncrementSentComments(
        CalendarFormatterInl.concatCalendarFields(
          request.getStartCalendar(),
          Calendar.YEAR,
          Calendar.MONTH,
          Calendar.DAY_OF_MONTH)
          + Constants.kCassandraIdConcat
          + Constants.kWorldRegion) );
    
    // ig_analytics.daily_regional_counters.increment_received_comments
    request.getDispatcher().addDispatchMessage(
      DailyRegionalCounters.i().getQueryDispatchableIncrementReceivedComments(
        CalendarFormatterInl.concatCalendarFields(
          request.getStartCalendar(),
          Calendar.YEAR,
          Calendar.MONTH,
          Calendar.DAY_OF_MONTH)
          + Constants.kCassandraIdConcat
          + postOwnerLocation) );
    
    // ig_analytics.daily_regional_counters.increment_received_comments
    request.getDispatcher().addDispatchMessage(
      DailyRegionalCounters.i().getQueryDispatchableIncrementReceivedComments(
        CalendarFormatterInl.concatCalendarFields(
          request.getStartCalendar(),
          Calendar.YEAR,
          Calendar.MONTH,
          Calendar.DAY_OF_MONTH)
          + Constants.kCassandraIdConcat
          + Constants.kWorldRegion) );
  }
}
