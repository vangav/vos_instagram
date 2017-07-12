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

package com.vangav.vos_instagram.controllers.post_photo;

import java.nio.ByteBuffer;
import java.util.Calendar;
import java.util.UUID;

import com.datastax.driver.core.BatchStatement;
import com.datastax.driver.core.BatchStatement.Type;
import com.vangav.backend.cassandra.Cassandra;
import com.vangav.backend.cassandra.formatting.CalendarFormatterInl;
import com.vangav.backend.content.formatting.EncodingInl;
import com.vangav.backend.content.formatting.SerializationInl;
import com.vangav.backend.exceptions.BadRequestException;
import com.vangav.backend.exceptions.CodeException;
import com.vangav.backend.geo.geo_grids.GeoCoordinates;
import com.vangav.backend.geo.geo_grids.GeoGrid;
import com.vangav.backend.geo.reverse_geo_coding.ReverseGeoCode;
import com.vangav.backend.geo.reverse_geo_coding.ReverseGeoCoding;
import com.vangav.backend.networks.jobs.Job;
import com.vangav.backend.networks.jobs.JobsExecutorInl;
import com.vangav.backend.play_framework.request.Request;
import com.vangav.backend.play_framework.request.RequestJsonBody;
import com.vangav.backend.play_framework.request.RequestState;
import com.vangav.backend.play_framework.request.response.ResponseBody;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_analytics.AnnualRegionalCounters;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_analytics.DailyRegionalCounters;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_app_data.CountPerWeek;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_app_data.Posts;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_app_data.PostsIndex;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_app_data.UserFeedPosts;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_app_data.UserPosts;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_app_data.UserPostsCount;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_blobs.PhotosBlobs;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_blobs.PhotosThumnbnailsBlobs;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_jobs.CurrentJobs;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_jobs.HourlyCurrentJobs;
import com.vangav.vos_instagram.common.Authenticator;
import com.vangav.vos_instagram.common.Constants;
import com.vangav.vos_instagram.common.properties.DispenseProperties;
import com.vangav.vos_instagram.controllers.CommonPlayHandler;

/**
 * GENERATED using ControllersGeneratorMain.java
 */
/**
 * HandlerPostPhoto
 *   handles request-to-response processing
 *   also handles after response processing (if any)
 * */
public class HandlerPostPhoto extends CommonPlayHandler {

  private static final String kName = "PostPhoto";

  @Override
  protected String getName () {

    return kName;
  }

  @Override
  protected RequestJsonBody getRequestJson () {

    return new RequestPostPhoto();
  }

  @Override
  protected ResponseBody getResponseBody () {

    return new ResponsePostPhoto();
  }
  
  @Override
  protected Authenticator getRequestAuthenticator (
    final RequestJsonBody requestJsonBody) throws Exception {
    
    return
      new Authenticator(
        ((RequestPostPhoto)requestJsonBody).device_token,
        ((RequestPostPhoto)requestJsonBody).user_id,
        ((RequestPostPhoto)requestJsonBody).access_token);
  }

  @Override
  protected void processRequest (final Request request) throws Exception {

    // use the following request Object to process the request and set
    //   the response to be returned
    RequestPostPhoto requestPostPhoto =
      (RequestPostPhoto)request.getRequestJsonBody();
    
    // create new post_id (also used as photo_id and photo_thumbnail_id)
    UUID postId = UUID.randomUUID();
    
    // create dispense job using request's uuid and request's time
    
    Job job =
      new Job(
        DispenseProperties.i().getStringPropterty(
          DispenseProperties.kPostPhotoToFollowers) );
    
    job.addParam("user_id", requestPostPhoto.getUserId().toString() );
    job.addParam("post_id", postId.toString() );
    job.addParam("photo_id", postId.toString() );
    job.addParam("post_time", "" + request.getStartTime() );
    job.addParam("job_id", request.getRequestId().toString() );
    
    // serialize job
    String jobSerialized =
      SerializationInl.serializeObject(job);
    
    ByteBuffer jobByteBuffer =
      EncodingInl.encodeStringIntoByteBuffer(jobSerialized);
    
    // insert into ig_jobs
    // all queries must succeed
    BatchStatement batchStatement = new BatchStatement(Type.LOGGED);
    
    // insert into ig_jobs.current_jobs
    batchStatement.add(
      CurrentJobs.i().getBoundStatementInsert(
        request.getRequestId(),
        request.getStartTime(),
        jobByteBuffer) );
    
    // insert into ig_jobs.hourly_current_jobs
    batchStatement.add(
      HourlyCurrentJobs.i().getBoundStatementInsert(
        CalendarFormatterInl.concatCalendarFields(
          request.getStartCalendar(),
          Calendar.YEAR,
          Calendar.MONTH,
          Calendar.DAY_OF_MONTH,
          Calendar.HOUR_OF_DAY),
        request.getStartTime(),
        request.getRequestId() ) );
    
    // execute batch statement
    Cassandra.i().executeSync(batchStatement);
    
    // insert into ig_blobs
    // all queries must succeed
    batchStatement = new BatchStatement(Type.LOGGED);
    
    ByteBuffer photoByteBuffer =
      EncodingInl.encodeStringIntoByteBuffer(requestPostPhoto.photo);
    
    // insert into ig_blobs.photos_blobs
    batchStatement.add(
      PhotosBlobs.i().getBoundStatementInsert(
        postId,
        photoByteBuffer) );
    
    ByteBuffer photoThumbnailByteBuffer =
      EncodingInl.encodeStringIntoByteBuffer(requestPostPhoto.photo_thumbnail);
    
    // insert into ig_blobs.photos_thumbnails_blobs
    batchStatement.add(
      PhotosThumnbnailsBlobs.i().getBoundStatementInsert(
        postId,
        photoThumbnailByteBuffer) );
    
    try {
      
      // execute batch statement
      Cassandra.i().executeSync(batchStatement);
    } catch (Exception e) {
      
      // failed, undo successful queries and re-throw

      // undo ig_jobs

      batchStatement = new BatchStatement(Type.LOGGED);
      
      batchStatement.add(
        CurrentJobs.i().getBoundStatementDelete(
          request.getRequestId() ) );
      
      batchStatement.add(
        HourlyCurrentJobs.i().getBoundStatementDelete(
          CalendarFormatterInl.concatCalendarFields(
            request.getStartCalendar(),
            Calendar.YEAR,
            Calendar.MONTH,
            Calendar.DAY_OF_MONTH,
            Calendar.HOUR_OF_DAY),
          request.getStartTime(),
          request.getRequestId() ) );
      
      Cassandra.i().executeSync(batchStatement);
      
      throw e;
    }
    
    // insert into ig_app_data
    // all queries must succeed
    batchStatement = new BatchStatement(Type.LOGGED);
    
    String caption = "";
    
    if (requestPostPhoto.isValidParam(
          RequestPostPhoto.kCaptionName) == true) {
      
      caption = requestPostPhoto.caption;
    }
    
    // insert into ig_app_data.posts
    if (requestPostPhoto.isValidParam(
          RequestPostPhoto.kLatitudeName) == true &&
        requestPostPhoto.isValidParam(
          RequestPostPhoto.kLatitudeName) == true) {
      
      batchStatement.add(
        Posts.i().getBoundStatementInsert(
          postId,
          request.getStartTime(),
          postId,
          caption,
          requestPostPhoto.getUserId(),
          requestPostPhoto.latitude,
          requestPostPhoto.longitude) );
    } else {
      
      batchStatement.add(
        Posts.i().getBoundStatementInsertWithoutLocation(
          postId,
          request.getStartTime(),
          postId,
          caption,
          requestPostPhoto.getUserId() ) );
    }
    
    long postGridId = Constants.kDefaultGridId;
    
    if (requestPostPhoto.isValidParam(
          RequestPostPhoto.kLatitudeName) == true &&
        requestPostPhoto.isValidParam(
          RequestPostPhoto.kLatitudeName) == true) {
      
      GeoCoordinates postGeoCoordinates =
        new GeoCoordinates(
          requestPostPhoto.latitude,
          requestPostPhoto.longitude);
      
      GeoGrid postGeoGrid =
        new GeoGrid(
          Constants.kGeoGridsConfig,
          postGeoCoordinates);
      
      postGridId = postGeoGrid.getGeoGridId().getId();
    }
    
    // insert into ig_app_data.posts_index
    batchStatement.add(
      PostsIndex.i().getBoundStatementInsert(
        CalendarFormatterInl.concatCalendarFields(
          request.getStartCalendar(),
          Calendar.YEAR,
          Calendar.MONTH,
          Calendar.DAY_OF_MONTH)
        + Constants.kCassandraIdConcat
        + postGridId,
        request.getStartTime(),
        postId) );
    
    // insert into ig_app_data.user_posts
    batchStatement.add(
      UserPosts.i().getBoundStatementInsert(
        requestPostPhoto.getUserId(),
        request.getStartTime(),
        postId) );
    
    // insert into ig_app_data.user_feed_posts
    batchStatement.add(
      UserFeedPosts.i().getBoundStatementInsert(
        requestPostPhoto.getUserId(),
        request.getStartTime(),
        postId) );
    
    try {
      
      // execute batch statement
      Cassandra.i().executeSync(batchStatement);
    } catch (Exception e) {
      
      // failed, undo successful queries and re-throw

      // undo ig_jobs
      
      batchStatement = new BatchStatement(Type.LOGGED);
      
      batchStatement.add(
        CurrentJobs.i().getBoundStatementDelete(
          request.getRequestId() ) );
      
      batchStatement.add(
        HourlyCurrentJobs.i().getBoundStatementDelete(
          CalendarFormatterInl.concatCalendarFields(
            request.getStartCalendar(),
            Calendar.YEAR,
            Calendar.MONTH,
            Calendar.DAY_OF_MONTH,
            Calendar.HOUR_OF_DAY),
          request.getStartTime(),
          request.getRequestId() ) );
      
      Cassandra.i().executeSync(batchStatement);

      // undo ig_blobs
      
      batchStatement = new BatchStatement(Type.LOGGED);
     
      batchStatement.add(
        PhotosBlobs.i().getBoundStatementDelete(
          postId) );
      
      batchStatement.add(
        PhotosThumnbnailsBlobs.i().getBoundStatementDelete(
          postId) );
      
      Cassandra.i().executeSync(batchStatement);
      
      throw e;
    }
    
    // pass job to dispense service
    try {
      
      // non-fatal on failure, jobs service will retry executing the job
      JobsExecutorInl.executeJobsAsync(job);
    } catch (BadRequestException | CodeException ve) {
      
      request.addVangavException(ve);
    } catch (Exception e) {
      
      request.addException(e);
    }
    
    // set response
    ((ResponsePostPhoto)request.getResponseBody() ).set(
      requestPostPhoto.request_tracking_id,
      postId.toString(),
      postId.toString(),
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
    RequestPostPhoto requestPostPhoto =
      (RequestPostPhoto)request.getRequestJsonBody();
    
    // increment ig_app_data.count_per_week
    request.getDispatcher().addDispatchMessage(
      CountPerWeek.i().getQueryDispatchableIncrementPostsCount(
        requestPostPhoto.getUserId().toString()
        + Constants.kCassandraIdConcat
        + CalendarFormatterInl.concatCalendarFields(
          request.getStartCalendar(),
          Calendar.YEAR,
          Calendar.WEEK_OF_YEAR) ) );
    
    // increment ig_app_data.user_posts_count
    request.getDispatcher().addDispatchMessage(
      UserPostsCount.i().getQueryDispatchableIncrement(
        requestPostPhoto.getUserId() ) );
  }

  @Override
  protected void dispatchAnalysis (
    final Request request) throws Exception {
    
    // only process this method if user's request was a success
    if (request.getState() != RequestState.OK) {
      
      return;
    }
    
    // get request's body
    RequestPostPhoto requestPostPhoto =
      (RequestPostPhoto)request.getRequestJsonBody();
    
    // set post's location
    
    String postLocation = Constants.kDefaultRegion;

    if (requestPostPhoto.isValidParam(
          RequestPostPhoto.kLatitudeName) == true &&
        requestPostPhoto.isValidParam(
          RequestPostPhoto.kLatitudeName) == true) {
      
      ReverseGeoCode reverseGeoCode =
        ReverseGeoCoding.i().getReverseGeoCode(
          requestPostPhoto.latitude,
          requestPostPhoto.longitude);
      
      postLocation = reverseGeoCode.getContinentCode();
    }
    
    // increment ig_analytics.annual_regional_counters
    request.getDispatcher().addDispatchMessage(
      AnnualRegionalCounters.i().getQueryDispatchableIncrementNewPosts(
        CalendarFormatterInl.concatCalendarFields(
          request.getStartCalendar(),
          Calendar.YEAR)
          + Constants.kCassandraIdConcat
          + postLocation) );
    
    // increment ig_analytics.annual_regional_counters
    request.getDispatcher().addDispatchMessage(
      AnnualRegionalCounters.i().getQueryDispatchableIncrementNewPosts(
        CalendarFormatterInl.concatCalendarFields(
          request.getStartCalendar(),
          Calendar.YEAR)
          + Constants.kCassandraIdConcat
          + Constants.kWorldRegion) );
    
    // increment ig_analytics.daily_regional_counters
    request.getDispatcher().addDispatchMessage(
      DailyRegionalCounters.i().getQueryDispatchableIncrementNewPosts(
        CalendarFormatterInl.concatCalendarFields(
          request.getStartCalendar(),
          Calendar.YEAR,
          Calendar.MONTH,
          Calendar.DAY_OF_MONTH)
          + Constants.kCassandraIdConcat
          + postLocation) );
    
    // increment ig_analytics.daily_regional_counters
    request.getDispatcher().addDispatchMessage(
      DailyRegionalCounters.i().getQueryDispatchableIncrementNewPosts(
        CalendarFormatterInl.concatCalendarFields(
          request.getStartCalendar(),
          Calendar.YEAR,
          Calendar.MONTH,
          Calendar.DAY_OF_MONTH)
          + Constants.kCassandraIdConcat
          + Constants.kWorldRegion) );
  }
}
