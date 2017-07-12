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

package com.vangav.vos_instagram.controllers;

import java.util.Calendar;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.vangav.backend.cassandra.formatting.CalendarFormatterInl;
import com.vangav.backend.exceptions.BadRequestException;
import com.vangav.backend.exceptions.DefaultException;
import com.vangav.backend.exceptions.VangavException;
import com.vangav.backend.exceptions.VangavException.ExceptionClass;
import com.vangav.backend.metrics.time.Period;
import com.vangav.backend.metrics.time.TimeUnitType;
import com.vangav.backend.play_framework.ParentPlayHandler;
import com.vangav.backend.play_framework.request.Request;
import com.vangav.backend.play_framework.request.RequestJsonBody;
import com.vangav.backend.play_framework.request.RequestState;
import com.vangav.backend.play_framework.request.response.ResponseBodyError;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_auth.AccessTokens;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_logging.DailyRequestsCounters;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_logging.DailyUsersErrorLogs;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_logging.DailyUsersLogs;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_logging.ErrorLogs;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_logging.HourlyControllersErrorLogs;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_logging.HourlyRequestsCounters;
import com.vangav.vos_instagram.common.Authenticator;
import com.vangav.vos_instagram.common.BigRequestInt;
import com.vangav.vos_instagram.common.BigResponseInt;
import com.vangav.vos_instagram.common.Constants;

/**
 * GENERATED using ControllersGeneratorMain.java
 */
/**
 * CommonPlayHandler represents the parent handler for all controllers
 * */
public abstract class CommonPlayHandler extends ParentPlayHandler {

  @Override
  protected void checkSource (
    final Request request) throws Exception {

     // implement here and/or override per Controller's Handler
  }

  @Override
  protected void throttle (
    final Request request) throws Exception {

     // implement here and/or override per Controller's Handler
  }
  
  /**
   * getRequestAuthenticator
   * @param requestJsonBody
   * @return requests Authenticator Object
   * @throws Exception
   */
  protected Authenticator getRequestAuthenticator (
    final RequestJsonBody requestJsonBody) throws Exception {
    
    return null;
  }

  @Override
  protected void authenticateRequest (
    final Request request) throws Exception {

     // implement here and/or override per Controller's Handler
    
    // get authenticator
    Authenticator authenticator =
      this.getRequestAuthenticator(request.getRequestJsonBody() );
    
    // no common authentication for this controller?
    if (authenticator == null) {
      
      return;
    }
    
    // get user's access token
    ResultSet resultSet =
      AccessTokens.i().executeSyncSelect(
        authenticator.getUserId(),
        authenticator.getDeviceToken() );
    
    // no access token for this user id and device token
    if (resultSet.isExhausted() == true) {
      
      throw new BadRequestException(
        400,
        1,
        "No access token for user_id ["
          + authenticator.getUserId().toString()
          + "] and device_token ["
          + authenticator.getDeviceToken()
          + "]",
        ExceptionClass.AUTHENTICATION);
    }
    
    // extract access token data
    
    Row row = resultSet.one();
    
    String dbAccessToken = row.getString(AccessTokens.kAccessTokenColumnName);
    long dbTimeStamp = row.getLong(AccessTokens.kTimeStampColumnName);
    boolean dbExpired = row.getBool(AccessTokens.kExpiredColumnName);
    
    // wrong access token?
    if (authenticator.getAccessToken().compareTo(dbAccessToken) != 0) {
      
      throw new BadRequestException(
        400,
        2,
        "Wrong access token for user_id ["
          + authenticator.getUserId().toString()
          + "] and device_token ["
          + authenticator.getDeviceToken()
          + "] expected access_token ["
          + dbAccessToken
          + "] but got access_token ["
          + authenticator.getAccessToken()
          + "]",
        ExceptionClass.AUTHENTICATION);
    }
    
    // expired access token?
    if (dbExpired == true) {
      
      throw new BadRequestException(
        400,
        3,
        "Expired access token for user_id ["
          + authenticator.getUserId().toString()
          + "] and device_token ["
          + authenticator.getDeviceToken()
          + "] and access_token ["
          + authenticator.getAccessToken()
          + "]",
        ExceptionClass.AUTHENTICATION);
    }

    // get current access token life time
    Period currAccessTokenLifeTime =
      new Period(
        System.currentTimeMillis() - dbTimeStamp,
        TimeUnitType.MILLISECOND);
    
    // should expire?
    if (currAccessTokenLifeTime.greaterThan(
          Constants.kAccessTokenLifeTime) == true) {
      
      // set access token as expired
      AccessTokens.i().executeSyncExpire(
        authenticator.getUserId(),
        authenticator.getDeviceToken() );
      
      throw new BadRequestException(
        400,
        4,
        "Expired access token for user_id ["
          + authenticator.getUserId().toString()
          + "] and device_token ["
          + authenticator.getDeviceToken()
          + "] and access_token ["
          + authenticator.getAccessToken()
          + "]",
        ExceptionClass.AUTHENTICATION);
    }
    
    // successful authentication!
  }

  @Override
  protected void afterProcessing (
    final Request request) throws Exception {

     // Override in a Controller's Handler to implement
  }

  @Override
  protected void dispatchDefaultOperations (
    final Request request) throws Exception {

     // implement here and/or override per Controller's Handler
  }

  @Override
  protected void dispatchPushNotifications (
    final Request request) throws Exception {

     // Override in a Controller's Handler to implement
  }

  @Override
  protected void dispatchAnalysis (
    final Request request) throws Exception {

     // Override in a Controller's Handler to implement
  }

  @Override
  final protected void dispatchDefaultAnalysis (
    final Request request) throws Exception {

     // no default analysis for vos_instagram so far
  }

  @Override
  protected void dispatchLogging (
    final Request request) throws Exception {

     // Override in a Controller's Handler to implement
  }
  
  private static final String kAllControllersName = "all_controllers";

  @Override
  final protected void dispatchDefaultLogging (
    final Request request) throws Exception {
    
    String cassandraDay =
      CalendarFormatterInl.concatCalendarFields(
        request.getStartCalendar(),
        Calendar.YEAR,
        Calendar.MONTH,
        Calendar.DAY_OF_MONTH);
    
    String cassandraHour =
      CalendarFormatterInl.concatCalendarFields(
        request.getStartCalendar(),
        Calendar.YEAR,
        Calendar.MONTH,
        Calendar.DAY_OF_MONTH,
        Calendar.HOUR_OF_DAY);

    // log in:
    //   ig_logging.daily_requests_counters
    //   ig_logging.hourly_requests_counters
    
    if (request.getState() == RequestState.OK) {
      
      // for this controller
      
      request.getDispatcher().addDispatchMessage(
        DailyRequestsCounters.i(
          ).getQueryDispatchableIncrementOkResponses(
            (long)request.getExecTime(),
            cassandraDay
              + Constants.kCassandraIdConcat
              + request.getControllerName() ) );
      
      request.getDispatcher().addDispatchMessage(
        HourlyRequestsCounters.i(
          ).getQueryDispatchableIncrementOkResponses(
            (long)request.getExecTime(),
            cassandraHour
              + Constants.kCassandraIdConcat
              + request.getControllerName() ) );
      
      // for all controllers
      
      request.getDispatcher().addDispatchMessage(
        DailyRequestsCounters.i(
          ).getQueryDispatchableIncrementOkResponses(
            (long)request.getExecTime(),
            cassandraDay
              + Constants.kCassandraIdConcat
              + kAllControllersName) );
      
      request.getDispatcher().addDispatchMessage(
        HourlyRequestsCounters.i(
          ).getQueryDispatchableIncrementOkResponses(
            (long)request.getExecTime(),
            cassandraHour
              + Constants.kCassandraIdConcat
              + kAllControllersName) );
    } else if (request.getState() == RequestState.BAD_REQUEST) {
      
      // for this controller
      
      request.getDispatcher().addDispatchMessage(
        DailyRequestsCounters.i(
          ).getQueryDispatchableIncrementBadRequestResponses(
            (long)request.getExecTime(),
            cassandraDay
              + Constants.kCassandraIdConcat
              + request.getControllerName() ) );
      
      request.getDispatcher().addDispatchMessage(
        HourlyRequestsCounters.i(
          ).getQueryDispatchableIncrementBadRequestResponses(
            (long)request.getExecTime(),
            cassandraHour
              + Constants.kCassandraIdConcat
              + request.getControllerName() ) );
      
      // for all controllers
      
      request.getDispatcher().addDispatchMessage(
        DailyRequestsCounters.i(
          ).getQueryDispatchableIncrementBadRequestResponses(
            (long)request.getExecTime(),
            cassandraDay
              + Constants.kCassandraIdConcat
              + kAllControllersName) );
      
      request.getDispatcher().addDispatchMessage(
        HourlyRequestsCounters.i(
          ).getQueryDispatchableIncrementBadRequestResponses(
            (long)request.getExecTime(),
            cassandraHour
              + Constants.kCassandraIdConcat
              + kAllControllersName) );
    } else {
      
      // for this controller
      
      request.getDispatcher().addDispatchMessage(
        DailyRequestsCounters.i(
          ).getQueryDispatchableIncrementInternalErrorResponses(
            (long)request.getExecTime(),
            cassandraDay
              + Constants.kCassandraIdConcat
              + request.getControllerName() ) );
      
      request.getDispatcher().addDispatchMessage(
        HourlyRequestsCounters.i(
          ).getQueryDispatchableIncrementInternalErrorResponses(
            (long)request.getExecTime(),
            cassandraHour
              + Constants.kCassandraIdConcat
              + request.getControllerName() ) );
      
      // for all controllers
      
      request.getDispatcher().addDispatchMessage(
        DailyRequestsCounters.i(
          ).getQueryDispatchableIncrementInternalErrorResponses(
            (long)request.getExecTime(),
            cassandraDay
              + Constants.kCassandraIdConcat
              + kAllControllersName) );
      
      request.getDispatcher().addDispatchMessage(
        HourlyRequestsCounters.i(
          ).getQueryDispatchableIncrementInternalErrorResponses(
            (long)request.getExecTime(),
            cassandraHour
              + Constants.kCassandraIdConcat
              + kAllControllersName) );
    }
    
    // omit big objects (e.g.: photos) from request for logging
    
    String loggableRequest;
    
    if (request.getRequestJsonBody() instanceof BigRequestInt) {
      
      loggableRequest =
        ((BigRequestInt)request.getResponseBody()).getMinRequestAsString();
    } else {
      
      loggableRequest =
        request.getRequestJsonBody().toString();
    }
    
    // omit big objects (e.g.: photos) from response for logging
    
    String loggableResponse;
    
    if (request.getResponseBody() instanceof BigResponseInt &&
        request.getState() == RequestState.OK) {

      loggableResponse =
        ((BigResponseInt)request.getResponseBody() ).getMinResponseAsString();
    } else if (request.getState() == RequestState.OK) {
      
      loggableResponse =
        request.getResponseBody().toString();
    } else if (request.getState() == RequestState.BAD_REQUEST) {
      
      if (request.getTheLastVangavException() != null) {
        
        ResponseBodyError responseBodyError =
          new ResponseBodyError(
            request.getTheLastVangavException(),
            request.getRequestId() );
        
        loggableResponse = responseBodyError.toString();
      } else {
        
        ResponseBodyError responseBodyError =
          new ResponseBodyError(
            new DefaultException(),
            request.getRequestId() );
        
        loggableResponse = responseBodyError.toString();
      }
    } else {
      
      if (request.getTheLastVangavException() != null) {
        
        ResponseBodyError responseBodyError =
          new ResponseBodyError(
            request.getTheLastVangavException(),
            request.getRequestId() );
        
        loggableResponse = responseBodyError.toString();
      } else if (request.getTheLastException() != null) {

        
        loggableResponse =
          VangavException.getExceptionStackTrace(
            request.getTheLastException() );
      } else {
        
        ResponseBodyError responseBodyError =
          new ResponseBodyError(
            new DefaultException(),
            request.getRequestId() );
        
        loggableResponse = responseBodyError.toString();
      }
    }
    
    // log in: ig_logging.daily_users_logs
    request.getDispatcher().addDispatchMessage(
      DailyUsersLogs.i().getQueryDispatchableInsert(
        cassandraDay
          + Constants.kCassandraIdConcat
          + request.getUserId().toString(),
        request.getStartTime(),
        request.getRequestId(),
        request.getControllerName(),
        loggableRequest,
        request.getState().toString(),
        loggableResponse,
        request.getExecTime() ) );
    
    // something went wrong? log error
    // log in:
    //   ig_logging.error_logs
    //   ig_logging.hourly_controllers_error_logs
    //   ig_logging.daily_users_error_logs
    if (request.getState() != RequestState.OK) {
      
      request.getDispatcher().addDispatchMessage(
        ErrorLogs.i().getQueryDispatchableInsert(
          request.getRequestId(),
          request.getStartTime(),
          request.getControllerName(),
          request.getUserId(),
          Constants.getHttpStatusCode(request.getState() ),
          loggableRequest,
          loggableResponse) );
      
      request.getDispatcher().addDispatchMessage(
        HourlyControllersErrorLogs.i().getQueryDispatchableInsert(
          cassandraHour
            + Constants.kCassandraIdConcat
            + request.getControllerName(),
          request.getStartTime(),
          request.getRequestId() ) );
      
      request.getDispatcher().addDispatchMessage(
        DailyUsersErrorLogs.i().getQueryDispatchableInsert(
          cassandraDay
            + Constants.kCassandraIdConcat
            + request.getUserId().toString(),
          request.getStartTime(),
          request.getRequestId() ) );
    } // if (request.getState() != RequestState.OK)
  }

  // IMPORTANT: this method must be implemented within a try and catch block
  //             because any exceptions thrown by this method will terminate
  //             the service instance and disables it from handling other
  //             requests
  @Override
  protected void absorbUnhandledExceptions (
    final Exception exception) {

    // TODO: implement here or override per-controller
  }

}
