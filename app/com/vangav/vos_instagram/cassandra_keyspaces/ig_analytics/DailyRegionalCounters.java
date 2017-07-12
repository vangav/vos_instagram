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

package com.vangav.vos_instagram.cassandra_keyspaces.ig_analytics;

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
 * DailyRegionalCounters represents
 *   Table [daily_regional_counters]
 *   in Keyspace [ig_analytics]
 * 
 * Name: daily_regional_counters
 * Description:
 *   stores counters per-day per-region (world, continent, country, ...) 
 * 
 * Columns:
 *   year_month_day_region : varchar
 *   new_users : counter
 *   new_posts : counter
 *   sent_follows : counter
 *   received_follows : counter
 *   sent_unfollows : counter
 *   received_unfollows : counter
 *   sent_likes : counter
 *   received_likes : counter
 *   sent_comments : counter
 *   received_comments : counter

 * Partition Keys: year_month_day_region
 * Secondary Keys: 
 * Caching: ALL
 * Order By:

 * Queries:
 *   - Name: increment_new_users
 *   Description:
 *     increments new_users 
 *   Prepared Statement:
 *     UPDATE ig_analytics.daily_regional_counters SET new_users = 
 *     new_users + 1 WHERE year_month_day_region = 
 *     :year_month_day_region; 
 *   - Name: increment_new_posts
 *   Description:
 *     increments new_posts 
 *   Prepared Statement:
 *     UPDATE ig_analytics.daily_regional_counters SET new_posts = 
 *     new_posts + 1 WHERE year_month_day_region = 
 *     :year_month_day_region; 
 *   - Name: increment_sent_follows
 *   Description:
 *     increments sent_follows 
 *   Prepared Statement:
 *     UPDATE ig_analytics.daily_regional_counters SET sent_follows = 
 *     sent_follows + 1 WHERE year_month_day_region = 
 *     :year_month_day_region; 
 *   - Name: increment_received_follows
 *   Description:
 *     increments received_follows 
 *   Prepared Statement:
 *     UPDATE ig_analytics.daily_regional_counters SET received_follows = 
 *     received_follows + 1 WHERE year_month_day_region = 
 *     :year_month_day_region; 
 *   - Name: increment_sent_unfollows
 *   Description:
 *     increments sent_unfollows 
 *   Prepared Statement:
 *     UPDATE ig_analytics.daily_regional_counters SET sent_unfollows = 
 *     sent_unfollows + 1 WHERE year_month_day_region = 
 *     :year_month_day_region; 
 *   - Name: increment_received_unfollows
 *   Description:
 *     increments received_unfollows 
 *   Prepared Statement:
 *     UPDATE ig_analytics.daily_regional_counters SET received_unfollows 
 *     = received_unfollows + 1 WHERE year_month_day_region = 
 *     :year_month_day_region; 
 *   - Name: increment_sent_likes
 *   Description:
 *     increments sent_likes 
 *   Prepared Statement:
 *     UPDATE ig_analytics.daily_regional_counters SET sent_likes = 
 *     sent_likes + 1 WHERE year_month_day_region = 
 *     :year_month_day_region; 
 *   - Name: increment_received_likes
 *   Description:
 *     increments received_likes 
 *   Prepared Statement:
 *     UPDATE ig_analytics.daily_regional_counters SET received_likes = 
 *     received_likes + 1 WHERE year_month_day_region = 
 *     :year_month_day_region; 
 *   - Name: increment_sent_comments
 *   Description:
 *     increments sent_comments 
 *   Prepared Statement:
 *     UPDATE ig_analytics.daily_regional_counters SET sent_comments = 
 *     sent_comments + 1 WHERE year_month_day_region = 
 *     :year_month_day_region; 
 *   - Name: increment_received_comments
 *   Description:
 *     increments received_comments 
 *   Prepared Statement:
 *     UPDATE ig_analytics.daily_regional_counters SET received_comments = 
 *     received_comments + 1 WHERE year_month_day_region = 
 *     :year_month_day_region; 
 *   - Name: select
 *   Description:
 *     selects counters per-year per-region 
 *   Prepared Statement:
 *     SELECT new_users, new_posts, sent_follows, received_follows, 
 *     sent_unfollows, received_unfollows, sent_likes, 
 *     received_likes, sent_comments, received_comments FROM 
 *     ig_analytics.daily_regional_counters WHERE 
 *     year_month_day_region = :year_month_day_region; 
 * */
public class DailyRegionalCounters extends Table {

  private static final String kKeySpaceName =
    "ig_analytics";
  private static final String kTableName =
    "daily_regional_counters";

  public static final String kYearMonthDayRegionColumnName =
    "year_month_day_region";
  public static final String kNewUsersColumnName =
    "new_users";
  public static final String kNewPostsColumnName =
    "new_posts";
  public static final String kSentFollowsColumnName =
    "sent_follows";
  public static final String kReceivedFollowsColumnName =
    "received_follows";
  public static final String kSentUnfollowsColumnName =
    "sent_unfollows";
  public static final String kReceivedUnfollowsColumnName =
    "received_unfollows";
  public static final String kSentLikesColumnName =
    "sent_likes";
  public static final String kReceivedLikesColumnName =
    "received_likes";
  public static final String kSentCommentsColumnName =
    "sent_comments";
  public static final String kReceivedCommentsColumnName =
    "received_comments";

  /**
   * Query:
   * Name: increment_new_users
   * Description:
   *   increments new_users 
   * Prepared Statement:
   *   UPDATE ig_analytics.daily_regional_counters SET new_users = 
   *   new_users + 1 WHERE year_month_day_region = 
   *   :year_month_day_region; 
   */
  private static final String kIncrementNewUsersName =
    "increment_new_users";
  private static final String kIncrementNewUsersDescription =
    "increments new_users ";
  private static final String kIncrementNewUsersPreparedStatement =
    "UPDATE ig_analytics.daily_regional_counters SET new_users = new_users "
    + "+ 1 WHERE year_month_day_region = :year_month_day_region; ";

  /**
   * Query:
   * Name: increment_new_posts
   * Description:
   *   increments new_posts 
   * Prepared Statement:
   *   UPDATE ig_analytics.daily_regional_counters SET new_posts = 
   *   new_posts + 1 WHERE year_month_day_region = 
   *   :year_month_day_region; 
   */
  private static final String kIncrementNewPostsName =
    "increment_new_posts";
  private static final String kIncrementNewPostsDescription =
    "increments new_posts ";
  private static final String kIncrementNewPostsPreparedStatement =
    "UPDATE ig_analytics.daily_regional_counters SET new_posts = new_posts "
    + "+ 1 WHERE year_month_day_region = :year_month_day_region; ";

  /**
   * Query:
   * Name: increment_sent_follows
   * Description:
   *   increments sent_follows 
   * Prepared Statement:
   *   UPDATE ig_analytics.daily_regional_counters SET sent_follows = 
   *   sent_follows + 1 WHERE year_month_day_region = 
   *   :year_month_day_region; 
   */
  private static final String kIncrementSentFollowsName =
    "increment_sent_follows";
  private static final String kIncrementSentFollowsDescription =
    "increments sent_follows ";
  private static final String kIncrementSentFollowsPreparedStatement =
    "UPDATE ig_analytics.daily_regional_counters SET sent_follows = "
    + "sent_follows + 1 WHERE year_month_day_region = "
    + ":year_month_day_region; ";

  /**
   * Query:
   * Name: increment_received_follows
   * Description:
   *   increments received_follows 
   * Prepared Statement:
   *   UPDATE ig_analytics.daily_regional_counters SET received_follows = 
   *   received_follows + 1 WHERE year_month_day_region = 
   *   :year_month_day_region; 
   */
  private static final String kIncrementReceivedFollowsName =
    "increment_received_follows";
  private static final String kIncrementReceivedFollowsDescription =
    "increments received_follows ";
  private static final String kIncrementReceivedFollowsPreparedStatement =
    "UPDATE ig_analytics.daily_regional_counters SET received_follows = "
    + "received_follows + 1 WHERE year_month_day_region = "
    + ":year_month_day_region; ";

  /**
   * Query:
   * Name: increment_sent_unfollows
   * Description:
   *   increments sent_unfollows 
   * Prepared Statement:
   *   UPDATE ig_analytics.daily_regional_counters SET sent_unfollows = 
   *   sent_unfollows + 1 WHERE year_month_day_region = 
   *   :year_month_day_region; 
   */
  private static final String kIncrementSentUnfollowsName =
    "increment_sent_unfollows";
  private static final String kIncrementSentUnfollowsDescription =
    "increments sent_unfollows ";
  private static final String kIncrementSentUnfollowsPreparedStatement =
    "UPDATE ig_analytics.daily_regional_counters SET sent_unfollows = "
    + "sent_unfollows + 1 WHERE year_month_day_region = "
    + ":year_month_day_region; ";

  /**
   * Query:
   * Name: increment_received_unfollows
   * Description:
   *   increments received_unfollows 
   * Prepared Statement:
   *   UPDATE ig_analytics.daily_regional_counters SET received_unfollows 
   *   = received_unfollows + 1 WHERE year_month_day_region = 
   *   :year_month_day_region; 
   */
  private static final String kIncrementReceivedUnfollowsName =
    "increment_received_unfollows";
  private static final String kIncrementReceivedUnfollowsDescription =
    "increments received_unfollows ";
  private static final String kIncrementReceivedUnfollowsPreparedStatement =
    "UPDATE ig_analytics.daily_regional_counters SET received_unfollows = "
    + "received_unfollows + 1 WHERE year_month_day_region = "
    + ":year_month_day_region; ";

  /**
   * Query:
   * Name: increment_sent_likes
   * Description:
   *   increments sent_likes 
   * Prepared Statement:
   *   UPDATE ig_analytics.daily_regional_counters SET sent_likes = 
   *   sent_likes + 1 WHERE year_month_day_region = 
   *   :year_month_day_region; 
   */
  private static final String kIncrementSentLikesName =
    "increment_sent_likes";
  private static final String kIncrementSentLikesDescription =
    "increments sent_likes ";
  private static final String kIncrementSentLikesPreparedStatement =
    "UPDATE ig_analytics.daily_regional_counters SET sent_likes = "
    + "sent_likes + 1 WHERE year_month_day_region = "
    + ":year_month_day_region; ";

  /**
   * Query:
   * Name: increment_received_likes
   * Description:
   *   increments received_likes 
   * Prepared Statement:
   *   UPDATE ig_analytics.daily_regional_counters SET received_likes = 
   *   received_likes + 1 WHERE year_month_day_region = 
   *   :year_month_day_region; 
   */
  private static final String kIncrementReceivedLikesName =
    "increment_received_likes";
  private static final String kIncrementReceivedLikesDescription =
    "increments received_likes ";
  private static final String kIncrementReceivedLikesPreparedStatement =
    "UPDATE ig_analytics.daily_regional_counters SET received_likes = "
    + "received_likes + 1 WHERE year_month_day_region = "
    + ":year_month_day_region; ";

  /**
   * Query:
   * Name: increment_sent_comments
   * Description:
   *   increments sent_comments 
   * Prepared Statement:
   *   UPDATE ig_analytics.daily_regional_counters SET sent_comments = 
   *   sent_comments + 1 WHERE year_month_day_region = 
   *   :year_month_day_region; 
   */
  private static final String kIncrementSentCommentsName =
    "increment_sent_comments";
  private static final String kIncrementSentCommentsDescription =
    "increments sent_comments ";
  private static final String kIncrementSentCommentsPreparedStatement =
    "UPDATE ig_analytics.daily_regional_counters SET sent_comments = "
    + "sent_comments + 1 WHERE year_month_day_region = "
    + ":year_month_day_region; ";

  /**
   * Query:
   * Name: increment_received_comments
   * Description:
   *   increments received_comments 
   * Prepared Statement:
   *   UPDATE ig_analytics.daily_regional_counters SET received_comments = 
   *   received_comments + 1 WHERE year_month_day_region = 
   *   :year_month_day_region; 
   */
  private static final String kIncrementReceivedCommentsName =
    "increment_received_comments";
  private static final String kIncrementReceivedCommentsDescription =
    "increments received_comments ";
  private static final String kIncrementReceivedCommentsPreparedStatement =
    "UPDATE ig_analytics.daily_regional_counters SET received_comments = "
    + "received_comments + 1 WHERE year_month_day_region = "
    + ":year_month_day_region; ";

  /**
   * Query:
   * Name: select
   * Description:
   *   selects counters per-year per-region 
   * Prepared Statement:
   *   SELECT new_users, new_posts, sent_follows, received_follows, 
   *   sent_unfollows, received_unfollows, sent_likes, 
   *   received_likes, sent_comments, received_comments FROM 
   *   ig_analytics.daily_regional_counters WHERE 
   *   year_month_day_region = :year_month_day_region; 
   */
  private static final String kSelectName =
    "select";
  private static final String kSelectDescription =
    "selects counters per-year per-region ";
  private static final String kSelectPreparedStatement =
    "SELECT new_users, new_posts, sent_follows, received_follows, "
    + "sent_unfollows, received_unfollows, sent_likes, received_likes, "
    + "sent_comments, received_comments FROM "
    + "ig_analytics.daily_regional_counters WHERE "
    + "year_month_day_region = :year_month_day_region; ";

  /**
   * Constructor DailyRegionalCounters
   * @return new DailyRegionalCounters Object
   * @throws Exception
   */
  private DailyRegionalCounters () throws Exception {

    super (
      kKeySpaceName,
      kTableName,
      new Query (
        kIncrementNewUsersDescription,
        kIncrementNewUsersName,
        kIncrementNewUsersPreparedStatement),
      new Query (
        kIncrementNewPostsDescription,
        kIncrementNewPostsName,
        kIncrementNewPostsPreparedStatement),
      new Query (
        kIncrementSentFollowsDescription,
        kIncrementSentFollowsName,
        kIncrementSentFollowsPreparedStatement),
      new Query (
        kIncrementReceivedFollowsDescription,
        kIncrementReceivedFollowsName,
        kIncrementReceivedFollowsPreparedStatement),
      new Query (
        kIncrementSentUnfollowsDescription,
        kIncrementSentUnfollowsName,
        kIncrementSentUnfollowsPreparedStatement),
      new Query (
        kIncrementReceivedUnfollowsDescription,
        kIncrementReceivedUnfollowsName,
        kIncrementReceivedUnfollowsPreparedStatement),
      new Query (
        kIncrementSentLikesDescription,
        kIncrementSentLikesName,
        kIncrementSentLikesPreparedStatement),
      new Query (
        kIncrementReceivedLikesDescription,
        kIncrementReceivedLikesName,
        kIncrementReceivedLikesPreparedStatement),
      new Query (
        kIncrementSentCommentsDescription,
        kIncrementSentCommentsName,
        kIncrementSentCommentsPreparedStatement),
      new Query (
        kIncrementReceivedCommentsDescription,
        kIncrementReceivedCommentsName,
        kIncrementReceivedCommentsPreparedStatement),
      new Query (
        kSelectDescription,
        kSelectName,
        kSelectPreparedStatement));
  }

  private static DailyRegionalCounters instance = null;

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

      instance = new DailyRegionalCounters();
    }
  }

  /**
   * i
   * @return singleton static instance of DailyRegionalCounters
   * @throws Exception
   */
  public static DailyRegionalCounters i () throws Exception {

    if (instance == null) {

      instance = new DailyRegionalCounters();
    }

    return instance;
  }

  // Query: IncrementNewUsers
  // Description:
  //   increments new_users 
  // Parepared Statement:
  //   UPDATE ig_analytics.daily_regional_counters SET new_users = 
  //   new_users + 1 WHERE year_month_day_region = 
  //   :year_month_day_region; 

  /**
   * getQueryIncrementNewUsers
   * @return IncrementNewUsers Query in the form of
   *           a Query Object
   * @throws Exception
   */
  public Query getQueryIncrementNewUsers (
    ) throws Exception {

    return this.getQuery(kIncrementNewUsersName);
  }

  /**
   * getQueryDispatchableIncrementNewUsers
   * @param yearmonthdayregion
   * @return IncrementNewUsers Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableIncrementNewUsers (
    Object yearmonthdayregion) throws Exception {

    return
      this.getQueryDispatchable(
        kIncrementNewUsersName,
        yearmonthdayregion);
  }

  /**
   * getBoundStatementIncrementNewUsers
   * @param yearmonthdayregion
   * @return IncrementNewUsers Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementIncrementNewUsers (
    Object yearmonthdayregion) throws Exception {

    return
      this.getQuery(kIncrementNewUsersName).getBoundStatement(
        yearmonthdayregion);
  }

  /**
   * executeAsyncIncrementNewUsers
   * executes IncrementNewUsers Query asynchronously
   * @param yearmonthdayregion
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncIncrementNewUsers (
    Object yearmonthdayregion) throws Exception {

    return
      this.getQuery(kIncrementNewUsersName).executeAsync(
        yearmonthdayregion);
  }

  /**
   * executeSyncIncrementNewUsers
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes IncrementNewUsers Query synchronously
   * @param yearmonthdayregion
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncIncrementNewUsers (
    Object yearmonthdayregion) throws Exception {

    return
      this.getQuery(kIncrementNewUsersName).executeSync(
        yearmonthdayregion);
  }

  // Query: IncrementNewPosts
  // Description:
  //   increments new_posts 
  // Parepared Statement:
  //   UPDATE ig_analytics.daily_regional_counters SET new_posts = 
  //   new_posts + 1 WHERE year_month_day_region = 
  //   :year_month_day_region; 

  /**
   * getQueryIncrementNewPosts
   * @return IncrementNewPosts Query in the form of
   *           a Query Object
   * @throws Exception
   */
  public Query getQueryIncrementNewPosts (
    ) throws Exception {

    return this.getQuery(kIncrementNewPostsName);
  }

  /**
   * getQueryDispatchableIncrementNewPosts
   * @param yearmonthdayregion
   * @return IncrementNewPosts Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableIncrementNewPosts (
    Object yearmonthdayregion) throws Exception {

    return
      this.getQueryDispatchable(
        kIncrementNewPostsName,
        yearmonthdayregion);
  }

  /**
   * getBoundStatementIncrementNewPosts
   * @param yearmonthdayregion
   * @return IncrementNewPosts Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementIncrementNewPosts (
    Object yearmonthdayregion) throws Exception {

    return
      this.getQuery(kIncrementNewPostsName).getBoundStatement(
        yearmonthdayregion);
  }

  /**
   * executeAsyncIncrementNewPosts
   * executes IncrementNewPosts Query asynchronously
   * @param yearmonthdayregion
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncIncrementNewPosts (
    Object yearmonthdayregion) throws Exception {

    return
      this.getQuery(kIncrementNewPostsName).executeAsync(
        yearmonthdayregion);
  }

  /**
   * executeSyncIncrementNewPosts
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes IncrementNewPosts Query synchronously
   * @param yearmonthdayregion
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncIncrementNewPosts (
    Object yearmonthdayregion) throws Exception {

    return
      this.getQuery(kIncrementNewPostsName).executeSync(
        yearmonthdayregion);
  }

  // Query: IncrementSentFollows
  // Description:
  //   increments sent_follows 
  // Parepared Statement:
  //   UPDATE ig_analytics.daily_regional_counters SET sent_follows = 
  //   sent_follows + 1 WHERE year_month_day_region = 
  //   :year_month_day_region; 

  /**
   * getQueryIncrementSentFollows
   * @return IncrementSentFollows Query in the form of
   *           a Query Object
   * @throws Exception
   */
  public Query getQueryIncrementSentFollows (
    ) throws Exception {

    return this.getQuery(kIncrementSentFollowsName);
  }

  /**
   * getQueryDispatchableIncrementSentFollows
   * @param yearmonthdayregion
   * @return IncrementSentFollows Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableIncrementSentFollows (
    Object yearmonthdayregion) throws Exception {

    return
      this.getQueryDispatchable(
        kIncrementSentFollowsName,
        yearmonthdayregion);
  }

  /**
   * getBoundStatementIncrementSentFollows
   * @param yearmonthdayregion
   * @return IncrementSentFollows Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementIncrementSentFollows (
    Object yearmonthdayregion) throws Exception {

    return
      this.getQuery(kIncrementSentFollowsName).getBoundStatement(
        yearmonthdayregion);
  }

  /**
   * executeAsyncIncrementSentFollows
   * executes IncrementSentFollows Query asynchronously
   * @param yearmonthdayregion
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncIncrementSentFollows (
    Object yearmonthdayregion) throws Exception {

    return
      this.getQuery(kIncrementSentFollowsName).executeAsync(
        yearmonthdayregion);
  }

  /**
   * executeSyncIncrementSentFollows
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes IncrementSentFollows Query synchronously
   * @param yearmonthdayregion
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncIncrementSentFollows (
    Object yearmonthdayregion) throws Exception {

    return
      this.getQuery(kIncrementSentFollowsName).executeSync(
        yearmonthdayregion);
  }

  // Query: IncrementReceivedFollows
  // Description:
  //   increments received_follows 
  // Parepared Statement:
  //   UPDATE ig_analytics.daily_regional_counters SET received_follows = 
  //   received_follows + 1 WHERE year_month_day_region = 
  //   :year_month_day_region; 

  /**
   * getQueryIncrementReceivedFollows
   * @return IncrementReceivedFollows Query in the form of
   *           a Query Object
   * @throws Exception
   */
  public Query getQueryIncrementReceivedFollows (
    ) throws Exception {

    return this.getQuery(kIncrementReceivedFollowsName);
  }

  /**
   * getQueryDispatchableIncrementReceivedFollows
   * @param yearmonthdayregion
   * @return IncrementReceivedFollows Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableIncrementReceivedFollows (
    Object yearmonthdayregion) throws Exception {

    return
      this.getQueryDispatchable(
        kIncrementReceivedFollowsName,
        yearmonthdayregion);
  }

  /**
   * getBoundStatementIncrementReceivedFollows
   * @param yearmonthdayregion
   * @return IncrementReceivedFollows Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementIncrementReceivedFollows (
    Object yearmonthdayregion) throws Exception {

    return
      this.getQuery(kIncrementReceivedFollowsName).getBoundStatement(
        yearmonthdayregion);
  }

  /**
   * executeAsyncIncrementReceivedFollows
   * executes IncrementReceivedFollows Query asynchronously
   * @param yearmonthdayregion
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncIncrementReceivedFollows (
    Object yearmonthdayregion) throws Exception {

    return
      this.getQuery(kIncrementReceivedFollowsName).executeAsync(
        yearmonthdayregion);
  }

  /**
   * executeSyncIncrementReceivedFollows
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes IncrementReceivedFollows Query synchronously
   * @param yearmonthdayregion
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncIncrementReceivedFollows (
    Object yearmonthdayregion) throws Exception {

    return
      this.getQuery(kIncrementReceivedFollowsName).executeSync(
        yearmonthdayregion);
  }

  // Query: IncrementSentUnfollows
  // Description:
  //   increments sent_unfollows 
  // Parepared Statement:
  //   UPDATE ig_analytics.daily_regional_counters SET sent_unfollows = 
  //   sent_unfollows + 1 WHERE year_month_day_region = 
  //   :year_month_day_region; 

  /**
   * getQueryIncrementSentUnfollows
   * @return IncrementSentUnfollows Query in the form of
   *           a Query Object
   * @throws Exception
   */
  public Query getQueryIncrementSentUnfollows (
    ) throws Exception {

    return this.getQuery(kIncrementSentUnfollowsName);
  }

  /**
   * getQueryDispatchableIncrementSentUnfollows
   * @param yearmonthdayregion
   * @return IncrementSentUnfollows Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableIncrementSentUnfollows (
    Object yearmonthdayregion) throws Exception {

    return
      this.getQueryDispatchable(
        kIncrementSentUnfollowsName,
        yearmonthdayregion);
  }

  /**
   * getBoundStatementIncrementSentUnfollows
   * @param yearmonthdayregion
   * @return IncrementSentUnfollows Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementIncrementSentUnfollows (
    Object yearmonthdayregion) throws Exception {

    return
      this.getQuery(kIncrementSentUnfollowsName).getBoundStatement(
        yearmonthdayregion);
  }

  /**
   * executeAsyncIncrementSentUnfollows
   * executes IncrementSentUnfollows Query asynchronously
   * @param yearmonthdayregion
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncIncrementSentUnfollows (
    Object yearmonthdayregion) throws Exception {

    return
      this.getQuery(kIncrementSentUnfollowsName).executeAsync(
        yearmonthdayregion);
  }

  /**
   * executeSyncIncrementSentUnfollows
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes IncrementSentUnfollows Query synchronously
   * @param yearmonthdayregion
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncIncrementSentUnfollows (
    Object yearmonthdayregion) throws Exception {

    return
      this.getQuery(kIncrementSentUnfollowsName).executeSync(
        yearmonthdayregion);
  }

  // Query: IncrementReceivedUnfollows
  // Description:
  //   increments received_unfollows 
  // Parepared Statement:
  //   UPDATE ig_analytics.daily_regional_counters SET received_unfollows 
  //   = received_unfollows + 1 WHERE year_month_day_region = 
  //   :year_month_day_region; 

  /**
   * getQueryIncrementReceivedUnfollows
   * @return IncrementReceivedUnfollows Query in the form of
   *           a Query Object
   * @throws Exception
   */
  public Query getQueryIncrementReceivedUnfollows (
    ) throws Exception {

    return this.getQuery(kIncrementReceivedUnfollowsName);
  }

  /**
   * getQueryDispatchableIncrementReceivedUnfollows
   * @param yearmonthdayregion
   * @return IncrementReceivedUnfollows Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableIncrementReceivedUnfollows (
    Object yearmonthdayregion) throws Exception {

    return
      this.getQueryDispatchable(
        kIncrementReceivedUnfollowsName,
        yearmonthdayregion);
  }

  /**
   * getBoundStatementIncrementReceivedUnfollows
   * @param yearmonthdayregion
   * @return IncrementReceivedUnfollows Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementIncrementReceivedUnfollows (
    Object yearmonthdayregion) throws Exception {

    return
      this.getQuery(kIncrementReceivedUnfollowsName).getBoundStatement(
        yearmonthdayregion);
  }

  /**
   * executeAsyncIncrementReceivedUnfollows
   * executes IncrementReceivedUnfollows Query asynchronously
   * @param yearmonthdayregion
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncIncrementReceivedUnfollows (
    Object yearmonthdayregion) throws Exception {

    return
      this.getQuery(kIncrementReceivedUnfollowsName).executeAsync(
        yearmonthdayregion);
  }

  /**
   * executeSyncIncrementReceivedUnfollows
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes IncrementReceivedUnfollows Query synchronously
   * @param yearmonthdayregion
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncIncrementReceivedUnfollows (
    Object yearmonthdayregion) throws Exception {

    return
      this.getQuery(kIncrementReceivedUnfollowsName).executeSync(
        yearmonthdayregion);
  }

  // Query: IncrementSentLikes
  // Description:
  //   increments sent_likes 
  // Parepared Statement:
  //   UPDATE ig_analytics.daily_regional_counters SET sent_likes = 
  //   sent_likes + 1 WHERE year_month_day_region = 
  //   :year_month_day_region; 

  /**
   * getQueryIncrementSentLikes
   * @return IncrementSentLikes Query in the form of
   *           a Query Object
   * @throws Exception
   */
  public Query getQueryIncrementSentLikes (
    ) throws Exception {

    return this.getQuery(kIncrementSentLikesName);
  }

  /**
   * getQueryDispatchableIncrementSentLikes
   * @param yearmonthdayregion
   * @return IncrementSentLikes Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableIncrementSentLikes (
    Object yearmonthdayregion) throws Exception {

    return
      this.getQueryDispatchable(
        kIncrementSentLikesName,
        yearmonthdayregion);
  }

  /**
   * getBoundStatementIncrementSentLikes
   * @param yearmonthdayregion
   * @return IncrementSentLikes Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementIncrementSentLikes (
    Object yearmonthdayregion) throws Exception {

    return
      this.getQuery(kIncrementSentLikesName).getBoundStatement(
        yearmonthdayregion);
  }

  /**
   * executeAsyncIncrementSentLikes
   * executes IncrementSentLikes Query asynchronously
   * @param yearmonthdayregion
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncIncrementSentLikes (
    Object yearmonthdayregion) throws Exception {

    return
      this.getQuery(kIncrementSentLikesName).executeAsync(
        yearmonthdayregion);
  }

  /**
   * executeSyncIncrementSentLikes
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes IncrementSentLikes Query synchronously
   * @param yearmonthdayregion
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncIncrementSentLikes (
    Object yearmonthdayregion) throws Exception {

    return
      this.getQuery(kIncrementSentLikesName).executeSync(
        yearmonthdayregion);
  }

  // Query: IncrementReceivedLikes
  // Description:
  //   increments received_likes 
  // Parepared Statement:
  //   UPDATE ig_analytics.daily_regional_counters SET received_likes = 
  //   received_likes + 1 WHERE year_month_day_region = 
  //   :year_month_day_region; 

  /**
   * getQueryIncrementReceivedLikes
   * @return IncrementReceivedLikes Query in the form of
   *           a Query Object
   * @throws Exception
   */
  public Query getQueryIncrementReceivedLikes (
    ) throws Exception {

    return this.getQuery(kIncrementReceivedLikesName);
  }

  /**
   * getQueryDispatchableIncrementReceivedLikes
   * @param yearmonthdayregion
   * @return IncrementReceivedLikes Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableIncrementReceivedLikes (
    Object yearmonthdayregion) throws Exception {

    return
      this.getQueryDispatchable(
        kIncrementReceivedLikesName,
        yearmonthdayregion);
  }

  /**
   * getBoundStatementIncrementReceivedLikes
   * @param yearmonthdayregion
   * @return IncrementReceivedLikes Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementIncrementReceivedLikes (
    Object yearmonthdayregion) throws Exception {

    return
      this.getQuery(kIncrementReceivedLikesName).getBoundStatement(
        yearmonthdayregion);
  }

  /**
   * executeAsyncIncrementReceivedLikes
   * executes IncrementReceivedLikes Query asynchronously
   * @param yearmonthdayregion
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncIncrementReceivedLikes (
    Object yearmonthdayregion) throws Exception {

    return
      this.getQuery(kIncrementReceivedLikesName).executeAsync(
        yearmonthdayregion);
  }

  /**
   * executeSyncIncrementReceivedLikes
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes IncrementReceivedLikes Query synchronously
   * @param yearmonthdayregion
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncIncrementReceivedLikes (
    Object yearmonthdayregion) throws Exception {

    return
      this.getQuery(kIncrementReceivedLikesName).executeSync(
        yearmonthdayregion);
  }

  // Query: IncrementSentComments
  // Description:
  //   increments sent_comments 
  // Parepared Statement:
  //   UPDATE ig_analytics.daily_regional_counters SET sent_comments = 
  //   sent_comments + 1 WHERE year_month_day_region = 
  //   :year_month_day_region; 

  /**
   * getQueryIncrementSentComments
   * @return IncrementSentComments Query in the form of
   *           a Query Object
   * @throws Exception
   */
  public Query getQueryIncrementSentComments (
    ) throws Exception {

    return this.getQuery(kIncrementSentCommentsName);
  }

  /**
   * getQueryDispatchableIncrementSentComments
   * @param yearmonthdayregion
   * @return IncrementSentComments Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableIncrementSentComments (
    Object yearmonthdayregion) throws Exception {

    return
      this.getQueryDispatchable(
        kIncrementSentCommentsName,
        yearmonthdayregion);
  }

  /**
   * getBoundStatementIncrementSentComments
   * @param yearmonthdayregion
   * @return IncrementSentComments Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementIncrementSentComments (
    Object yearmonthdayregion) throws Exception {

    return
      this.getQuery(kIncrementSentCommentsName).getBoundStatement(
        yearmonthdayregion);
  }

  /**
   * executeAsyncIncrementSentComments
   * executes IncrementSentComments Query asynchronously
   * @param yearmonthdayregion
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncIncrementSentComments (
    Object yearmonthdayregion) throws Exception {

    return
      this.getQuery(kIncrementSentCommentsName).executeAsync(
        yearmonthdayregion);
  }

  /**
   * executeSyncIncrementSentComments
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes IncrementSentComments Query synchronously
   * @param yearmonthdayregion
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncIncrementSentComments (
    Object yearmonthdayregion) throws Exception {

    return
      this.getQuery(kIncrementSentCommentsName).executeSync(
        yearmonthdayregion);
  }

  // Query: IncrementReceivedComments
  // Description:
  //   increments received_comments 
  // Parepared Statement:
  //   UPDATE ig_analytics.daily_regional_counters SET received_comments = 
  //   received_comments + 1 WHERE year_month_day_region = 
  //   :year_month_day_region; 

  /**
   * getQueryIncrementReceivedComments
   * @return IncrementReceivedComments Query in the form of
   *           a Query Object
   * @throws Exception
   */
  public Query getQueryIncrementReceivedComments (
    ) throws Exception {

    return this.getQuery(kIncrementReceivedCommentsName);
  }

  /**
   * getQueryDispatchableIncrementReceivedComments
   * @param yearmonthdayregion
   * @return IncrementReceivedComments Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableIncrementReceivedComments (
    Object yearmonthdayregion) throws Exception {

    return
      this.getQueryDispatchable(
        kIncrementReceivedCommentsName,
        yearmonthdayregion);
  }

  /**
   * getBoundStatementIncrementReceivedComments
   * @param yearmonthdayregion
   * @return IncrementReceivedComments Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementIncrementReceivedComments (
    Object yearmonthdayregion) throws Exception {

    return
      this.getQuery(kIncrementReceivedCommentsName).getBoundStatement(
        yearmonthdayregion);
  }

  /**
   * executeAsyncIncrementReceivedComments
   * executes IncrementReceivedComments Query asynchronously
   * @param yearmonthdayregion
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncIncrementReceivedComments (
    Object yearmonthdayregion) throws Exception {

    return
      this.getQuery(kIncrementReceivedCommentsName).executeAsync(
        yearmonthdayregion);
  }

  /**
   * executeSyncIncrementReceivedComments
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes IncrementReceivedComments Query synchronously
   * @param yearmonthdayregion
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncIncrementReceivedComments (
    Object yearmonthdayregion) throws Exception {

    return
      this.getQuery(kIncrementReceivedCommentsName).executeSync(
        yearmonthdayregion);
  }

  // Query: Select
  // Description:
  //   selects counters per-year per-region 
  // Parepared Statement:
  //   SELECT new_users, new_posts, sent_follows, received_follows, 
  //   sent_unfollows, received_unfollows, sent_likes, 
  //   received_likes, sent_comments, received_comments FROM 
  //   ig_analytics.daily_regional_counters WHERE 
  //   year_month_day_region = :year_month_day_region; 

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
   * @param yearmonthdayregion
   * @return Select Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableSelect (
    Object yearmonthdayregion) throws Exception {

    return
      this.getQueryDispatchable(
        kSelectName,
        yearmonthdayregion);
  }

  /**
   * getBoundStatementSelect
   * @param yearmonthdayregion
   * @return Select Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementSelect (
    Object yearmonthdayregion) throws Exception {

    return
      this.getQuery(kSelectName).getBoundStatement(
        yearmonthdayregion);
  }

  /**
   * executeAsyncSelect
   * executes Select Query asynchronously
   * @param yearmonthdayregion
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncSelect (
    Object yearmonthdayregion) throws Exception {

    return
      this.getQuery(kSelectName).executeAsync(
        yearmonthdayregion);
  }

  /**
   * executeSyncSelect
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes Select Query synchronously
   * @param yearmonthdayregion
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncSelect (
    Object yearmonthdayregion) throws Exception {

    return
      this.getQuery(kSelectName).executeSync(
        yearmonthdayregion);
  }

}
