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
 * UserFeedPosts represents
 *   Table [user_feed_posts]
 *   in Keyspace [ig_app_data]
 * 
 * Name: user_feed_posts
 * Description:
 *   stores posts per-user that a user sees in her/his feed (posted by the 
 *   user or one of the users she/he is following) ordered by the 
 *   most recent posts 
 * 
 * Columns:
 *   user_id : uuid
 *   post_time : bigint
 *   post_id : uuid

 * Partition Keys: user_id, post_time, post_id
 * Secondary Keys: 
 * Caching: ALL
 * Order By:
 *   post_time : DESC

 * Queries:
 *   - Name: insert
 *   Description:
 *     inserts a new user's feed post 
 *   Prepared Statement:
 *     INSERT INTO ig_app_data.user_feed_posts (user_id, post_time, 
 *     post_id) VALUES (:user_id, :post_time, :post_id); 
 *   - Name: select_recent_limit
 *   Description:
 *     selects the most recent posts posted in a user's feed where the 
 *     returned count is limited by the value of limit (e.g.: 10) 
 *   Prepared Statement:
 *     SELECT post_time, post_id FROM ig_app_data.user_feed_posts WHERE 
 *     user_id = :user_id LIMIT 30; 
 *   - Name: select_at_or_before_time_limit
 *   Description:
 *     selects posts posted in a user's feed at or before a specified time 
 *     where the returned count is limited by the value of limit 
 *     (e.g.: 10) 
 *   Prepared Statement:
 *     SELECT post_time, post_id FROM ig_app_data.user_feed_posts WHERE 
 *     user_id = :user_id AND post_time <= :post_time LIMIT 30; 
 * */
public class UserFeedPosts extends Table {

  private static final String kKeySpaceName =
    "ig_app_data";
  private static final String kTableName =
    "user_feed_posts";

  public static final String kUserIdColumnName =
    "user_id";
  public static final String kPostTimeColumnName =
    "post_time";
  public static final String kPostIdColumnName =
    "post_id";

  /**
   * Query:
   * Name: insert
   * Description:
   *   inserts a new user's feed post 
   * Prepared Statement:
   *   INSERT INTO ig_app_data.user_feed_posts (user_id, post_time, 
   *   post_id) VALUES (:user_id, :post_time, :post_id); 
   */
  private static final String kInsertName =
    "insert";
  private static final String kInsertDescription =
    "inserts a new user's feed post ";
  private static final String kInsertPreparedStatement =
    "INSERT INTO ig_app_data.user_feed_posts (user_id, post_time, post_id) "
    + "VALUES (:user_id, :post_time, :post_id); ";

  /**
   * Query:
   * Name: select_recent_limit
   * Description:
   *   selects the most recent posts posted in a user's feed where the 
   *   returned count is limited by the value of limit (e.g.: 10) 
   * Prepared Statement:
   *   SELECT post_time, post_id FROM ig_app_data.user_feed_posts WHERE 
   *   user_id = :user_id LIMIT 30; 
   */
  private static final String kSelectRecentLimitName =
    "select_recent_limit";
  private static final String kSelectRecentLimitDescription =
    "selects the most recent posts posted in a user's feed where the "
    + "returned count is limited by the value of limit (e.g.: 10) ";
  private static final String kSelectRecentLimitPreparedStatement =
    "SELECT post_time, post_id FROM ig_app_data.user_feed_posts WHERE "
    + "user_id = :user_id LIMIT 30; ";

  /**
   * Query:
   * Name: select_at_or_before_time_limit
   * Description:
   *   selects posts posted in a user's feed at or before a specified time 
   *   where the returned count is limited by the value of limit 
   *   (e.g.: 10) 
   * Prepared Statement:
   *   SELECT post_time, post_id FROM ig_app_data.user_feed_posts WHERE 
   *   user_id = :user_id AND post_time <= :post_time LIMIT 30; 
   */
  private static final String kSelectAtOrBeforeTimeLimitName =
    "select_at_or_before_time_limit";
  private static final String kSelectAtOrBeforeTimeLimitDescription =
    "selects posts posted in a user's feed at or before a specified time "
    + "where the returned count is limited by the value of limit "
    + "(e.g.: 10) ";
  private static final String kSelectAtOrBeforeTimeLimitPreparedStatement =
    "SELECT post_time, post_id FROM ig_app_data.user_feed_posts WHERE "
    + "user_id = :user_id AND post_time <= :post_time LIMIT 30; ";

  /**
   * Constructor UserFeedPosts
   * @return new UserFeedPosts Object
   * @throws Exception
   */
  private UserFeedPosts () throws Exception {

    super (
      kKeySpaceName,
      kTableName,
      new Query (
        kInsertDescription,
        kInsertName,
        kInsertPreparedStatement),
      new Query (
        kSelectRecentLimitDescription,
        kSelectRecentLimitName,
        kSelectRecentLimitPreparedStatement),
      new Query (
        kSelectAtOrBeforeTimeLimitDescription,
        kSelectAtOrBeforeTimeLimitName,
        kSelectAtOrBeforeTimeLimitPreparedStatement));
  }

  private static UserFeedPosts instance = null;

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

      instance = new UserFeedPosts();
    }
  }

  /**
   * i
   * @return singleton static instance of UserFeedPosts
   * @throws Exception
   */
  public static UserFeedPosts i () throws Exception {

    if (instance == null) {

      instance = new UserFeedPosts();
    }

    return instance;
  }

  // Query: Insert
  // Description:
  //   inserts a new user's feed post 
  // Parepared Statement:
  //   INSERT INTO ig_app_data.user_feed_posts (user_id, post_time, 
  //   post_id) VALUES (:user_id, :post_time, :post_id); 

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
   * @param posttime
   * @param postid
   * @return Insert Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableInsert (
    Object userid,
    Object posttime,
    Object postid) throws Exception {

    return
      this.getQueryDispatchable(
        kInsertName,
        userid,
        posttime,
        postid);
  }

  /**
   * getBoundStatementInsert
   * @param userid
   * @param posttime
   * @param postid
   * @return Insert Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementInsert (
    Object userid,
    Object posttime,
    Object postid) throws Exception {

    return
      this.getQuery(kInsertName).getBoundStatement(
        userid,
        posttime,
        postid);
  }

  /**
   * executeAsyncInsert
   * executes Insert Query asynchronously
   * @param userid
   * @param posttime
   * @param postid
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncInsert (
    Object userid,
    Object posttime,
    Object postid) throws Exception {

    return
      this.getQuery(kInsertName).executeAsync(
        userid,
        posttime,
        postid);
  }

  /**
   * executeSyncInsert
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes Insert Query synchronously
   * @param userid
   * @param posttime
   * @param postid
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncInsert (
    Object userid,
    Object posttime,
    Object postid) throws Exception {

    return
      this.getQuery(kInsertName).executeSync(
        userid,
        posttime,
        postid);
  }

  // Query: SelectRecentLimit
  // Description:
  //   selects the most recent posts posted in a user's feed where the 
  //   returned count is limited by the value of limit (e.g.: 10) 
  // Parepared Statement:
  //   SELECT post_time, post_id FROM ig_app_data.user_feed_posts WHERE 
  //   user_id = :user_id LIMIT 30; 

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
   * @param userid
   * @return SelectRecentLimit Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableSelectRecentLimit (
    Object userid) throws Exception {

    return
      this.getQueryDispatchable(
        kSelectRecentLimitName,
        userid);
  }

  /**
   * getBoundStatementSelectRecentLimit
   * @param userid
   * @return SelectRecentLimit Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementSelectRecentLimit (
    Object userid) throws Exception {

    return
      this.getQuery(kSelectRecentLimitName).getBoundStatement(
        userid);
  }

  /**
   * executeAsyncSelectRecentLimit
   * executes SelectRecentLimit Query asynchronously
   * @param userid
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncSelectRecentLimit (
    Object userid) throws Exception {

    return
      this.getQuery(kSelectRecentLimitName).executeAsync(
        userid);
  }

  /**
   * executeSyncSelectRecentLimit
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes SelectRecentLimit Query synchronously
   * @param userid
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncSelectRecentLimit (
    Object userid) throws Exception {

    return
      this.getQuery(kSelectRecentLimitName).executeSync(
        userid);
  }

  // Query: SelectAtOrBeforeTimeLimit
  // Description:
  //   selects posts posted in a user's feed at or before a specified time 
  //   where the returned count is limited by the value of limit 
  //   (e.g.: 10) 
  // Parepared Statement:
  //   SELECT post_time, post_id FROM ig_app_data.user_feed_posts WHERE 
  //   user_id = :user_id AND post_time <= :post_time LIMIT 30; 

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
   * @param userid
   * @param posttime
   * @return SelectAtOrBeforeTimeLimit Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableSelectAtOrBeforeTimeLimit (
    Object userid,
    Object posttime) throws Exception {

    return
      this.getQueryDispatchable(
        kSelectAtOrBeforeTimeLimitName,
        userid,
        posttime);
  }

  /**
   * getBoundStatementSelectAtOrBeforeTimeLimit
   * @param userid
   * @param posttime
   * @return SelectAtOrBeforeTimeLimit Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementSelectAtOrBeforeTimeLimit (
    Object userid,
    Object posttime) throws Exception {

    return
      this.getQuery(kSelectAtOrBeforeTimeLimitName).getBoundStatement(
        userid,
        posttime);
  }

  /**
   * executeAsyncSelectAtOrBeforeTimeLimit
   * executes SelectAtOrBeforeTimeLimit Query asynchronously
   * @param userid
   * @param posttime
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncSelectAtOrBeforeTimeLimit (
    Object userid,
    Object posttime) throws Exception {

    return
      this.getQuery(kSelectAtOrBeforeTimeLimitName).executeAsync(
        userid,
        posttime);
  }

  /**
   * executeSyncSelectAtOrBeforeTimeLimit
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes SelectAtOrBeforeTimeLimit Query synchronously
   * @param userid
   * @param posttime
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncSelectAtOrBeforeTimeLimit (
    Object userid,
    Object posttime) throws Exception {

    return
      this.getQuery(kSelectAtOrBeforeTimeLimitName).executeSync(
        userid,
        posttime);
  }

}
