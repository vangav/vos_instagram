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
 * FacebookIds represents
 *   Table [facebook_ids]
 *   in Keyspace [ig_auth]
 * 
 * Name: facebook_ids
 * Description:
 *   used to map a user's facebook id to her/his user id 
 * 
 * Columns:
 *   facebook_id : varchar
 *   user_id : uuid

 * Partition Keys: facebook_id
 * Secondary Keys: 
 * Caching: NONE
 * Order By:

 * Queries:
 *   - Name: insert
 *   Description:
 *     inserts new user's facebook id and user id upon signup 
 *   Prepared Statement:
 *     INSERT INTO ig_auth.facebook_ids (facebook_id, user_id) VALUES 
 *     (:facebook_id, :user_id); 
 *   - Name: delete
 *   Description:
 *     deletes a user's facebook_id 
 *   Prepared Statement:
 *     DELETE FROM ig_auth.facebook_ids WHERE facebook_id = :facebook_id; 
 *   - Name: select
 *   Description:
 *     selects a user's user_id based on her/his facebook_id 
 *   Prepared Statement:
 *     SELECT user_id FROM ig_auth.facebook_ids WHERE facebook_id = 
 *     :facebook_id; 
 * */
public class FacebookIds extends Table {

  private static final String kKeySpaceName =
    "ig_auth";
  private static final String kTableName =
    "facebook_ids";

  public static final String kFacebookIdColumnName =
    "facebook_id";
  public static final String kUserIdColumnName =
    "user_id";

  /**
   * Query:
   * Name: insert
   * Description:
   *   inserts new user's facebook id and user id upon signup 
   * Prepared Statement:
   *   INSERT INTO ig_auth.facebook_ids (facebook_id, user_id) VALUES 
   *   (:facebook_id, :user_id); 
   */
  private static final String kInsertName =
    "insert";
  private static final String kInsertDescription =
    "inserts new user's facebook id and user id upon signup ";
  private static final String kInsertPreparedStatement =
    "INSERT INTO ig_auth.facebook_ids (facebook_id, user_id) VALUES "
    + "(:facebook_id, :user_id); ";

  /**
   * Query:
   * Name: delete
   * Description:
   *   deletes a user's facebook_id 
   * Prepared Statement:
   *   DELETE FROM ig_auth.facebook_ids WHERE facebook_id = :facebook_id; 
   */
  private static final String kDeleteName =
    "delete";
  private static final String kDeleteDescription =
    "deletes a user's facebook_id ";
  private static final String kDeletePreparedStatement =
    "DELETE FROM ig_auth.facebook_ids WHERE facebook_id = :facebook_id; ";

  /**
   * Query:
   * Name: select
   * Description:
   *   selects a user's user_id based on her/his facebook_id 
   * Prepared Statement:
   *   SELECT user_id FROM ig_auth.facebook_ids WHERE facebook_id = 
   *   :facebook_id; 
   */
  private static final String kSelectName =
    "select";
  private static final String kSelectDescription =
    "selects a user's user_id based on her/his facebook_id ";
  private static final String kSelectPreparedStatement =
    "SELECT user_id FROM ig_auth.facebook_ids WHERE facebook_id = "
    + ":facebook_id; ";

  /**
   * Constructor FacebookIds
   * @return new FacebookIds Object
   * @throws Exception
   */
  private FacebookIds () throws Exception {

    super (
      kKeySpaceName,
      kTableName,
      new Query (
        kInsertDescription,
        kInsertName,
        kInsertPreparedStatement),
      new Query (
        kDeleteDescription,
        kDeleteName,
        kDeletePreparedStatement),
      new Query (
        kSelectDescription,
        kSelectName,
        kSelectPreparedStatement));
  }

  private static FacebookIds instance = null;

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

      instance = new FacebookIds();
    }
  }

  /**
   * i
   * @return singleton static instance of FacebookIds
   * @throws Exception
   */
  public static FacebookIds i () throws Exception {

    if (instance == null) {

      instance = new FacebookIds();
    }

    return instance;
  }

  // Query: Insert
  // Description:
  //   inserts new user's facebook id and user id upon signup 
  // Parepared Statement:
  //   INSERT INTO ig_auth.facebook_ids (facebook_id, user_id) VALUES 
  //   (:facebook_id, :user_id); 

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
   * @param facebookid
   * @param userid
   * @return Insert Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableInsert (
    Object facebookid,
    Object userid) throws Exception {

    return
      this.getQueryDispatchable(
        kInsertName,
        facebookid,
        userid);
  }

  /**
   * getBoundStatementInsert
   * @param facebookid
   * @param userid
   * @return Insert Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementInsert (
    Object facebookid,
    Object userid) throws Exception {

    return
      this.getQuery(kInsertName).getBoundStatement(
        facebookid,
        userid);
  }

  /**
   * executeAsyncInsert
   * executes Insert Query asynchronously
   * @param facebookid
   * @param userid
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncInsert (
    Object facebookid,
    Object userid) throws Exception {

    return
      this.getQuery(kInsertName).executeAsync(
        facebookid,
        userid);
  }

  /**
   * executeSyncInsert
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes Insert Query synchronously
   * @param facebookid
   * @param userid
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncInsert (
    Object facebookid,
    Object userid) throws Exception {

    return
      this.getQuery(kInsertName).executeSync(
        facebookid,
        userid);
  }

  // Query: Delete
  // Description:
  //   deletes a user's facebook_id 
  // Parepared Statement:
  //   DELETE FROM ig_auth.facebook_ids WHERE facebook_id = :facebook_id; 

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
   * @param facebookid
   * @return Delete Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableDelete (
    Object facebookid) throws Exception {

    return
      this.getQueryDispatchable(
        kDeleteName,
        facebookid);
  }

  /**
   * getBoundStatementDelete
   * @param facebookid
   * @return Delete Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementDelete (
    Object facebookid) throws Exception {

    return
      this.getQuery(kDeleteName).getBoundStatement(
        facebookid);
  }

  /**
   * executeAsyncDelete
   * executes Delete Query asynchronously
   * @param facebookid
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncDelete (
    Object facebookid) throws Exception {

    return
      this.getQuery(kDeleteName).executeAsync(
        facebookid);
  }

  /**
   * executeSyncDelete
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes Delete Query synchronously
   * @param facebookid
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncDelete (
    Object facebookid) throws Exception {

    return
      this.getQuery(kDeleteName).executeSync(
        facebookid);
  }

  // Query: Select
  // Description:
  //   selects a user's user_id based on her/his facebook_id 
  // Parepared Statement:
  //   SELECT user_id FROM ig_auth.facebook_ids WHERE facebook_id = 
  //   :facebook_id; 

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
   * @param facebookid
   * @return Select Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableSelect (
    Object facebookid) throws Exception {

    return
      this.getQueryDispatchable(
        kSelectName,
        facebookid);
  }

  /**
   * getBoundStatementSelect
   * @param facebookid
   * @return Select Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementSelect (
    Object facebookid) throws Exception {

    return
      this.getQuery(kSelectName).getBoundStatement(
        facebookid);
  }

  /**
   * executeAsyncSelect
   * executes Select Query asynchronously
   * @param facebookid
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncSelect (
    Object facebookid) throws Exception {

    return
      this.getQuery(kSelectName).executeAsync(
        facebookid);
  }

  /**
   * executeSyncSelect
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes Select Query synchronously
   * @param facebookid
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncSelect (
    Object facebookid) throws Exception {

    return
      this.getQuery(kSelectName).executeSync(
        facebookid);
  }

}
