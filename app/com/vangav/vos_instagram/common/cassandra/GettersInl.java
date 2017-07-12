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

import java.util.Map;
import java.util.UUID;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.vangav.backend.geo.geo_grids.GeoCoordinates;
import com.vangav.backend.geo.geo_grids.GeoGrid;
import com.vangav.backend.geo.reverse_geo_coding.ReverseGeoCode;
import com.vangav.backend.geo.reverse_geo_coding.ReverseGeoCoding;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_app_data.Posts;
import com.vangav.vos_instagram.cassandra_keyspaces.ig_app_data.UsersInfo;
import com.vangav.vos_instagram.common.Constants;

/**
 * @author mustapha
 * fb.com/mustapha.abdallah
 */
/**
 * GettersInl has inline static methods for getting common data from the
 *   database. Common data is data needed by multiple controllers.
 *   e.g.: device tokens, user's name, ...
 */
public class GettersInl {

  // disable default instantiation
  private GettersInl () {}
  
  /**
   * getUserName
   * @param userId
   * @return user's name
   * @throws Exception
   */
  public static String getUserName (UUID userId) throws Exception {
    
    ResultSet resultSet = UsersInfo.i().executeSyncSelectName(userId);
    
    return resultSet.one().getString(UsersInfo.kNameColumnName);
  }
  
  /**
   * getUserDeviceTokens
   * @param userId
   * @return user's device tokens
   * @throws Exception
   */
  public static Map<String, String> getUserDeviceTokens (
    UUID userId) throws Exception {

    ResultSet resultSet =
      UsersInfo.i().executeSyncSelectDeviceTokens(userId);
    
    return
      resultSet.one().getMap(
        UsersInfo.kDeviceTokensColumnName,
        String.class,
        String.class);
  }
  
  /**
   * getUserLastGeoCoordinates
   * @param userId
   * @return user's last geo coordinates and null if it doesn't exist
   * @throws Exception
   */
  public static GeoCoordinates getUserLastGeoCoordinates (
    UUID userId) throws Exception {
    
    ResultSet resultSet =
      UsersInfo.i().executeSyncSelectLastLocation(userId);
    
    if (resultSet.isExhausted() == false) {
      
      Row row = resultSet.one();
      
      double latitude =
        row.getDouble(UsersInfo.kLastLatitudeColumnName);
      double longitude =
        row.getDouble(UsersInfo.kLastLongitudeColumnName);
      
      return new GeoCoordinates(latitude, longitude);
    } else {
      
      return null;
    }
  }
  
  /**
   * getUserLastReverseGeoCode
   * @param userId
   * @return user's last reverse geo code
   * @throws Exception
   */
  public static ReverseGeoCode getUserLastReverseGeoCode (
    UUID userId) throws Exception {
    
    GeoCoordinates geoCoordinates =
      getUserLastGeoCoordinates(userId);
    
    if (geoCoordinates == null) {
      
      return null;
    }
    
    return
      ReverseGeoCoding.i().getReverseGeoCode(
        geoCoordinates.getLatitude(),
        geoCoordinates.getLongitude() );
  }
  
  /**
   * getUserLastGeoGrid
   * @param userId
   * @return user's last geo grid
   * @throws Exception
   */
  public static GeoGrid getUserLastGeoGrid (
    UUID userId) throws Exception {
    
    GeoCoordinates geoCoordinates =
      getUserLastGeoCoordinates(userId);
    
    if (geoCoordinates == null) {
      
      return null;
    }
    
    return
      new GeoGrid(
        Constants.kGeoGridsConfig,
        geoCoordinates);
  }
  
  /**
   * getPostOwnerId
   * @param postId
   * @return post owner's user_id
   * @throws Exception
   */
  public static UUID getPostOwnerId (UUID postId) throws Exception {
    
    ResultSet resultSet = Posts.i().executeSyncSelectUserId(postId);
    
    return resultSet.one().getUUID(Posts.kUserIdColumnName);
  }
}
