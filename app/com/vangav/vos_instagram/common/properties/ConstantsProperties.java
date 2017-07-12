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

package com.vangav.vos_instagram.common.properties;

import java.util.HashMap;
import java.util.Map;

import com.vangav.backend.properties.PropertiesFile;
import com.vangav.backend.properties.PropertiesLoader;

/**
 * @author mustapha
 * fb.com/mustapha.abdallah
 */
/**
 * ConstantsProperties is the properties file with all vos_instagram's
 *   constants
 */
public class ConstantsProperties extends PropertiesFile {

  private static ConstantsProperties instance = null;
  
  // disable default instantiation
  private ConstantsProperties () {}
  
  public static ConstantsProperties i () {
    
    if (instance == null) {
      
      instance = new ConstantsProperties();
    }
    
    return instance;
  }
  
  private static final String kName = "constants_properties.prop";
  
  @Override
  public String getName () {
    
    return kName;
  }
  
  // properties names
  public static final String kAuthCodeLifeTimeValue =
    "auth_code_life_time_value";
  public static final String kAuthCodeLifeTimeUnit =
    "auth_code_life_time_unit";
  public static final String kAccessTokenLifeTimeValue =
    "access_token_life_time_value";
  public static final String kAccessTokenLifeTimeUnit =
    "access_token_life_time_unit";
  public static final String kFacebookAppId =
    "facebook_app_id";
  public static final String kDefaultRegion =
    "default_region";
  public static final String kWorldRegion =
    "world_region";
  public static final String kGeoGridDimensionMetres =
    "geo_grid_dimension_metres";
  public static final String kDefaultGridId =
    "default_grid_id";
  public static final String kThumbnailDimension =
    "thumbnail_dimension";
  
  // property name -> property default value
  private static final Map<String, String> kProperties;
  static {
    
    kProperties = new HashMap<String, String>();

    kProperties.put(
      kAuthCodeLifeTimeValue,
      "10");
    kProperties.put(
      kAuthCodeLifeTimeUnit,
      "MINUTE");
    kProperties.put(
      kAccessTokenLifeTimeValue,
      "1");
    kProperties.put(
      kAccessTokenLifeTimeUnit,
      "WEEK");
    kProperties.put(
      kFacebookAppId,
      "invalid_facebook_app_id");
    kProperties.put(
      kDefaultRegion,
      "Lala_Land");
    kProperties.put(
      kWorldRegion,
      "Planet_Earth");
    kProperties.put(
      kGeoGridDimensionMetres,
      "10000");
    kProperties.put(
      kDefaultGridId,
      "-1");
    kProperties.put(
      kThumbnailDimension,
      "300");
  }
  
  @Override
  protected String getProperty (String name) throws Exception {
    
    return PropertiesLoader.i().getProperty(
      this.getName(),
      name,
      kProperties.get(name) );
  }
}
