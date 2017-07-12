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
 * UsersIndex represents
 *   Table [users_index]
 *   in Keyspace [ig_app_data]
 * 
 * Name: users_index
 * Description:
 *   indexes all user ids partitioned by registration day -- to enable 
 *   iterating on all registered users 
 * 
 * Columns:
 *   year_month_day : varchar
 *   registration_time : bigint
 *   user_id : uuid

 * Partition Keys: year_month_day, registration_time, user_id
 * Secondary Keys: 
 * Caching: ALL
 * Order By:
 *   registration_time : ASC

 * Queries:
 *   - Name: insert
 *   Description:
 *     inserts a new registered user 
 *   Prepared Statement:
 *     INSERT INTO ig_app_data.users_index (year_month_day, 
 *     registration_time, user_id) VALUES (:year_month_day, 
 *     :registration_time, :user_id); 
 *   - Name: select_earliest_limit
 *   Description:
 *     selects earliest registered users on a day where the returned count 
 *     is limited by the value of limit (e.g.: 10) 
 *   Prepared Statement:
 *     SELECT registration_time, user_id FROM ig_app_data.users_index 
 *     WHERE year_month_day = :year_month_day LIMIT 30; 
 *   - Name: select_equal
 *   Description:
 *     selects users registered on a day having a specific registration 
 *     time 
 *   Prepared Statement:
 *     SELECT registration_time, user_id FROM ig_app_data.users_index 
 *     WHERE year_month_day = :year_month_day AND registration_time 
 *     = :registration_time; 
 *   - Name: select_at_or_after_limit
 *   Description:
 *     selects registered users on a day at or after a specific time where 
 *     the returned count is limited by the value of limit (e.g.: 
 *     10) 
 *   Prepared Statement:
 *     SELECT registration_time, user_id FROM ig_app_data.users_index 
 *     WHERE year_month_day = :year_month_day AND registration_time 
 *     >= :registration_time LIMIT 30; 
 *   - Name: select_after_limit
 *   Description:
 *     selects registered users on a day after a specific time where the 
 *     returned count is limited by the value of limit (e.g.: 10) 
 *   Prepared Statement:
 *     SELECT registration_time, user_id FROM ig_app_data.users_index 
 *     WHERE year_month_day = :year_month_day AND registration_time 
 *     > :registration_time LIMIT 30; 
 *   - Name: select
 *   Description:
 *     selects all users registered on a day, consider using paging when 
 *     using this query 
 *   Prepared Statement:
 *     SELECT registration_time, user_id FROM ig_app_data.users_index 
 *     WHERE year_month_day = :year_month_day; 
 * */
public class UsersIndex extends Table {

  private static final String kKeySpaceName =
    "ig_app_data";
  private static final String kTableName =
    "users_index";

  public static final String kYearMonthDayColumnName =
    "year_month_day";
  public static final String kRegistrationTimeColumnName =
    "registration_time";
  public static final String kUserIdColumnName =
    "user_id";

  /**
   * Query:
   * Name: insert
   * Description:
   *   inserts a new registered user 
   * Prepared Statement:
   *   INSERT INTO ig_app_data.users_index (year_month_day, 
   *   registration_time, user_id) VALUES (:year_month_day, 
   *   :registration_time, :user_id); 
   */
  private static final String kInsertName =
    "insert";
  private static final String kInsertDescription =
    "inserts a new registered user ";
  private static final String kInsertPreparedStatement =
    "INSERT INTO ig_app_data.users_index (year_month_day, "
    + "registration_time, user_id) VALUES (:year_month_day, "
    + ":registration_time, :user_id); ";

  /**
   * Query:
   * Name: select_earliest_limit
   * Description:
   *   selects earliest registered users on a day where the returned count 
   *   is limited by the value of limit (e.g.: 10) 
   * Prepared Statement:
   *   SELECT registration_time, user_id FROM ig_app_data.users_index 
   *   WHERE year_month_day = :year_month_day LIMIT 30; 
   */
  private static final String kSelectEarliestLimitName =
    "select_earliest_limit";
  private static final String kSelectEarliestLimitDescription =
    "selects earliest registered users on a day where the returned count is "
    + "limited by the value of limit (e.g.: 10) ";
  private static final String kSelectEarliestLimitPreparedStatement =
    "SELECT registration_time, user_id FROM ig_app_data.users_index WHERE "
    + "year_month_day = :year_month_day LIMIT 30; ";

  /**
   * Query:
   * Name: select_equal
   * Description:
   *   selects users registered on a day having a specific registration 
   *   time 
   * Prepared Statement:
   *   SELECT registration_time, user_id FROM ig_app_data.users_index 
   *   WHERE year_month_day = :year_month_day AND registration_time 
   *   = :registration_time; 
   */
  private static final String kSelectEqualName =
    "select_equal";
  private static final String kSelectEqualDescription =
    "selects users registered on a day having a specific registration time ";
  private static final String kSelectEqualPreparedStatement =
    "SELECT registration_time, user_id FROM ig_app_data.users_index WHERE "
    + "year_month_day = :year_month_day AND registration_time = "
    + ":registration_time; ";

  /**
   * Query:
   * Name: select_at_or_after_limit
   * Description:
   *   selects registered users on a day at or after a specific time where 
   *   the returned count is limited by the value of limit (e.g.: 
   *   10) 
   * Prepared Statement:
   *   SELECT registration_time, user_id FROM ig_app_data.users_index 
   *   WHERE year_month_day = :year_month_day AND registration_time 
   *   >= :registration_time LIMIT 30; 
   */
  private static final String kSelectAtOrAfterLimitName =
    "select_at_or_after_limit";
  private static final String kSelectAtOrAfterLimitDescription =
    "selects registered users on a day at or after a specific time where "
    + "the returned count is limited by the value of limit (e.g.: 10) ";
  private static final String kSelectAtOrAfterLimitPreparedStatement =
    "SELECT registration_time, user_id FROM ig_app_data.users_index WHERE "
    + "year_month_day = :year_month_day AND registration_time >= "
    + ":registration_time LIMIT 30; ";

  /**
   * Query:
   * Name: select_after_limit
   * Description:
   *   selects registered users on a day after a specific time where the 
   *   returned count is limited by the value of limit (e.g.: 10) 
   * Prepared Statement:
   *   SELECT registration_time, user_id FROM ig_app_data.users_index 
   *   WHERE year_month_day = :year_month_day AND registration_time 
   *   > :registration_time LIMIT 30; 
   */
  private static final String kSelectAfterLimitName =
    "select_after_limit";
  private static final String kSelectAfterLimitDescription =
    "selects registered users on a day after a specific time where the "
    + "returned count is limited by the value of limit (e.g.: 10) ";
  private static final String kSelectAfterLimitPreparedStatement =
    "SELECT registration_time, user_id FROM ig_app_data.users_index WHERE "
    + "year_month_day = :year_month_day AND registration_time > "
    + ":registration_time LIMIT 30; ";

  /**
   * Query:
   * Name: select
   * Description:
   *   selects all users registered on a day, consider using paging when 
   *   using this query 
   * Prepared Statement:
   *   SELECT registration_time, user_id FROM ig_app_data.users_index 
   *   WHERE year_month_day = :year_month_day; 
   */
  private static final String kSelectName =
    "select";
  private static final String kSelectDescription =
    "selects all users registered on a day, consider using paging when "
    + "using this query ";
  private static final String kSelectPreparedStatement =
    "SELECT registration_time, user_id FROM ig_app_data.users_index WHERE "
    + "year_month_day = :year_month_day; ";

  /**
   * Constructor UsersIndex
   * @return new UsersIndex Object
   * @throws Exception
   */
  private UsersIndex () throws Exception {

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

  private static UsersIndex instance = null;

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

      instance = new UsersIndex();
    }
  }

  /**
   * i
   * @return singleton static instance of UsersIndex
   * @throws Exception
   */
  public static UsersIndex i () throws Exception {

    if (instance == null) {

      instance = new UsersIndex();
    }

    return instance;
  }

  // Query: Insert
  // Description:
  //   inserts a new registered user 
  // Parepared Statement:
  //   INSERT INTO ig_app_data.users_index (year_month_day, 
  //   registration_time, user_id) VALUES (:year_month_day, 
  //   :registration_time, :user_id); 

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
   * @param yearmonthday
   * @param registrationtime
   * @param userid
   * @return Insert Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableInsert (
    Object yearmonthday,
    Object registrationtime,
    Object userid) throws Exception {

    return
      this.getQueryDispatchable(
        kInsertName,
        yearmonthday,
        registrationtime,
        userid);
  }

  /**
   * getBoundStatementInsert
   * @param yearmonthday
   * @param registrationtime
   * @param userid
   * @return Insert Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementInsert (
    Object yearmonthday,
    Object registrationtime,
    Object userid) throws Exception {

    return
      this.getQuery(kInsertName).getBoundStatement(
        yearmonthday,
        registrationtime,
        userid);
  }

  /**
   * executeAsyncInsert
   * executes Insert Query asynchronously
   * @param yearmonthday
   * @param registrationtime
   * @param userid
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncInsert (
    Object yearmonthday,
    Object registrationtime,
    Object userid) throws Exception {

    return
      this.getQuery(kInsertName).executeAsync(
        yearmonthday,
        registrationtime,
        userid);
  }

  /**
   * executeSyncInsert
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes Insert Query synchronously
   * @param yearmonthday
   * @param registrationtime
   * @param userid
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncInsert (
    Object yearmonthday,
    Object registrationtime,
    Object userid) throws Exception {

    return
      this.getQuery(kInsertName).executeSync(
        yearmonthday,
        registrationtime,
        userid);
  }

  // Query: SelectEarliestLimit
  // Description:
  //   selects earliest registered users on a day where the returned count 
  //   is limited by the value of limit (e.g.: 10) 
  // Parepared Statement:
  //   SELECT registration_time, user_id FROM ig_app_data.users_index 
  //   WHERE year_month_day = :year_month_day LIMIT 30; 

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
   * @param yearmonthday
   * @return SelectEarliestLimit Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableSelectEarliestLimit (
    Object yearmonthday) throws Exception {

    return
      this.getQueryDispatchable(
        kSelectEarliestLimitName,
        yearmonthday);
  }

  /**
   * getBoundStatementSelectEarliestLimit
   * @param yearmonthday
   * @return SelectEarliestLimit Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementSelectEarliestLimit (
    Object yearmonthday) throws Exception {

    return
      this.getQuery(kSelectEarliestLimitName).getBoundStatement(
        yearmonthday);
  }

  /**
   * executeAsyncSelectEarliestLimit
   * executes SelectEarliestLimit Query asynchronously
   * @param yearmonthday
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncSelectEarliestLimit (
    Object yearmonthday) throws Exception {

    return
      this.getQuery(kSelectEarliestLimitName).executeAsync(
        yearmonthday);
  }

  /**
   * executeSyncSelectEarliestLimit
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes SelectEarliestLimit Query synchronously
   * @param yearmonthday
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncSelectEarliestLimit (
    Object yearmonthday) throws Exception {

    return
      this.getQuery(kSelectEarliestLimitName).executeSync(
        yearmonthday);
  }

  // Query: SelectEqual
  // Description:
  //   selects users registered on a day having a specific registration 
  //   time 
  // Parepared Statement:
  //   SELECT registration_time, user_id FROM ig_app_data.users_index 
  //   WHERE year_month_day = :year_month_day AND registration_time 
  //   = :registration_time; 

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
   * @param yearmonthday
   * @param registrationtime
   * @return SelectEqual Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableSelectEqual (
    Object yearmonthday,
    Object registrationtime) throws Exception {

    return
      this.getQueryDispatchable(
        kSelectEqualName,
        yearmonthday,
        registrationtime);
  }

  /**
   * getBoundStatementSelectEqual
   * @param yearmonthday
   * @param registrationtime
   * @return SelectEqual Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementSelectEqual (
    Object yearmonthday,
    Object registrationtime) throws Exception {

    return
      this.getQuery(kSelectEqualName).getBoundStatement(
        yearmonthday,
        registrationtime);
  }

  /**
   * executeAsyncSelectEqual
   * executes SelectEqual Query asynchronously
   * @param yearmonthday
   * @param registrationtime
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncSelectEqual (
    Object yearmonthday,
    Object registrationtime) throws Exception {

    return
      this.getQuery(kSelectEqualName).executeAsync(
        yearmonthday,
        registrationtime);
  }

  /**
   * executeSyncSelectEqual
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes SelectEqual Query synchronously
   * @param yearmonthday
   * @param registrationtime
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncSelectEqual (
    Object yearmonthday,
    Object registrationtime) throws Exception {

    return
      this.getQuery(kSelectEqualName).executeSync(
        yearmonthday,
        registrationtime);
  }

  // Query: SelectAtOrAfterLimit
  // Description:
  //   selects registered users on a day at or after a specific time where 
  //   the returned count is limited by the value of limit (e.g.: 
  //   10) 
  // Parepared Statement:
  //   SELECT registration_time, user_id FROM ig_app_data.users_index 
  //   WHERE year_month_day = :year_month_day AND registration_time 
  //   >= :registration_time LIMIT 30; 

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
   * @param yearmonthday
   * @param registrationtime
   * @return SelectAtOrAfterLimit Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableSelectAtOrAfterLimit (
    Object yearmonthday,
    Object registrationtime) throws Exception {

    return
      this.getQueryDispatchable(
        kSelectAtOrAfterLimitName,
        yearmonthday,
        registrationtime);
  }

  /**
   * getBoundStatementSelectAtOrAfterLimit
   * @param yearmonthday
   * @param registrationtime
   * @return SelectAtOrAfterLimit Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementSelectAtOrAfterLimit (
    Object yearmonthday,
    Object registrationtime) throws Exception {

    return
      this.getQuery(kSelectAtOrAfterLimitName).getBoundStatement(
        yearmonthday,
        registrationtime);
  }

  /**
   * executeAsyncSelectAtOrAfterLimit
   * executes SelectAtOrAfterLimit Query asynchronously
   * @param yearmonthday
   * @param registrationtime
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncSelectAtOrAfterLimit (
    Object yearmonthday,
    Object registrationtime) throws Exception {

    return
      this.getQuery(kSelectAtOrAfterLimitName).executeAsync(
        yearmonthday,
        registrationtime);
  }

  /**
   * executeSyncSelectAtOrAfterLimit
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes SelectAtOrAfterLimit Query synchronously
   * @param yearmonthday
   * @param registrationtime
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncSelectAtOrAfterLimit (
    Object yearmonthday,
    Object registrationtime) throws Exception {

    return
      this.getQuery(kSelectAtOrAfterLimitName).executeSync(
        yearmonthday,
        registrationtime);
  }

  // Query: SelectAfterLimit
  // Description:
  //   selects registered users on a day after a specific time where the 
  //   returned count is limited by the value of limit (e.g.: 10) 
  // Parepared Statement:
  //   SELECT registration_time, user_id FROM ig_app_data.users_index 
  //   WHERE year_month_day = :year_month_day AND registration_time 
  //   > :registration_time LIMIT 30; 

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
   * @param yearmonthday
   * @param registrationtime
   * @return SelectAfterLimit Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableSelectAfterLimit (
    Object yearmonthday,
    Object registrationtime) throws Exception {

    return
      this.getQueryDispatchable(
        kSelectAfterLimitName,
        yearmonthday,
        registrationtime);
  }

  /**
   * getBoundStatementSelectAfterLimit
   * @param yearmonthday
   * @param registrationtime
   * @return SelectAfterLimit Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementSelectAfterLimit (
    Object yearmonthday,
    Object registrationtime) throws Exception {

    return
      this.getQuery(kSelectAfterLimitName).getBoundStatement(
        yearmonthday,
        registrationtime);
  }

  /**
   * executeAsyncSelectAfterLimit
   * executes SelectAfterLimit Query asynchronously
   * @param yearmonthday
   * @param registrationtime
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncSelectAfterLimit (
    Object yearmonthday,
    Object registrationtime) throws Exception {

    return
      this.getQuery(kSelectAfterLimitName).executeAsync(
        yearmonthday,
        registrationtime);
  }

  /**
   * executeSyncSelectAfterLimit
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes SelectAfterLimit Query synchronously
   * @param yearmonthday
   * @param registrationtime
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncSelectAfterLimit (
    Object yearmonthday,
    Object registrationtime) throws Exception {

    return
      this.getQuery(kSelectAfterLimitName).executeSync(
        yearmonthday,
        registrationtime);
  }

  // Query: Select
  // Description:
  //   selects all users registered on a day, consider using paging when 
  //   using this query 
  // Parepared Statement:
  //   SELECT registration_time, user_id FROM ig_app_data.users_index 
  //   WHERE year_month_day = :year_month_day; 

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
   * @param yearmonthday
   * @return Select Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableSelect (
    Object yearmonthday) throws Exception {

    return
      this.getQueryDispatchable(
        kSelectName,
        yearmonthday);
  }

  /**
   * getBoundStatementSelect
   * @param yearmonthday
   * @return Select Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementSelect (
    Object yearmonthday) throws Exception {

    return
      this.getQuery(kSelectName).getBoundStatement(
        yearmonthday);
  }

  /**
   * executeAsyncSelect
   * executes Select Query asynchronously
   * @param yearmonthday
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncSelect (
    Object yearmonthday) throws Exception {

    return
      this.getQuery(kSelectName).executeAsync(
        yearmonthday);
  }

  /**
   * executeSyncSelect
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes Select Query synchronously
   * @param yearmonthday
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncSelect (
    Object yearmonthday) throws Exception {

    return
      this.getQuery(kSelectName).executeSync(
        yearmonthday);
  }

}
