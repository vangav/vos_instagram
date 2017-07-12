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

package com.vangav.vos_instagram.cassandra_keyspaces.ig_auth;

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
 * DeviceTokens represents
 *   Table [device_tokens]
 *   in Keyspace [ig_auth]
 * 
 * Name: device_tokens
 * Description:
 *   used to keep track of users' device tokens 
 * 
 * Columns:
 *   device_token : varchar
 *   user_ids : set<uuid>

 * Partition Keys: device_token
 * Secondary Keys: 
 * Caching: KEYS_ONLY
 * Order By:

 * Queries:
 *   - Name: insert
 *   Description:
 *     inserts a new device token and user id 
 *   Prepared Statement:
 *     INSERT INTO ig_auth.device_tokens (device_token, user_ids) VALUES 
 *     (:device_token, :user_id); 
 *   - Name: add_user_id
 *   Description:
 *     adds a user_id to a device token 
 *   Prepared Statement:
 *     UPDATE ig_auth.device_tokens SET user_ids = user_ids + :user_id 
 *     WHERE device_token = :device_token; 
 *   - Name: remove_user_id
 *   Description:
 *     removes a user_id from a device token 
 *   Prepared Statement:
 *     UPDATE ig_auth.device_tokens SET user_ids = user_ids - :user_id 
 *     WHERE device_token = :device_token; 
 *   - Name: select
 *   Description:
 *     selects the set of user ids associated with a device token 
 *   Prepared Statement:
 *     SELECT user_ids FROM ig_auth.device_tokens WHERE device_token = 
 *     :device_token; 
 * */
public class DeviceTokens extends Table {

  private static final String kKeySpaceName =
    "ig_auth";
  private static final String kTableName =
    "device_tokens";

  public static final String kDeviceTokenColumnName =
    "device_token";
  public static final String kUserIdsColumnName =
    "user_ids";

  /**
   * Query:
   * Name: insert
   * Description:
   *   inserts a new device token and user id 
   * Prepared Statement:
   *   INSERT INTO ig_auth.device_tokens (device_token, user_ids) VALUES 
   *   (:device_token, :user_id); 
   */
  private static final String kInsertName =
    "insert";
  private static final String kInsertDescription =
    "inserts a new device token and user id ";
  private static final String kInsertPreparedStatement =
    "INSERT INTO ig_auth.device_tokens (device_token, user_ids) VALUES "
    + "(:device_token, :user_id); ";

  /**
   * Query:
   * Name: add_user_id
   * Description:
   *   adds a user_id to a device token 
   * Prepared Statement:
   *   UPDATE ig_auth.device_tokens SET user_ids = user_ids + :user_id 
   *   WHERE device_token = :device_token; 
   */
  private static final String kAddUserIdName =
    "add_user_id";
  private static final String kAddUserIdDescription =
    "adds a user_id to a device token ";
  private static final String kAddUserIdPreparedStatement =
    "UPDATE ig_auth.device_tokens SET user_ids = user_ids + :user_id WHERE "
    + "device_token = :device_token; ";

  /**
   * Query:
   * Name: remove_user_id
   * Description:
   *   removes a user_id from a device token 
   * Prepared Statement:
   *   UPDATE ig_auth.device_tokens SET user_ids = user_ids - :user_id 
   *   WHERE device_token = :device_token; 
   */
  private static final String kRemoveUserIdName =
    "remove_user_id";
  private static final String kRemoveUserIdDescription =
    "removes a user_id from a device token ";
  private static final String kRemoveUserIdPreparedStatement =
    "UPDATE ig_auth.device_tokens SET user_ids = user_ids - :user_id WHERE "
    + "device_token = :device_token; ";

  /**
   * Query:
   * Name: select
   * Description:
   *   selects the set of user ids associated with a device token 
   * Prepared Statement:
   *   SELECT user_ids FROM ig_auth.device_tokens WHERE device_token = 
   *   :device_token; 
   */
  private static final String kSelectName =
    "select";
  private static final String kSelectDescription =
    "selects the set of user ids associated with a device token ";
  private static final String kSelectPreparedStatement =
    "SELECT user_ids FROM ig_auth.device_tokens WHERE device_token = "
    + ":device_token; ";

  /**
   * Constructor DeviceTokens
   * @return new DeviceTokens Object
   * @throws Exception
   */
  private DeviceTokens () throws Exception {

    super (
      kKeySpaceName,
      kTableName,
      new Query (
        kInsertDescription,
        kInsertName,
        kInsertPreparedStatement),
      new Query (
        kAddUserIdDescription,
        kAddUserIdName,
        kAddUserIdPreparedStatement),
      new Query (
        kRemoveUserIdDescription,
        kRemoveUserIdName,
        kRemoveUserIdPreparedStatement),
      new Query (
        kSelectDescription,
        kSelectName,
        kSelectPreparedStatement));
  }

  private static DeviceTokens instance = null;

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

      instance = new DeviceTokens();
    }
  }

  /**
   * i
   * @return singleton static instance of DeviceTokens
   * @throws Exception
   */
  public static DeviceTokens i () throws Exception {

    if (instance == null) {

      instance = new DeviceTokens();
    }

    return instance;
  }

  // Query: Insert
  // Description:
  //   inserts a new device token and user id 
  // Parepared Statement:
  //   INSERT INTO ig_auth.device_tokens (device_token, user_ids) VALUES 
  //   (:device_token, :user_id); 

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
   * @param devicetoken
   * @param userid
   * @return Insert Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableInsert (
    Object devicetoken,
    Object userid) throws Exception {

    return
      this.getQueryDispatchable(
        kInsertName,
        devicetoken,
        userid);
  }

  /**
   * getBoundStatementInsert
   * @param devicetoken
   * @param userid
   * @return Insert Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementInsert (
    Object devicetoken,
    Object userid) throws Exception {

    return
      this.getQuery(kInsertName).getBoundStatement(
        devicetoken,
        userid);
  }

  /**
   * executeAsyncInsert
   * executes Insert Query asynchronously
   * @param devicetoken
   * @param userid
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncInsert (
    Object devicetoken,
    Object userid) throws Exception {

    return
      this.getQuery(kInsertName).executeAsync(
        devicetoken,
        userid);
  }

  /**
   * executeSyncInsert
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes Insert Query synchronously
   * @param devicetoken
   * @param userid
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncInsert (
    Object devicetoken,
    Object userid) throws Exception {

    return
      this.getQuery(kInsertName).executeSync(
        devicetoken,
        userid);
  }

  // Query: AddUserId
  // Description:
  //   adds a user_id to a device token 
  // Parepared Statement:
  //   UPDATE ig_auth.device_tokens SET user_ids = user_ids + :user_id 
  //   WHERE device_token = :device_token; 

  /**
   * getQueryAddUserId
   * @return AddUserId Query in the form of
   *           a Query Object
   * @throws Exception
   */
  public Query getQueryAddUserId (
    ) throws Exception {

    return this.getQuery(kAddUserIdName);
  }

  /**
   * getQueryDispatchableAddUserId
   * @param userid
   * @param devicetoken
   * @return AddUserId Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableAddUserId (
    Object userid,
    Object devicetoken) throws Exception {

    return
      this.getQueryDispatchable(
        kAddUserIdName,
        userid,
        devicetoken);
  }

  /**
   * getBoundStatementAddUserId
   * @param userid
   * @param devicetoken
   * @return AddUserId Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementAddUserId (
    Object userid,
    Object devicetoken) throws Exception {

    return
      this.getQuery(kAddUserIdName).getBoundStatement(
        userid,
        devicetoken);
  }

  /**
   * executeAsyncAddUserId
   * executes AddUserId Query asynchronously
   * @param userid
   * @param devicetoken
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncAddUserId (
    Object userid,
    Object devicetoken) throws Exception {

    return
      this.getQuery(kAddUserIdName).executeAsync(
        userid,
        devicetoken);
  }

  /**
   * executeSyncAddUserId
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes AddUserId Query synchronously
   * @param userid
   * @param devicetoken
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncAddUserId (
    Object userid,
    Object devicetoken) throws Exception {

    return
      this.getQuery(kAddUserIdName).executeSync(
        userid,
        devicetoken);
  }

  // Query: RemoveUserId
  // Description:
  //   removes a user_id from a device token 
  // Parepared Statement:
  //   UPDATE ig_auth.device_tokens SET user_ids = user_ids - :user_id 
  //   WHERE device_token = :device_token; 

  /**
   * getQueryRemoveUserId
   * @return RemoveUserId Query in the form of
   *           a Query Object
   * @throws Exception
   */
  public Query getQueryRemoveUserId (
    ) throws Exception {

    return this.getQuery(kRemoveUserIdName);
  }

  /**
   * getQueryDispatchableRemoveUserId
   * @param userid
   * @param devicetoken
   * @return RemoveUserId Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableRemoveUserId (
    Object userid,
    Object devicetoken) throws Exception {

    return
      this.getQueryDispatchable(
        kRemoveUserIdName,
        userid,
        devicetoken);
  }

  /**
   * getBoundStatementRemoveUserId
   * @param userid
   * @param devicetoken
   * @return RemoveUserId Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementRemoveUserId (
    Object userid,
    Object devicetoken) throws Exception {

    return
      this.getQuery(kRemoveUserIdName).getBoundStatement(
        userid,
        devicetoken);
  }

  /**
   * executeAsyncRemoveUserId
   * executes RemoveUserId Query asynchronously
   * @param userid
   * @param devicetoken
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncRemoveUserId (
    Object userid,
    Object devicetoken) throws Exception {

    return
      this.getQuery(kRemoveUserIdName).executeAsync(
        userid,
        devicetoken);
  }

  /**
   * executeSyncRemoveUserId
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes RemoveUserId Query synchronously
   * @param userid
   * @param devicetoken
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncRemoveUserId (
    Object userid,
    Object devicetoken) throws Exception {

    return
      this.getQuery(kRemoveUserIdName).executeSync(
        userid,
        devicetoken);
  }

  // Query: Select
  // Description:
  //   selects the set of user ids associated with a device token 
  // Parepared Statement:
  //   SELECT user_ids FROM ig_auth.device_tokens WHERE device_token = 
  //   :device_token; 

  /**
   * getQuerySelect
   * @return Select Query in the form of
   *           a Query Object
   * @throws Exception
   */
  public Query getQuerySelect (
    ) throws Exception {

    return this.getQuery(kSelectName);
  }

  /**
   * getQueryDispatchableSelect
   * @param devicetoken
   * @return Select Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableSelect (
    Object devicetoken) throws Exception {

    return
      this.getQueryDispatchable(
        kSelectName,
        devicetoken);
  }

  /**
   * getBoundStatementSelect
   * @param devicetoken
   * @return Select Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementSelect (
    Object devicetoken) throws Exception {

    return
      this.getQuery(kSelectName).getBoundStatement(
        devicetoken);
  }

  /**
   * executeAsyncSelect
   * executes Select Query asynchronously
   * @param devicetoken
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncSelect (
    Object devicetoken) throws Exception {

    return
      this.getQuery(kSelectName).executeAsync(
        devicetoken);
  }

  /**
   * executeSyncSelect
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes Select Query synchronously
   * @param devicetoken
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncSelect (
    Object devicetoken) throws Exception {

    return
      this.getQuery(kSelectName).executeSync(
        devicetoken);
  }

}
