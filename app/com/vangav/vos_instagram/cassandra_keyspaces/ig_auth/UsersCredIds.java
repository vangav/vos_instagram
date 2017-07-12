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
 * UsersCredIds represents
 *   Table [users_cred_ids]
 *   in Keyspace [ig_auth]
 * 
 * Name: users_cred_ids
 * Description:
 *   acts as a reverse index users' authentication cred-ids (email and 
 *   facebook_id) 
 * 
 * Columns:
 *   user_id : uuid
 *   facebook_id : varchar
 *   email : varchar

 * Partition Keys: user_id
 * Secondary Keys: 
 * Caching: ALL
 * Order By:

 * Queries:
 *   - Name: insert
 *   Description:
 *     inserts a blank row for a new user 
 *   Prepared Statement:
 *     INSERT INTO ig_auth.users_cred_ids (user_id) VALUES (:user_id); 
 *   - Name: set_facebook_id
 *   Description:
 *     sets a user's facebook_id 
 *   Prepared Statement:
 *     UPDATE ig_auth.users_cred_ids SET facebook_id = :facebook_id WHERE 
 *     user_id = :user_id; 
 *   - Name: set_email
 *   Description:
 *     sets a user's email 
 *   Prepared Statement:
 *     UPDATE ig_auth.users_cred_ids SET email = :email WHERE user_id = 
 *     :user_id; 
 *   - Name: delete
 *   Description:
 *     deletes a user's cred ids 
 *   Prepared Statement:
 *     DELETE FROM ig_auth.users_cred_ids WHERE user_id = :user_id; 
 *   - Name: select_facebook_id
 *   Description:
 *     selects a user's facebook_id 
 *   Prepared Statement:
 *     SELECT facebook_id FROM ig_auth.users_cred_ids WHERE user_id = 
 *     :user_id; 
 *   - Name: select_email
 *   Description:
 *     selects a user's email 
 *   Prepared Statement:
 *     SELECT email FROM ig_auth.users_cred_ids WHERE user_id = :user_id; 
 *   - Name: select_all
 *   Description:
 *     selects a user's facebook_id and email 
 *   Prepared Statement:
 *     SELECT facebook_id, email FROM ig_auth.users_cred_ids WHERE user_id 
 *     = :user_id; 
 * */
public class UsersCredIds extends Table {

  private static final String kKeySpaceName =
    "ig_auth";
  private static final String kTableName =
    "users_cred_ids";

  public static final String kUserIdColumnName =
    "user_id";
  public static final String kFacebookIdColumnName =
    "facebook_id";
  public static final String kEmailColumnName =
    "email";

  /**
   * Query:
   * Name: insert
   * Description:
   *   inserts a blank row for a new user 
   * Prepared Statement:
   *   INSERT INTO ig_auth.users_cred_ids (user_id) VALUES (:user_id); 
   */
  private static final String kInsertName =
    "insert";
  private static final String kInsertDescription =
    "inserts a blank row for a new user ";
  private static final String kInsertPreparedStatement =
    "INSERT INTO ig_auth.users_cred_ids (user_id) VALUES (:user_id); ";

  /**
   * Query:
   * Name: set_facebook_id
   * Description:
   *   sets a user's facebook_id 
   * Prepared Statement:
   *   UPDATE ig_auth.users_cred_ids SET facebook_id = :facebook_id WHERE 
   *   user_id = :user_id; 
   */
  private static final String kSetFacebookIdName =
    "set_facebook_id";
  private static final String kSetFacebookIdDescription =
    "sets a user's facebook_id ";
  private static final String kSetFacebookIdPreparedStatement =
    "UPDATE ig_auth.users_cred_ids SET facebook_id = :facebook_id WHERE "
    + "user_id = :user_id; ";

  /**
   * Query:
   * Name: set_email
   * Description:
   *   sets a user's email 
   * Prepared Statement:
   *   UPDATE ig_auth.users_cred_ids SET email = :email WHERE user_id = 
   *   :user_id; 
   */
  private static final String kSetEmailName =
    "set_email";
  private static final String kSetEmailDescription =
    "sets a user's email ";
  private static final String kSetEmailPreparedStatement =
    "UPDATE ig_auth.users_cred_ids SET email = :email WHERE user_id = "
    + ":user_id; ";

  /**
   * Query:
   * Name: delete
   * Description:
   *   deletes a user's cred ids 
   * Prepared Statement:
   *   DELETE FROM ig_auth.users_cred_ids WHERE user_id = :user_id; 
   */
  private static final String kDeleteName =
    "delete";
  private static final String kDeleteDescription =
    "deletes a user's cred ids ";
  private static final String kDeletePreparedStatement =
    "DELETE FROM ig_auth.users_cred_ids WHERE user_id = :user_id; ";

  /**
   * Query:
   * Name: select_facebook_id
   * Description:
   *   selects a user's facebook_id 
   * Prepared Statement:
   *   SELECT facebook_id FROM ig_auth.users_cred_ids WHERE user_id = 
   *   :user_id; 
   */
  private static final String kSelectFacebookIdName =
    "select_facebook_id";
  private static final String kSelectFacebookIdDescription =
    "selects a user's facebook_id ";
  private static final String kSelectFacebookIdPreparedStatement =
    "SELECT facebook_id FROM ig_auth.users_cred_ids WHERE user_id = "
    + ":user_id; ";

  /**
   * Query:
   * Name: select_email
   * Description:
   *   selects a user's email 
   * Prepared Statement:
   *   SELECT email FROM ig_auth.users_cred_ids WHERE user_id = :user_id; 
   */
  private static final String kSelectEmailName =
    "select_email";
  private static final String kSelectEmailDescription =
    "selects a user's email ";
  private static final String kSelectEmailPreparedStatement =
    "SELECT email FROM ig_auth.users_cred_ids WHERE user_id = :user_id; ";

  /**
   * Query:
   * Name: select_all
   * Description:
   *   selects a user's facebook_id and email 
   * Prepared Statement:
   *   SELECT facebook_id, email FROM ig_auth.users_cred_ids WHERE user_id 
   *   = :user_id; 
   */
  private static final String kSelectAllName =
    "select_all";
  private static final String kSelectAllDescription =
    "selects a user's facebook_id and email ";
  private static final String kSelectAllPreparedStatement =
    "SELECT facebook_id, email FROM ig_auth.users_cred_ids WHERE user_id = "
    + ":user_id; ";

  /**
   * Constructor UsersCredIds
   * @return new UsersCredIds Object
   * @throws Exception
   */
  private UsersCredIds () throws Exception {

    super (
      kKeySpaceName,
      kTableName,
      new Query (
        kInsertDescription,
        kInsertName,
        kInsertPreparedStatement),
      new Query (
        kSetFacebookIdDescription,
        kSetFacebookIdName,
        kSetFacebookIdPreparedStatement),
      new Query (
        kSetEmailDescription,
        kSetEmailName,
        kSetEmailPreparedStatement),
      new Query (
        kDeleteDescription,
        kDeleteName,
        kDeletePreparedStatement),
      new Query (
        kSelectFacebookIdDescription,
        kSelectFacebookIdName,
        kSelectFacebookIdPreparedStatement),
      new Query (
        kSelectEmailDescription,
        kSelectEmailName,
        kSelectEmailPreparedStatement),
      new Query (
        kSelectAllDescription,
        kSelectAllName,
        kSelectAllPreparedStatement));
  }

  private static UsersCredIds instance = null;

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

      instance = new UsersCredIds();
    }
  }

  /**
   * i
   * @return singleton static instance of UsersCredIds
   * @throws Exception
   */
  public static UsersCredIds i () throws Exception {

    if (instance == null) {

      instance = new UsersCredIds();
    }

    return instance;
  }

  // Query: Insert
  // Description:
  //   inserts a blank row for a new user 
  // Parepared Statement:
  //   INSERT INTO ig_auth.users_cred_ids (user_id) VALUES (:user_id); 

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
   * @return Insert Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableInsert (
    Object userid) throws Exception {

    return
      this.getQueryDispatchable(
        kInsertName,
        userid);
  }

  /**
   * getBoundStatementInsert
   * @param userid
   * @return Insert Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementInsert (
    Object userid) throws Exception {

    return
      this.getQuery(kInsertName).getBoundStatement(
        userid);
  }

  /**
   * executeAsyncInsert
   * executes Insert Query asynchronously
   * @param userid
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncInsert (
    Object userid) throws Exception {

    return
      this.getQuery(kInsertName).executeAsync(
        userid);
  }

  /**
   * executeSyncInsert
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes Insert Query synchronously
   * @param userid
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncInsert (
    Object userid) throws Exception {

    return
      this.getQuery(kInsertName).executeSync(
        userid);
  }

  // Query: SetFacebookId
  // Description:
  //   sets a user's facebook_id 
  // Parepared Statement:
  //   UPDATE ig_auth.users_cred_ids SET facebook_id = :facebook_id WHERE 
  //   user_id = :user_id; 

  /**
   * getQuerySetFacebookId
   * @return SetFacebookId Query in the form of
   *           a Query Object
   * @throws Exception
   */
  public Query getQuerySetFacebookId (
    ) throws Exception {

    return this.getQuery(kSetFacebookIdName);
  }

  /**
   * getQueryDispatchableSetFacebookId
   * @param facebookid
   * @param userid
   * @return SetFacebookId Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableSetFacebookId (
    Object facebookid,
    Object userid) throws Exception {

    return
      this.getQueryDispatchable(
        kSetFacebookIdName,
        facebookid,
        userid);
  }

  /**
   * getBoundStatementSetFacebookId
   * @param facebookid
   * @param userid
   * @return SetFacebookId Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementSetFacebookId (
    Object facebookid,
    Object userid) throws Exception {

    return
      this.getQuery(kSetFacebookIdName).getBoundStatement(
        facebookid,
        userid);
  }

  /**
   * executeAsyncSetFacebookId
   * executes SetFacebookId Query asynchronously
   * @param facebookid
   * @param userid
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncSetFacebookId (
    Object facebookid,
    Object userid) throws Exception {

    return
      this.getQuery(kSetFacebookIdName).executeAsync(
        facebookid,
        userid);
  }

  /**
   * executeSyncSetFacebookId
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes SetFacebookId Query synchronously
   * @param facebookid
   * @param userid
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncSetFacebookId (
    Object facebookid,
    Object userid) throws Exception {

    return
      this.getQuery(kSetFacebookIdName).executeSync(
        facebookid,
        userid);
  }

  // Query: SetEmail
  // Description:
  //   sets a user's email 
  // Parepared Statement:
  //   UPDATE ig_auth.users_cred_ids SET email = :email WHERE user_id = 
  //   :user_id; 

  /**
   * getQuerySetEmail
   * @return SetEmail Query in the form of
   *           a Query Object
   * @throws Exception
   */
  public Query getQuerySetEmail (
    ) throws Exception {

    return this.getQuery(kSetEmailName);
  }

  /**
   * getQueryDispatchableSetEmail
   * @param email
   * @param userid
   * @return SetEmail Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableSetEmail (
    Object email,
    Object userid) throws Exception {

    return
      this.getQueryDispatchable(
        kSetEmailName,
        email,
        userid);
  }

  /**
   * getBoundStatementSetEmail
   * @param email
   * @param userid
   * @return SetEmail Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementSetEmail (
    Object email,
    Object userid) throws Exception {

    return
      this.getQuery(kSetEmailName).getBoundStatement(
        email,
        userid);
  }

  /**
   * executeAsyncSetEmail
   * executes SetEmail Query asynchronously
   * @param email
   * @param userid
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncSetEmail (
    Object email,
    Object userid) throws Exception {

    return
      this.getQuery(kSetEmailName).executeAsync(
        email,
        userid);
  }

  /**
   * executeSyncSetEmail
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes SetEmail Query synchronously
   * @param email
   * @param userid
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncSetEmail (
    Object email,
    Object userid) throws Exception {

    return
      this.getQuery(kSetEmailName).executeSync(
        email,
        userid);
  }

  // Query: Delete
  // Description:
  //   deletes a user's cred ids 
  // Parepared Statement:
  //   DELETE FROM ig_auth.users_cred_ids WHERE user_id = :user_id; 

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
   * @return Delete Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableDelete (
    Object userid) throws Exception {

    return
      this.getQueryDispatchable(
        kDeleteName,
        userid);
  }

  /**
   * getBoundStatementDelete
   * @param userid
   * @return Delete Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementDelete (
    Object userid) throws Exception {

    return
      this.getQuery(kDeleteName).getBoundStatement(
        userid);
  }

  /**
   * executeAsyncDelete
   * executes Delete Query asynchronously
   * @param userid
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncDelete (
    Object userid) throws Exception {

    return
      this.getQuery(kDeleteName).executeAsync(
        userid);
  }

  /**
   * executeSyncDelete
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes Delete Query synchronously
   * @param userid
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncDelete (
    Object userid) throws Exception {

    return
      this.getQuery(kDeleteName).executeSync(
        userid);
  }

  // Query: SelectFacebookId
  // Description:
  //   selects a user's facebook_id 
  // Parepared Statement:
  //   SELECT facebook_id FROM ig_auth.users_cred_ids WHERE user_id = 
  //   :user_id; 

  /**
   * getQuerySelectFacebookId
   * @return SelectFacebookId Query in the form of
   *           a Query Object
   * @throws Exception
   */
  public Query getQuerySelectFacebookId (
    ) throws Exception {

    return this.getQuery(kSelectFacebookIdName);
  }

  /**
   * getQueryDispatchableSelectFacebookId
   * @param userid
   * @return SelectFacebookId Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableSelectFacebookId (
    Object userid) throws Exception {

    return
      this.getQueryDispatchable(
        kSelectFacebookIdName,
        userid);
  }

  /**
   * getBoundStatementSelectFacebookId
   * @param userid
   * @return SelectFacebookId Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementSelectFacebookId (
    Object userid) throws Exception {

    return
      this.getQuery(kSelectFacebookIdName).getBoundStatement(
        userid);
  }

  /**
   * executeAsyncSelectFacebookId
   * executes SelectFacebookId Query asynchronously
   * @param userid
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncSelectFacebookId (
    Object userid) throws Exception {

    return
      this.getQuery(kSelectFacebookIdName).executeAsync(
        userid);
  }

  /**
   * executeSyncSelectFacebookId
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes SelectFacebookId Query synchronously
   * @param userid
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncSelectFacebookId (
    Object userid) throws Exception {

    return
      this.getQuery(kSelectFacebookIdName).executeSync(
        userid);
  }

  // Query: SelectEmail
  // Description:
  //   selects a user's email 
  // Parepared Statement:
  //   SELECT email FROM ig_auth.users_cred_ids WHERE user_id = :user_id; 

  /**
   * getQuerySelectEmail
   * @return SelectEmail Query in the form of
   *           a Query Object
   * @throws Exception
   */
  public Query getQuerySelectEmail (
    ) throws Exception {

    return this.getQuery(kSelectEmailName);
  }

  /**
   * getQueryDispatchableSelectEmail
   * @param userid
   * @return SelectEmail Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableSelectEmail (
    Object userid) throws Exception {

    return
      this.getQueryDispatchable(
        kSelectEmailName,
        userid);
  }

  /**
   * getBoundStatementSelectEmail
   * @param userid
   * @return SelectEmail Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementSelectEmail (
    Object userid) throws Exception {

    return
      this.getQuery(kSelectEmailName).getBoundStatement(
        userid);
  }

  /**
   * executeAsyncSelectEmail
   * executes SelectEmail Query asynchronously
   * @param userid
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncSelectEmail (
    Object userid) throws Exception {

    return
      this.getQuery(kSelectEmailName).executeAsync(
        userid);
  }

  /**
   * executeSyncSelectEmail
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes SelectEmail Query synchronously
   * @param userid
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncSelectEmail (
    Object userid) throws Exception {

    return
      this.getQuery(kSelectEmailName).executeSync(
        userid);
  }

  // Query: SelectAll
  // Description:
  //   selects a user's facebook_id and email 
  // Parepared Statement:
  //   SELECT facebook_id, email FROM ig_auth.users_cred_ids WHERE user_id 
  //   = :user_id; 

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
