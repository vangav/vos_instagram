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
 * PostsIndex represents
 *   Table [posts_index]
 *   in Keyspace [ig_app_data]
 * 
 * Name: posts_index
 * Description:
 *   indexes all posts by-day by-grid-id -- to enable iterating on all 
 *   posts per-day per-grid 
 * 
 * Columns:
 *   year_month_day_grid_id : varchar
 *   post_time : bigint
 *   post_id : uuid

 * Partition Keys: year_month_day_grid_id, post_time, post_id
 * Secondary Keys: 
 * Caching: ALL
 * Order By:
 *   post_time : ASC

 * Queries:
 *   - Name: insert
 *   Description:
 *     inserts a new post 
 *   Prepared Statement:
 *     INSERT INTO ig_app_data.posts_index (year_month_day_grid_id, 
 *     post_time, post_id) VALUES (:year_month_day_grid_id, 
 *     :post_time, :post_id); 
 *   - Name: select_earliest_limit
 *   Description:
 *     selects earliest posted posts on a day in a grid where the returned 
 *     count is limited by the value of limit (e.g.: 10) 
 *   Prepared Statement:
 *     SELECT post_time, post_id FROM ig_app_data.posts_index WHERE 
 *     year_month_day_grid_id = :year_month_day_grid_id LIMIT 30; 
 *   - Name: select_equal
 *   Description:
 *     selects posts posted on a day in a grid having a specific post time 
 *   Prepared Statement:
 *     SELECT post_time, post_id FROM ig_app_data.posts_index WHERE 
 *     year_month_day_grid_id = :year_month_day_grid_id AND 
 *     post_time = :post_time; 
 *   - Name: select_at_or_after_limit
 *   Description:
 *     selects posted posts on a day in a grid at or after a specific time 
 *     where the returned count is limited by the value of limit 
 *     (e.g.: 10) 
 *   Prepared Statement:
 *     SELECT post_time, post_id FROM ig_app_data.posts_index WHERE 
 *     year_month_day_grid_id = :year_month_day_grid_id AND 
 *     post_time >= :post_time LIMIT 30; 
 *   - Name: select_after_limit
 *   Description:
 *     selects posted posts on a day after a specific time where the 
 *     returned count is limited by the value of limit (e.g.: 10) 
 *   Prepared Statement:
 *     SELECT post_time, post_id FROM ig_app_data.posts_index WHERE 
 *     year_month_day_grid_id = :year_month_day_grid_id AND 
 *     post_time > :post_time LIMIT 30; 
 *   - Name: select
 *   Description:
 *     selects all posts posted in a grid on a day, consider using paging 
 *     when using this query 
 *   Prepared Statement:
 *     SELECT post_time, post_id FROM ig_app_data.posts_index WHERE 
 *     year_month_day_grid_id = :year_month_day_grid_id; 
 * */
public class PostsIndex extends Table {

  private static final String kKeySpaceName =
    "ig_app_data";
  private static final String kTableName =
    "posts_index";

  public static final String kYearMonthDayGridIdColumnName =
    "year_month_day_grid_id";
  public static final String kPostTimeColumnName =
    "post_time";
  public static final String kPostIdColumnName =
    "post_id";

  /**
   * Query:
   * Name: insert
   * Description:
   *   inserts a new post 
   * Prepared Statement:
   *   INSERT INTO ig_app_data.posts_index (year_month_day_grid_id, 
   *   post_time, post_id) VALUES (:year_month_day_grid_id, 
   *   :post_time, :post_id); 
   */
  private static final String kInsertName =
    "insert";
  private static final String kInsertDescription =
    "inserts a new post ";
  private static final String kInsertPreparedStatement =
    "INSERT INTO ig_app_data.posts_index (year_month_day_grid_id, "
    + "post_time, post_id) VALUES (:year_month_day_grid_id, "
    + ":post_time, :post_id); ";

  /**
   * Query:
   * Name: select_earliest_limit
   * Description:
   *   selects earliest posted posts on a day in a grid where the returned 
   *   count is limited by the value of limit (e.g.: 10) 
   * Prepared Statement:
   *   SELECT post_time, post_id FROM ig_app_data.posts_index WHERE 
   *   year_month_day_grid_id = :year_month_day_grid_id LIMIT 30; 
   */
  private static final String kSelectEarliestLimitName =
    "select_earliest_limit";
  private static final String kSelectEarliestLimitDescription =
    "selects earliest posted posts on a day in a grid where the returned "
    + "count is limited by the value of limit (e.g.: 10) ";
  private static final String kSelectEarliestLimitPreparedStatement =
    "SELECT post_time, post_id FROM ig_app_data.posts_index WHERE "
    + "year_month_day_grid_id = :year_month_day_grid_id LIMIT 30; ";

  /**
   * Query:
   * Name: select_equal
   * Description:
   *   selects posts posted on a day in a grid having a specific post time 
   * Prepared Statement:
   *   SELECT post_time, post_id FROM ig_app_data.posts_index WHERE 
   *   year_month_day_grid_id = :year_month_day_grid_id AND 
   *   post_time = :post_time; 
   */
  private static final String kSelectEqualName =
    "select_equal";
  private static final String kSelectEqualDescription =
    "selects posts posted on a day in a grid having a specific post time ";
  private static final String kSelectEqualPreparedStatement =
    "SELECT post_time, post_id FROM ig_app_data.posts_index WHERE "
    + "year_month_day_grid_id = :year_month_day_grid_id AND post_time "
    + "= :post_time; ";

  /**
   * Query:
   * Name: select_at_or_after_limit
   * Description:
   *   selects posted posts on a day in a grid at or after a specific time 
   *   where the returned count is limited by the value of limit 
   *   (e.g.: 10) 
   * Prepared Statement:
   *   SELECT post_time, post_id FROM ig_app_data.posts_index WHERE 
   *   year_month_day_grid_id = :year_month_day_grid_id AND 
   *   post_time >= :post_time LIMIT 30; 
   */
  private static final String kSelectAtOrAfterLimitName =
    "select_at_or_after_limit";
  private static final String kSelectAtOrAfterLimitDescription =
    "selects posted posts on a day in a grid at or after a specific time "
    + "where the returned count is limited by the value of limit "
    + "(e.g.: 10) ";
  private static final String kSelectAtOrAfterLimitPreparedStatement =
    "SELECT post_time, post_id FROM ig_app_data.posts_index WHERE "
    + "year_month_day_grid_id = :year_month_day_grid_id AND post_time "
    + ">= :post_time LIMIT 30; ";

  /**
   * Query:
   * Name: select_after_limit
   * Description:
   *   selects posted posts on a day after a specific time where the 
   *   returned count is limited by the value of limit (e.g.: 10) 
   * Prepared Statement:
   *   SELECT post_time, post_id FROM ig_app_data.posts_index WHERE 
   *   year_month_day_grid_id = :year_month_day_grid_id AND 
   *   post_time > :post_time LIMIT 30; 
   */
  private static final String kSelectAfterLimitName =
    "select_after_limit";
  private static final String kSelectAfterLimitDescription =
    "selects posted posts on a day after a specific time where the returned "
    + "count is limited by the value of limit (e.g.: 10) ";
  private static final String kSelectAfterLimitPreparedStatement =
    "SELECT post_time, post_id FROM ig_app_data.posts_index WHERE "
    + "year_month_day_grid_id = :year_month_day_grid_id AND post_time "
    + "> :post_time LIMIT 30; ";

  /**
   * Query:
   * Name: select
   * Description:
   *   selects all posts posted in a grid on a day, consider using paging 
   *   when using this query 
   * Prepared Statement:
   *   SELECT post_time, post_id FROM ig_app_data.posts_index WHERE 
   *   year_month_day_grid_id = :year_month_day_grid_id; 
   */
  private static final String kSelectName =
    "select";
  private static final String kSelectDescription =
    "selects all posts posted in a grid on a day, consider using paging "
    + "when using this query ";
  private static final String kSelectPreparedStatement =
    "SELECT post_time, post_id FROM ig_app_data.posts_index WHERE "
    + "year_month_day_grid_id = :year_month_day_grid_id; ";

  /**
   * Constructor PostsIndex
   * @return new PostsIndex Object
   * @throws Exception
   */
  private PostsIndex () throws Exception {

    super (
      kKeySpaceName,
      kTableName,
      new Query (
        kInsertDescription,
        kInsertName,
        kInsertPreparedStatement),
      new Query (
        kSelectEarliestLimitDescription,
        kSelectEarliestLimitName,
        kSelectEarliestLimitPreparedStatement),
      new Query (
        kSelectEqualDescription,
        kSelectEqualName,
        kSelectEqualPreparedStatement),
      new Query (
        kSelectAtOrAfterLimitDescription,
        kSelectAtOrAfterLimitName,
        kSelectAtOrAfterLimitPreparedStatement),
      new Query (
        kSelectAfterLimitDescription,
        kSelectAfterLimitName,
        kSelectAfterLimitPreparedStatement),
      new Query (
        kSelectDescription,
        kSelectName,
        kSelectPreparedStatement));
  }

  private static PostsIndex instance = null;

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

      instance = new PostsIndex();
    }
  }

  /**
   * i
   * @return singleton static instance of PostsIndex
   * @throws Exception
   */
  public static PostsIndex i () throws Exception {

    if (instance == null) {

      instance = new PostsIndex();
    }

    return instance;
  }

  // Query: Insert
  // Description:
  //   inserts a new post 
  // Parepared Statement:
  //   INSERT INTO ig_app_data.posts_index (year_month_day_grid_id, 
  //   post_time, post_id) VALUES (:year_month_day_grid_id, 
  //   :post_time, :post_id); 

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
   * @param yearmonthdaygridid
   * @param posttime
   * @param postid
   * @return Insert Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableInsert (
    Object yearmonthdaygridid,
    Object posttime,
    Object postid) throws Exception {

    return
      this.getQueryDispatchable(
        kInsertName,
        yearmonthdaygridid,
        posttime,
        postid);
  }

  /**
   * getBoundStatementInsert
   * @param yearmonthdaygridid
   * @param posttime
   * @param postid
   * @return Insert Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementInsert (
    Object yearmonthdaygridid,
    Object posttime,
    Object postid) throws Exception {

    return
      this.getQuery(kInsertName).getBoundStatement(
        yearmonthdaygridid,
        posttime,
        postid);
  }

  /**
   * executeAsyncInsert
   * executes Insert Query asynchronously
   * @param yearmonthdaygridid
   * @param posttime
   * @param postid
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncInsert (
    Object yearmonthdaygridid,
    Object posttime,
    Object postid) throws Exception {

    return
      this.getQuery(kInsertName).executeAsync(
        yearmonthdaygridid,
        posttime,
        postid);
  }

  /**
   * executeSyncInsert
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes Insert Query synchronously
   * @param yearmonthdaygridid
   * @param posttime
   * @param postid
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncInsert (
    Object yearmonthdaygridid,
    Object posttime,
    Object postid) throws Exception {

    return
      this.getQuery(kInsertName).executeSync(
        yearmonthdaygridid,
        posttime,
        postid);
  }

  // Query: SelectEarliestLimit
  // Description:
  //   selects earliest posted posts on a day in a grid where the returned 
  //   count is limited by the value of limit (e.g.: 10) 
  // Parepared Statement:
  //   SELECT post_time, post_id FROM ig_app_data.posts_index WHERE 
  //   year_month_day_grid_id = :year_month_day_grid_id LIMIT 30; 

  /**
   * getQuerySelectEarliestLimit
   * @return SelectEarliestLimit Query in the form of
   *           a Query Object
   * @throws Exception
   */
  public Query getQuerySelectEarliestLimit (
    ) throws Exception {

    return this.getQuery(kSelectEarliestLimitName);
  }

  /**
   * getQueryDispatchableSelectEarliestLimit
   * @param yearmonthdaygridid
   * @return SelectEarliestLimit Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableSelectEarliestLimit (
    Object yearmonthdaygridid) throws Exception {

    return
      this.getQueryDispatchable(
        kSelectEarliestLimitName,
        yearmonthdaygridid);
  }

  /**
   * getBoundStatementSelectEarliestLimit
   * @param yearmonthdaygridid
   * @return SelectEarliestLimit Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementSelectEarliestLimit (
    Object yearmonthdaygridid) throws Exception {

    return
      this.getQuery(kSelectEarliestLimitName).getBoundStatement(
        yearmonthdaygridid);
  }

  /**
   * executeAsyncSelectEarliestLimit
   * executes SelectEarliestLimit Query asynchronously
   * @param yearmonthdaygridid
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncSelectEarliestLimit (
    Object yearmonthdaygridid) throws Exception {

    return
      this.getQuery(kSelectEarliestLimitName).executeAsync(
        yearmonthdaygridid);
  }

  /**
   * executeSyncSelectEarliestLimit
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes SelectEarliestLimit Query synchronously
   * @param yearmonthdaygridid
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncSelectEarliestLimit (
    Object yearmonthdaygridid) throws Exception {

    return
      this.getQuery(kSelectEarliestLimitName).executeSync(
        yearmonthdaygridid);
  }

  // Query: SelectEqual
  // Description:
  //   selects posts posted on a day in a grid having a specific post time 
  // Parepared Statement:
  //   SELECT post_time, post_id FROM ig_app_data.posts_index WHERE 
  //   year_month_day_grid_id = :year_month_day_grid_id AND 
  //   post_time = :post_time; 

  /**
   * getQuerySelectEqual
   * @return SelectEqual Query in the form of
   *           a Query Object
   * @throws Exception
   */
  public Query getQuerySelectEqual (
    ) throws Exception {

    return this.getQuery(kSelectEqualName);
  }

  /**
   * getQueryDispatchableSelectEqual
   * @param yearmonthdaygridid
   * @param posttime
   * @return SelectEqual Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableSelectEqual (
    Object yearmonthdaygridid,
    Object posttime) throws Exception {

    return
      this.getQueryDispatchable(
        kSelectEqualName,
        yearmonthdaygridid,
        posttime);
  }

  /**
   * getBoundStatementSelectEqual
   * @param yearmonthdaygridid
   * @param posttime
   * @return SelectEqual Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementSelectEqual (
    Object yearmonthdaygridid,
    Object posttime) throws Exception {

    return
      this.getQuery(kSelectEqualName).getBoundStatement(
        yearmonthdaygridid,
        posttime);
  }

  /**
   * executeAsyncSelectEqual
   * executes SelectEqual Query asynchronously
   * @param yearmonthdaygridid
   * @param posttime
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncSelectEqual (
    Object yearmonthdaygridid,
    Object posttime) throws Exception {

    return
      this.getQuery(kSelectEqualName).executeAsync(
        yearmonthdaygridid,
        posttime);
  }

  /**
   * executeSyncSelectEqual
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes SelectEqual Query synchronously
   * @param yearmonthdaygridid
   * @param posttime
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncSelectEqual (
    Object yearmonthdaygridid,
    Object posttime) throws Exception {

    return
      this.getQuery(kSelectEqualName).executeSync(
        yearmonthdaygridid,
        posttime);
  }

  // Query: SelectAtOrAfterLimit
  // Description:
  //   selects posted posts on a day in a grid at or after a specific time 
  //   where the returned count is limited by the value of limit 
  //   (e.g.: 10) 
  // Parepared Statement:
  //   SELECT post_time, post_id FROM ig_app_data.posts_index WHERE 
  //   year_month_day_grid_id = :year_month_day_grid_id AND 
  //   post_time >= :post_time LIMIT 30; 

  /**
   * getQuerySelectAtOrAfterLimit
   * @return SelectAtOrAfterLimit Query in the form of
   *           a Query Object
   * @throws Exception
   */
  public Query getQuerySelectAtOrAfterLimit (
    ) throws Exception {

    return this.getQuery(kSelectAtOrAfterLimitName);
  }

  /**
   * getQueryDispatchableSelectAtOrAfterLimit
   * @param yearmonthdaygridid
   * @param posttime
   * @return SelectAtOrAfterLimit Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableSelectAtOrAfterLimit (
    Object yearmonthdaygridid,
    Object posttime) throws Exception {

    return
      this.getQueryDispatchable(
        kSelectAtOrAfterLimitName,
        yearmonthdaygridid,
        posttime);
  }

  /**
   * getBoundStatementSelectAtOrAfterLimit
   * @param yearmonthdaygridid
   * @param posttime
   * @return SelectAtOrAfterLimit Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementSelectAtOrAfterLimit (
    Object yearmonthdaygridid,
    Object posttime) throws Exception {

    return
      this.getQuery(kSelectAtOrAfterLimitName).getBoundStatement(
        yearmonthdaygridid,
        posttime);
  }

  /**
   * executeAsyncSelectAtOrAfterLimit
   * executes SelectAtOrAfterLimit Query asynchronously
   * @param yearmonthdaygridid
   * @param posttime
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncSelectAtOrAfterLimit (
    Object yearmonthdaygridid,
    Object posttime) throws Exception {

    return
      this.getQuery(kSelectAtOrAfterLimitName).executeAsync(
        yearmonthdaygridid,
        posttime);
  }

  /**
   * executeSyncSelectAtOrAfterLimit
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes SelectAtOrAfterLimit Query synchronously
   * @param yearmonthdaygridid
   * @param posttime
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncSelectAtOrAfterLimit (
    Object yearmonthdaygridid,
    Object posttime) throws Exception {

    return
      this.getQuery(kSelectAtOrAfterLimitName).executeSync(
        yearmonthdaygridid,
        posttime);
  }

  // Query: SelectAfterLimit
  // Description:
  //   selects posted posts on a day after a specific time where the 
  //   returned count is limited by the value of limit (e.g.: 10) 
  // Parepared Statement:
  //   SELECT post_time, post_id FROM ig_app_data.posts_index WHERE 
  //   year_month_day_grid_id = :year_month_day_grid_id AND 
  //   post_time > :post_time LIMIT 30; 

  /**
   * getQuerySelectAfterLimit
   * @return SelectAfterLimit Query in the form of
   *           a Query Object
   * @throws Exception
   */
  public Query getQuerySelectAfterLimit (
    ) throws Exception {

    return this.getQuery(kSelectAfterLimitName);
  }

  /**
   * getQueryDispatchableSelectAfterLimit
   * @param yearmonthdaygridid
   * @param posttime
   * @return SelectAfterLimit Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableSelectAfterLimit (
    Object yearmonthdaygridid,
    Object posttime) throws Exception {

    return
      this.getQueryDispatchable(
        kSelectAfterLimitName,
        yearmonthdaygridid,
        posttime);
  }

  /**
   * getBoundStatementSelectAfterLimit
   * @param yearmonthdaygridid
   * @param posttime
   * @return SelectAfterLimit Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementSelectAfterLimit (
    Object yearmonthdaygridid,
    Object posttime) throws Exception {

    return
      this.getQuery(kSelectAfterLimitName).getBoundStatement(
        yearmonthdaygridid,
        posttime);
  }

  /**
   * executeAsyncSelectAfterLimit
   * executes SelectAfterLimit Query asynchronously
   * @param yearmonthdaygridid
   * @param posttime
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncSelectAfterLimit (
    Object yearmonthdaygridid,
    Object posttime) throws Exception {

    return
      this.getQuery(kSelectAfterLimitName).executeAsync(
        yearmonthdaygridid,
        posttime);
  }

  /**
   * executeSyncSelectAfterLimit
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes SelectAfterLimit Query synchronously
   * @param yearmonthdaygridid
   * @param posttime
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncSelectAfterLimit (
    Object yearmonthdaygridid,
    Object posttime) throws Exception {

    return
      this.getQuery(kSelectAfterLimitName).executeSync(
        yearmonthdaygridid,
        posttime);
  }

  // Query: Select
  // Description:
  //   selects all posts posted in a grid on a day, consider using paging 
  //   when using this query 
  // Parepared Statement:
  //   SELECT post_time, post_id FROM ig_app_data.posts_index WHERE 
  //   year_month_day_grid_id = :year_month_day_grid_id; 

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
   * @param yearmonthdaygridid
   * @return Select Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableSelect (
    Object yearmonthdaygridid) throws Exception {

    return
      this.getQueryDispatchable(
        kSelectName,
        yearmonthdaygridid);
  }

  /**
   * getBoundStatementSelect
   * @param yearmonthdaygridid
   * @return Select Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementSelect (
    Object yearmonthdaygridid) throws Exception {

    return
      this.getQuery(kSelectName).getBoundStatement(
        yearmonthdaygridid);
  }

  /**
   * executeAsyncSelect
   * executes Select Query asynchronously
   * @param yearmonthdaygridid
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncSelect (
    Object yearmonthdaygridid) throws Exception {

    return
      this.getQuery(kSelectName).executeAsync(
        yearmonthdaygridid);
  }

  /**
   * executeSyncSelect
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes Select Query synchronously
   * @param yearmonthdaygridid
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncSelect (
    Object yearmonthdaygridid) throws Exception {

    return
      this.getQuery(kSelectName).executeSync(
        yearmonthdaygridid);
  }

}
