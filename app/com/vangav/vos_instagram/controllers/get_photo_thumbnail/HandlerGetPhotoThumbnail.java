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

package com.vangav.vos_instagram.controllers.get_photo_thumbnail;

import java.nio.ByteBuffer;
import java.util.UUID;

import com.datastax.driver.core.ResultSet;
import com.vangav.backend.content.formatting.EncodingInl;
import com.vangav.backend.exceptions.BadRequestException;
import com.vangav.backend.exceptions.VangavException.ExceptionClass;
import com.vangav.backend.play_framework.request.Request;
import com.vangav.backend.play_framework.request.RequestJsonBody;
import com.vangav.backend.play_framework.request.response.ResponseBody;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_blobs.PhotosThumnbnailsBlobs;
import com.vangav.vos_instagram.common.Authenticator;
import com.vangav.vos_instagram.controllers.CommonPlayHandler;

/**
 * GENERATED using ControllersGeneratorMain.java
 */
/**
 * HandlerGetPhotoThumbnail
 *   handles request-to-response processing
 *   also handles after response processing (if any)
 * */
public class HandlerGetPhotoThumbnail extends CommonPlayHandler {

  private static final String kName = "GetPhotoThumbnail";

  @Override
  protected String getName () {

    return kName;
  }

  @Override
  protected RequestJsonBody getRequestJson () {

    return new RequestGetPhotoThumbnail();
  }

  @Override
  protected ResponseBody getResponseBody () {

    return new ResponseGetPhotoThumbnail();
  }
  
  @Override
  protected Authenticator getRequestAuthenticator (
    final RequestJsonBody requestJsonBody) throws Exception {
    
    return
      new Authenticator(
        ((RequestGetPhotoThumbnail)requestJsonBody).device_token,
        ((RequestGetPhotoThumbnail)requestJsonBody).user_id,
        ((RequestGetPhotoThumbnail)requestJsonBody).access_token);
  }

  @Override
  protected void processRequest (final Request request) throws Exception {

    // use the following request Object to process the request and set
    //   the response to be returned
    RequestGetPhotoThumbnail requestGetPhotoThumbnail =
      (RequestGetPhotoThumbnail)request.getRequestJsonBody();
    
    // select photo thumbnail from database
    
    ResultSet resultSet =
      PhotosThumnbnailsBlobs.i().executeSyncSelect(
        UUID.fromString(requestGetPhotoThumbnail.photo_id) );
    
    if (resultSet.isExhausted() == true) {
      
      throw new BadRequestException(
        409,
        1,
        "Photo with photo_id ["
          + requestGetPhotoThumbnail.photo_id
          + "] doesn't exist, request issued by user_id ["
          + requestGetPhotoThumbnail.user_id
          + "] from device_token ["
          + requestGetPhotoThumbnail.device_token
          + "]",
        ExceptionClass.INVALID);
    }
    
    // Extract photo thumbnail
    ByteBuffer photoThumbnailByteBuffer =
      resultSet.one().getBytes(
        PhotosThumnbnailsBlobs.kPhotoThumbnailColumnName);
    
    // decode photo thumbnail
    String photoThumbnailString =
      EncodingInl.decodeStringFromByteBuffer(photoThumbnailByteBuffer);
    
    // set response
    ((ResponseGetPhotoThumbnail)request.getResponseBody() ).set(
      requestGetPhotoThumbnail.request_tracking_id,
      photoThumbnailString);
  }
}
