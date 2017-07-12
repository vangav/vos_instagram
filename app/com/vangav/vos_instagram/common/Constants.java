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

package com.vangav.vos_instagram.common;

import com.vangav.backend.exceptions.CodeException;
import com.vangav.backend.exceptions.VangavException;
import com.vangav.backend.exceptions.VangavException.ExceptionClass;
import com.vangav.backend.geo.geo_grids.GeoGridsConfig;
import com.vangav.backend.metrics.distance.Distance;
import com.vangav.backend.metrics.distance.DistanceUnitType;
import com.vangav.backend.metrics.time.Period;
import com.vangav.backend.metrics.time.TimeUnitType;
import com.vangav.backend.play_framework.request.RequestState;
import com.vangav.vos_instagram.common.properties.ConstantsProperties;

/**
 * @author mustapha
 * fb.com/mustapha.abdallah
 */
/**
 * Constants has vos_instagram's constants
 */
public class Constants {
  
  /**
   * kAuthCodeLifeTime represents the life time of an authorization code
   *   before it expires
   */
  public static final Period kAuthCodeLifeTime;
  static {
    
    try {
      
      kAuthCodeLifeTime =
        new Period(
          ConstantsProperties.i().getDoubleProperty(
            ConstantsProperties.kAuthCodeLifeTimeValue),
          TimeUnitType.valueOf(
            ConstantsProperties.i().getStringPropterty(
              ConstantsProperties.kAuthCodeLifeTimeUnit) ) );
    } catch (Exception e) {
      
      throw new CodeException(
        300,
        1,
        "Couldn't initialize kAuthCodeLifeTime: "
          + VangavException.getExceptionStackTrace(e),
        ExceptionClass.INITIALIZATION);
    }
  }

  /**
   * kAccessTokenLifeTime represents the life time of an access token before
   *   it expires and has to be refreshed
   */
  public static final Period kAccessTokenLifeTime;
  static {
    
    try {
      
      kAccessTokenLifeTime =
        new Period(
          ConstantsProperties.i().getDoubleProperty(
            ConstantsProperties.kAccessTokenLifeTimeValue),
          TimeUnitType.valueOf(
            ConstantsProperties.i().getStringPropterty(
              ConstantsProperties.kAccessTokenLifeTimeUnit) ) );
    } catch (Exception e) {
      
      throw new CodeException(
        300,
        2,
        "Couldn't initialize kAccessTokenLifeTime: "
          + VangavException.getExceptionStackTrace(e),
        ExceptionClass.INITIALIZATION);
    }
  }
  
  /**
   * kFacebookAppId represents facebook's app id, used for facebook-related
   *   operations
   * Only need to be set to a valid value to be able to use facebook-related
   *   functionalities like facebook login
   */
  public static final String kFacebookAppId;
  static {
    
    try {
      
      kFacebookAppId =
        ConstantsProperties.i().getStringPropterty(
          ConstantsProperties.kFacebookAppId);
    } catch (Exception e) {
      
      throw new CodeException(
        300,
        3,
        "Couldn't initialize kFacebookAppId: "
          + VangavException.getExceptionStackTrace(e),
        ExceptionClass.INITIALIZATION);
    }
  }
  
  /**
   * kCassandraIdConcat is the String used to concat multi-part IDs
   *   e.g.: year_month_day
   */
  public static final String kCassandraIdConcat = "_";
  
  /**
   * kDefaultUuid is used when the needed UUID can't be determined
   */
  public static final String kDefaultUuid =
    "00000000-0000-1000-0000-000000000000";
  
  /**
   * kDefaultRegion is used when the needed region can't be determined
   */
  public static final String kDefaultRegion;
  static {
    
    try {
      
      kDefaultRegion =
        ConstantsProperties.i().getStringPropterty(
          ConstantsProperties.kDefaultRegion);
    } catch (Exception e) {
      
      throw new CodeException(
        300,
        4,
        "Couldn't initialize kDefaultRegion: "
          + VangavException.getExceptionStackTrace(e),
        ExceptionClass.INITIALIZATION);
    }
  }
  
  /**
   * kWorldRegion is used to represent the whole world's region
   */
  public static final String kWorldRegion;
  static {
    
    try {
      
      kWorldRegion =
        ConstantsProperties.i().getStringPropterty(
          ConstantsProperties.kWorldRegion);
    } catch (Exception e) {
      
      throw new CodeException(
        300,
        5,
        "Couldn't initialize kWorldRegion: "
          + VangavException.getExceptionStackTrace(e),
        ExceptionClass.INITIALIZATION);
    }
  }
  
  /**
   * kDefaultGridId is used when the needed grid id can't be determined
   */
  public static final long kDefaultGridId;
  static {
    
    try {
      
      kDefaultGridId =
        ConstantsProperties.i().getLongProperty(
          ConstantsProperties.kDefaultGridId);
    } catch (Exception e) {
      
      throw new CodeException(
        300,
        6,
        "Couldn't initialize kDefaultGridId: "
          + VangavException.getExceptionStackTrace(e),
        ExceptionClass.INITIALIZATION);
    }
  }
  
  /**
   * kGeoGridDimensionMetres defines the dimension of geo grids
   */
  public static final double kGeoGridDimensionMetres;
  static {
    
    try {
      
      kGeoGridDimensionMetres =
        ConstantsProperties.i().getDoubleProperty(
          ConstantsProperties.kGeoGridDimensionMetres);
    } catch (Exception e) {
      
      throw new CodeException(
        300,
        7,
        "Couldn't initialize kGeoGridDimensionMetres: "
          + VangavException.getExceptionStackTrace(e),
        ExceptionClass.INITIALIZATION);
    }
  }
  
  /**
   * kGeoGridsConfig defines the geo grids config
   */
  public static final GeoGridsConfig kGeoGridsConfig;
  static {
    
    try {
      
      kGeoGridsConfig =
        new GeoGridsConfig(
          "vos_instagram",
          new Distance(
            kGeoGridDimensionMetres,
            DistanceUnitType.METRE) );
    } catch (Exception e) {
      
      throw new CodeException(
        300,
        8,
        "Couldn't initialize kGeoGridsConfig: "
          + VangavException.getExceptionStackTrace(e),
        ExceptionClass.INITIALIZATION);
    }
  }
  
  public static final String kImagesType = "png";
  
  /**
   * kThumbnailDimension represents a thumbnail's dimension in pixels
   */
  public static final int kThumbnailDimension;
  static {
    
    try {
      
      kThumbnailDimension =
        ConstantsProperties.i().getIntProperty(
          ConstantsProperties.kThumbnailDimension);
    } catch (Exception e) {
      
      throw new CodeException(
        300,
        9,
        "Couldn't initialize kThumbnailDimension: "
          + VangavException.getExceptionStackTrace(e),
        ExceptionClass.INITIALIZATION);
    }
  }
  
  /**
   * kFacebookProfilePictureDimension used to query Facebook Graph API
   *   for users' profile pictures
   */
  public static final int kFacebookProfilePictureDimension = 400;
  
  /**
   * getHttpStatusCode
   * @param requestState
   * @return the HTTP status code int corresponding to param requestState
   * @throws Exception
   */
  public static int getHttpStatusCode (
    RequestState requestState) throws Exception {
    
    if (requestState == RequestState.OK) {
      
      return 200;
    } else if (requestState == RequestState.BAD_REQUEST) {
      
      return 400;
    }
    
    return 500;
  }
  
  public static final String kAppleDeviceTypeName = "apple";
  public static final String kAndroidDeviceTypeName = "android";
  
  public static final int kDefaultGettersCountLimit = 30;
  public static final int kMinGettersCountLimit = 1;
  public static final int kMaxGettersCountLimit = 30;
}
