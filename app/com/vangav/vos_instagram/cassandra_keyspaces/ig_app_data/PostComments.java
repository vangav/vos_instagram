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
 * PostComments represents
 *   Table [post_comments]
 *   in Keyspace [ig_app_data]
 * 
 * Name: post_comments
 * Description:
 *   stores who commented on a post per-post along with the comment's 
 *   content ordered by the most recent likes 
 * 
 * Columns:
 *   post_id : uuid
 *   comment_time : bigint
 *   user_id : uuid
 *   comment : varchar

 * Partition Keys: post_id, comment_time, user_id
 * Secondary Keys: 
 * Caching: ALL
 * Order By:
 *   comment_time : DESC

 * Queries:
 *   - Name: insert
 *   Description:
 *     inserts a new post's comment 
 *   Prepared Statement:
 *     INSERT INTO ig_app_data.post_comments (post_id, comment_time, 
 *     user_id, comment) VALUES (:post_id, :comment_time, :user_id, 
 *     :comment); 
 *   - Name: delete
 *   Description:
 *     deletes a post's comment 
 *   Prepared Statement:
 *     DELETE FROM ig_app_data.post_comments WHERE post_id = :post_id AND 
 *     comment_time = :comment_time AND user_id = :user_id; 
 *   - Name: select_recent_limit
 *   Description:
 *     selects the most recent post's comments where the returned count is 
 *     limited by the value of limit (e.g.: 10) 
 *   Prepared Statement:
 *     SELECT comment_time, user_id, comment FROM 
 *     ig_app_data.post_comments WHERE post_id = :post_id LIMIT 30; 
 *   - Name: select_at_or_before_time_limit
 *   Description:
 *     selects post's comments at-or-before a specified time where the 
 *     returned count is limited by the value of limit (e.g.: 10) 
 *   Prepared Statement:
 *     SELECT comment_time, user_id, comment FROM 
 *     ig_app_data.post_comments WHERE post_id = :post_id AND 
 *     comment_time <= :comment_time LIMIT 30; 
 * */
public class PostComments extends Table {

  private static final String kKeySpaceName =
    "ig_app_data";
  private static final String kTableName =
    "post_comments";

  public static final String kPostIdColumnName =
    "post_id";
  public static final String kCommentTimeColumnName =
    "comment_time";
  public static final String kUserIdColumnName =
    "user_id";
  public static final String kCommentColumnName =
    "comment";

  /**
   * Query:
   * Name: insert
   * Description:
   *   inserts a new post's comment 
   * Prepared Statement:
   *   INSERT INTO ig_app_data.post_comments (post_id, comment_time, 
   *   user_id, comment) VALUES (:post_id, :comment_time, :user_id, 
   *   :comment); 
   */
  private static final String kInsertName =
    "insert";
  private static final String kInsertDescription =
    "inserts a new post's comment ";
  private static final String kInsertPreparedStatement =
    "INSERT INTO ig_app_data.post_comments (post_id, comment_time, user_id, "
    + "comment) VALUES (:post_id, :comment_time, :user_id, :comment); ";

  /**
   * Query:
   * Name: delete
   * Description:
   *   deletes a post's comment 
   * Prepared Statement:
   *   DELETE FROM ig_app_data.post_comments WHERE post_id = :post_id AND 
   *   comment_time = :comment_time AND user_id = :user_id; 
   */
  private static final String kDeleteName =
    "delete";
  private static final String kDeleteDescription =
    "deletes a post's comment ";
  private static final String kDeletePreparedStatement =
    "DELETE FROM ig_app_data.post_comments WHERE post_id = :post_id AND "
    + "comment_time = :comment_time AND user_id = :user_id; ";

  /**
   * Query:
   * Name: select_recent_limit
   * Description:
   *   selects the most recent post's comments where the returned count is 
   *   limited by the value of limit (e.g.: 10) 
   * Prepared Statement:
   *   SELECT comment_time, user_id, comment FROM 
   *   ig_app_data.post_comments WHERE post_id = :post_id LIMIT 30; 
   */
  private static final String kSelectRecentLimitName =
    "select_recent_limit";
  private static final String kSelectRecentLimitDescription =
    "selects the most recent post's comments where the returned count is "
    + "limited by the value of limit (e.g.: 10) ";
  private static final String kSelectRecentLimitPreparedStatement =
    "SELECT comment_time, user_id, comment FROM ig_app_data.post_comments "
    + "WHERE post_id = :post_id LIMIT 30; ";

  /**
   * Query:
   * Name: select_at_or_before_time_limit
   * Description:
   *   selects post's comments at-or-before a specified time where the 
   *   returned count is limited by the value of limit (e.g.: 10) 
   * Prepared Statement:
   *   SELECT comment_time, user_id, comment FROM 
   *   ig_app_data.post_comments WHERE post_id = :post_id AND 
   *   comment_time <= :comment_time LIMIT 30; 
   */
  private static final String kSelectAtOrBeforeTimeLimitName =
    "select_at_or_before_time_limit";
  private static final String kSelectAtOrBeforeTimeLimitDescription =
    "selects post's comments at-or-before a specified time where the "
    + "returned count is limited by the value of limit (e.g.: 10) ";
  private static final String kSelectAtOrBeforeTimeLimitPreparedStatement =
    "SELECT comment_time, user_id, comment FROM ig_app_data.post_comments "
    + "WHERE post_id = :post_id AND comment_time <= :comment_time "
    + "LIMIT 30; ";

  /**
   * Constructor PostComments
   * @return new PostComments Object
   * @throws Exception
   */
  private PostComments () throws Exception {

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
        kSelectRecentLimitDescription,
        kSelectRecentLimitName,
        kSelectRecentLimitPreparedStatement),
      new Query (
        kSelectAtOrBeforeTimeLimitDescription,
        kSelectAtOrBeforeTimeLimitName,
        kSelectAtOrBeforeTimeLimitPreparedStatement));
  }

  private static PostComments instance = null;

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

      instance = new PostComments();
    }
  }

  /**
   * i
   * @return singleton static instance of PostComments
   * @throws Exception
   */
  public static PostComments i () throws Exception {

    if (instance == null) {

      instance = new PostComments();
    }

    return instance;
  }

  // Query: Insert
  // Description:
  //   inserts a new post's comment 
  // Parepared Statement:
  //   INSERT INTO ig_app_data.post_comments (post_id, comment_time, 
  //   user_id, comment) VALUES (:post_id, :comment_time, :user_id, 
  //   :comment); 

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
   * @param commenttime
   * @param userid
   * @param comment
   * @return Insert Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableInsert (
    Object postid,
    Object commenttime,
    Object userid,
    Object comment) throws Exception {

    return
      this.getQueryDispatchable(
        kInsertName,
        postid,
        commenttime,
        userid,
        comment);
  }

  /**
   * getBoundStatementInsert
   * @param postid
   * @param commenttime
   * @param userid
   * @param comment
   * @return Insert Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementInsert (
    Object postid,
    Object commenttime,
    Object userid,
    Object comment) throws Exception {

    return
      this.getQuery(kInsertName).getBoundStatement(
        postid,
        commenttime,
        userid,
        comment);
  }

  /**
   * executeAsyncInsert
   * executes Insert Query asynchronously
   * @param postid
   * @param commenttime
   * @param userid
   * @param comment
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncInsert (
    Object postid,
    Object commenttime,
    Object userid,
    Object comment) throws Exception {

    return
      this.getQuery(kInsertName).executeAsync(
        postid,
        commenttime,
        userid,
        comment);
  }

  /**
   * executeSyncInsert
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes Insert Query synchronously
   * @param postid
   * @param commenttime
   * @param userid
   * @param comment
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncInsert (
    Object postid,
    Object commenttime,
    Object userid,
    Object comment) throws Exception {

    return
      this.getQuery(kInsertName).executeSync(
        postid,
        commenttime,
        userid,
        comment);
  }

  // Query: Delete
  // Description:
  //   deletes a post's comment 
  // Parepared Statement:
  //   DELETE FROM ig_app_data.post_comments WHERE post_id = :post_id AND 
  //   comment_time = :comment_time AND user_id = :user_id; 

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
   * @param commenttime
   * @param userid
   * @return Delete Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableDelete (
    Object postid,
    Object commenttime,
    Object userid) throws Exception {

    return
      this.getQueryDispatchable(
        kDeleteName,
        postid,
        commenttime,
        userid);
  }

  /**
   * getBoundStatementDelete
   * @param postid
   * @param commenttime
   * @param userid
   * @return Delete Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementDelete (
    Object postid,
    Object commenttime,
    Object userid) throws Exception {

    return
      this.getQuery(kDeleteName).getBoundStatement(
        postid,
        commenttime,
        userid);
  }

  /**
   * executeAsyncDelete
   * executes Delete Query asynchronously
   * @param postid
   * @param commenttime
   * @param userid
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncDelete (
    Object postid,
    Object commenttime,
    Object userid) throws Exception {

    return
      this.getQuery(kDeleteName).executeAsync(
        postid,
        commenttime,
        userid);
  }

  /**
   * executeSyncDelete
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes Delete Query synchronously
   * @param postid
   * @param commenttime
   * @param userid
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncDelete (
    Object postid,
    Object commenttime,
    Object userid) throws Exception {

    return
      this.getQuery(kDeleteName).executeSync(
        postid,
        commenttime,
        userid);
  }

  // Query: SelectRecentLimit
  // Description:
  //   selects the most recent post's comments where the returned count is 
  //   limited by the value of limit (e.g.: 10) 
  // Parepared Statement:
  //   SELECT comment_time, user_id, comment FROM 
  //   ig_app_data.post_comments WHERE post_id = :post_id LIMIT 30; 

  /**
   * getQuerySelectRecentLimit
   * @return SelectRecentLimit Query in the form of
   *           a Query Object
   * @throws Exception
   */
  public Query getQuerySelectRecentLimit (
    ) throws Exception {

    return this.getQuery(kSelectRecentLimitName);
  }

  /**
   * getQueryDispatchableSelectRecentLimit
   * @param postid
   * @return SelectRecentLimit Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableSelectRecentLimit (
    Object postid) throws Exception {

    return
      this.getQueryDispatchable(
        kSelectRecentLimitName,
        postid);
  }

  /**
   * getBoundStatementSelectRecentLimit
   * @param postid
   * @return SelectRecentLimit Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementSelectRecentLimit (
    Object postid) throws Exception {

    return
      this.getQuery(kSelectRecentLimitName).getBoundStatement(
        postid);
  }

  /**
   * executeAsyncSelectRecentLimit
   * executes SelectRecentLimit Query asynchronously
   * @param postid
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncSelectRecentLimit (
    Object postid) throws Exception {

    return
      this.getQuery(kSelectRecentLimitName).executeAsync(
        postid);
  }

  /**
   * executeSyncSelectRecentLimit
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes SelectRecentLimit Query synchronously
   * @param postid
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncSelectRecentLimit (
    Object postid) throws Exception {

    return
      this.getQuery(kSelectRecentLimitName).executeSync(
        postid);
  }

  // Query: SelectAtOrBeforeTimeLimit
  // Description:
  //   selects post's comments at-or-before a specified time where the 
  //   returned count is limited by the value of limit (e.g.: 10) 
  // Parepared Statement:
  //   SELECT comment_time, user_id, comment FROM 
  //   ig_app_data.post_comments WHERE post_id = :post_id AND 
  //   comment_time <= :comment_time LIMIT 30; 

  /**
   * getQuerySelectAtOrBeforeTimeLimit
   * @return SelectAtOrBeforeTimeLimit Query in the form of
   *           a Query Object
   * @throws Exception
   */
  public Query getQuerySelectAtOrBeforeTimeLimit (
    ) throws Exception {

    return this.getQuery(kSelectAtOrBeforeTimeLimitName);
  }

  /**
   * getQueryDispatchableSelectAtOrBeforeTimeLimit
   * @param postid
   * @param commenttime
   * @return SelectAtOrBeforeTimeLimit Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableSelectAtOrBeforeTimeLimit (
    Object postid,
    Object commenttime) throws Exception {

    return
      this.getQueryDispatchable(
        kSelectAtOrBeforeTimeLimitName,
        postid,
        commenttime);
  }

  /**
   * getBoundStatementSelectAtOrBeforeTimeLimit
   * @param postid
   * @param commenttime
   * @return SelectAtOrBeforeTimeLimit Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementSelectAtOrBeforeTimeLimit (
    Object postid,
    Object commenttime) throws Exception {

    return
      this.getQuery(kSelectAtOrBeforeTimeLimitName).getBoundStatement(
        postid,
        commenttime);
  }

  /**
   * executeAsyncSelectAtOrBeforeTimeLimit
   * executes SelectAtOrBeforeTimeLimit Query asynchronously
   * @param postid
   * @param commenttime
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncSelectAtOrBeforeTimeLimit (
    Object postid,
    Object commenttime) throws Exception {

    return
      this.getQuery(kSelectAtOrBeforeTimeLimitName).executeAsync(
        postid,
        commenttime);
  }

  /**
   * executeSyncSelectAtOrBeforeTimeLimit
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes SelectAtOrBeforeTimeLimit Query synchronously
   * @param postid
   * @param commenttime
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncSelectAtOrBeforeTimeLimit (
    Object postid,
    Object commenttime) throws Exception {

    return
      this.getQuery(kSelectAtOrBeforeTimeLimitName).executeSync(
        postid,
        commenttime);
  }

}
