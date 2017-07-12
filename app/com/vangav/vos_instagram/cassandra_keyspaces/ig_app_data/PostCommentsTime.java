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
 * PostCommentsTime represents
 *   Table [post_comments_time]
 *   in Keyspace [ig_app_data]
 * 
 * Name: post_comments_time
 * Description:
 *   stores the time when a user commented on a post 
 * 
 * Columns:
 *   post_id : uuid
 *   user_id : uuid
 *   comment_time : bigint

 * Partition Keys: post_id, user_id
 * Secondary Keys: 
 * Caching: ALL
 * Order By:

 * Queries:
 *   - Name: insert
 *   Description:
 *     inserts a post's comment time 
 *   Prepared Statement:
 *     INSERT INTO ig_app_data.post_comments_time (post_id, user_id, 
 *     comment_time) VALUES (:post_id, :user_id, :comment_time); 
 *   - Name: delete
 *   Description:
 *     deletes a post's comment time (after deleting a comment) 
 *   Prepared Statement:
 *     DELETE FROM ig_app_data.post_comments_time WHERE post_id = :post_id 
 *     AND user_id = :user_id; 
 *   - Name: select
 *   Description:
 *     selects a post's comment time 
 *   Prepared Statement:
 *     SELECT comment_time FROM ig_app_data.post_comments_time WHERE 
 *     post_id = :post_id AND user_id = :user_id; 
 * */
public class PostCommentsTime extends Table {

  private static final String kKeySpaceName =
    "ig_app_data";
  private static final String kTableName =
    "post_comments_time";

  public static final String kPostIdColumnName =
    "post_id";
  public static final String kUserIdColumnName =
    "user_id";
  public static final String kCommentTimeColumnName =
    "comment_time";

  /**
   * Query:
   * Name: insert
   * Description:
   *   inserts a post's comment time 
   * Prepared Statement:
   *   INSERT INTO ig_app_data.post_comments_time (post_id, user_id, 
   *   comment_time) VALUES (:post_id, :user_id, :comment_time); 
   */
  private static final String kInsertName =
    "insert";
  private static final String kInsertDescription =
    "inserts a post's comment time ";
  private static final String kInsertPreparedStatement =
    "INSERT INTO ig_app_data.post_comments_time (post_id, user_id, "
    + "comment_time) VALUES (:post_id, :user_id, :comment_time); ";

  /**
   * Query:
   * Name: delete
   * Description:
   *   deletes a post's comment time (after deleting a comment) 
   * Prepared Statement:
   *   DELETE FROM ig_app_data.post_comments_time WHERE post_id = :post_id 
   *   AND user_id = :user_id; 
   */
  private static final String kDeleteName =
    "delete";
  private static final String kDeleteDescription =
    "deletes a post's comment time (after deleting a comment) ";
  private static final String kDeletePreparedStatement =
    "DELETE FROM ig_app_data.post_comments_time WHERE post_id = :post_id "
    + "AND user_id = :user_id; ";

  /**
   * Query:
   * Name: select
   * Description:
   *   selects a post's comment time 
   * Prepared Statement:
   *   SELECT comment_time FROM ig_app_data.post_comments_time WHERE 
   *   post_id = :post_id AND user_id = :user_id; 
   */
  private static final String kSelectName =
    "select";
  private static final String kSelectDescription =
    "selects a post's comment time ";
  private static final String kSelectPreparedStatement =
    "SELECT comment_time FROM ig_app_data.post_comments_time WHERE post_id "
    + "= :post_id AND user_id = :user_id; ";

  /**
   * Constructor PostCommentsTime
   * @return new PostCommentsTime Object
   * @throws Exception
   */
  private PostCommentsTime () throws Exception {

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

  private static PostCommentsTime instance = null;

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

      instance = new PostCommentsTime();
    }
  }

  /**
   * i
   * @return singleton static instance of PostCommentsTime
   * @throws Exception
   */
  public static PostCommentsTime i () throws Exception {

    if (instance == null) {

      instance = new PostCommentsTime();
    }

    return instance;
  }

  // Query: Insert
  // Description:
  //   inserts a post's comment time 
  // Parepared Statement:
  //   INSERT INTO ig_app_data.post_comments_time (post_id, user_id, 
  //   comment_time) VALUES (:post_id, :user_id, :comment_time); 

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
   * @param postid
   * @param userid
   * @param commenttime
   * @return Insert Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableInsert (
    Object postid,
    Object userid,
    Object commenttime) throws Exception {

    return
      this.getQueryDispatchable(
        kInsertName,
        postid,
        userid,
        commenttime);
  }

  /**
   * getBoundStatementInsert
   * @param postid
   * @param userid
   * @param commenttime
   * @return Insert Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementInsert (
    Object postid,
    Object userid,
    Object commenttime) throws Exception {

    return
      this.getQuery(kInsertName).getBoundStatement(
        postid,
        userid,
        commenttime);
  }

  /**
   * executeAsyncInsert
   * executes Insert Query asynchronously
   * @param postid
   * @param userid
   * @param commenttime
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncInsert (
    Object postid,
    Object userid,
    Object commenttime) throws Exception {

    return
      this.getQuery(kInsertName).executeAsync(
        postid,
        userid,
        commenttime);
  }

  /**
   * executeSyncInsert
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes Insert Query synchronously
   * @param postid
   * @param userid
   * @param commenttime
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncInsert (
    Object postid,
    Object userid,
    Object commenttime) throws Exception {

    return
      this.getQuery(kInsertName).executeSync(
        postid,
        userid,
        commenttime);
  }

  // Query: Delete
  // Description:
  //   deletes a post's comment time (after deleting a comment) 
  // Parepared Statement:
  //   DELETE FROM ig_app_data.post_comments_time WHERE post_id = :post_id 
  //   AND user_id = :user_id; 

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
   * @param postid
   * @param userid
   * @return Delete Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableDelete (
    Object postid,
    Object userid) throws Exception {

    return
      this.getQueryDispatchable(
        kDeleteName,
        postid,
        userid);
  }

  /**
   * getBoundStatementDelete
   * @param postid
   * @param userid
   * @return Delete Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementDelete (
    Object postid,
    Object userid) throws Exception {

    return
      this.getQuery(kDeleteName).getBoundStatement(
        postid,
        userid);
  }

  /**
   * executeAsyncDelete
   * executes Delete Query asynchronously
   * @param postid
   * @param userid
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncDelete (
    Object postid,
    Object userid) throws Exception {

    return
      this.getQuery(kDeleteName).executeAsync(
        postid,
        userid);
  }

  /**
   * executeSyncDelete
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes Delete Query synchronously
   * @param postid
   * @param userid
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncDelete (
    Object postid,
    Object userid) throws Exception {

    return
      this.getQuery(kDeleteName).executeSync(
        postid,
        userid);
  }

  // Query: Select
  // Description:
  //   selects a post's comment time 
  // Parepared Statement:
  //   SELECT comment_time FROM ig_app_data.post_comments_time WHERE 
  //   post_id = :post_id AND user_id = :user_id; 

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
   * @param postid
   * @param userid
   * @return Select Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableSelect (
    Object postid,
    Object userid) throws Exception {

    return
      this.getQueryDispatchable(
        kSelectName,
        postid,
        userid);
  }

  /**
   * getBoundStatementSelect
   * @param postid
   * @param userid
   * @return Select Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementSelect (
    Object postid,
    Object userid) throws Exception {

    return
      this.getQuery(kSelectName).getBoundStatement(
        postid,
        userid);
  }

  /**
   * executeAsyncSelect
   * executes Select Query asynchronously
   * @param postid
   * @param userid
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncSelect (
    Object postid,
    Object userid) throws Exception {

    return
      this.getQuery(kSelectName).executeAsync(
        postid,
        userid);
  }

  /**
   * executeSyncSelect
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes Select Query synchronously
   * @param postid
   * @param userid
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncSelect (
    Object postid,
    Object userid) throws Exception {

    return
      this.getQuery(kSelectName).executeSync(
        postid,
        userid);
  }

}
