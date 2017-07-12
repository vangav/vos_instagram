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

package com.vangav.vos_instagram.common.cassandra;

import java.util.UUID;

import com.datastax.driver.core.ResultSet;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_app_data.FollowingTime;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_app_data.PostLikesTime;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_app_data.Posts;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_app_data.UsersInfo;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_auth.EmailCreds;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_auth.FacebookIds;

/**
 * @author mustapha
 * fb.com/mustapha.abdallah
 */
/**
 * CheckersInl has inline static methods for checking common items in the
 *   database. Common items are items that need to be checked by multiple
 *   controllers. e.g.: user exists, post exists, ...
 */
public class CheckersInl {

  // disable default instantiation
  private CheckersInl () {}
  
  /**
   * userExists
   * @param userId
   * @return true if user with param userId exists in the database and false
   *           otherwise
   * @throws Exception
   */
  public static boolean userIdExists (UUID userId) throws Exception {
    
    ResultSet resultSet =
      UsersInfo.i().executeSyncSelectName(userId);
    
    if (resultSet.isExhausted() == true) {
      
      return false;
    }
    
    return true;
  }
  
  /**
   * userFacebookIdExists
   * @param facebookId
   * @return true if user with param facebookId exists in the database and
   *           false otherwise
   * @throws Exception
   */
  public static boolean userFacebookIdExists (
    String facebookId) throws Exception {
    
    ResultSet resultSet =
      FacebookIds.i().executeSyncSelect(facebookId);
    
    if (resultSet.isExhausted() == true) {
      
      return false;
    }
    
    return true;
  }
  
  /**
   * userEmailExists
   * @param email
   * @return true if user with param email exists in the database and false
   *           otherwise
   * @throws Exception
   */
  public static boolean userEmailExists (String email) throws Exception {
    
    ResultSet resultSet =
      EmailCreds.i().executeSyncSelect(email);
    
    if (resultSet.isExhausted() == true) {
      
      return false;
    }
    
    return true;
  }
  
  /**
   * postExists
   * @param postId
   * @return true if post with param postId exists in the databaser and false
   *           otherwise
   * @throws Exception
   */
  public static boolean postExists (UUID postId) throws Exception {
    
    ResultSet resultSet = Posts.i().executeSyncSelectTime(postId);
    
    if (resultSet.isExhausted() == true) {
      
      return false;
    }
    
    return true;
  }
  
  /**
   * userLikedPost
   * @param postId
   * @param userId
   * @return true if param userId liked param postId and false otherwise
   * @throws Exception
   */
  public static boolean userLikedPost (
    UUID postId,
    UUID userId) throws Exception {
    
    ResultSet resultSet =
      PostLikesTime.i().executeSyncSelect(
        postId,
        userId);
    
    if (resultSet.isExhausted() == true) {
      
      return false;
    }
    
    return true;
  }
  
  /**
   * isFollowing
   * @param followerUserId
   * @param followedUserId
   * @return true if followerUserId is following followedUserId and false
   *           otherwise
   * @throws Exception
   */
  public static boolean isFollowing (
    UUID followerUserId,
    UUID followedUserId) throws Exception {
    
    ResultSet resultSet =
      FollowingTime.i().executeSyncSelect(
        followerUserId,
        followedUserId);
    
    if (resultSet.isExhausted() == true) {
      
      return false;
    }
    
    return true;
  }
}
