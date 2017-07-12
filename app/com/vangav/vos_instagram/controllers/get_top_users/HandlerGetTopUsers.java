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

package com.vangav.vos_instagram.controllers.get_top_users;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.vangav.backend.cassandra.Cassandra;
import com.vangav.backend.cassandra.formatting.CalendarFormatterInl;
import com.vangav.backend.geo.geo_grids.GeoGrid;
import com.vangav.backend.geo.reverse_geo_coding.ReverseGeoCode;
import com.vangav.backend.play_framework.request.Request;
import com.vangav.backend.play_framework.request.RequestJsonBody;
import com.vangav.backend.play_framework.request.response.ResponseBody;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_app_data.UsersRankCountry;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_app_data.UsersRankGrid;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_app_data.UsersRankWorld;
import com.vangav.vos_instagram.common.Authenticator;
import com.vangav.vos_instagram.common.Constants;
import com.vangav.vos_instagram.common.cassandra.GettersInl;
import com.vangav.vos_instagram.controllers.CommonPlayHandler;

/**
 * GENERATED using ControllersGeneratorMain.java
 */
/**
 * HandlerGetTopUsers
 *   handles request-to-response processing
 *   also handles after response processing (if any)
 * */
public class HandlerGetTopUsers extends CommonPlayHandler {

  private static final String kName = "GetTopUsers";

  @Override
  protected String getName () {

    return kName;
  }

  @Override
  protected RequestJsonBody getRequestJson () {

    return new RequestGetTopUsers();
  }

  @Override
  protected ResponseBody getResponseBody () {

    return new ResponseGetTopUsers();
  }
  
  @Override
  protected Authenticator getRequestAuthenticator (
    final RequestJsonBody requestJsonBody) throws Exception {
    
    return
      new Authenticator(
        ((RequestGetTopUsers)requestJsonBody).device_token,
        ((RequestGetTopUsers)requestJsonBody).user_id,
        ((RequestGetTopUsers)requestJsonBody).access_token);
  }

  @Override
  protected void processRequest (final Request request) throws Exception {

    // use the following request Object to process the request and set
    //   the response to be returned
    RequestGetTopUsers requestGetTopUsers =
      (RequestGetTopUsers)request.getRequestJsonBody();
    
    // get top posts from last week
    Calendar lastWeekCalendar = (Calendar)request.getStartCalendar().clone();
    lastWeekCalendar.set(Calendar.WEEK_OF_YEAR, -1);
    
    // store bound statements
    ArrayList<BoundStatement> boundStatements =
      new ArrayList<BoundStatement>();
    
    // (0) select from ig_app_data.users_rank_grid
    
    ArrayList<Long> geoGridIds = new ArrayList<Long>();
    
    // get user's last geo grid
    GeoGrid userLastGeoGrid =
      GettersInl.getUserLastGeoGrid(requestGetTopUsers.getUserId() );
    
    if (userLastGeoGrid != null) {
      
      // get user's surrounding grids
      ArrayList<GeoGrid> surroundingGrids =
        userLastGeoGrid.getSurroundingGridsLevel(
          1,
          false);
      
      for (GeoGrid geoGrid : surroundingGrids) {
        
        geoGridIds.add(
          geoGrid.getGeoGridId().getId() );
      }
    } else {
      
      // use default grid id
      geoGridIds.add(
        Constants.kDefaultGridId);
    }
    
    // make partition keys using grid ids
    
    ArrayList<String> yearWeekGridIds = new ArrayList<String>();
    
    for (long gridId : geoGridIds) {
      
      yearWeekGridIds.add(
        CalendarFormatterInl.concatCalendarFields(
          lastWeekCalendar,
          Calendar.YEAR,
          Calendar.WEEK_OF_YEAR)
        + Constants.kCassandraIdConcat
        + gridId);
    }
    
    // add select statement
    boundStatements.add(
      UsersRankGrid.i().getBoundStatementSelectGridsTopLimit(
        yearWeekGridIds.toArray(new String[0] ) ) );
    
    // (1) select from ig_app_data.users_rank_country
    
    String userLastCountryCode;
    
    // get user's last reverse geo code
    ReverseGeoCode userLastReverseGeoCode =
      GettersInl.getUserLastReverseGeoCode(requestGetTopUsers.getUserId() );
    
    if (userLastReverseGeoCode != null) {
      
      // get user's last country code
      userLastCountryCode = userLastReverseGeoCode.getCountryCode();
    } else {
      
      // use default region
      userLastCountryCode = Constants.kDefaultRegion;
    }
    
    // make partition key
    String yearWeekCountryCode =
      CalendarFormatterInl.concatCalendarFields(
        lastWeekCalendar,
        Calendar.YEAR,
        Calendar.WEEK_OF_YEAR)
      + Constants.kCassandraIdConcat
      + userLastCountryCode;
    
    // add select statement
    boundStatements.add(
      UsersRankCountry.i().getBoundStatementSelectTopLimit(
        yearWeekCountryCode) );
    
    // (2) select from ig_app_data.users_rank_world
    boundStatements.add(
      UsersRankWorld.i().getBoundStatementSelectTopLimit(
        CalendarFormatterInl.concatCalendarFields(
          lastWeekCalendar,
          Calendar.YEAR,
          Calendar.WEEK_OF_YEAR) ) );
    
    // execute bound statements
    ArrayList<ResultSet> resultSets =
      Cassandra.i().executeSync(
        boundStatements.toArray(new BoundStatement[0] ) );
    
    // to store response's top users ids
    Set<String> topUsersIds = new HashSet<String>();
    
    Row row;
    
    // (0) get from ig_app_data.users_rank_grid
    ResultSet resultSet = resultSets.get(0);
    
    while (resultSet.isExhausted() == false) {
      
      row = resultSet.one();
      
      topUsersIds.add(
        row.getUUID(UsersRankGrid.kUserIdColumnName).toString() );
    }
    
    // (1) get from ig_app_data.users_rank_country
    resultSet = resultSets.get(1);
    
    while (resultSet.isExhausted() == false) {
      
      row = resultSet.one();
      
      topUsersIds.add(
        row.getUUID(UsersRankCountry.kUserIdColumnName).toString() );
    }
    
    // (2) get from ig_app_data.users_rank_world
    resultSet = resultSets.get(2);
    
    while (resultSet.isExhausted() == false) {
      
      row = resultSet.one();
      
      topUsersIds.add(
        row.getUUID(UsersRankWorld.kUserIdColumnName).toString() );
    }
    
    // convert set to list
    ArrayList<String> topUsersIdsList = new ArrayList<String>(topUsersIds);
    
    // shuffle
    Collections.shuffle(topUsersIdsList);
    
    // set response
    ((ResponseGetTopUsers)request.getResponseBody() ).set(
      requestGetTopUsers.request_tracking_id,
      topUsersIdsList.toArray(new String[0] ) );
  }
}
