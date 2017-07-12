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
 * CountPerWeek represents
 *   Table [count_per_week]
 *   in Keyspace [ig_app_data]
 * 
 * Name: count_per_week
 * Description:
 *   stores user-related counts per-week (e.g.: new followers, new 
 *   unfollowers, new posts, ...) -- used for ranking/scoring 
 * 
 * Columns:
 *   user_id_year_week : varchar
 *   follower_count : counter
 *   unfollower_count : counter
 *   posts_count : counter
 *   likes_received_count : counter
 *   comments_received_count : counter

 * Partition Keys: user_id_year_week
 * Secondary Keys: 
 * Caching: ALL
 * Order By:

 * Queries:
 *   - Name: increment_follower_count
 *   Description:
 *     increments new followers count 
 *   Prepared Statement:
 *     UPDATE ig_app_data.count_per_week SET follower_count = 
 *     follower_count + 1 WHERE user_id_year_week = 
 *     :user_id_year_week; 
 *   - Name: increment_unfollower_count
 *   Description:
 *     increments new unfollowers count 
 *   Prepared Statement:
 *     UPDATE ig_app_data.count_per_week SET unfollower_count = 
 *     unfollower_count + 1 WHERE user_id_year_week = 
 *     :user_id_year_week; 
 *   - Name: increment_posts_count
 *   Description:
 *     increments new posts by user count 
 *   Prepared Statement:
 *     UPDATE ig_app_data.count_per_week SET posts_count = posts_count + 1 
 *     WHERE user_id_year_week = :user_id_year_week; 
 *   - Name: increment_likes_received_count
 *   Description:
 *     increments received likes count 
 *   Prepared Statement:
 *     UPDATE ig_app_data.count_per_week SET likes_received_count = 
 *     likes_received_count + 1 WHERE user_id_year_week = 
 *     :user_id_year_week; 
 *   - Name: increment_comments_received_count
 *   Description:
 *     increments received comments count 
 *   Prepared Statement:
 *     UPDATE ig_app_data.count_per_week SET comments_received_count = 
 *     comments_received_count + 1 WHERE user_id_year_week = 
 *     :user_id_year_week; 
 *   - Name: select
 *   Description:
 *     selects all counters 
 *   Prepared Statement:
 *     SELECT follower_count, unfollower_count, posts_count, 
 *     likes_received_count, comments_received_count FROM 
 *     ig_app_data.count_per_week WHERE user_id_year_week = 
 *     :user_id_year_week; 
 * */
public class CountPerWeek extends Table {

  private static final String kKeySpaceName =
    "ig_app_data";
  private static final String kTableName =
    "count_per_week";

  public static final String kUserIdYearWeekColumnName =
    "user_id_year_week";
  public static final String kFollowerCountColumnName =
    "follower_count";
  public static final String kUnfollowerCountColumnName =
    "unfollower_count";
  public static final String kPostsCountColumnName =
    "posts_count";
  public static final String kLikesReceivedCountColumnName =
    "likes_received_count";
  public static final String kCommentsReceivedCountColumnName =
    "comments_received_count";

  /**
   * Query:
   * Name: increment_follower_count
   * Description:
   *   increments new followers count 
   * Prepared Statement:
   *   UPDATE ig_app_data.count_per_week SET follower_count = 
   *   follower_count + 1 WHERE user_id_year_week = 
   *   :user_id_year_week; 
   */
  private static final String kIncrementFollowerCountName =
    "increment_follower_count";
  private static final String kIncrementFollowerCountDescription =
    "increments new followers count ";
  private static final String kIncrementFollowerCountPreparedStatement =
    "UPDATE ig_app_data.count_per_week SET follower_count = follower_count "
    + "+ 1 WHERE user_id_year_week = :user_id_year_week; ";

  /**
   * Query:
   * Name: increment_unfollower_count
   * Description:
   *   increments new unfollowers count 
   * Prepared Statement:
   *   UPDATE ig_app_data.count_per_week SET unfollower_count = 
   *   unfollower_count + 1 WHERE user_id_year_week = 
   *   :user_id_year_week; 
   */
  private static final String kIncrementUnfollowerCountName =
    "increment_unfollower_count";
  private static final String kIncrementUnfollowerCountDescription =
    "increments new unfollowers count ";
  private static final String kIncrementUnfollowerCountPreparedStatement =
    "UPDATE ig_app_data.count_per_week SET unfollower_count = "
    + "unfollower_count + 1 WHERE user_id_year_week = "
    + ":user_id_year_week; ";

  /**
   * Query:
   * Name: increment_posts_count
   * Description:
   *   increments new posts by user count 
   * Prepared Statement:
   *   UPDATE ig_app_data.count_per_week SET posts_count = posts_count + 1 
   *   WHERE user_id_year_week = :user_id_year_week; 
   */
  private static final String kIncrementPostsCountName =
    "increment_posts_count";
  private static final String kIncrementPostsCountDescription =
    "increments new posts by user count ";
  private static final String kIncrementPostsCountPreparedStatement =
    "UPDATE ig_app_data.count_per_week SET posts_count = posts_count + 1 "
    + "WHERE user_id_year_week = :user_id_year_week; ";

  /**
   * Query:
   * Name: increment_likes_received_count
   * Description:
   *   increments received likes count 
   * Prepared Statement:
   *   UPDATE ig_app_data.count_per_week SET likes_received_count = 
   *   likes_received_count + 1 WHERE user_id_year_week = 
   *   :user_id_year_week; 
   */
  private static final String kIncrementLikesReceivedCountName =
    "increment_likes_received_count";
  private static final String kIncrementLikesReceivedCountDescription =
    "increments received likes count ";
  private static final String kIncrementLikesReceivedCountPreparedStatement =
    "UPDATE ig_app_data.count_per_week SET likes_received_count = "
    + "likes_received_count + 1 WHERE user_id_year_week = "
    + ":user_id_year_week; ";

  /**
   * Query:
   * Name: increment_comments_received_count
   * Description:
   *   increments received comments count 
   * Prepared Statement:
   *   UPDATE ig_app_data.count_per_week SET comments_received_count = 
   *   comments_received_count + 1 WHERE user_id_year_week = 
   *   :user_id_year_week; 
   */
  private static final String kIncrementCommentsReceivedCountName =
    "increment_comments_received_count";
  private static final String kIncrementCommentsReceivedCountDescription =
    "increments received comments count ";
  private static final String kIncrementCommentsReceivedCountPreparedStatement =
    "UPDATE ig_app_data.count_per_week SET comments_received_count = "
    + "comments_received_count + 1 WHERE user_id_year_week = "
    + ":user_id_year_week; ";

  /**
   * Query:
   * Name: select
   * Description:
   *   selects all counters 
   * Prepared Statement:
   *   SELECT follower_count, unfollower_count, posts_count, 
   *   likes_received_count, comments_received_count FROM 
   *   ig_app_data.count_per_week WHERE user_id_year_week = 
   *   :user_id_year_week; 
   */
  private static final String kSelectName =
    "select";
  private static final String kSelectDescription =
    "selects all counters ";
  private static final String kSelectPreparedStatement =
    "SELECT follower_count, unfollower_count, posts_count, "
    + "likes_received_count, comments_received_count FROM "
    + "ig_app_data.count_per_week WHERE user_id_year_week = "
    + ":user_id_year_week; ";

  /**
   * Constructor CountPerWeek
   * @return new CountPerWeek Object
   * @throws Exception
   */
  private CountPerWeek () throws Exception {

    super (
      kKeySpaceName,
      kTableName,
      new Query (
        kIncrementFollowerCountDescription,
        kIncrementFollowerCountName,
        kIncrementFollowerCountPreparedStatement),
      new Query (
        kIncrementUnfollowerCountDescription,
        kIncrementUnfollowerCountName,
        kIncrementUnfollowerCountPreparedStatement),
      new Query (
        kIncrementPostsCountDescription,
        kIncrementPostsCountName,
        kIncrementPostsCountPreparedStatement),
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

  private static CountPerWeek instance = null;

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

      instance = new CountPerWeek();
    }
  }

  /**
   * i
   * @return singleton static instance of CountPerWeek
   * @throws Exception
   */
  public static CountPerWeek i () throws Exception {

    if (instance == null) {

      instance = new CountPerWeek();
    }

    return instance;
  }

  // Query: IncrementFollowerCount
  // Description:
  //   increments new followers count 
  // Parepared Statement:
  //   UPDATE ig_app_data.count_per_week SET follower_count = 
  //   follower_count + 1 WHERE user_id_year_week = 
  //   :user_id_year_week; 

  /**
   * getQueryIncrementFollowerCount
   * @return IncrementFollowerCount Query in the form of
   *           a Query Object
   * @throws Exception
   */
  public Query getQueryIncrementFollowerCount (
    ) throws Exception {

    return this.getQuery(kIncrementFollowerCountName);
  }

  /**
   * getQueryDispatchableIncrementFollowerCount
   * @param useridyearweek
   * @return IncrementFollowerCount Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableIncrementFollowerCount (
    Object useridyearweek) throws Exception {

    return
      this.getQueryDispatchable(
        kIncrementFollowerCountName,
        useridyearweek);
  }

  /**
   * getBoundStatementIncrementFollowerCount
   * @param useridyearweek
   * @return IncrementFollowerCount Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementIncrementFollowerCount (
    Object useridyearweek) throws Exception {

    return
      this.getQuery(kIncrementFollowerCountName).getBoundStatement(
        useridyearweek);
  }

  /**
   * executeAsyncIncrementFollowerCount
   * executes IncrementFollowerCount Query asynchronously
   * @param useridyearweek
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncIncrementFollowerCount (
    Object useridyearweek) throws Exception {

    return
      this.getQuery(kIncrementFollowerCountName).executeAsync(
        useridyearweek);
  }

  /**
   * executeSyncIncrementFollowerCount
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes IncrementFollowerCount Query synchronously
   * @param useridyearweek
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncIncrementFollowerCount (
    Object useridyearweek) throws Exception {

    return
      this.getQuery(kIncrementFollowerCountName).executeSync(
        useridyearweek);
  }

  // Query: IncrementUnfollowerCount
  // Description:
  //   increments new unfollowers count 
  // Parepared Statement:
  //   UPDATE ig_app_data.count_per_week SET unfollower_count = 
  //   unfollower_count + 1 WHERE user_id_year_week = 
  //   :user_id_year_week; 

  /**
   * getQueryIncrementUnfollowerCount
   * @return IncrementUnfollowerCount Query in the form of
   *           a Query Object
   * @throws Exception
   */
  public Query getQueryIncrementUnfollowerCount (
    ) throws Exception {

    return this.getQuery(kIncrementUnfollowerCountName);
  }

  /**
   * getQueryDispatchableIncrementUnfollowerCount
   * @param useridyearweek
   * @return IncrementUnfollowerCount Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableIncrementUnfollowerCount (
    Object useridyearweek) throws Exception {

    return
      this.getQueryDispatchable(
        kIncrementUnfollowerCountName,
        useridyearweek);
  }

  /**
   * getBoundStatementIncrementUnfollowerCount
   * @param useridyearweek
   * @return IncrementUnfollowerCount Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementIncrementUnfollowerCount (
    Object useridyearweek) throws Exception {

    return
      this.getQuery(kIncrementUnfollowerCountName).getBoundStatement(
        useridyearweek);
  }

  /**
   * executeAsyncIncrementUnfollowerCount
   * executes IncrementUnfollowerCount Query asynchronously
   * @param useridyearweek
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncIncrementUnfollowerCount (
    Object useridyearweek) throws Exception {

    return
      this.getQuery(kIncrementUnfollowerCountName).executeAsync(
        useridyearweek);
  }

  /**
   * executeSyncIncrementUnfollowerCount
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes IncrementUnfollowerCount Query synchronously
   * @param useridyearweek
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncIncrementUnfollowerCount (
    Object useridyearweek) throws Exception {

    return
      this.getQuery(kIncrementUnfollowerCountName).executeSync(
        useridyearweek);
  }

  // Query: IncrementPostsCount
  // Description:
  //   increments new posts by user count 
  // Parepared Statement:
  //   UPDATE ig_app_data.count_per_week SET posts_count = posts_count + 1 
  //   WHERE user_id_year_week = :user_id_year_week; 

  /**
   * getQueryIncrementPostsCount
   * @return IncrementPostsCount Query in the form of
   *           a Query Object
   * @throws Exception
   */
  public Query getQueryIncrementPostsCount (
    ) throws Exception {

    return this.getQuery(kIncrementPostsCountName);
  }

  /**
   * getQueryDispatchableIncrementPostsCount
   * @param useridyearweek
   * @return IncrementPostsCount Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableIncrementPostsCount (
    Object useridyearweek) throws Exception {

    return
      this.getQueryDispatchable(
        kIncrementPostsCountName,
        useridyearweek);
  }

  /**
   * getBoundStatementIncrementPostsCount
   * @param useridyearweek
   * @return IncrementPostsCount Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementIncrementPostsCount (
    Object useridyearweek) throws Exception {

    return
      this.getQuery(kIncrementPostsCountName).getBoundStatement(
        useridyearweek);
  }

  /**
   * executeAsyncIncrementPostsCount
   * executes IncrementPostsCount Query asynchronously
   * @param useridyearweek
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncIncrementPostsCount (
    Object useridyearweek) throws Exception {

    return
      this.getQuery(kIncrementPostsCountName).executeAsync(
        useridyearweek);
  }

  /**
   * executeSyncIncrementPostsCount
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes IncrementPostsCount Query synchronously
   * @param useridyearweek
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncIncrementPostsCount (
    Object useridyearweek) throws Exception {

    return
      this.getQuery(kIncrementPostsCountName).executeSync(
        useridyearweek);
  }

  // Query: IncrementLikesReceivedCount
  // Description:
  //   increments received likes count 
  // Parepared Statement:
  //   UPDATE ig_app_data.count_per_week SET likes_received_count = 
  //   likes_received_count + 1 WHERE user_id_year_week = 
  //   :user_id_year_week; 

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
   * @param useridyearweek
   * @return IncrementLikesReceivedCount Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableIncrementLikesReceivedCount (
    Object useridyearweek) throws Exception {

    return
      this.getQueryDispatchable(
        kIncrementLikesReceivedCountName,
        useridyearweek);
  }

  /**
   * getBoundStatementIncrementLikesReceivedCount
   * @param useridyearweek
   * @return IncrementLikesReceivedCount Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementIncrementLikesReceivedCount (
    Object useridyearweek) throws Exception {

    return
      this.getQuery(kIncrementLikesReceivedCountName).getBoundStatement(
        useridyearweek);
  }

  /**
   * executeAsyncIncrementLikesReceivedCount
   * executes IncrementLikesReceivedCount Query asynchronously
   * @param useridyearweek
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncIncrementLikesReceivedCount (
    Object useridyearweek) throws Exception {

    return
      this.getQuery(kIncrementLikesReceivedCountName).executeAsync(
        useridyearweek);
  }

  /**
   * executeSyncIncrementLikesReceivedCount
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes IncrementLikesReceivedCount Query synchronously
   * @param useridyearweek
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncIncrementLikesReceivedCount (
    Object useridyearweek) throws Exception {

    return
      this.getQuery(kIncrementLikesReceivedCountName).executeSync(
        useridyearweek);
  }

  // Query: IncrementCommentsReceivedCount
  // Description:
  //   increments received comments count 
  // Parepared Statement:
  //   UPDATE ig_app_data.count_per_week SET comments_received_count = 
  //   comments_received_count + 1 WHERE user_id_year_week = 
  //   :user_id_year_week; 

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
   * @param useridyearweek
   * @return IncrementCommentsReceivedCount Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableIncrementCommentsReceivedCount (
    Object useridyearweek) throws Exception {

    return
      this.getQueryDispatchable(
        kIncrementCommentsReceivedCountName,
        useridyearweek);
  }

  /**
   * getBoundStatementIncrementCommentsReceivedCount
   * @param useridyearweek
   * @return IncrementCommentsReceivedCount Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementIncrementCommentsReceivedCount (
    Object useridyearweek) throws Exception {

    return
      this.getQuery(kIncrementCommentsReceivedCountName).getBoundStatement(
        useridyearweek);
  }

  /**
   * executeAsyncIncrementCommentsReceivedCount
   * executes IncrementCommentsReceivedCount Query asynchronously
   * @param useridyearweek
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncIncrementCommentsReceivedCount (
    Object useridyearweek) throws Exception {

    return
      this.getQuery(kIncrementCommentsReceivedCountName).executeAsync(
        useridyearweek);
  }

  /**
   * executeSyncIncrementCommentsReceivedCount
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes IncrementCommentsReceivedCount Query synchronously
   * @param useridyearweek
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncIncrementCommentsReceivedCount (
    Object useridyearweek) throws Exception {

    return
      this.getQuery(kIncrementCommentsReceivedCountName).executeSync(
        useridyearweek);
  }

  // Query: Select
  // Description:
  //   selects all counters 
  // Parepared Statement:
  //   SELECT follower_count, unfollower_count, posts_count, 
  //   likes_received_count, comments_received_count FROM 
  //   ig_app_data.count_per_week WHERE user_id_year_week = 
  //   :user_id_year_week; 

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
   * @param useridyearweek
   * @return Select Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableSelect (
    Object useridyearweek) throws Exception {

    return
      this.getQueryDispatchable(
        kSelectName,
        useridyearweek);
  }

  /**
   * getBoundStatementSelect
   * @param useridyearweek
   * @return Select Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementSelect (
    Object useridyearweek) throws Exception {

    return
      this.getQuery(kSelectName).getBoundStatement(
        useridyearweek);
  }

  /**
   * executeAsyncSelect
   * executes Select Query asynchronously
   * @param useridyearweek
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncSelect (
    Object useridyearweek) throws Exception {

    return
      this.getQuery(kSelectName).executeAsync(
        useridyearweek);
  }

  /**
   * executeSyncSelect
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes Select Query synchronously
   * @param useridyearweek
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncSelect (
    Object useridyearweek) throws Exception {

    return
      this.getQuery(kSelectName).executeSync(
        useridyearweek);
  }

}
