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

package com.vangav.vos_instagram.controllers.update_facebook_info;

import com.datastax.driver.core.BatchStatement;
import com.datastax.driver.core.BatchStatement.Type;
import com.vangav.backend.cassandra.Cassandra;
import com.vangav.backend.content.formatting.EncodingInl;
import com.vangav.backend.play_framework.request.Request;
import com.vangav.backend.play_framework.request.RequestJsonBody;
import com.vangav.backend.play_framework.request.response.ResponseBody;
import com.vangav.backend.public_apis.facebook.FacebookGraph;
import com.vangav.backend.security.authentication.facebook.FacebookAuthInl;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_app_data.UsersInfo;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_auth.FacebookIds;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_auth.UsersCredIds;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_blobs.ProfilePicturesBlobs;
import com.vangav.vos_instagram.common.Authenticator;
import com.vangav.vos_instagram.common.Constants;
import com.vangav.vos_instagram.controllers.CommonPlayHandler;

/**
 * GENERATED using ControllersGeneratorMain.java
 */
/**
 * HandlerUpdateFacebookInfo
 *   handles request-to-response processing
 *   also handles after response processing (if any)
 * */
public class HandlerUpdateFacebookInfo extends CommonPlayHandler {

  private static final String kName = "UpdateFacebookInfo";

  @Override
  protected String getName () {

    return kName;
  }

  @Override
  protected RequestJsonBody getRequestJson () {

    return new RequestUpdateFacebookInfo();
  }

  @Override
  protected ResponseBody getResponseBody () {

    return new ResponseUpdateFacebookInfo();
  }
  
  @Override
  protected Authenticator getRequestAuthenticator (
    final RequestJsonBody requestJsonBody) throws Exception {
    
    return
      new Authenticator(
        ((RequestUpdateFacebookInfo)requestJsonBody).device_token,
        ((RequestUpdateFacebookInfo)requestJsonBody).user_id,
        ((RequestUpdateFacebookInfo)requestJsonBody).access_token);
  }

  @Override
  protected void processRequest (final Request request) throws Exception {

    // use the following request Object to process the request and set
    //   the response to be returned
    RequestUpdateFacebookInfo requestUpdateFacebookInfo =
      (RequestUpdateFacebookInfo)request.getRequestJsonBody();
    
    // authenticate Facebook's access token
    FacebookAuthInl.validateFacebookAccessToken(
      requestUpdateFacebookInfo.fb_access_token,
      Constants.kFacebookAppId);

    // get user's facebook id
    FacebookGraph facebookGraph =
      new FacebookGraph(requestUpdateFacebookInfo.fb_access_token);
    
    String facebookId = facebookGraph.getUserId();
    
    // get user's info from facebook
    
    // get user's Facebook profile picture
    String profilePicture =
      facebookGraph.getProfilePictureSync(
        Constants.kFacebookProfilePictureDimension);
    
    // database queries
    
    // insert into ig_blobs.profile_pictures_blobs
    ProfilePicturesBlobs.i().executeSyncInsert(
      requestUpdateFacebookInfo.getUserId(),
      EncodingInl.encodeStringIntoByteBuffer(profilePicture) );
    
    try {
      
      // set profile_picture_id in ig_app_data.users_info
      UsersInfo.i().executeSyncSetProfilePictureId(
        requestUpdateFacebookInfo.getUserId(),
        requestUpdateFacebookInfo.getUserId() );
    } catch (Exception e) {
     
      ProfilePicturesBlobs.i().executeSyncDelete(
        requestUpdateFacebookInfo.getUserId() );
      
      throw e;
    }

    // all queries must succeed - ig_auth
    BatchStatement batchStatement = new BatchStatement(Type.LOGGED);
    
    // insert into ig_auth.facebook_ids
    batchStatement.add(
      FacebookIds.i().getBoundStatementInsert(
        facebookId,
        requestUpdateFacebookInfo.getUserId() ) );
    
    // set facebook_id in ig_auth.users_cred_ids
    batchStatement.add(
      UsersCredIds.i().getBoundStatementSetFacebookId(
        facebookId,
        requestUpdateFacebookInfo.getUserId() ) );
    
    // execute batch statement
    Cassandra.i().executeSync(batchStatement);
    
    // set response
    ((ResponseUpdateFacebookInfo)request.getResponseBody() ).set(
      requestUpdateFacebookInfo.request_tracking_id);
  }
}
