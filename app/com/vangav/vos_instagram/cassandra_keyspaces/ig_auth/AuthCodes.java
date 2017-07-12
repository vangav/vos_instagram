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
 * AuthCodes represents
 *   Table [auth_codes]
 *   in Keyspace [ig_auth]
 * 
 * Name: auth_codes
 * Description:
 *   used to store users' auth codes 
 * 
 * Columns:
 *   user_id : uuid
 *   device_token : varchar
 *   auth_code : varchar
 *   access_token : varchar
 *   refresh_token : varchar

 * Partition Keys: user_id, device_token
 * Secondary Keys: 
 * Caching: ALL
 * Order By:

 * Queries:
 *   - Name: insert
 *   Description:
 *     inserts a user's auth codes upon signup/login, where the entry is 
 *     valid for ttl-time only where the client should make the get 
 *     access and refresh tokens call 
 *   Prepared Statement:
 *     INSERT INTO ig_auth.auth_codes (user_id, device_token, auth_code, 
 *     access_token, refresh_token) VALUES (:user_id, 
 *     :device_token, :auth_code, :access_token, :refresh_token) 
 *     USING TTL :ttl; 
 *   - Name: select
 *   Description:
 *     selects user's auth codes 
 *   Prepared Statement:
 *     SELECT auth_code, access_token, refresh_token FROM 
 *     ig_auth.auth_codes WHERE user_id = :user_id AND device_token 
 *     = :device_token; 
 *   - Name: delete
 *   Description:
 *     deletes user's auth codes 
 *   Prepared Statement:
 *     DELETE FROM ig_auth.auth_codes WHERE user_id = :user_id AND 
 *     device_token = :device_token; 
 *   - Name: delete_all
 *   Description:
 *     deletes all user's auth codes 
 *   Prepared Statement:
 *     DELETE FROM ig_auth.auth_codes WHERE user_id = :user_id; 
 * */
public class AuthCodes extends Table {

  private static final String kKeySpaceName =
    "ig_auth";
  private static final String kTableName =
    "auth_codes";

  public static final String kUserIdColumnName =
    "user_id";
  public static final String kDeviceTokenColumnName =
    "device_token";
  public static final String kAuthCodeColumnName =
    "auth_code";
  public static final String kAccessTokenColumnName =
    "access_token";
  public static final String kRefreshTokenColumnName =
    "refresh_token";

  /**
   * Query:
   * Name: insert
   * Description:
   *   inserts a user's auth codes upon signup/login, where the entry is 
   *   valid for ttl-time only where the client should make the get 
   *   access and refresh tokens call 
   * Prepared Statement:
   *   INSERT INTO ig_auth.auth_codes (user_id, device_token, auth_code, 
   *   access_token, refresh_token) VALUES (:user_id, 
   *   :device_token, :auth_code, :access_token, :refresh_token) 
   *   USING TTL :ttl; 
   */
  private static final String kInsertName =
    "insert";
  private static final String kInsertDescription =
    "inserts a user's auth codes upon signup/login, where the entry is "
    + "valid for ttl-time only where the client should make the get "
    + "access and refresh tokens call ";
  private static final String kInsertPreparedStatement =
    "INSERT INTO ig_auth.auth_codes (user_id, device_token, auth_code, "
    + "access_token, refresh_token) VALUES (:user_id, :device_token, "
    + ":auth_code, :access_token, :refresh_token) USING TTL :ttl; ";

  /**
   * Query:
   * Name: select
   * Description:
   *   selects user's auth codes 
   * Prepared Statement:
   *   SELECT auth_code, access_token, refresh_token FROM 
   *   ig_auth.auth_codes WHERE user_id = :user_id AND device_token 
   *   = :device_token; 
   */
  private static final String kSelectName =
    "select";
  private static final String kSelectDescription =
    "selects user's auth codes ";
  private static final String kSelectPreparedStatement =
    "SELECT auth_code, access_token, refresh_token FROM ig_auth.auth_codes "
    + "WHERE user_id = :user_id AND device_token = :device_token; ";

  /**
   * Query:
   * Name: delete
   * Description:
   *   deletes user's auth codes 
   * Prepared Statement:
   *   DELETE FROM ig_auth.auth_codes WHERE user_id = :user_id AND 
   *   device_token = :device_token; 
   */
  private static final String kDeleteName =
    "delete";
  private static final String kDeleteDescription =
    "deletes user's auth codes ";
  private static final String kDeletePreparedStatement =
    "DELETE FROM ig_auth.auth_codes WHERE user_id = :user_id AND "
    + "device_token = :device_token; ";

  /**
   * Query:
   * Name: delete_all
   * Description:
   *   deletes all user's auth codes 
   * Prepared Statement:
   *   DELETE FROM ig_auth.auth_codes WHERE user_id = :user_id; 
   */
  private static final String kDeleteAllName =
    "delete_all";
  private static final String kDeleteAllDescription =
    "deletes all user's auth codes ";
  private static final String kDeleteAllPreparedStatement =
    "DELETE FROM ig_auth.auth_codes WHERE user_id = :user_id; ";

  /**
   * Constructor AuthCodes
   * @return new AuthCodes Object
   * @throws Exception
   */
  private AuthCodes () throws Exception {

    super (
      kKeySpaceName,
      kTableName,
      new Query (
        kInsertDescription,
        kInsertName,
        kInsertPreparedStatement),
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

  private static AuthCodes instance = null;

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

      instance = new AuthCodes();
    }
  }

  /**
   * i
   * @return singleton static instance of AuthCodes
   * @throws Exception
   */
  public static AuthCodes i () throws Exception {

    if (instance == null) {

      instance = new AuthCodes();
    }

    return instance;
  }

  // Query: Insert
  // Description:
  //   inserts a user's auth codes upon signup/login, where the entry is 
  //   valid for ttl-time only where the client should make the get 
  //   access and refresh tokens call 
  // Parepared Statement:
  //   INSERT INTO ig_auth.auth_codes (user_id, device_token, auth_code, 
  //   access_token, refresh_token) VALUES (:user_id, 
  //   :device_token, :auth_code, :access_token, :refresh_token) 
  //   USING TTL :ttl; 

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
   * @param authcode
   * @param accesstoken
   * @param refreshtoken
   * @param ttl
   * @return Insert Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableInsert (
    Object userid,
    Object devicetoken,
    Object authcode,
    Object accesstoken,
    Object refreshtoken,
    Object ttl) throws Exception {

    return
      this.getQueryDispatchable(
        kInsertName,
        userid,
        devicetoken,
        authcode,
        accesstoken,
        refreshtoken,
        ttl);
  }

  /**
   * getBoundStatementInsert
   * @param userid
   * @param devicetoken
   * @param authcode
   * @param accesstoken
   * @param refreshtoken
   * @param ttl
   * @return Insert Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementInsert (
    Object userid,
    Object devicetoken,
    Object authcode,
    Object accesstoken,
    Object refreshtoken,
    Object ttl) throws Exception {

    return
      this.getQuery(kInsertName).getBoundStatement(
        userid,
        devicetoken,
        authcode,
        accesstoken,
        refreshtoken,
        ttl);
  }

  /**
   * executeAsyncInsert
   * executes Insert Query asynchronously
   * @param userid
   * @param devicetoken
   * @param authcode
   * @param accesstoken
   * @param refreshtoken
   * @param ttl
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncInsert (
    Object userid,
    Object devicetoken,
    Object authcode,
    Object accesstoken,
    Object refreshtoken,
    Object ttl) throws Exception {

    return
      this.getQuery(kInsertName).executeAsync(
        userid,
        devicetoken,
        authcode,
        accesstoken,
        refreshtoken,
        ttl);
  }

  /**
   * executeSyncInsert
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes Insert Query synchronously
   * @param userid
   * @param devicetoken
   * @param authcode
   * @param accesstoken
   * @param refreshtoken
   * @param ttl
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncInsert (
    Object userid,
    Object devicetoken,
    Object authcode,
    Object accesstoken,
    Object refreshtoken,
    Object ttl) throws Exception {

    return
      this.getQuery(kInsertName).executeSync(
        userid,
        devicetoken,
        authcode,
        accesstoken,
        refreshtoken,
        ttl);
  }

  // Query: Select
  // Description:
  //   selects user's auth codes 
  // Parepared Statement:
  //   SELECT auth_code, access_token, refresh_token FROM 
  //   ig_auth.auth_codes WHERE user_id = :user_id AND device_token 
  //   = :device_token; 

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
  //   deletes user's auth codes 
  // Parepared Statement:
  //   DELETE FROM ig_auth.auth_codes WHERE user_id = :user_id AND 
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
  //   deletes all user's auth codes 
  // Parepared Statement:
  //   DELETE FROM ig_auth.auth_codes WHERE user_id = :user_id; 

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
