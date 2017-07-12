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

package com.vangav.vos_instagram.controllers.update_location;

import java.util.Calendar;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.vangav.backend.cassandra.formatting.CalendarFormatterInl;
import com.vangav.backend.geo.reverse_geo_coding.ReverseGeoCode;
import com.vangav.backend.geo.reverse_geo_coding.ReverseGeoCoding;
import com.vangav.backend.metrics.time.CalendarAndDateOperationsInl;
import com.vangav.backend.play_framework.request.Request;
import com.vangav.backend.play_framework.request.RequestJsonBody;
import com.vangav.backend.play_framework.request.response.ResponseBody;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_analytics.AnnualRegionalCounters;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_analytics.DailyRegionalCounters;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_app_data.UsersInfo;
import com.vangav.vos_instagram.common.Authenticator;
import com.vangav.vos_instagram.common.Constants;
import com.vangav.vos_instagram.controllers.CommonPlayHandler;

/**
 * GENERATED using ControllersGeneratorMain.java
 */
/**
 * HandlerUpdateLocation
 *   handles request-to-response processing
 *   also handles after response processing (if any)
 * */
public class HandlerUpdateLocation extends CommonPlayHandler {

  private static final String kName = "UpdateLocation";

  @Override
  protected String getName () {

    return kName;
  }

  @Override
  protected RequestJsonBody getRequestJson () {

    return new RequestUpdateLocation();
  }

  @Override
  protected ResponseBody getResponseBody () {

    return new ResponseUpdateLocation();
  }
  
  @Override
  protected Authenticator getRequestAuthenticator (
    final RequestJsonBody requestJsonBody) throws Exception {
    
    return
      new Authenticator(
        ((RequestUpdateLocation)requestJsonBody).device_token,
        ((RequestUpdateLocation)requestJsonBody).user_id,
        ((RequestUpdateLocation)requestJsonBody).access_token);
  }

  @Override
  protected void processRequest (final Request request) throws Exception {

    // use the following request Object to process the request and set
    //   the response to be returned
    RequestUpdateLocation requestUpdateLocation =
      (RequestUpdateLocation)request.getRequestJsonBody();
    
    // get last user's location
    ResultSet resultSet =
      UsersInfo.i().executeSyncSelectLastLocation(
        requestUpdateLocation.getUserId() );
      
    Row row = resultSet.one();
    
    // extract location's coordinates
    double lastLatitude = row.getDouble(UsersInfo.kLastLatitudeColumnName);
    double lastLongitude = row.getDouble(UsersInfo.kLastLongitudeColumnName);
    
    // first time to update location?
    if (lastLatitude == 0.0 && lastLongitude == 0.0) {
      
      // get location's continent code
      ReverseGeoCode reverseGeoCode =
        ReverseGeoCoding.i().getReverseGeoCode(lastLatitude, lastLongitude);
      
      // get user's registration time
      
      resultSet =
        UsersInfo.i().executeSyncSelectRegistrationTime(
          requestUpdateLocation.getUserId() );
      
      long registrationTime =
        resultSet.one().getLong(UsersInfo.kRegistrationTimeColumnName);
      
      Calendar registrationCalendar =
        CalendarAndDateOperationsInl.getCalendarFromUnixTime(
          registrationTime);
      
      // dispatch analytics
      
      // ig_analytics.annual_regional_counters.increment_new_users
      request.getDispatcher().addDispatchMessage(
        AnnualRegionalCounters.i().getQueryDispatchableIncrementNewUsers(
          CalendarFormatterInl.concatCalendarFields(
            registrationCalendar,
            Calendar.YEAR)
            + Constants.kCassandraIdConcat
            + reverseGeoCode.getContinentCode() ) );
      
      // ig_analytics.daily_regional_counters.increment_new_users
      request.getDispatcher().addDispatchMessage(
        DailyRegionalCounters.i().getQueryDispatchableIncrementNewUsers(
          CalendarFormatterInl.concatCalendarFields(
            registrationCalendar,
            Calendar.YEAR,
            Calendar.MONTH,
            Calendar.DAY_OF_MONTH)
            + Constants.kCassandraIdConcat
            + reverseGeoCode.getContinentCode() ) );
    }
    
    // update last_latitude and last_longitude
    UsersInfo.i().executeSyncSetLastLocation(
      requestUpdateLocation.latitude,
      requestUpdateLocation.longitude,
      requestUpdateLocation.getUserId() );
    
    // set response
    ((ResponseUpdateLocation)request.getResponseBody() ).set(
      requestUpdateLocation.request_tracking_id,
      request.getStartTime() );
  }
}
