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
 * AccessTokens represents
 *   Table [access_tokens]
 *   in Keyspace [ig_auth]
 * 
 * Name: access_tokens
 * Description:
 *   used to store users' access tokens 
 * 
 * Columns:
 *   user_id : uuid
 *   device_token : varchar
 *   access_token : varchar
 *   time_stamp : bigint
 *   expired : boolean

 * Partition Keys: user_id, device_token
 * Secondary Keys: 
 * Caching: ALL
 * Order By:

 * Queries:
 *   - Name: insert
 *   Description:
 *     inserts a user's access token 
 *   Prepared Statement:
 *     INSERT INTO ig_auth.access_tokens (user_id, device_token, 
 *     access_token, time_stamp, expired) VALUES (:user_id, 
 *     :device_token, :access_token, :time_stamp, false); 
 *   - Name: update
 *   Description:
 *     updates a user's access token 
 *   Prepared Statement:
 *     UPDATE ig_auth.access_tokens SET access_token = :access_token, 
 *     time_stamp = :time_stamp, expired = false WHERE user_id = 
 *     :user_id AND device_token = :device_token; 
 *   - Name: expire
 *   Description:
 *     updates the auth token's expried flag to false when it gets expired 
 *   Prepared Statement:
 *     UPDATE ig_auth.access_tokens SET expired = true WHERE user_id = 
 *     :user_id AND device_token = :device_token; 
 *   - Name: select
 *   Description:
 *     selects a user's access token 
 *   Prepared Statement:
 *     SELECT access_token, time_stamp, expired FROM ig_auth.access_tokens 
 *     WHERE user_id = :user_id AND device_token = :device_token; 
 *   - Name: delete
 *   Description:
 *     delets a user's access token upon logout 
 *   Prepared Statement:
 *     DELETE FROM ig_auth.access_tokens WHERE user_id = :user_id AND 
 *     device_token = :device_token; 
 *   - Name: delete_all
 *   Description:
 *     deletes all user's access tokens upon logging out from all devices 
 *   Prepared Statement:
 *     DELETE FROM ig_auth.access_tokens WHERE user_id = :user_id; 
 * */
public class AccessTokens extends Table {

  private static final String kKeySpaceName =
    "ig_auth";
  private static final String kTableName =
    "access_tokens";

  public static final String kUserIdColumnName =
    "user_id";
  public static final String kDeviceTokenColumnName =
    "device_token";
  public static final String kAccessTokenColumnName =
    "access_token";
  public static final String kTimeStampColumnName =
    "time_stamp";
  public static final String kExpiredColumnName =
    "expired";

  /**
   * Query:
   * Name: insert
   * Description:
   *   inserts a user's access token 
   * Prepared Statement:
   *   INSERT INTO ig_auth.access_tokens (user_id, device_token, 
   *   access_token, time_stamp, expired) VALUES (:user_id, 
   *   :device_token, :access_token, :time_stamp, false); 
   */
  private static final String kInsertName =
    "insert";
  private static final String kInsertDescription =
    "inserts a user's access token ";
  private static final String kInsertPreparedStatement =
    "INSERT INTO ig_auth.access_tokens (user_id, device_token, "
    + "access_token, time_stamp, expired) VALUES (:user_id, "
    + ":device_token, :access_token, :time_stamp, false); ";

  /**
   * Query:
   * Name: update
   * Description:
   *   updates a user's access token 
   * Prepared Statement:
   *   UPDATE ig_auth.access_tokens SET access_token = :access_token, 
   *   time_stamp = :time_stamp, expired = false WHERE user_id = 
   *   :user_id AND device_token = :device_token; 
   */
  private static final String kUpdateName =
    "update";
  private static final String kUpdateDescription =
    "updates a user's access token ";
  private static final String kUpdatePreparedStatement =
    "UPDATE ig_auth.access_tokens SET access_token = :access_token, "
    + "time_stamp = :time_stamp, expired = false WHERE user_id = "
    + ":user_id AND device_token = :device_token; ";

  /**
   * Query:
   * Name: expire
   * Description:
   *   updates the auth token's expried flag to false when it gets expired 
   * Prepared Statement:
   *   UPDATE ig_auth.access_tokens SET expired = true WHERE user_id = 
   *   :user_id AND device_token = :device_token; 
   */
  private static final String kExpireName =
    "expire";
  private static final String kExpireDescription =
    "updates the auth token's expried flag to false when it gets expired ";
  private static final String kExpirePreparedStatement =
    "UPDATE ig_auth.access_tokens SET expired = true WHERE user_id = "
    + ":user_id AND device_token = :device_token; ";

  /**
   * Query:
   * Name: select
   * Description:
   *   selects a user's access token 
   * Prepared Statement:
   *   SELECT access_token, time_stamp, expired FROM ig_auth.access_tokens 
   *   WHERE user_id = :user_id AND device_token = :device_token; 
   */
  private static final String kSelectName =
    "select";
  private static final String kSelectDescription =
    "selects a user's access token ";
  private static final String kSelectPreparedStatement =
    "SELECT access_token, time_stamp, expired FROM ig_auth.access_tokens "
    + "WHERE user_id = :user_id AND device_token = :device_token; ";

  /**
   * Query:
   * Name: delete
   * Description:
   *   delets a user's access token upon logout 
   * Prepared Statement:
   *   DELETE FROM ig_auth.access_tokens WHERE user_id = :user_id AND 
   *   device_token = :device_token; 
   */
  private static final String kDeleteName =
    "delete";
  private static final String kDeleteDescription =
    "delets a user's access token upon logout ";
  private static final String kDeletePreparedStatement =
    "DELETE FROM ig_auth.access_tokens WHERE user_id = :user_id AND "
    + "device_token = :device_token; ";

  /**
   * Query:
   * Name: delete_all
   * Description:
   *   deletes all user's access tokens upon logging out from all devices 
   * Prepared Statement:
   *   DELETE FROM ig_auth.access_tokens WHERE user_id = :user_id; 
   */
  private static final String kDeleteAllName =
    "delete_all";
  private static final String kDeleteAllDescription =
    "deletes all user's access tokens upon logging out from all devices ";
  private static final String kDeleteAllPreparedStatement =
    "DELETE FROM ig_auth.access_tokens WHERE user_id = :user_id; ";

  /**
   * Constructor AccessTokens
   * @return new AccessTokens Object
   * @throws Exception
   */
  private AccessTokens () throws Exception {

    super (
      kKeySpaceName,
      kTableName,
      new Query (
        kInsertDescription,
        kInsertName,
        kInsertPreparedStatement),
      new Query (
        kUpdateDescription,
        kUpdateName,
        kUpdatePreparedStatement),
      new Query (
        kExpireDescription,
        kExpireName,
        kExpirePreparedStatement),
      new Query (
        kSelectDescription,
        kSelectName,
        kSelectPreparedStatement),
      new Query (
        kDeleteDescription,
        kDeleteName,
        kDeletePreparedStatement),
      new Query (
        kDeleteAllDescription,
        kDeleteAllName,
        kDeleteAllPreparedStatement));
  }

  private static AccessTokens instance = null;

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

      instance = new AccessTokens();
    }
  }

  /**
   * i
   * @return singleton static instance of AccessTokens
   * @throws Exception
   */
  public static AccessTokens i () throws Exception {

    if (instance == null) {

      instance = new AccessTokens();
    }

    return instance;
  }

  // Query: Insert
  // Description:
  //   inserts a user's access token 
  // Parepared Statement:
  //   INSERT INTO ig_auth.access_tokens (user_id, device_token, 
  //   access_token, time_stamp, expired) VALUES (:user_id, 
  //   :device_token, :access_token, :time_stamp, false); 

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
   * @param devicetoken
   * @param accesstoken
   * @param timestamp
   * @return Insert Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableInsert (
    Object userid,
    Object devicetoken,
    Object accesstoken,
    Object timestamp) throws Exception {

    return
      this.getQueryDispatchable(
        kInsertName,
        userid,
        devicetoken,
        accesstoken,
        timestamp);
  }

  /**
   * getBoundStatementInsert
   * @param userid
   * @param devicetoken
   * @param accesstoken
   * @param timestamp
   * @return Insert Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementInsert (
    Object userid,
    Object devicetoken,
    Object accesstoken,
    Object timestamp) throws Exception {

    return
      this.getQuery(kInsertName).getBoundStatement(
        userid,
        devicetoken,
        accesstoken,
        timestamp);
  }

  /**
   * executeAsyncInsert
   * executes Insert Query asynchronously
   * @param userid
   * @param devicetoken
   * @param accesstoken
   * @param timestamp
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncInsert (
    Object userid,
    Object devicetoken,
    Object accesstoken,
    Object timestamp) throws Exception {

    return
      this.getQuery(kInsertName).executeAsync(
        userid,
        devicetoken,
        accesstoken,
        timestamp);
  }

  /**
   * executeSyncInsert
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes Insert Query synchronously
   * @param userid
   * @param devicetoken
   * @param accesstoken
   * @param timestamp
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncInsert (
    Object userid,
    Object devicetoken,
    Object accesstoken,
    Object timestamp) throws Exception {

    return
      this.getQuery(kInsertName).executeSync(
        userid,
        devicetoken,
        accesstoken,
        timestamp);
  }

  // Query: Update
  // Description:
  //   updates a user's access token 
  // Parepared Statement:
  //   UPDATE ig_auth.access_tokens SET access_token = :access_token, 
  //   time_stamp = :time_stamp, expired = false WHERE user_id = 
  //   :user_id AND device_token = :device_token; 

  /**
   * getQueryUpdate
   * @return Update Query in the form of
   *           a Query Object
   * @throws Exception
   */
  public Query getQueryUpdate (
    ) throws Exception {

    return this.getQuery(kUpdateName);
  }

  /**
   * getQueryDispatchableUpdate
   * @param accesstoken
   * @param timestamp
   * @param userid
   * @param devicetoken
   * @return Update Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableUpdate (
    Object accesstoken,
    Object timestamp,
    Object userid,
    Object devicetoken) throws Exception {

    return
      this.getQueryDispatchable(
        kUpdateName,
        accesstoken,
        timestamp,
        userid,
        devicetoken);
  }

  /**
   * getBoundStatementUpdate
   * @param accesstoken
   * @param timestamp
   * @param userid
   * @param devicetoken
   * @return Update Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementUpdate (
    Object accesstoken,
    Object timestamp,
    Object userid,
    Object devicetoken) throws Exception {

    return
      this.getQuery(kUpdateName).getBoundStatement(
        accesstoken,
        timestamp,
        userid,
        devicetoken);
  }

  /**
   * executeAsyncUpdate
   * executes Update Query asynchronously
   * @param accesstoken
   * @param timestamp
   * @param userid
   * @param devicetoken
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncUpdate (
    Object accesstoken,
    Object timestamp,
    Object userid,
    Object devicetoken) throws Exception {

    return
      this.getQuery(kUpdateName).executeAsync(
        accesstoken,
        timestamp,
        userid,
        devicetoken);
  }

  /**
   * executeSyncUpdate
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes Update Query synchronously
   * @param accesstoken
   * @param timestamp
   * @param userid
   * @param devicetoken
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncUpdate (
    Object accesstoken,
    Object timestamp,
    Object userid,
    Object devicetoken) throws Exception {

    return
      this.getQuery(kUpdateName).executeSync(
        accesstoken,
        timestamp,
        userid,
        devicetoken);
  }

  // Query: Expire
  // Description:
  //   updates the auth token's expried flag to false when it gets expired 
  // Parepared Statement:
  //   UPDATE ig_auth.access_tokens SET expired = true WHERE user_id = 
  //   :user_id AND device_token = :device_token; 

  /**
   * getQueryExpire
   * @return Expire Query in the form of
   *           a Query Object
   * @throws Exception
   */
  public Query getQueryExpire (
    ) throws Exception {

    return this.getQuery(kExpireName);
  }

  /**
   * getQueryDispatchableExpire
   * @param userid
   * @param devicetoken
   * @return Expire Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableExpire (
    Object userid,
    Object devicetoken) throws Exception {

    return
      this.getQueryDispatchable(
        kExpireName,
        userid,
        devicetoken);
  }

  /**
   * getBoundStatementExpire
   * @param userid
   * @param devicetoken
   * @return Expire Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementExpire (
    Object userid,
    Object devicetoken) throws Exception {

    return
      this.getQuery(kExpireName).getBoundStatement(
        userid,
        devicetoken);
  }

  /**
   * executeAsyncExpire
   * executes Expire Query asynchronously
   * @param userid
   * @param devicetoken
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncExpire (
    Object userid,
    Object devicetoken) throws Exception {

    return
      this.getQuery(kExpireName).executeAsync(
        userid,
        devicetoken);
  }

  /**
   * executeSyncExpire
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes Expire Query synchronously
   * @param userid
   * @param devicetoken
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncExpire (
    Object userid,
    Object devicetoken) throws Exception {

    return
      this.getQuery(kExpireName).executeSync(
        userid,
        devicetoken);
  }

  // Query: Select
  // Description:
  //   selects a user's access token 
  // Parepared Statement:
  //   SELECT access_token, time_stamp, expired FROM ig_auth.access_tokens 
  //   WHERE user_id = :user_id AND device_token = :device_token; 

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
   * @param userid
   * @param devicetoken
   * @return Select Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableSelect (
    Object userid,
    Object devicetoken) throws Exception {

    return
      this.getQueryDispatchable(
        kSelectName,
        userid,
        devicetoken);
  }

  /**
   * getBoundStatementSelect
   * @param userid
   * @param devicetoken
   * @return Select Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementSelect (
    Object userid,
    Object devicetoken) throws Exception {

    return
      this.getQuery(kSelectName).getBoundStatement(
        userid,
        devicetoken);
  }

  /**
   * executeAsyncSelect
   * executes Select Query asynchronously
   * @param userid
   * @param devicetoken
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncSelect (
    Object userid,
    Object devicetoken) throws Exception {

    return
      this.getQuery(kSelectName).executeAsync(
        userid,
        devicetoken);
  }

  /**
   * executeSyncSelect
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes Select Query synchronously
   * @param userid
   * @param devicetoken
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncSelect (
    Object userid,
    Object devicetoken) throws Exception {

    return
      this.getQuery(kSelectName).executeSync(
        userid,
        devicetoken);
  }

  // Query: Delete
  // Description:
  //   delets a user's access token upon logout 
  // Parepared Statement:
  //   DELETE FROM ig_auth.access_tokens WHERE user_id = :user_id AND 
  //   device_token = :device_token; 

  /**
   * getQueryDelete
   * @return Delete Query in the form of
   *           a Query Object
   * @throws Exception
   */
  public Query getQueryDelete (
    ) throws Exception {

    return this.getQuery(kDeleteName);
  }

  /**
   * getQueryDispatchableDelete
   * @param userid
   * @param devicetoken
   * @return Delete Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableDelete (
    Object userid,
    Object devicetoken) throws Exception {

    return
      this.getQueryDispatchable(
        kDeleteName,
        userid,
        devicetoken);
  }

  /**
   * getBoundStatementDelete
   * @param userid
   * @param devicetoken
   * @return Delete Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementDelete (
    Object userid,
    Object devicetoken) throws Exception {

    return
      this.getQuery(kDeleteName).getBoundStatement(
        userid,
        devicetoken);
  }

  /**
   * executeAsyncDelete
   * executes Delete Query asynchronously
   * @param userid
   * @param devicetoken
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncDelete (
    Object userid,
    Object devicetoken) throws Exception {

    return
      this.getQuery(kDeleteName).executeAsync(
        userid,
        devicetoken);
  }

  /**
   * executeSyncDelete
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes Delete Query synchronously
   * @param userid
   * @param devicetoken
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncDelete (
    Object userid,
    Object devicetoken) throws Exception {

    return
      this.getQuery(kDeleteName).executeSync(
        userid,
        devicetoken);
  }

  // Query: DeleteAll
  // Description:
  //   deletes all user's access tokens upon logging out from all devices 
  // Parepared Statement:
  //   DELETE FROM ig_auth.access_tokens WHERE user_id = :user_id; 

  /**
   * getQueryDeleteAll
   * @return DeleteAll Query in the form of
   *           a Query Object
   * @throws Exception
   */
  public Query getQueryDeleteAll (
    ) throws Exception {

    return this.getQuery(kDeleteAllName);
  }

  /**
   * getQueryDispatchableDeleteAll
   * @param userid
   * @return DeleteAll Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableDeleteAll (
    Object userid) throws Exception {

    return
      this.getQueryDispatchable(
        kDeleteAllName,
        userid);
  }

  /**
   * getBoundStatementDeleteAll
   * @param userid
   * @return DeleteAll Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementDeleteAll (
    Object userid) throws Exception {

    return
      this.getQuery(kDeleteAllName).getBoundStatement(
        userid);
  }

  /**
   * executeAsyncDeleteAll
   * executes DeleteAll Query asynchronously
   * @param userid
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncDeleteAll (
    Object userid) throws Exception {

    return
      this.getQuery(kDeleteAllName).executeAsync(
        userid);
  }

  /**
   * executeSyncDeleteAll
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes DeleteAll Query synchronously
   * @param userid
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncDeleteAll (
    Object userid) throws Exception {

    return
      this.getQuery(kDeleteAllName).executeSync(
        userid);
  }

}
