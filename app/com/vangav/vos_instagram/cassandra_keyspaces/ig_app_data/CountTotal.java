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
 * CountTotal represents
 *   Table [count_total]
 *   in Keyspace [ig_app_data]
 * 
 * Name: count_total
 * Description:
 *   stores user-related counts (e.g.: total likes/comments received, ...) 
 *   -- used for ranking/scoring 
 * 
 * Columns:
 *   user_id : uuid
 *   likes_received_count : counter
 *   comments_received_count : counter

 * Partition Keys: user_id
 * Secondary Keys: 
 * Caching: ALL
 * Order By:

 * Queries:
 *   - Name: increment_likes_received_count
 *   Description:
 *     increments received likes count 
 *   Prepared Statement:
 *     UPDATE ig_app_data.count_total SET likes_received_count = 
 *     likes_received_count + 1 WHERE user_id = :user_id; 
 *   - Name: increment_comments_received_count
 *   Description:
 *     increments received comments count 
 *   Prepared Statement:
 *     UPDATE ig_app_data.count_total SET comments_received_count = 
 *     comments_received_count + 1 WHERE user_id = :user_id; 
 *   - Name: select
 *   Description:
 *     selects all counters 
 *   Prepared Statement:
 *     SELECT likes_received_count, comments_received_count FROM 
 *     ig_app_data.count_total WHERE user_id = :user_id; 
 * */
public class CountTotal extends Table {

  private static final String kKeySpaceName =
    "ig_app_data";
  private static final String kTableName =
    "count_total";

  public static final String kUserIdColumnName =
    "user_id";
  public static final String kLikesReceivedCountColumnName =
    "likes_received_count";
  public static final String kCommentsReceivedCountColumnName =
    "comments_received_count";

  /**
   * Query:
   * Name: increment_likes_received_count
   * Description:
   *   increments received likes count 
   * Prepared Statement:
   *   UPDATE ig_app_data.count_total SET likes_received_count = 
   *   likes_received_count + 1 WHERE user_id = :user_id; 
   */
  private static final String kIncrementLikesReceivedCountName =
    "increment_likes_received_count";
  private static final String kIncrementLikesReceivedCountDescription =
    "increments received likes count ";
  private static final String kIncrementLikesReceivedCountPreparedStatement =
    "UPDATE ig_app_data.count_total SET likes_received_count = "
    + "likes_received_count + 1 WHERE user_id = :user_id; ";

  /**
   * Query:
   * Name: increment_comments_received_count
   * Description:
   *   increments received comments count 
   * Prepared Statement:
   *   UPDATE ig_app_data.count_total SET comments_received_count = 
   *   comments_received_count + 1 WHERE user_id = :user_id; 
   */
  private static final String kIncrementCommentsReceivedCountName =
    "increment_comments_received_count";
  private static final String kIncrementCommentsReceivedCountDescription =
    "increments received comments count ";
  private static final String kIncrementCommentsReceivedCountPreparedStatement =
    "UPDATE ig_app_data.count_total SET comments_received_count = "
    + "comments_received_count + 1 WHERE user_id = :user_id; ";

  /**
   * Query:
   * Name: select
   * Description:
   *   selects all counters 
   * Prepared Statement:
   *   SELECT likes_received_count, comments_received_count FROM 
   *   ig_app_data.count_total WHERE user_id = :user_id; 
   */
  private static final String kSelectName =
    "select";
  private static final String kSelectDescription =
    "selects all counters ";
  private static final String kSelectPreparedStatement =
    "SELECT likes_received_count, comments_received_count FROM "
    + "ig_app_data.count_total WHERE user_id = :user_id; ";

  /**
   * Constructor CountTotal
   * @return new CountTotal Object
   * @throws Exception
   */
  private CountTotal () throws Exception {

    super (
      kKeySpaceName,
      kTableName,
      new Query (
        kIncrementLikesReceivedCountDescription,
        kIncrementLikesReceivedCountName,
        kIncrementLikesReceivedCountPreparedStatement),
      new Query (
        kIncrementCommentsReceivedCountDescription,
        kIncrementCommentsReceivedCountName,
        kIncrementCommentsReceivedCountPreparedStatement),
      new Query (
        kSelectDescription,
        kSelectName,
        kSelectPreparedStatement));
  }

  private static CountTotal instance = null;

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

      instance = new CountTotal();
    }
  }

  /**
   * i
   * @return singleton static instance of CountTotal
   * @throws Exception
   */
  public static CountTotal i () throws Exception {

    if (instance == null) {

      instance = new CountTotal();
    }

    return instance;
  }

  // Query: IncrementLikesReceivedCount
  // Description:
  //   increments received likes count 
  // Parepared Statement:
  //   UPDATE ig_app_data.count_total SET likes_received_count = 
  //   likes_received_count + 1 WHERE user_id = :user_id; 

  /**
   * getQueryIncrementLikesReceivedCount
   * @return IncrementLikesReceivedCount Query in the form of
   *           a Query Object
   * @throws Exception
   */
  public Query getQueryIncrementLikesReceivedCount (
    ) throws Exception {

    return this.getQuery(kIncrementLikesReceivedCountName);
  }

  /**
   * getQueryDispatchableIncrementLikesReceivedCount
   * @param userid
   * @return IncrementLikesReceivedCount Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableIncrementLikesReceivedCount (
    Object userid) throws Exception {

    return
      this.getQueryDispatchable(
        kIncrementLikesReceivedCountName,
        userid);
  }

  /**
   * getBoundStatementIncrementLikesReceivedCount
   * @param userid
   * @return IncrementLikesReceivedCount Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementIncrementLikesReceivedCount (
    Object userid) throws Exception {

    return
      this.getQuery(kIncrementLikesReceivedCountName).getBoundStatement(
        userid);
  }

  /**
   * executeAsyncIncrementLikesReceivedCount
   * executes IncrementLikesReceivedCount Query asynchronously
   * @param userid
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncIncrementLikesReceivedCount (
    Object userid) throws Exception {

    return
      this.getQuery(kIncrementLikesReceivedCountName).executeAsync(
        userid);
  }

  /**
   * executeSyncIncrementLikesReceivedCount
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes IncrementLikesReceivedCount Query synchronously
   * @param userid
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncIncrementLikesReceivedCount (
    Object userid) throws Exception {

    return
      this.getQuery(kIncrementLikesReceivedCountName).executeSync(
        userid);
  }

  // Query: IncrementCommentsReceivedCount
  // Description:
  //   increments received comments count 
  // Parepared Statement:
  //   UPDATE ig_app_data.count_total SET comments_received_count = 
  //   comments_received_count + 1 WHERE user_id = :user_id; 

  /**
   * getQueryIncrementCommentsReceivedCount
   * @return IncrementCommentsReceivedCount Query in the form of
   *           a Query Object
   * @throws Exception
   */
  public Query getQueryIncrementCommentsReceivedCount (
    ) throws Exception {

    return this.getQuery(kIncrementCommentsReceivedCountName);
  }

  /**
   * getQueryDispatchableIncrementCommentsReceivedCount
   * @param userid
   * @return IncrementCommentsReceivedCount Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableIncrementCommentsReceivedCount (
    Object userid) throws Exception {

    return
      this.getQueryDispatchable(
        kIncrementCommentsReceivedCountName,
        userid);
  }

  /**
   * getBoundStatementIncrementCommentsReceivedCount
   * @param userid
   * @return IncrementCommentsReceivedCount Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementIncrementCommentsReceivedCount (
    Object userid) throws Exception {

    return
      this.getQuery(kIncrementCommentsReceivedCountName).getBoundStatement(
        userid);
  }

  /**
   * executeAsyncIncrementCommentsReceivedCount
   * executes IncrementCommentsReceivedCount Query asynchronously
   * @param userid
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncIncrementCommentsReceivedCount (
    Object userid) throws Exception {

    return
      this.getQuery(kIncrementCommentsReceivedCountName).executeAsync(
        userid);
  }

  /**
   * executeSyncIncrementCommentsReceivedCount
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes IncrementCommentsReceivedCount Query synchronously
   * @param userid
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncIncrementCommentsReceivedCount (
    Object userid) throws Exception {

    return
      this.getQuery(kIncrementCommentsReceivedCountName).executeSync(
        userid);
  }

  // Query: Select
  // Description:
  //   selects all counters 
  // Parepared Statement:
  //   SELECT likes_received_count, comments_received_count FROM 
  //   ig_app_data.count_total WHERE user_id = :user_id; 

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
   * @return Select Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableSelect (
    Object userid) throws Exception {

    return
      this.getQueryDispatchable(
        kSelectName,
        userid);
  }

  /**
   * getBoundStatementSelect
   * @param userid
   * @return Select Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementSelect (
    Object userid) throws Exception {

    return
      this.getQuery(kSelectName).getBoundStatement(
        userid);
  }

  /**
   * executeAsyncSelect
   * executes Select Query asynchronously
   * @param userid
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncSelect (
    Object userid) throws Exception {

    return
      this.getQuery(kSelectName).executeAsync(
        userid);
  }

  /**
   * executeSyncSelect
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes Select Query synchronously
   * @param userid
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncSelect (
    Object userid) throws Exception {

    return
      this.getQuery(kSelectName).executeSync(
        userid);
  }

}
