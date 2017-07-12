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
 * PostsRankGrid represents
 *   Table [posts_rank_grid]
 *   in Keyspace [ig_app_data]
 * 
 * Name: posts_rank_grid
 * Description:
 *   ranks all recent posts in a grid (e.g.: 10 KM x 10 KM) each day 
 * 
 * Columns:
 *   year_month_day_grid_id : varchar
 *   rank : double
 *   post_id : uuid

 * Partition Keys: year_month_day_grid_id, rank, post_id
 * Secondary Keys: 
 * Caching: ALL
 * Order By:
 *   rank : DESC

 * Queries:
 *   - Name: insert
 *   Description:
 *     inserts a post's rank 
 *   Prepared Statement:
 *     INSERT INTO ig_app_data.posts_rank_grid (year_month_day_grid_id, 
 *     rank, post_id) VALUES (:year_month_day_grid_id, :rank, 
 *     :post_id); 
 *   - Name: select_top_limit
 *   Description:
 *     selects the top ranked posts in a grid where the returned count is 
 *     limited by the value of limit (e.g.: 10) 
 *   Prepared Statement:
 *     SELECT rank, post_id FROM ig_app_data.posts_rank_grid WHERE 
 *     year_month_day_grid_id = :year_month_day_grid_id LIMIT 30; 
 *   - Name: select_grids_top_limit
 *   Description:
 *     selects the top ranked posts in a group of grids where the returned 
 *     count is limited by the value of limit (e.g.: 10) 
 *   Prepared Statement:
 *     SELECT rank, post_id FROM ig_app_data.posts_rank_grid WHERE 
 *     year_month_day_grid_id IN :year_month_day_grid_ids LIMIT 30; 
 *   - Name: select_top_equal
 *   Description:
 *     selects the top ranked posts in a grid having a specific rank value 
 *   Prepared Statement:
 *     SELECT rank, post_id FROM ig_app_data.posts_rank_grid WHERE 
 *     year_month_day_grid_id = :year_month_day_grid_id AND rank = 
 *     :rank; 
 *   - Name: select_top_smaller_than_or_equal_limit
 *   Description:
 *     selects the top ranked posts in a grid having a rank smaller than 
 *     or equal to a spcified rank value and the returned count is 
 *     limited by the value of limit (e.g.: 10) 
 *   Prepared Statement:
 *     SELECT rank, post_id FROM ig_app_data.posts_rank_grid WHERE 
 *     year_month_day_grid_id = :year_month_day_grid_id AND rank <= 
 *     :rank LIMIT 30; 
 *   - Name: select_top_smaller_than_limit
 *   Description:
 *     selects the top ranked posts in a grid having a rank smaller than a 
 *     spcified rank value and the returned count is limited by the 
 *     value of limit (e.g.: 10) 
 *   Prepared Statement:
 *     SELECT rank, post_id FROM ig_app_data.posts_rank_grid WHERE 
 *     year_month_day_grid_id = :year_month_day_grid_id AND rank < 
 *     :rank LIMIT 30; 
 * */
public class PostsRankGrid extends Table {

  private static final String kKeySpaceName =
    "ig_app_data";
  private static final String kTableName =
    "posts_rank_grid";

  public static final String kYearMonthDayGridIdColumnName =
    "year_month_day_grid_id";
  public static final String kRankColumnName =
    "rank";
  public static final String kPostIdColumnName =
    "post_id";

  /**
   * Query:
   * Name: insert
   * Description:
   *   inserts a post's rank 
   * Prepared Statement:
   *   INSERT INTO ig_app_data.posts_rank_grid (year_month_day_grid_id, 
   *   rank, post_id) VALUES (:year_month_day_grid_id, :rank, 
   *   :post_id); 
   */
  private static final String kInsertName =
    "insert";
  private static final String kInsertDescription =
    "inserts a post's rank ";
  private static final String kInsertPreparedStatement =
    "INSERT INTO ig_app_data.posts_rank_grid (year_month_day_grid_id, rank, "
    + "post_id) VALUES (:year_month_day_grid_id, :rank, :post_id); ";

  /**
   * Query:
   * Name: select_top_limit
   * Description:
   *   selects the top ranked posts in a grid where the returned count is 
   *   limited by the value of limit (e.g.: 10) 
   * Prepared Statement:
   *   SELECT rank, post_id FROM ig_app_data.posts_rank_grid WHERE 
   *   year_month_day_grid_id = :year_month_day_grid_id LIMIT 30; 
   */
  private static final String kSelectTopLimitName =
    "select_top_limit";
  private static final String kSelectTopLimitDescription =
    "selects the top ranked posts in a grid where the returned count is "
    + "limited by the value of limit (e.g.: 10) ";
  private static final String kSelectTopLimitPreparedStatement =
    "SELECT rank, post_id FROM ig_app_data.posts_rank_grid WHERE "
    + "year_month_day_grid_id = :year_month_day_grid_id LIMIT 30; ";

  /**
   * Query:
   * Name: select_grids_top_limit
   * Description:
   *   selects the top ranked posts in a group of grids where the returned 
   *   count is limited by the value of limit (e.g.: 10) 
   * Prepared Statement:
   *   SELECT rank, post_id FROM ig_app_data.posts_rank_grid WHERE 
   *   year_month_day_grid_id IN :year_month_day_grid_ids LIMIT 30; 
   */
  private static final String kSelectGridsTopLimitName =
    "select_grids_top_limit";
  private static final String kSelectGridsTopLimitDescription =
    "selects the top ranked posts in a group of grids where the returned "
    + "count is limited by the value of limit (e.g.: 10) ";
  private static final String kSelectGridsTopLimitPreparedStatement =
    "SELECT rank, post_id FROM ig_app_data.posts_rank_grid WHERE "
    + "year_month_day_grid_id IN :year_month_day_grid_ids LIMIT 30; ";

  /**
   * Query:
   * Name: select_top_equal
   * Description:
   *   selects the top ranked posts in a grid having a specific rank value 
   * Prepared Statement:
   *   SELECT rank, post_id FROM ig_app_data.posts_rank_grid WHERE 
   *   year_month_day_grid_id = :year_month_day_grid_id AND rank = 
   *   :rank; 
   */
  private static final String kSelectTopEqualName =
    "select_top_equal";
  private static final String kSelectTopEqualDescription =
    "selects the top ranked posts in a grid having a specific rank value ";
  private static final String kSelectTopEqualPreparedStatement =
    "SELECT rank, post_id FROM ig_app_data.posts_rank_grid WHERE "
    + "year_month_day_grid_id = :year_month_day_grid_id AND rank = "
    + ":rank; ";

  /**
   * Query:
   * Name: select_top_smaller_than_or_equal_limit
   * Description:
   *   selects the top ranked posts in a grid having a rank smaller than 
   *   or equal to a spcified rank value and the returned count is 
   *   limited by the value of limit (e.g.: 10) 
   * Prepared Statement:
   *   SELECT rank, post_id FROM ig_app_data.posts_rank_grid WHERE 
   *   year_month_day_grid_id = :year_month_day_grid_id AND rank <= 
   *   :rank LIMIT 30; 
   */
  private static final String kSelectTopSmallerThanOrEqualLimitName =
    "select_top_smaller_than_or_equal_limit";
  private static final String kSelectTopSmallerThanOrEqualLimitDescription =
    "selects the top ranked posts in a grid having a rank smaller than or "
    + "equal to a spcified rank value and the returned count is "
    + "limited by the value of limit (e.g.: 10) ";
  private static final String kSelectTopSmallerThanOrEqualLimitPreparedStatement =
    "SELECT rank, post_id FROM ig_app_data.posts_rank_grid WHERE "
    + "year_month_day_grid_id = :year_month_day_grid_id AND rank <= "
    + ":rank LIMIT 30; ";

  /**
   * Query:
   * Name: select_top_smaller_than_limit
   * Description:
   *   selects the top ranked posts in a grid having a rank smaller than a 
   *   spcified rank value and the returned count is limited by the 
   *   value of limit (e.g.: 10) 
   * Prepared Statement:
   *   SELECT rank, post_id FROM ig_app_data.posts_rank_grid WHERE 
   *   year_month_day_grid_id = :year_month_day_grid_id AND rank < 
   *   :rank LIMIT 30; 
   */
  private static final String kSelectTopSmallerThanLimitName =
    "select_top_smaller_than_limit";
  private static final String kSelectTopSmallerThanLimitDescription =
    "selects the top ranked posts in a grid having a rank smaller than a "
    + "spcified rank value and the returned count is limited by the "
    + "value of limit (e.g.: 10) ";
  private static final String kSelectTopSmallerThanLimitPreparedStatement =
    "SELECT rank, post_id FROM ig_app_data.posts_rank_grid WHERE "
    + "year_month_day_grid_id = :year_month_day_grid_id AND rank < "
    + ":rank LIMIT 30; ";

  /**
   * Constructor PostsRankGrid
   * @return new PostsRankGrid Object
   * @throws Exception
   */
  private PostsRankGrid () throws Exception {

    super (
      kKeySpaceName,
      kTableName,
      new Query (
        kInsertDescription,
        kInsertName,
        kInsertPreparedStatement),
      new Query (
        kSelectTopLimitDescription,
        kSelectTopLimitName,
        kSelectTopLimitPreparedStatement),
      new Query (
        kSelectGridsTopLimitDescription,
        kSelectGridsTopLimitName,
        kSelectGridsTopLimitPreparedStatement),
      new Query (
        kSelectTopEqualDescription,
        kSelectTopEqualName,
        kSelectTopEqualPreparedStatement),
      new Query (
        kSelectTopSmallerThanOrEqualLimitDescription,
        kSelectTopSmallerThanOrEqualLimitName,
        kSelectTopSmallerThanOrEqualLimitPreparedStatement),
      new Query (
        kSelectTopSmallerThanLimitDescription,
        kSelectTopSmallerThanLimitName,
        kSelectTopSmallerThanLimitPreparedStatement));
  }

  private static PostsRankGrid instance = null;

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

      instance = new PostsRankGrid();
    }
  }

  /**
   * i
   * @return singleton static instance of PostsRankGrid
   * @throws Exception
   */
  public static PostsRankGrid i () throws Exception {

    if (instance == null) {

      instance = new PostsRankGrid();
    }

    return instance;
  }

  // Query: Insert
  // Description:
  //   inserts a post's rank 
  // Parepared Statement:
  //   INSERT INTO ig_app_data.posts_rank_grid (year_month_day_grid_id, 
  //   rank, post_id) VALUES (:year_month_day_grid_id, :rank, 
  //   :post_id); 

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
   * @param rank
   * @param postid
   * @return Insert Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableInsert (
    Object yearmonthdaygridid,
    Object rank,
    Object postid) throws Exception {

    return
      this.getQueryDispatchable(
        kInsertName,
        yearmonthdaygridid,
        rank,
        postid);
  }

  /**
   * getBoundStatementInsert
   * @param yearmonthdaygridid
   * @param rank
   * @param postid
   * @return Insert Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementInsert (
    Object yearmonthdaygridid,
    Object rank,
    Object postid) throws Exception {

    return
      this.getQuery(kInsertName).getBoundStatement(
        yearmonthdaygridid,
        rank,
        postid);
  }

  /**
   * executeAsyncInsert
   * executes Insert Query asynchronously
   * @param yearmonthdaygridid
   * @param rank
   * @param postid
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncInsert (
    Object yearmonthdaygridid,
    Object rank,
    Object postid) throws Exception {

    return
      this.getQuery(kInsertName).executeAsync(
        yearmonthdaygridid,
        rank,
        postid);
  }

  /**
   * executeSyncInsert
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes Insert Query synchronously
   * @param yearmonthdaygridid
   * @param rank
   * @param postid
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncInsert (
    Object yearmonthdaygridid,
    Object rank,
    Object postid) throws Exception {

    return
      this.getQuery(kInsertName).executeSync(
        yearmonthdaygridid,
        rank,
        postid);
  }

  // Query: SelectTopLimit
  // Description:
  //   selects the top ranked posts in a grid where the returned count is 
  //   limited by the value of limit (e.g.: 10) 
  // Parepared Statement:
  //   SELECT rank, post_id FROM ig_app_data.posts_rank_grid WHERE 
  //   year_month_day_grid_id = :year_month_day_grid_id LIMIT 30; 

  /**
   * getQuerySelectTopLimit
   * @return SelectTopLimit Query in the form of
   *           a Query Object
   * @throws Exception
   */
  public Query getQuerySelectTopLimit (
    ) throws Exception {

    return this.getQuery(kSelectTopLimitName);
  }

  /**
   * getQueryDispatchableSelectTopLimit
   * @param yearmonthdaygridid
   * @return SelectTopLimit Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableSelectTopLimit (
    Object yearmonthdaygridid) throws Exception {

    return
      this.getQueryDispatchable(
        kSelectTopLimitName,
        yearmonthdaygridid);
  }

  /**
   * getBoundStatementSelectTopLimit
   * @param yearmonthdaygridid
   * @return SelectTopLimit Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementSelectTopLimit (
    Object yearmonthdaygridid) throws Exception {

    return
      this.getQuery(kSelectTopLimitName).getBoundStatement(
        yearmonthdaygridid);
  }

  /**
   * executeAsyncSelectTopLimit
   * executes SelectTopLimit Query asynchronously
   * @param yearmonthdaygridid
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncSelectTopLimit (
    Object yearmonthdaygridid) throws Exception {

    return
      this.getQuery(kSelectTopLimitName).executeAsync(
        yearmonthdaygridid);
  }

  /**
   * executeSyncSelectTopLimit
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes SelectTopLimit Query synchronously
   * @param yearmonthdaygridid
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncSelectTopLimit (
    Object yearmonthdaygridid) throws Exception {

    return
      this.getQuery(kSelectTopLimitName).executeSync(
        yearmonthdaygridid);
  }

  // Query: SelectGridsTopLimit
  // Description:
  //   selects the top ranked posts in a group of grids where the returned 
  //   count is limited by the value of limit (e.g.: 10) 
  // Parepared Statement:
  //   SELECT rank, post_id FROM ig_app_data.posts_rank_grid WHERE 
  //   year_month_day_grid_id IN :year_month_day_grid_ids LIMIT 30; 

  /**
   * getQuerySelectGridsTopLimit
   * @return SelectGridsTopLimit Query in the form of
   *           a Query Object
   * @throws Exception
   */
  public Query getQuerySelectGridsTopLimit (
    ) throws Exception {

    return this.getQuery(kSelectGridsTopLimitName);
  }

  /**
   * getQueryDispatchableSelectGridsTopLimit
   * @param yearmonthdaygridids
   * @return SelectGridsTopLimit Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableSelectGridsTopLimit (
    Object yearmonthdaygridids) throws Exception {

    return
      this.getQueryDispatchable(
        kSelectGridsTopLimitName,
        yearmonthdaygridids);
  }

  /**
   * getBoundStatementSelectGridsTopLimit
   * @param yearmonthdaygridids
   * @return SelectGridsTopLimit Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementSelectGridsTopLimit (
    Object yearmonthdaygridids) throws Exception {

    return
      this.getQuery(kSelectGridsTopLimitName).getBoundStatement(
        yearmonthdaygridids);
  }

  /**
   * executeAsyncSelectGridsTopLimit
   * executes SelectGridsTopLimit Query asynchronously
   * @param yearmonthdaygridids
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncSelectGridsTopLimit (
    Object yearmonthdaygridids) throws Exception {

    return
      this.getQuery(kSelectGridsTopLimitName).executeAsync(
        yearmonthdaygridids);
  }

  /**
   * executeSyncSelectGridsTopLimit
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes SelectGridsTopLimit Query synchronously
   * @param yearmonthdaygridids
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncSelectGridsTopLimit (
    Object yearmonthdaygridids) throws Exception {

    return
      this.getQuery(kSelectGridsTopLimitName).executeSync(
        yearmonthdaygridids);
  }

  // Query: SelectTopEqual
  // Description:
  //   selects the top ranked posts in a grid having a specific rank value 
  // Parepared Statement:
  //   SELECT rank, post_id FROM ig_app_data.posts_rank_grid WHERE 
  //   year_month_day_grid_id = :year_month_day_grid_id AND rank = 
  //   :rank; 

  /**
   * getQuerySelectTopEqual
   * @return SelectTopEqual Query in the form of
   *           a Query Object
   * @throws Exception
   */
  public Query getQuerySelectTopEqual (
    ) throws Exception {

    return this.getQuery(kSelectTopEqualName);
  }

  /**
   * getQueryDispatchableSelectTopEqual
   * @param yearmonthdaygridid
   * @param rank
   * @return SelectTopEqual Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableSelectTopEqual (
    Object yearmonthdaygridid,
    Object rank) throws Exception {

    return
      this.getQueryDispatchable(
        kSelectTopEqualName,
        yearmonthdaygridid,
        rank);
  }

  /**
   * getBoundStatementSelectTopEqual
   * @param yearmonthdaygridid
   * @param rank
   * @return SelectTopEqual Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementSelectTopEqual (
    Object yearmonthdaygridid,
    Object rank) throws Exception {

    return
      this.getQuery(kSelectTopEqualName).getBoundStatement(
        yearmonthdaygridid,
        rank);
  }

  /**
   * executeAsyncSelectTopEqual
   * executes SelectTopEqual Query asynchronously
   * @param yearmonthdaygridid
   * @param rank
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncSelectTopEqual (
    Object yearmonthdaygridid,
    Object rank) throws Exception {

    return
      this.getQuery(kSelectTopEqualName).executeAsync(
        yearmonthdaygridid,
        rank);
  }

  /**
   * executeSyncSelectTopEqual
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes SelectTopEqual Query synchronously
   * @param yearmonthdaygridid
   * @param rank
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncSelectTopEqual (
    Object yearmonthdaygridid,
    Object rank) throws Exception {

    return
      this.getQuery(kSelectTopEqualName).executeSync(
        yearmonthdaygridid,
        rank);
  }

  // Query: SelectTopSmallerThanOrEqualLimit
  // Description:
  //   selects the top ranked posts in a grid having a rank smaller than 
  //   or equal to a spcified rank value and the returned count is 
  //   limited by the value of limit (e.g.: 10) 
  // Parepared Statement:
  //   SELECT rank, post_id FROM ig_app_data.posts_rank_grid WHERE 
  //   year_month_day_grid_id = :year_month_day_grid_id AND rank <= 
  //   :rank LIMIT 30; 

  /**
   * getQuerySelectTopSmallerThanOrEqualLimit
   * @return SelectTopSmallerThanOrEqualLimit Query in the form of
   *           a Query Object
   * @throws Exception
   */
  public Query getQuerySelectTopSmallerThanOrEqualLimit (
    ) throws Exception {

    return this.getQuery(kSelectTopSmallerThanOrEqualLimitName);
  }

  /**
   * getQueryDispatchableSelectTopSmallerThanOrEqualLimit
   * @param yearmonthdaygridid
   * @param rank
   * @return SelectTopSmallerThanOrEqualLimit Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableSelectTopSmallerThanOrEqualLimit (
    Object yearmonthdaygridid,
    Object rank) throws Exception {

    return
      this.getQueryDispatchable(
        kSelectTopSmallerThanOrEqualLimitName,
        yearmonthdaygridid,
        rank);
  }

  /**
   * getBoundStatementSelectTopSmallerThanOrEqualLimit
   * @param yearmonthdaygridid
   * @param rank
   * @return SelectTopSmallerThanOrEqualLimit Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementSelectTopSmallerThanOrEqualLimit (
    Object yearmonthdaygridid,
    Object rank) throws Exception {

    return
      this.getQuery(kSelectTopSmallerThanOrEqualLimitName).getBoundStatement(
        yearmonthdaygridid,
        rank);
  }

  /**
   * executeAsyncSelectTopSmallerThanOrEqualLimit
   * executes SelectTopSmallerThanOrEqualLimit Query asynchronously
   * @param yearmonthdaygridid
   * @param rank
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncSelectTopSmallerThanOrEqualLimit (
    Object yearmonthdaygridid,
    Object rank) throws Exception {

    return
      this.getQuery(kSelectTopSmallerThanOrEqualLimitName).executeAsync(
        yearmonthdaygridid,
        rank);
  }

  /**
   * executeSyncSelectTopSmallerThanOrEqualLimit
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes SelectTopSmallerThanOrEqualLimit Query synchronously
   * @param yearmonthdaygridid
   * @param rank
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncSelectTopSmallerThanOrEqualLimit (
    Object yearmonthdaygridid,
    Object rank) throws Exception {

    return
      this.getQuery(kSelectTopSmallerThanOrEqualLimitName).executeSync(
        yearmonthdaygridid,
        rank);
  }

  // Query: SelectTopSmallerThanLimit
  // Description:
  //   selects the top ranked posts in a grid having a rank smaller than a 
  //   spcified rank value and the returned count is limited by the 
  //   value of limit (e.g.: 10) 
  // Parepared Statement:
  //   SELECT rank, post_id FROM ig_app_data.posts_rank_grid WHERE 
  //   year_month_day_grid_id = :year_month_day_grid_id AND rank < 
  //   :rank LIMIT 30; 

  /**
   * getQuerySelectTopSmallerThanLimit
   * @return SelectTopSmallerThanLimit Query in the form of
   *           a Query Object
   * @throws Exception
   */
  public Query getQuerySelectTopSmallerThanLimit (
    ) throws Exception {

    return this.getQuery(kSelectTopSmallerThanLimitName);
  }

  /**
   * getQueryDispatchableSelectTopSmallerThanLimit
   * @param yearmonthdaygridid
   * @param rank
   * @return SelectTopSmallerThanLimit Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableSelectTopSmallerThanLimit (
    Object yearmonthdaygridid,
    Object rank) throws Exception {

    return
      this.getQueryDispatchable(
        kSelectTopSmallerThanLimitName,
        yearmonthdaygridid,
        rank);
  }

  /**
   * getBoundStatementSelectTopSmallerThanLimit
   * @param yearmonthdaygridid
   * @param rank
   * @return SelectTopSmallerThanLimit Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementSelectTopSmallerThanLimit (
    Object yearmonthdaygridid,
    Object rank) throws Exception {

    return
      this.getQuery(kSelectTopSmallerThanLimitName).getBoundStatement(
        yearmonthdaygridid,
        rank);
  }

  /**
   * executeAsyncSelectTopSmallerThanLimit
   * executes SelectTopSmallerThanLimit Query asynchronously
   * @param yearmonthdaygridid
   * @param rank
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncSelectTopSmallerThanLimit (
    Object yearmonthdaygridid,
    Object rank) throws Exception {

    return
      this.getQuery(kSelectTopSmallerThanLimitName).executeAsync(
        yearmonthdaygridid,
        rank);
  }

  /**
   * executeSyncSelectTopSmallerThanLimit
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes SelectTopSmallerThanLimit Query synchronously
   * @param yearmonthdaygridid
   * @param rank
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncSelectTopSmallerThanLimit (
    Object yearmonthdaygridid,
    Object rank) throws Exception {

    return
      this.getQuery(kSelectTopSmallerThanLimitName).executeSync(
        yearmonthdaygridid,
        rank);
  }

}
