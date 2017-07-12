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
 * PostsRankCountry represents
 *   Table [posts_rank_country]
 *   in Keyspace [ig_app_data]
 * 
 * Name: posts_rank_country
 * Description:
 *   ranks the top N (e.g.: 1000) posts by country each day 
 * 
 * Columns:
 *   year_month_day_country_code : varchar
 *   rank : double
 *   post_id : uuid

 * Partition Keys: year_month_day_country_code, rank, post_id
 * Secondary Keys: 
 * Caching: ALL
 * Order By:
 *   rank : DESC

 * Queries:
 *   - Name: insert
 *   Description:
 *     inserts a post's rank 
 *   Prepared Statement:
 *     INSERT INTO ig_app_data.posts_rank_country 
 *     (year_month_day_country_code, rank, post_id) VALUES 
 *     (:year_month_day_country_code, :rank, :post_id); 
 *   - Name: select_top_limit
 *   Description:
 *     selects the top ranked posts in a country where the returned count 
 *     is limited by the value of limit (e.g.: 10) 
 *   Prepared Statement:
 *     SELECT rank, post_id FROM ig_app_data.posts_rank_country WHERE 
 *     year_month_day_country_code = :year_month_day_country_code 
 *     LIMIT 30; 
 *   - Name: select_top_equal
 *   Description:
 *     selects the top ranked posts in a country having a specific rank 
 *     value 
 *   Prepared Statement:
 *     SELECT rank, post_id FROM ig_app_data.posts_rank_country WHERE 
 *     year_month_day_country_code = :year_month_day_country_code 
 *     AND rank = :rank; 
 *   - Name: select_top_smaller_than_or_equal_limit
 *   Description:
 *     selects the top ranked posts from a country having a rank smaller 
 *     than or equal to a specified rank value and the returned 
 *     count is limited by the value of limit (e.g.: 10) 
 *   Prepared Statement:
 *     SELECT rank, post_id FROM ig_app_data.posts_rank_country WHERE 
 *     year_month_day_country_code = :year_month_day_country_code 
 *     AND rank <= :rank LIMIT 30; 
 *   - Name: select_top_smaller_than_limit
 *   Description:
 *     selects the top ranked posts from a country having a rank smaller 
 *     than a specified rank value and the returned count is 
 *     limited by the value of limit (e.g.: 10) 
 *   Prepared Statement:
 *     SELECT rank, post_id FROM ig_app_data.posts_rank_country WHERE 
 *     year_month_day_country_code = :year_month_day_country_code 
 *     AND rank < :rank LIMIT 30; 
 * */
public class PostsRankCountry extends Table {

  private static final String kKeySpaceName =
    "ig_app_data";
  private static final String kTableName =
    "posts_rank_country";

  public static final String kYearMonthDayCountryCodeColumnName =
    "year_month_day_country_code";
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
   *   INSERT INTO ig_app_data.posts_rank_country 
   *   (year_month_day_country_code, rank, post_id) VALUES 
   *   (:year_month_day_country_code, :rank, :post_id); 
   */
  private static final String kInsertName =
    "insert";
  private static final String kInsertDescription =
    "inserts a post's rank ";
  private static final String kInsertPreparedStatement =
    "INSERT INTO ig_app_data.posts_rank_country "
    + "(year_month_day_country_code, rank, post_id) VALUES "
    + "(:year_month_day_country_code, :rank, :post_id); ";

  /**
   * Query:
   * Name: select_top_limit
   * Description:
   *   selects the top ranked posts in a country where the returned count 
   *   is limited by the value of limit (e.g.: 10) 
   * Prepared Statement:
   *   SELECT rank, post_id FROM ig_app_data.posts_rank_country WHERE 
   *   year_month_day_country_code = :year_month_day_country_code 
   *   LIMIT 30; 
   */
  private static final String kSelectTopLimitName =
    "select_top_limit";
  private static final String kSelectTopLimitDescription =
    "selects the top ranked posts in a country where the returned count is "
    + "limited by the value of limit (e.g.: 10) ";
  private static final String kSelectTopLimitPreparedStatement =
    "SELECT rank, post_id FROM ig_app_data.posts_rank_country WHERE "
    + "year_month_day_country_code = :year_month_day_country_code "
    + "LIMIT 30; ";

  /**
   * Query:
   * Name: select_top_equal
   * Description:
   *   selects the top ranked posts in a country having a specific rank 
   *   value 
   * Prepared Statement:
   *   SELECT rank, post_id FROM ig_app_data.posts_rank_country WHERE 
   *   year_month_day_country_code = :year_month_day_country_code 
   *   AND rank = :rank; 
   */
  private static final String kSelectTopEqualName =
    "select_top_equal";
  private static final String kSelectTopEqualDescription =
    "selects the top ranked posts in a country having a specific rank value ";
  private static final String kSelectTopEqualPreparedStatement =
    "SELECT rank, post_id FROM ig_app_data.posts_rank_country WHERE "
    + "year_month_day_country_code = :year_month_day_country_code AND "
    + "rank = :rank; ";

  /**
   * Query:
   * Name: select_top_smaller_than_or_equal_limit
   * Description:
   *   selects the top ranked posts from a country having a rank smaller 
   *   than or equal to a specified rank value and the returned 
   *   count is limited by the value of limit (e.g.: 10) 
   * Prepared Statement:
   *   SELECT rank, post_id FROM ig_app_data.posts_rank_country WHERE 
   *   year_month_day_country_code = :year_month_day_country_code 
   *   AND rank <= :rank LIMIT 30; 
   */
  private static final String kSelectTopSmallerThanOrEqualLimitName =
    "select_top_smaller_than_or_equal_limit";
  private static final String kSelectTopSmallerThanOrEqualLimitDescription =
    "selects the top ranked posts from a country having a rank smaller than "
    + "or equal to a specified rank value and the returned count is "
    + "limited by the value of limit (e.g.: 10) ";
  private static final String kSelectTopSmallerThanOrEqualLimitPreparedStatement =
    "SELECT rank, post_id FROM ig_app_data.posts_rank_country WHERE "
    + "year_month_day_country_code = :year_month_day_country_code AND "
    + "rank <= :rank LIMIT 30; ";

  /**
   * Query:
   * Name: select_top_smaller_than_limit
   * Description:
   *   selects the top ranked posts from a country having a rank smaller 
   *   than a specified rank value and the returned count is 
   *   limited by the value of limit (e.g.: 10) 
   * Prepared Statement:
   *   SELECT rank, post_id FROM ig_app_data.posts_rank_country WHERE 
   *   year_month_day_country_code = :year_month_day_country_code 
   *   AND rank < :rank LIMIT 30; 
   */
  private static final String kSelectTopSmallerThanLimitName =
    "select_top_smaller_than_limit";
  private static final String kSelectTopSmallerThanLimitDescription =
    "selects the top ranked posts from a country having a rank smaller than "
    + "a specified rank value and the returned count is limited by the "
    + "value of limit (e.g.: 10) ";
  private static final String kSelectTopSmallerThanLimitPreparedStatement =
    "SELECT rank, post_id FROM ig_app_data.posts_rank_country WHERE "
    + "year_month_day_country_code = :year_month_day_country_code AND "
    + "rank < :rank LIMIT 30; ";

  /**
   * Constructor PostsRankCountry
   * @return new PostsRankCountry Object
   * @throws Exception
   */
  private PostsRankCountry () throws Exception {

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

  private static PostsRankCountry instance = null;

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

      instance = new PostsRankCountry();
    }
  }

  /**
   * i
   * @return singleton static instance of PostsRankCountry
   * @throws Exception
   */
  public static PostsRankCountry i () throws Exception {

    if (instance == null) {

      instance = new PostsRankCountry();
    }

    return instance;
  }

  // Query: Insert
  // Description:
  //   inserts a post's rank 
  // Parepared Statement:
  //   INSERT INTO ig_app_data.posts_rank_country 
  //   (year_month_day_country_code, rank, post_id) VALUES 
  //   (:year_month_day_country_code, :rank, :post_id); 

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
   * @param yearmonthdaycountrycode
   * @param rank
   * @param postid
   * @return Insert Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableInsert (
    Object yearmonthdaycountrycode,
    Object rank,
    Object postid) throws Exception {

    return
      this.getQueryDispatchable(
        kInsertName,
        yearmonthdaycountrycode,
        rank,
        postid);
  }

  /**
   * getBoundStatementInsert
   * @param yearmonthdaycountrycode
   * @param rank
   * @param postid
   * @return Insert Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementInsert (
    Object yearmonthdaycountrycode,
    Object rank,
    Object postid) throws Exception {

    return
      this.getQuery(kInsertName).getBoundStatement(
        yearmonthdaycountrycode,
        rank,
        postid);
  }

  /**
   * executeAsyncInsert
   * executes Insert Query asynchronously
   * @param yearmonthdaycountrycode
   * @param rank
   * @param postid
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncInsert (
    Object yearmonthdaycountrycode,
    Object rank,
    Object postid) throws Exception {

    return
      this.getQuery(kInsertName).executeAsync(
        yearmonthdaycountrycode,
        rank,
        postid);
  }

  /**
   * executeSyncInsert
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes Insert Query synchronously
   * @param yearmonthdaycountrycode
   * @param rank
   * @param postid
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncInsert (
    Object yearmonthdaycountrycode,
    Object rank,
    Object postid) throws Exception {

    return
      this.getQuery(kInsertName).executeSync(
        yearmonthdaycountrycode,
        rank,
        postid);
  }

  // Query: SelectTopLimit
  // Description:
  //   selects the top ranked posts in a country where the returned count 
  //   is limited by the value of limit (e.g.: 10) 
  // Parepared Statement:
  //   SELECT rank, post_id FROM ig_app_data.posts_rank_country WHERE 
  //   year_month_day_country_code = :year_month_day_country_code 
  //   LIMIT 30; 

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
   * @param yearmonthdaycountrycode
   * @return SelectTopLimit Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableSelectTopLimit (
    Object yearmonthdaycountrycode) throws Exception {

    return
      this.getQueryDispatchable(
        kSelectTopLimitName,
        yearmonthdaycountrycode);
  }

  /**
   * getBoundStatementSelectTopLimit
   * @param yearmonthdaycountrycode
   * @return SelectTopLimit Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementSelectTopLimit (
    Object yearmonthdaycountrycode) throws Exception {

    return
      this.getQuery(kSelectTopLimitName).getBoundStatement(
        yearmonthdaycountrycode);
  }

  /**
   * executeAsyncSelectTopLimit
   * executes SelectTopLimit Query asynchronously
   * @param yearmonthdaycountrycode
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncSelectTopLimit (
    Object yearmonthdaycountrycode) throws Exception {

    return
      this.getQuery(kSelectTopLimitName).executeAsync(
        yearmonthdaycountrycode);
  }

  /**
   * executeSyncSelectTopLimit
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes SelectTopLimit Query synchronously
   * @param yearmonthdaycountrycode
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncSelectTopLimit (
    Object yearmonthdaycountrycode) throws Exception {

    return
      this.getQuery(kSelectTopLimitName).executeSync(
        yearmonthdaycountrycode);
  }

  // Query: SelectTopEqual
  // Description:
  //   selects the top ranked posts in a country having a specific rank 
  //   value 
  // Parepared Statement:
  //   SELECT rank, post_id FROM ig_app_data.posts_rank_country WHERE 
  //   year_month_day_country_code = :year_month_day_country_code 
  //   AND rank = :rank; 

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
   * @param yearmonthdaycountrycode
   * @param rank
   * @return SelectTopEqual Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableSelectTopEqual (
    Object yearmonthdaycountrycode,
    Object rank) throws Exception {

    return
      this.getQueryDispatchable(
        kSelectTopEqualName,
        yearmonthdaycountrycode,
        rank);
  }

  /**
   * getBoundStatementSelectTopEqual
   * @param yearmonthdaycountrycode
   * @param rank
   * @return SelectTopEqual Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementSelectTopEqual (
    Object yearmonthdaycountrycode,
    Object rank) throws Exception {

    return
      this.getQuery(kSelectTopEqualName).getBoundStatement(
        yearmonthdaycountrycode,
        rank);
  }

  /**
   * executeAsyncSelectTopEqual
   * executes SelectTopEqual Query asynchronously
   * @param yearmonthdaycountrycode
   * @param rank
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncSelectTopEqual (
    Object yearmonthdaycountrycode,
    Object rank) throws Exception {

    return
      this.getQuery(kSelectTopEqualName).executeAsync(
        yearmonthdaycountrycode,
        rank);
  }

  /**
   * executeSyncSelectTopEqual
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes SelectTopEqual Query synchronously
   * @param yearmonthdaycountrycode
   * @param rank
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncSelectTopEqual (
    Object yearmonthdaycountrycode,
    Object rank) throws Exception {

    return
      this.getQuery(kSelectTopEqualName).executeSync(
        yearmonthdaycountrycode,
        rank);
  }

  // Query: SelectTopSmallerThanOrEqualLimit
  // Description:
  //   selects the top ranked posts from a country having a rank smaller 
  //   than or equal to a specified rank value and the returned 
  //   count is limited by the value of limit (e.g.: 10) 
  // Parepared Statement:
  //   SELECT rank, post_id FROM ig_app_data.posts_rank_country WHERE 
  //   year_month_day_country_code = :year_month_day_country_code 
  //   AND rank <= :rank LIMIT 30; 

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
   * @param yearmonthdaycountrycode
   * @param rank
   * @return SelectTopSmallerThanOrEqualLimit Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableSelectTopSmallerThanOrEqualLimit (
    Object yearmonthdaycountrycode,
    Object rank) throws Exception {

    return
      this.getQueryDispatchable(
        kSelectTopSmallerThanOrEqualLimitName,
        yearmonthdaycountrycode,
        rank);
  }

  /**
   * getBoundStatementSelectTopSmallerThanOrEqualLimit
   * @param yearmonthdaycountrycode
   * @param rank
   * @return SelectTopSmallerThanOrEqualLimit Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementSelectTopSmallerThanOrEqualLimit (
    Object yearmonthdaycountrycode,
    Object rank) throws Exception {

    return
      this.getQuery(kSelectTopSmallerThanOrEqualLimitName).getBoundStatement(
        yearmonthdaycountrycode,
        rank);
  }

  /**
   * executeAsyncSelectTopSmallerThanOrEqualLimit
   * executes SelectTopSmallerThanOrEqualLimit Query asynchronously
   * @param yearmonthdaycountrycode
   * @param rank
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncSelectTopSmallerThanOrEqualLimit (
    Object yearmonthdaycountrycode,
    Object rank) throws Exception {

    return
      this.getQuery(kSelectTopSmallerThanOrEqualLimitName).executeAsync(
        yearmonthdaycountrycode,
        rank);
  }

  /**
   * executeSyncSelectTopSmallerThanOrEqualLimit
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes SelectTopSmallerThanOrEqualLimit Query synchronously
   * @param yearmonthdaycountrycode
   * @param rank
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncSelectTopSmallerThanOrEqualLimit (
    Object yearmonthdaycountrycode,
    Object rank) throws Exception {

    return
      this.getQuery(kSelectTopSmallerThanOrEqualLimitName).executeSync(
        yearmonthdaycountrycode,
        rank);
  }

  // Query: SelectTopSmallerThanLimit
  // Description:
  //   selects the top ranked posts from a country having a rank smaller 
  //   than a specified rank value and the returned count is 
  //   limited by the value of limit (e.g.: 10) 
  // Parepared Statement:
  //   SELECT rank, post_id FROM ig_app_data.posts_rank_country WHERE 
  //   year_month_day_country_code = :year_month_day_country_code 
  //   AND rank < :rank LIMIT 30; 

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
   * @param yearmonthdaycountrycode
   * @param rank
   * @return SelectTopSmallerThanLimit Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableSelectTopSmallerThanLimit (
    Object yearmonthdaycountrycode,
    Object rank) throws Exception {

    return
      this.getQueryDispatchable(
        kSelectTopSmallerThanLimitName,
        yearmonthdaycountrycode,
        rank);
  }

  /**
   * getBoundStatementSelectTopSmallerThanLimit
   * @param yearmonthdaycountrycode
   * @param rank
   * @return SelectTopSmallerThanLimit Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementSelectTopSmallerThanLimit (
    Object yearmonthdaycountrycode,
    Object rank) throws Exception {

    return
      this.getQuery(kSelectTopSmallerThanLimitName).getBoundStatement(
        yearmonthdaycountrycode,
        rank);
  }

  /**
   * executeAsyncSelectTopSmallerThanLimit
   * executes SelectTopSmallerThanLimit Query asynchronously
   * @param yearmonthdaycountrycode
   * @param rank
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncSelectTopSmallerThanLimit (
    Object yearmonthdaycountrycode,
    Object rank) throws Exception {

    return
      this.getQuery(kSelectTopSmallerThanLimitName).executeAsync(
        yearmonthdaycountrycode,
        rank);
  }

  /**
   * executeSyncSelectTopSmallerThanLimit
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes SelectTopSmallerThanLimit Query synchronously
   * @param yearmonthdaycountrycode
   * @param rank
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncSelectTopSmallerThanLimit (
    Object yearmonthdaycountrycode,
    Object rank) throws Exception {

    return
      this.getQuery(kSelectTopSmallerThanLimitName).executeSync(
        yearmonthdaycountrycode,
        rank);
  }

}
