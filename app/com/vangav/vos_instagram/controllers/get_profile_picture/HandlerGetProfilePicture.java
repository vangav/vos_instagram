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

package com.vangav.vos_instagram.controllers.get_profile_picture;

import java.nio.ByteBuffer;
import java.util.UUID;

import com.datastax.driver.core.ResultSet;
import com.vangav.backend.content.formatting.EncodingInl;
import com.vangav.backend.exceptions.BadRequestException;
import com.vangav.backend.exceptions.VangavException.ExceptionClass;
import com.vangav.backend.play_framework.request.Request;
import com.vangav.backend.play_framework.request.RequestJsonBody;
import com.vangav.backend.play_framework.request.response.ResponseBody;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_app_data.UsersInfo;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_blobs.ProfilePicturesBlobs;
import com.vangav.vos_instagram.common.Authenticator;
import com.vangav.vos_instagram.common.cassandra.CheckersInl;
import com.vangav.vos_instagram.controllers.CommonPlayHandler;

/**
 * GENERATED using ControllersGeneratorMain.java
 */
/**
 * HandlerGetProfilePicture
 *   handles request-to-response processing
 *   also handles after response processing (if any)
 * */
public class HandlerGetProfilePicture extends CommonPlayHandler {

  private static final String kName = "GetProfilePicture";

  @Override
  protected String getName () {

    return kName;
  }

  @Override
  protected RequestJsonBody getRequestJson () {

    return new RequestGetProfilePicture();
  }

  @Override
  protected ResponseBody getResponseBody () {

    return new ResponseGetProfilePicture();
  }
  
  @Override
  protected Authenticator getRequestAuthenticator (
    final RequestJsonBody requestJsonBody) throws Exception {
    
    return
      new Authenticator(
        ((RequestGetProfilePicture)requestJsonBody).device_token,
        ((RequestGetProfilePicture)requestJsonBody).user_id,
        ((RequestGetProfilePicture)requestJsonBody).access_token);
  }

  @Override
  protected void processRequest (final Request request) throws Exception {

    // use the following request Object to process the request and set
    //   the response to be returned
    RequestGetProfilePicture requestGetProfilePicture =
      (RequestGetProfilePicture)request.getRequestJsonBody();
    
    // set/check get_profile_picture_user_id
    
    UUID getProfilePictureUserId =
      UUID.fromString(requestGetProfilePicture.get_profile_picture_user_id);
    
    if (requestGetProfilePicture.get_profile_picture_user_id.compareTo(
          requestGetProfilePicture.user_id) != 0) {
    
      if (CheckersInl.userIdExists(getProfilePictureUserId) == false) {
        
        throw new BadRequestException(
          414,
          1,
          "User with user_id ["
            + requestGetProfilePicture.get_profile_picture_user_id
            + "] doesn't exist, request issued by user_id ["
            + requestGetProfilePicture.user_id
            + "] from device_token ["
            + requestGetProfilePicture.device_token
            + "]",
          ExceptionClass.INVALID);
      }
    }
    
    // get user's profile_picture_id
    ResultSet resultSet =
      UsersInfo.i().executeSyncSelectProfilePictureId(getProfilePictureUserId);
    
    // no profile picture for this user?
    if (resultSet.isExhausted() == true) {
      
      // set empty response
      ((ResponseGetProfilePicture)request.getResponseBody() ).set(
        requestGetProfilePicture.request_tracking_id,
        "");
      
      return;
    }
    
    // extract profile_picture_id from result
    UUID profilePictureId =
      resultSet.one().getUUID(UsersInfo.kProfilePictureIdColumnName);
    
    // get profile picture
    resultSet =
      ProfilePicturesBlobs.i().executeSyncSelect(profilePictureId);
    
    // extract profile picture
    ByteBuffer profilePictureByteBuffer =
      resultSet.one().getBytes(ProfilePicturesBlobs.kPictureColumnName);
    
    // decode profile picture
    String profilePictureString =
      EncodingInl.decodeStringFromByteBuffer(profilePictureByteBuffer);
    
    // set response
    ((ResponseGetProfilePicture)request.getResponseBody() ).set(
      requestGetProfilePicture.request_tracking_id,
      profilePictureString);
  }
}
