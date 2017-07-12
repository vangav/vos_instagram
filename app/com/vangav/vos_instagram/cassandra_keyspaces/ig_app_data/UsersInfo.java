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

package com.vangav.vos_instagram.cassandra_keyspaces.ig_app_data;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.ResultSetFuture;
import com.vangav.backend.cassandra.keyspaces.Query;
import com.vangav.backend.cassandra.keyspaces.Table;
import com.vangav.backend.cassandra.keyspaces.dispatch_message.QueryDispatchable;

/**
 * GENERATED using JavaClientGeneratorMain.java
 */
/**
 * UsersInfo represents
 *   Table [users_info]
 *   in Keyspace [ig_app_data]
 * 
 * Name: users_info
 * Description:
 *   used to store users' info 
 * 
 * Columns:
 *   user_id : uuid
 *   name : varchar
 *   device_tokens : map<varchar, varchar>
 *   profile_picture_id : uuid
 *   registration_time : bigint
 *   last_active_time : bigint
 *   last_latitude : double
 *   last_longitude : double

 * Partition Keys: user_id
 * Secondary Keys: 
 * Caching: ALL
 * Order By:

 * Queries:
 *   - Name: insert
 *   Description:
 *     inserts a new user's user_id and name 
 *   Prepared Statement:
 *     INSERT INTO ig_app_data.users_info (user_id, name, 
 *     registration_time, last_active_time) VALUES (:user_id, 
 *     :name, :registration_time, :last_active_time); 
 *   - Name: add_device_token
 *   Description:
 *     adds a user's device_token 
 *   Prepared Statement:
 *     UPDATE ig_app_data.users_info SET device_tokens[:device_token] = 
 *     :phone_type WHERE user_id = :user_id; 
 *   - Name: remove_device_token
 *   Description:
 *     removes a user's device_token 
 *   Prepared Statement:
 *     DELETE device_tokens[:device_tokens] FROM ig_app_data.users_info 
 *     WHERE user_id = :user_id; 
 *   - Name: set_profile_picture_id
 *   Description:
 *     sets a user's profile_picture_id 
 *   Prepared Statement:
 *     UPDATE ig_app_data.users_info SET profile_picture_id = 
 *     :profile_picture_id WHERE user_id = :user_id; 
 *   - Name: set_last_active_time
 *   Description:
 *     sets a user's last_active_time 
 *   Prepared Statement:
 *     UPDATE ig_app_data.users_info SET last_active_time = 
 *     :last_active_time WHERE user_id = :user_id; 
 *   - Name: set_last_location
 *   Description:
 *     sets a user's last_latitude and last_longitude 
 *   Prepared Statement:
 *     UPDATE ig_app_data.users_info SET last_latitude = :last_latitude, 
 *     last_longitude = :last_longitude WHERE user_id = :user_id; 
 *   - Name: select_name
 *   Description:
 *     selects a user's name 
 *   Prepared Statement:
 *     SELECT name FROM ig_app_data.users_info WHERE user_id = :user_id; 
 *   - Name: select_device_tokens
 *   Description:
 *     selects a user's device_tokens 
 *   Prepared Statement:
 *     SELECT device_tokens FROM ig_app_data.users_info WHERE user_id = 
 *     :user_id; 
 *   - Name: select_profile_picture_id
 *   Description:
 *     selects a user's profile_picture_id 
 *   Prepared Statement:
 *     SELECT profile_picture_id FROM ig_app_data.users_info WHERE user_id 
 *     = :user_id; 
 *   - Name: select_registration_time
 *   Description:
 *     selects a user's registration_time 
 *   Prepared Statement:
 *     SELECT registration_time FROM ig_app_data.users_info WHERE user_id 
 *     = :user_id; 
 *   - Name: select_last_active_time
 *   Description:
 *     selects a user's last_active_time 
 *   Prepared Statement:
 *     SELECT last_active_time FROM ig_app_data.users_info WHERE user_id = 
 *     :user_id; 
 *   - Name: select_last_location
 *   Description:
 *     selects a user's last location (latitude and longitude) 
 *   Prepared Statement:
 *     SELECT last_latitude, last_longitude FROM ig_app_data.users_info 
 *     WHERE user_id = :user_id; 
 *   - Name: select_all
 *   Description:
 *     selects all user's info 
 *   Prepared Statement:
 *     SELECT name, device_tokens, profile_picture_id, registration_time, 
 *     last_active_time, last_latitude, last_longitude FROM 
 *     ig_app_data.users_info WHERE user_id = :user_id; 
 * */
public class UsersInfo extends Table {

  private static final String kKeySpaceName =
    "ig_app_data";
  private static final String kTableName =
    "users_info";

  public static final String kUserIdColumnName =
    "user_id";
  public static final String kNameColumnName =
    "name";
  public static final String kDeviceTokensColumnName =
    "device_tokens";
  public static final String kProfilePictureIdColumnName =
    "profile_picture_id";
  public static final String kRegistrationTimeColumnName =
    "registration_time";
  public static final String kLastActiveTimeColumnName =
    "last_active_time";
  public static final String kLastLatitudeColumnName =
    "last_latitude";
  public static final String kLastLongitudeColumnName =
    "last_longitude";

  /**
   * Query:
   * Name: insert
   * Description:
   *   inserts a new user's user_id and name 
   * Prepared Statement:
   *   INSERT INTO ig_app_data.users_info (user_id, name, 
   *   registration_time, last_active_time) VALUES (:user_id, 
   *   :name, :registration_time, :last_active_time); 
   */
  private static final String kInsertName =
    "insert";
  private static final String kInsertDescription =
    "inserts a new user's user_id and name ";
  private static final String kInsertPreparedStatement =
    "INSERT INTO ig_app_data.users_info (user_id, name, registration_time, "
    + "last_active_time) VALUES (:user_id, :name, :registration_time, "
    + ":last_active_time); ";

  /**
   * Query:
   * Name: add_device_token
   * Description:
   *   adds a user's device_token 
   * Prepared Statement:
   *   UPDATE ig_app_data.users_info SET device_tokens[:device_token] = 
   *   :phone_type WHERE user_id = :user_id; 
   */
  private static final String kAddDeviceTokenName =
    "add_device_token";
  private static final String kAddDeviceTokenDescription =
    "adds a user's device_token ";
  private static final String kAddDeviceTokenPreparedStatement =
    "UPDATE ig_app_data.users_info SET device_tokens[:device_token] = "
    + ":phone_type WHERE user_id = :user_id; ";

  /**
   * Query:
   * Name: remove_device_token
   * Description:
   *   removes a user's device_token 
   * Prepared Statement:
   *   DELETE device_tokens[:device_tokens] FROM ig_app_data.users_info 
   *   WHERE user_id = :user_id; 
   */
  private static final String kRemoveDeviceTokenName =
    "remove_device_token";
  private static final String kRemoveDeviceTokenDescription =
    "removes a user's device_token ";
  private static final String kRemoveDeviceTokenPreparedStatement =
    "DELETE device_tokens[:device_tokens] FROM ig_app_data.users_info WHERE "
    + "user_id = :user_id; ";

  /**
   * Query:
   * Name: set_profile_picture_id
   * Description:
   *   sets a user's profile_picture_id 
   * Prepared Statement:
   *   UPDATE ig_app_data.users_info SET profile_picture_id = 
   *   :profile_picture_id WHERE user_id = :user_id; 
   */
  private static final String kSetProfilePictureIdName =
    "set_profile_picture_id";
  private static final String kSetProfilePictureIdDescription =
    "sets a user's profile_picture_id ";
  private static final String kSetProfilePictureIdPreparedStatement =
    "UPDATE ig_app_data.users_info SET profile_picture_id = "
    + ":profile_picture_id WHERE user_id = :user_id; ";

  /**
   * Query:
   * Name: set_last_active_time
   * Description:
   *   sets a user's last_active_time 
   * Prepared Statement:
   *   UPDATE ig_app_data.users_info SET last_active_time = 
   *   :last_active_time WHERE user_id = :user_id; 
   */
  private static final String kSetLastActiveTimeName =
    "set_last_active_time";
  private static final String kSetLastActiveTimeDescription =
    "sets a user's last_active_time ";
  private static final String kSetLastActiveTimePreparedStatement =
    "UPDATE ig_app_data.users_info SET last_active_time = :last_active_time "
    + "WHERE user_id = :user_id; ";

  /**
   * Query:
   * Name: set_last_location
   * Description:
   *   sets a user's last_latitude and last_longitude 
   * Prepared Statement:
   *   UPDATE ig_app_data.users_info SET last_latitude = :last_latitude, 
   *   last_longitude = :last_longitude WHERE user_id = :user_id; 
   */
  private static final String kSetLastLocationName =
    "set_last_location";
  private static final String kSetLastLocationDescription =
    "sets a user's last_latitude and last_longitude ";
  private static final String kSetLastLocationPreparedStatement =
    "UPDATE ig_app_data.users_info SET last_latitude = :last_latitude, "
    + "last_longitude = :last_longitude WHERE user_id = :user_id; ";

  /**
   * Query:
   * Name: select_name
   * Description:
   *   selects a user's name 
   * Prepared Statement:
   *   SELECT name FROM ig_app_data.users_info WHERE user_id = :user_id; 
   */
  private static final String kSelectNameName =
    "select_name";
  private static final String kSelectNameDescription =
    "selects a user's name ";
  private static final String kSelectNamePreparedStatement =
    "SELECT name FROM ig_app_data.users_info WHERE user_id = :user_id; ";

  /**
   * Query:
   * Name: select_device_tokens
   * Description:
   *   selects a user's device_tokens 
   * Prepared Statement:
   *   SELECT device_tokens FROM ig_app_data.users_info WHERE user_id = 
   *   :user_id; 
   */
  private static final String kSelectDeviceTokensName =
    "select_device_tokens";
  private static final String kSelectDeviceTokensDescription =
    "selects a user's device_tokens ";
  private static final String kSelectDeviceTokensPreparedStatement =
    "SELECT device_tokens FROM ig_app_data.users_info WHERE user_id = "
    + ":user_id; ";

  /**
   * Query:
   * Name: select_profile_picture_id
   * Description:
   *   selects a user's profile_picture_id 
   * Prepared Statement:
   *   SELECT profile_picture_id FROM ig_app_data.users_info WHERE user_id 
   *   = :user_id; 
   */
  private static final String kSelectProfilePictureIdName =
    "select_profile_picture_id";
  private static final String kSelectProfilePictureIdDescription =
    "selects a user's profile_picture_id ";
  private static final String kSelectProfilePictureIdPreparedStatement =
    "SELECT profile_picture_id FROM ig_app_data.users_info WHERE user_id = "
    + ":user_id; ";

  /**
   * Query:
   * Name: select_registration_time
   * Description:
   *   selects a user's registration_time 
   * Prepared Statement:
   *   SELECT registration_time FROM ig_app_data.users_info WHERE user_id 
   *   = :user_id; 
   */
  private static final String kSelectRegistrationTimeName =
    "select_registration_time";
  private static final String kSelectRegistrationTimeDescription =
    "selects a user's registration_time ";
  private static final String kSelectRegistrationTimePreparedStatement =
    "SELECT registration_time FROM ig_app_data.users_info WHERE user_id = "
    + ":user_id; ";

  /**
   * Query:
   * Name: select_last_active_time
   * Description:
   *   selects a user's last_active_time 
   * Prepared Statement:
   *   SELECT last_active_time FROM ig_app_data.users_info WHERE user_id = 
   *   :user_id; 
   */
  private static final String kSelectLastActiveTimeName =
    "select_last_active_time";
  private static final String kSelectLastActiveTimeDescription =
    "selects a user's last_active_time ";
  private static final String kSelectLastActiveTimePreparedStatement =
    "SELECT last_active_time FROM ig_app_data.users_info WHERE user_id = "
    + ":user_id; ";

  /**
   * Query:
   * Name: select_last_location
   * Description:
   *   selects a user's last location (latitude and longitude) 
   * Prepared Statement:
   *   SELECT last_latitude, last_longitude FROM ig_app_data.users_info 
   *   WHERE user_id = :user_id; 
   */
  private static final String kSelectLastLocationName =
    "select_last_location";
  private static final String kSelectLastLocationDescription =
    "selects a user's last location (latitude and longitude) ";
  private static final String kSelectLastLocationPreparedStatement =
    "SELECT last_latitude, last_longitude FROM ig_app_data.users_info WHERE "
    + "user_id = :user_id; ";

  /**
   * Query:
   * Name: select_all
   * Description:
   *   selects all user's info 
   * Prepared Statement:
   *   SELECT name, device_tokens, profile_picture_id, registration_time, 
   *   last_active_time, last_latitude, last_longitude FROM 
   *   ig_app_data.users_info WHERE user_id = :user_id; 
   */
  private static final String kSelectAllName =
    "select_all";
  private static final String kSelectAllDescription =
    "selects all user's info ";
  private static final String kSelectAllPreparedStatement =
    "SELECT name, device_tokens, profile_picture_id, registration_time, "
    + "last_active_time, last_latitude, last_longitude FROM "
    + "ig_app_data.users_info WHERE user_id = :user_id; ";

  /**
   * Constructor UsersInfo
   * @return new UsersInfo Object
   * @throws Exception
   */
  private UsersInfo () throws Exception {

    super (
      kKeySpaceName,
      kTableName,
      new Query (
        kInsertDescription,
        kInsertName,
        kInsertPreparedStatement),
      new Query (
        kAddDeviceTokenDescription,
        kAddDeviceTokenName,
        kAddDeviceTokenPreparedStatement),
      new Query (
        kRemoveDeviceTokenDescription,
        kRemoveDeviceTokenName,
        kRemoveDeviceTokenPreparedStatement),
      new Query (
        kSetProfilePictureIdDescription,
        kSetProfilePictureIdName,
        kSetProfilePictureIdPreparedStatement),
      new Query (
        kSetLastActiveTimeDescription,
        kSetLastActiveTimeName,
        kSetLastActiveTimePreparedStatement),
      new Query (
        kSetLastLocationDescription,
        kSetLastLocationName,
        kSetLastLocationPreparedStatement),
      new Query (
        kSelectNameDescription,
        kSelectNameName,
        kSelectNamePreparedStatement),
      new Query (
        kSelectDeviceTokensDescription,
        kSelectDeviceTokensName,
        kSelectDeviceTokensPreparedStatement),
      new Query (
        kSelectProfilePictureIdDescription,
        kSelectProfilePictureIdName,
        kSelectProfilePictureIdPreparedStatement),
      new Query (
        kSelectRegistrationTimeDescription,
        kSelectRegistrationTimeName,
        kSelectRegistrationTimePreparedStatement),
      new Query (
        kSelectLastActiveTimeDescription,
        kSelectLastActiveTimeName,
        kSelectLastActiveTimePreparedStatement),
      new Query (
        kSelectLastLocationDescription,
        kSelectLastLocationName,
        kSelectLastLocationPreparedStatement),
      new Query (
        kSelectAllDescription,
        kSelectAllName,
        kSelectAllPreparedStatement));
  }

  private static UsersInfo instance = null;

  /**
   * loadTable
   * OPTIONAL method
   * instance is created either upon calling this method or upon the first call
   *   to singleton instance method i
   * this method is useful for loading upon program start instead of loading
   *   it upon the first use since there's a small time overhead for loading
   *   since all queries are prepared synchronously in a blocking network
   *   operation with Cassandra's server
   * @throws Exception
   */
  public static void loadTable () throws Exception {

    if (instance == null) {

      instance = new UsersInfo();
    }
  }

  /**
   * i
   * @return singleton static instance of UsersInfo
   * @throws Exception
   */
  public static UsersInfo i () throws Exception {

    if (instance == null) {

      instance = new UsersInfo();
    }

    return instance;
  }

  // Query: Insert
  // Description:
  //   inserts a new user's user_id and name 
  // Parepared Statement:
  //   INSERT INTO ig_app_data.users_info (user_id, name, 
  //   registration_time, last_active_time) VALUES (:user_id, 
  //   :name, :registration_time, :last_active_time); 

  /**
   * getQueryInsert
   * @return Insert Query in the form of
   *           a Query Object
   * @throws Exception
   */
  public Query getQueryInsert (
    ) throws Exception {

    return this.getQuery(kInsertName);
  }

  /**
   * getQueryDispatchableInsert
   * @param userid
   * @param name
   * @param registrationtime
   * @param lastactivetime
   * @return Insert Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableInsert (
    Object userid,
    Object name,
    Object registrationtime,
    Object lastactivetime) throws Exception {

    return
      this.getQueryDispatchable(
        kInsertName,
        userid,
        name,
        registrationtime,
        lastactivetime);
  }

  /**
   * getBoundStatementInsert
   * @param userid
   * @param name
   * @param registrationtime
   * @param lastactivetime
   * @return Insert Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementInsert (
    Object userid,
    Object name,
    Object registrationtime,
    Object lastactivetime) throws Exception {

    return
      this.getQuery(kInsertName).getBoundStatement(
        userid,
        name,
        registrationtime,
        lastactivetime);
  }

  /**
   * executeAsyncInsert
   * executes Insert Query asynchronously
   * @param userid
   * @param name
   * @param registrationtime
   * @param lastactivetime
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncInsert (
    Object userid,
    Object name,
    Object registrationtime,
    Object lastactivetime) throws Exception {

    return
      this.getQuery(kInsertName).executeAsync(
        userid,
        name,
        registrationtime,
        lastactivetime);
  }

  /**
   * executeSyncInsert
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes Insert Query synchronously
   * @param userid
   * @param name
   * @param registrationtime
   * @param lastactivetime
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncInsert (
    Object userid,
    Object name,
    Object registrationtime,
    Object lastactivetime) throws Exception {

    return
      this.getQuery(kInsertName).executeSync(
        userid,
        name,
        registrationtime,
        lastactivetime);
  }

  // Query: AddDeviceToken
  // Description:
  //   adds a user's device_token 
  // Parepared Statement:
  //   UPDATE ig_app_data.users_info SET device_tokens[:device_token] = 
  //   :phone_type WHERE user_id = :user_id; 

  /**
   * getQueryAddDeviceToken
   * @return AddDeviceToken Query in the form of
   *           a Query Object
   * @throws Exception
   */
  public Query getQueryAddDeviceToken (
    ) throws Exception {

    return this.getQuery(kAddDeviceTokenName);
  }

  /**
   * getQueryDispatchableAddDeviceToken
   * @param devicetoken
   * @param phonetype
   * @param userid
   * @return AddDeviceToken Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableAddDeviceToken (
    Object devicetoken,
    Object phonetype,
    Object userid) throws Exception {

    return
      this.getQueryDispatchable(
        kAddDeviceTokenName,
        devicetoken,
        phonetype,
        userid);
  }

  /**
   * getBoundStatementAddDeviceToken
   * @param devicetoken
   * @param phonetype
   * @param userid
   * @return AddDeviceToken Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementAddDeviceToken (
    Object devicetoken,
    Object phonetype,
    Object userid) throws Exception {

    return
      this.getQuery(kAddDeviceTokenName).getBoundStatement(
        devicetoken,
        phonetype,
        userid);
  }

  /**
   * executeAsyncAddDeviceToken
   * executes AddDeviceToken Query asynchronously
   * @param devicetoken
   * @param phonetype
   * @param userid
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncAddDeviceToken (
    Object devicetoken,
    Object phonetype,
    Object userid) throws Exception {

    return
      this.getQuery(kAddDeviceTokenName).executeAsync(
        devicetoken,
        phonetype,
        userid);
  }

  /**
   * executeSyncAddDeviceToken
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes AddDeviceToken Query synchronously
   * @param devicetoken
   * @param phonetype
   * @param userid
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncAddDeviceToken (
    Object devicetoken,
    Object phonetype,
    Object userid) throws Exception {

    return
      this.getQuery(kAddDeviceTokenName).executeSync(
        devicetoken,
        phonetype,
        userid);
  }

  // Query: RemoveDeviceToken
  // Description:
  //   removes a user's device_token 
  // Parepared Statement:
  //   DELETE device_tokens[:device_tokens] FROM ig_app_data.users_info 
  //   WHERE user_id = :user_id; 

  /**
   * getQueryRemoveDeviceToken
   * @return RemoveDeviceToken Query in the form of
   *           a Query Object
   * @throws Exception
   */
  public Query getQueryRemoveDeviceToken (
    ) throws Exception {

    return this.getQuery(kRemoveDeviceTokenName);
  }

  /**
   * getQueryDispatchableRemoveDeviceToken
   * @param devicetokens
   * @param userid
   * @return RemoveDeviceToken Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableRemoveDeviceToken (
    Object devicetokens,
    Object userid) throws Exception {

    return
      this.getQueryDispatchable(
        kRemoveDeviceTokenName,
        devicetokens,
        userid);
  }

  /**
   * getBoundStatementRemoveDeviceToken
   * @param devicetokens
   * @param userid
   * @return RemoveDeviceToken Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementRemoveDeviceToken (
    Object devicetokens,
    Object userid) throws Exception {

    return
      this.getQuery(kRemoveDeviceTokenName).getBoundStatement(
        devicetokens,
        userid);
  }

  /**
   * executeAsyncRemoveDeviceToken
   * executes RemoveDeviceToken Query asynchronously
   * @param devicetokens
   * @param userid
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncRemoveDeviceToken (
    Object devicetokens,
    Object userid) throws Exception {

    return
      this.getQuery(kRemoveDeviceTokenName).executeAsync(
        devicetokens,
        userid);
  }

  /**
   * executeSyncRemoveDeviceToken
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes RemoveDeviceToken Query synchronously
   * @param devicetokens
   * @param userid
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncRemoveDeviceToken (
    Object devicetokens,
    Object userid) throws Exception {

    return
      this.getQuery(kRemoveDeviceTokenName).executeSync(
        devicetokens,
        userid);
  }

  // Query: SetProfilePictureId
  // Description:
  //   sets a user's profile_picture_id 
  // Parepared Statement:
  //   UPDATE ig_app_data.users_info SET profile_picture_id = 
  //   :profile_picture_id WHERE user_id = :user_id; 

  /**
   * getQuerySetProfilePictureId
   * @return SetProfilePictureId Query in the form of
   *           a Query Object
   * @throws Exception
   */
  public Query getQuerySetProfilePictureId (
    ) throws Exception {

    return this.getQuery(kSetProfilePictureIdName);
  }

  /**
   * getQueryDispatchableSetProfilePictureId
   * @param profilepictureid
   * @param userid
   * @return SetProfilePictureId Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableSetProfilePictureId (
    Object profilepictureid,
    Object userid) throws Exception {

    return
      this.getQueryDispatchable(
        kSetProfilePictureIdName,
        profilepictureid,
        userid);
  }

  /**
   * getBoundStatementSetProfilePictureId
   * @param profilepictureid
   * @param userid
   * @return SetProfilePictureId Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementSetProfilePictureId (
    Object profilepictureid,
    Object userid) throws Exception {

    return
      this.getQuery(kSetProfilePictureIdName).getBoundStatement(
        profilepictureid,
        userid);
  }

  /**
   * executeAsyncSetProfilePictureId
   * executes SetProfilePictureId Query asynchronously
   * @param profilepictureid
   * @param userid
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncSetProfilePictureId (
    Object profilepictureid,
    Object userid) throws Exception {

    return
      this.getQuery(kSetProfilePictureIdName).executeAsync(
        profilepictureid,
        userid);
  }

  /**
   * executeSyncSetProfilePictureId
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes SetProfilePictureId Query synchronously
   * @param profilepictureid
   * @param userid
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncSetProfilePictureId (
    Object profilepictureid,
    Object userid) throws Exception {

    return
      this.getQuery(kSetProfilePictureIdName).executeSync(
        profilepictureid,
        userid);
  }

  // Query: SetLastActiveTime
  // Description:
  //   sets a user's last_active_time 
  // Parepared Statement:
  //   UPDATE ig_app_data.users_info SET last_active_time = 
  //   :last_active_time WHERE user_id = :user_id; 

  /**
   * getQuerySetLastActiveTime
   * @return SetLastActiveTime Query in the form of
   *           a Query Object
   * @throws Exception
   */
  public Query getQuerySetLastActiveTime (
    ) throws Exception {

    return this.getQuery(kSetLastActiveTimeName);
  }

  /**
   * getQueryDispatchableSetLastActiveTime
   * @param lastactivetime
   * @param userid
   * @return SetLastActiveTime Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableSetLastActiveTime (
    Object lastactivetime,
    Object userid) throws Exception {

    return
      this.getQueryDispatchable(
        kSetLastActiveTimeName,
        lastactivetime,
        userid);
  }

  /**
   * getBoundStatementSetLastActiveTime
   * @param lastactivetime
   * @param userid
   * @return SetLastActiveTime Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementSetLastActiveTime (
    Object lastactivetime,
    Object userid) throws Exception {

    return
      this.getQuery(kSetLastActiveTimeName).getBoundStatement(
        lastactivetime,
        userid);
  }

  /**
   * executeAsyncSetLastActiveTime
   * executes SetLastActiveTime Query asynchronously
   * @param lastactivetime
   * @param userid
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncSetLastActiveTime (
    Object lastactivetime,
    Object userid) throws Exception {

    return
      this.getQuery(kSetLastActiveTimeName).executeAsync(
        lastactivetime,
        userid);
  }

  /**
   * executeSyncSetLastActiveTime
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes SetLastActiveTime Query synchronously
   * @param lastactivetime
   * @param userid
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncSetLastActiveTime (
    Object lastactivetime,
    Object userid) throws Exception {

    return
      this.getQuery(kSetLastActiveTimeName).executeSync(
        lastactivetime,
        userid);
  }

  // Query: SetLastLocation
  // Description:
  //   sets a user's last_latitude and last_longitude 
  // Parepared Statement:
  //   UPDATE ig_app_data.users_info SET last_latitude = :last_latitude, 
  //   last_longitude = :last_longitude WHERE user_id = :user_id; 

  /**
   * getQuerySetLastLocation
   * @return SetLastLocation Query in the form of
   *           a Query Object
   * @throws Exception
   */
  public Query getQuerySetLastLocation (
    ) throws Exception {

    return this.getQuery(kSetLastLocationName);
  }

  /**
   * getQueryDispatchableSetLastLocation
   * @param lastlatitude
   * @param lastlongitude
   * @param userid
   * @return SetLastLocation Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableSetLastLocation (
    Object lastlatitude,
    Object lastlongitude,
    Object userid) throws Exception {

    return
      this.getQueryDispatchable(
        kSetLastLocationName,
        lastlatitude,
        lastlongitude,
        userid);
  }

  /**
   * getBoundStatementSetLastLocation
   * @param lastlatitude
   * @param lastlongitude
   * @param userid
   * @return SetLastLocation Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementSetLastLocation (
    Object lastlatitude,
    Object lastlongitude,
    Object userid) throws Exception {

    return
      this.getQuery(kSetLastLocationName).getBoundStatement(
        lastlatitude,
        lastlongitude,
        userid);
  }

  /**
   * executeAsyncSetLastLocation
   * executes SetLastLocation Query asynchronously
   * @param lastlatitude
   * @param lastlongitude
   * @param userid
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncSetLastLocation (
    Object lastlatitude,
    Object lastlongitude,
    Object userid) throws Exception {

    return
      this.getQuery(kSetLastLocationName).executeAsync(
        lastlatitude,
        lastlongitude,
        userid);
  }

  /**
   * executeSyncSetLastLocation
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes SetLastLocation Query synchronously
   * @param lastlatitude
   * @param lastlongitude
   * @param userid
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncSetLastLocation (
    Object lastlatitude,
    Object lastlongitude,
    Object userid) throws Exception {

    return
      this.getQuery(kSetLastLocationName).executeSync(
        lastlatitude,
        lastlongitude,
        userid);
  }

  // Query: SelectName
  // Description:
  //   selects a user's name 
  // Parepared Statement:
  //   SELECT name FROM ig_app_data.users_info WHERE user_id = :user_id; 

  /**
   * getQuerySelectName
   * @return SelectName Query in the form of
   *           a Query Object
   * @throws Exception
   */
  public Query getQuerySelectName (
    ) throws Exception {

    return this.getQuery(kSelectNameName);
  }

  /**
   * getQueryDispatchableSelectName
   * @param userid
   * @return SelectName Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableSelectName (
    Object userid) throws Exception {

    return
      this.getQueryDispatchable(
        kSelectNameName,
        userid);
  }

  /**
   * getBoundStatementSelectName
   * @param userid
   * @return SelectName Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementSelectName (
    Object userid) throws Exception {

    return
      this.getQuery(kSelectNameName).getBoundStatement(
        userid);
  }

  /**
   * executeAsyncSelectName
   * executes SelectName Query asynchronously
   * @param userid
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncSelectName (
    Object userid) throws Exception {

    return
      this.getQuery(kSelectNameName).executeAsync(
        userid);
  }

  /**
   * executeSyncSelectName
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes SelectName Query synchronously
   * @param userid
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncSelectName (
    Object userid) throws Exception {

    return
      this.getQuery(kSelectNameName).executeSync(
        userid);
  }

  // Query: SelectDeviceTokens
  // Description:
  //   selects a user's device_tokens 
  // Parepared Statement:
  //   SELECT device_tokens FROM ig_app_data.users_info WHERE user_id = 
  //   :user_id; 

  /**
   * getQuerySelectDeviceTokens
   * @return SelectDeviceTokens Query in the form of
   *           a Query Object
   * @throws Exception
   */
  public Query getQuerySelectDeviceTokens (
    ) throws Exception {

    return this.getQuery(kSelectDeviceTokensName);
  }

  /**
   * getQueryDispatchableSelectDeviceTokens
   * @param userid
   * @return SelectDeviceTokens Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableSelectDeviceTokens (
    Object userid) throws Exception {

    return
      this.getQueryDispatchable(
        kSelectDeviceTokensName,
        userid);
  }

  /**
   * getBoundStatementSelectDeviceTokens
   * @param userid
   * @return SelectDeviceTokens Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementSelectDeviceTokens (
    Object userid) throws Exception {

    return
      this.getQuery(kSelectDeviceTokensName).getBoundStatement(
        userid);
  }

  /**
   * executeAsyncSelectDeviceTokens
   * executes SelectDeviceTokens Query asynchronously
   * @param userid
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncSelectDeviceTokens (
    Object userid) throws Exception {

    return
      this.getQuery(kSelectDeviceTokensName).executeAsync(
        userid);
  }

  /**
   * executeSyncSelectDeviceTokens
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes SelectDeviceTokens Query synchronously
   * @param userid
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncSelectDeviceTokens (
    Object userid) throws Exception {

    return
      this.getQuery(kSelectDeviceTokensName).executeSync(
        userid);
  }

  // Query: SelectProfilePictureId
  // Description:
  //   selects a user's profile_picture_id 
  // Parepared Statement:
  //   SELECT profile_picture_id FROM ig_app_data.users_info WHERE user_id 
  //   = :user_id; 

  /**
   * getQuerySelectProfilePictureId
   * @return SelectProfilePictureId Query in the form of
   *           a Query Object
   * @throws Exception
   */
  public Query getQuerySelectProfilePictureId (
    ) throws Exception {

    return this.getQuery(kSelectProfilePictureIdName);
  }

  /**
   * getQueryDispatchableSelectProfilePictureId
   * @param userid
   * @return SelectProfilePictureId Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableSelectProfilePictureId (
    Object userid) throws Exception {

    return
      this.getQueryDispatchable(
        kSelectProfilePictureIdName,
        userid);
  }

  /**
   * getBoundStatementSelectProfilePictureId
   * @param userid
   * @return SelectProfilePictureId Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementSelectProfilePictureId (
    Object userid) throws Exception {

    return
      this.getQuery(kSelectProfilePictureIdName).getBoundStatement(
        userid);
  }

  /**
   * executeAsyncSelectProfilePictureId
   * executes SelectProfilePictureId Query asynchronously
   * @param userid
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncSelectProfilePictureId (
    Object userid) throws Exception {

    return
      this.getQuery(kSelectProfilePictureIdName).executeAsync(
        userid);
  }

  /**
   * executeSyncSelectProfilePictureId
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes SelectProfilePictureId Query synchronously
   * @param userid
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncSelectProfilePictureId (
    Object userid) throws Exception {

    return
      this.getQuery(kSelectProfilePictureIdName).executeSync(
        userid);
  }

  // Query: SelectRegistrationTime
  // Description:
  //   selects a user's registration_time 
  // Parepared Statement:
  //   SELECT registration_time FROM ig_app_data.users_info WHERE user_id 
  //   = :user_id; 

  /**
   * getQuerySelectRegistrationTime
   * @return SelectRegistrationTime Query in the form of
   *           a Query Object
   * @throws Exception
   */
  public Query getQuerySelectRegistrationTime (
    ) throws Exception {

    return this.getQuery(kSelectRegistrationTimeName);
  }

  /**
   * getQueryDispatchableSelectRegistrationTime
   * @param userid
   * @return SelectRegistrationTime Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableSelectRegistrationTime (
    Object userid) throws Exception {

    return
      this.getQueryDispatchable(
        kSelectRegistrationTimeName,
        userid);
  }

  /**
   * getBoundStatementSelectRegistrationTime
   * @param userid
   * @return SelectRegistrationTime Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementSelectRegistrationTime (
    Object userid) throws Exception {

    return
      this.getQuery(kSelectRegistrationTimeName).getBoundStatement(
        userid);
  }

  /**
   * executeAsyncSelectRegistrationTime
   * executes SelectRegistrationTime Query asynchronously
   * @param userid
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncSelectRegistrationTime (
    Object userid) throws Exception {

    return
      this.getQuery(kSelectRegistrationTimeName).executeAsync(
        userid);
  }

  /**
   * executeSyncSelectRegistrationTime
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes SelectRegistrationTime Query synchronously
   * @param userid
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncSelectRegistrationTime (
    Object userid) throws Exception {

    return
      this.getQuery(kSelectRegistrationTimeName).executeSync(
        userid);
  }

  // Query: SelectLastActiveTime
  // Description:
  //   selects a user's last_active_time 
  // Parepared Statement:
  //   SELECT last_active_time FROM ig_app_data.users_info WHERE user_id = 
  //   :user_id; 

  /**
   * getQuerySelectLastActiveTime
   * @return SelectLastActiveTime Query in the form of
   *           a Query Object
   * @throws Exception
   */
  public Query getQuerySelectLastActiveTime (
    ) throws Exception {

    return this.getQuery(kSelectLastActiveTimeName);
  }

  /**
   * getQueryDispatchableSelectLastActiveTime
   * @param userid
   * @return SelectLastActiveTime Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableSelectLastActiveTime (
    Object userid) throws Exception {

    return
      this.getQueryDispatchable(
        kSelectLastActiveTimeName,
        userid);
  }

  /**
   * getBoundStatementSelectLastActiveTime
   * @param userid
   * @return SelectLastActiveTime Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementSelectLastActiveTime (
    Object userid) throws Exception {

    return
      this.getQuery(kSelectLastActiveTimeName).getBoundStatement(
        userid);
  }

  /**
   * executeAsyncSelectLastActiveTime
   * executes SelectLastActiveTime Query asynchronously
   * @param userid
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncSelectLastActiveTime (
    Object userid) throws Exception {

    return
      this.getQuery(kSelectLastActiveTimeName).executeAsync(
        userid);
  }

  /**
   * executeSyncSelectLastActiveTime
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes SelectLastActiveTime Query synchronously
   * @param userid
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncSelectLastActiveTime (
    Object userid) throws Exception {

    return
      this.getQuery(kSelectLastActiveTimeName).executeSync(
        userid);
  }

  // Query: SelectLastLocation
  // Description:
  //   selects a user's last location (latitude and longitude) 
  // Parepared Statement:
  //   SELECT last_latitude, last_longitude FROM ig_app_data.users_info 
  //   WHERE user_id = :user_id; 

  /**
   * getQuerySelectLastLocation
   * @return SelectLastLocation Query in the form of
   *           a Query Object
   * @throws Exception
   */
  public Query getQuerySelectLastLocation (
    ) throws Exception {

    return this.getQuery(kSelectLastLocationName);
  }

  /**
   * getQueryDispatchableSelectLastLocation
   * @param userid
   * @return SelectLastLocation Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableSelectLastLocation (
    Object userid) throws Exception {

    return
      this.getQueryDispatchable(
        kSelectLastLocationName,
        userid);
  }

  /**
   * getBoundStatementSelectLastLocation
   * @param userid
   * @return SelectLastLocation Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementSelectLastLocation (
    Object userid) throws Exception {

    return
      this.getQuery(kSelectLastLocationName).getBoundStatement(
        userid);
  }

  /**
   * executeAsyncSelectLastLocation
   * executes SelectLastLocation Query asynchronously
   * @param userid
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncSelectLastLocation (
    Object userid) throws Exception {

    return
      this.getQuery(kSelectLastLocationName).executeAsync(
        userid);
  }

  /**
   * executeSyncSelectLastLocation
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes SelectLastLocation Query synchronously
   * @param userid
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncSelectLastLocation (
    Object userid) throws Exception {

    return
      this.getQuery(kSelectLastLocationName).executeSync(
        userid);
  }

  // Query: SelectAll
  // Description:
  //   selects all user's info 
  // Parepared Statement:
  //   SELECT name, device_tokens, profile_picture_id, registration_time, 
  //   last_active_time, last_latitude, last_longitude FROM 
  //   ig_app_data.users_info WHERE user_id = :user_id; 

  /**
   * getQuerySelectAll
   * @return SelectAll Query in the form of
   *           a Query Object
   * @throws Exception
   */
  public Query getQuerySelectAll (
    ) throws Exception {

    return this.getQuery(kSelectAllName);
  }

  /**
   * getQueryDispatchableSelectAll
   * @param userid
   * @return SelectAll Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableSelectAll (
    Object userid) throws Exception {

    return
      this.getQueryDispatchable(
        kSelectAllName,
        userid);
  }

  /**
   * getBoundStatementSelectAll
   * @param userid
   * @return SelectAll Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementSelectAll (
    Object userid) throws Exception {

    return
      this.getQuery(kSelectAllName).getBoundStatement(
        userid);
  }

  /**
   * executeAsyncSelectAll
   * executes SelectAll Query asynchronously
   * @param userid
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncSelectAll (
    Object userid) throws Exception {

    return
      this.getQuery(kSelectAllName).executeAsync(
        userid);
  }

  /**
   * executeSyncSelectAll
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes SelectAll Query synchronously
   * @param userid
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncSelectAll (
    Object userid) throws Exception {

    return
      this.getQuery(kSelectAllName).executeSync(
        userid);
  }

}
