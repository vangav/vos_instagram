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

package com.vangav.vos_instagram.cassandra_keyspaces.ig_logging;

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
 * DailyUsersErrorLogs represents
 *   Table [daily_users_error_logs]
 *   in Keyspace [ig_logging]
 * 
 * Name: daily_users_error_logs
 * Description:
 *   indexes logs for failed requests per-user per-day sorted by oldest 
 * 
 * Columns:
 *   year_month_day_user_id : varchar
 *   log_time : bigint
 *   log_id : uuid

 * Partition Keys: year_month_day_user_id, log_time, log_id
 * Secondary Keys: 
 * Caching: ALL
 * Order By:
 *   log_time : ASC

 * Queries:
 *   - Name: insert
 *   Description:
 *     inserts a new log's index 
 *   Prepared Statement:
 *     INSERT INTO ig_logging.daily_users_error_logs 
 *     (year_month_day_user_id, log_time, log_id) VALUES 
 *     (:year_month_day_user_id, :log_time, :log_id); 
 *   - Name: select
 *   Description:
 *     selects all user's logs during a day 
 *   Prepared Statement:
 *     SELECT log_time, log_id FROM ig_logging.daily_users_error_logs 
 *     WHERE year_month_day_user_id = :year_month_day_user_id; 
 *   - Name: select_after
 *   Description:
 *     selects all user's logs during a day after a specific time 
 *   Prepared Statement:
 *     SELECT log_time, log_id FROM ig_logging.daily_users_error_logs 
 *     WHERE year_month_day_user_id = :year_month_day_user_id AND 
 *     log_time > :log_time; 
 *   - Name: select_before
 *   Description:
 *     selects all user's logs during a day before a specific time 
 *   Prepared Statement:
 *     SELECT log_time, log_id FROM ig_logging.daily_users_error_logs 
 *     WHERE year_month_day_user_id = :year_month_day_user_id AND 
 *     log_time < :log_time; 
 *   - Name: select_during
 *   Description:
 *     selects all user's logs during a day during a specific period 
 *   Prepared Statement:
 *     SELECT log_time, log_id FROM ig_logging.daily_users_error_logs 
 *     WHERE year_month_day_user_id = :year_month_day_user_id AND 
 *     log_time >= :log_time_start AND log_time <= :log_time_end; 
 * */
public class DailyUsersErrorLogs extends Table {

  private static final String kKeySpaceName =
    "ig_logging";
  private static final String kTableName =
    "daily_users_error_logs";

  public static final String kYearMonthDayUserIdColumnName =
    "year_month_day_user_id";
  public static final String kLogTimeColumnName =
    "log_time";
  public static final String kLogIdColumnName =
    "log_id";

  /**
   * Query:
   * Name: insert
   * Description:
   *   inserts a new log's index 
   * Prepared Statement:
   *   INSERT INTO ig_logging.daily_users_error_logs 
   *   (year_month_day_user_id, log_time, log_id) VALUES 
   *   (:year_month_day_user_id, :log_time, :log_id); 
   */
  private static final String kInsertName =
    "insert";
  private static final String kInsertDescription =
    "inserts a new log's index ";
  private static final String kInsertPreparedStatement =
    "INSERT INTO ig_logging.daily_users_error_logs (year_month_day_user_id, "
    + "log_time, log_id) VALUES (:year_month_day_user_id, :log_time, "
    + ":log_id); ";

  /**
   * Query:
   * Name: select
   * Description:
   *   selects all user's logs during a day 
   * Prepared Statement:
   *   SELECT log_time, log_id FROM ig_logging.daily_users_error_logs 
   *   WHERE year_month_day_user_id = :year_month_day_user_id; 
   */
  private static final String kSelectName =
    "select";
  private static final String kSelectDescription =
    "selects all user's logs during a day ";
  private static final String kSelectPreparedStatement =
    "SELECT log_time, log_id FROM ig_logging.daily_users_error_logs WHERE "
    + "year_month_day_user_id = :year_month_day_user_id; ";

  /**
   * Query:
   * Name: select_after
   * Description:
   *   selects all user's logs during a day after a specific time 
   * Prepared Statement:
   *   SELECT log_time, log_id FROM ig_logging.daily_users_error_logs 
   *   WHERE year_month_day_user_id = :year_month_day_user_id AND 
   *   log_time > :log_time; 
   */
  private static final String kSelectAfterName =
    "select_after";
  private static final String kSelectAfterDescription =
    "selects all user's logs during a day after a specific time ";
  private static final String kSelectAfterPreparedStatement =
    "SELECT log_time, log_id FROM ig_logging.daily_users_error_logs WHERE "
    + "year_month_day_user_id = :year_month_day_user_id AND log_time > "
    + ":log_time; ";

  /**
   * Query:
   * Name: select_before
   * Description:
   *   selects all user's logs during a day before a specific time 
   * Prepared Statement:
   *   SELECT log_time, log_id FROM ig_logging.daily_users_error_logs 
   *   WHERE year_month_day_user_id = :year_month_day_user_id AND 
   *   log_time < :log_time; 
   */
  private static final String kSelectBeforeName =
    "select_before";
  private static final String kSelectBeforeDescription =
    "selects all user's logs during a day before a specific time ";
  private static final String kSelectBeforePreparedStatement =
    "SELECT log_time, log_id FROM ig_logging.daily_users_error_logs WHERE "
    + "year_month_day_user_id = :year_month_day_user_id AND log_time < "
    + ":log_time; ";

  /**
   * Query:
   * Name: select_during
   * Description:
   *   selects all user's logs during a day during a specific period 
   * Prepared Statement:
   *   SELECT log_time, log_id FROM ig_logging.daily_users_error_logs 
   *   WHERE year_month_day_user_id = :year_month_day_user_id AND 
   *   log_time >= :log_time_start AND log_time <= :log_time_end; 
   */
  private static final String kSelectDuringName =
    "select_during";
  private static final String kSelectDuringDescription =
    "selects all user's logs during a day during a specific period ";
  private static final String kSelectDuringPreparedStatement =
    "SELECT log_time, log_id FROM ig_logging.daily_users_error_logs WHERE "
    + "year_month_day_user_id = :year_month_day_user_id AND log_time "
    + ">= :log_time_start AND log_time <= :log_time_end; ";

  /**
   * Constructor DailyUsersErrorLogs
   * @return new DailyUsersErrorLogs Object
   * @throws Exception
   */
  private DailyUsersErrorLogs () throws Exception {

    super (
      kKeySpaceName,
      kTableName,
      new Query (
        kInsertDescription,
        kInsertName,
        kInsertPreparedStatement),
      new Query (
        kSelectDescription,
        kSelectName,
        kSelectPreparedStatement),
      new Query (
        kSelectAfterDescription,
        kSelectAfterName,
        kSelectAfterPreparedStatement),
      new Query (
        kSelectBeforeDescription,
        kSelectBeforeName,
        kSelectBeforePreparedStatement),
      new Query (
        kSelectDuringDescription,
        kSelectDuringName,
        kSelectDuringPreparedStatement));
  }

  private static DailyUsersErrorLogs instance = null;

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

      instance = new DailyUsersErrorLogs();
    }
  }

  /**
   * i
   * @return singleton static instance of DailyUsersErrorLogs
   * @throws Exception
   */
  public static DailyUsersErrorLogs i () throws Exception {

    if (instance == null) {

      instance = new DailyUsersErrorLogs();
    }

    return instance;
  }

  // Query: Insert
  // Description:
  //   inserts a new log's index 
  // Parepared Statement:
  //   INSERT INTO ig_logging.daily_users_error_logs 
  //   (year_month_day_user_id, log_time, log_id) VALUES 
  //   (:year_month_day_user_id, :log_time, :log_id); 

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
   * @param yearmonthdayuserid
   * @param logtime
   * @param logid
   * @return Insert Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableInsert (
    Object yearmonthdayuserid,
    Object logtime,
    Object logid) throws Exception {

    return
      this.getQueryDispatchable(
        kInsertName,
        yearmonthdayuserid,
        logtime,
        logid);
  }

  /**
   * getBoundStatementInsert
   * @param yearmonthdayuserid
   * @param logtime
   * @param logid
   * @return Insert Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementInsert (
    Object yearmonthdayuserid,
    Object logtime,
    Object logid) throws Exception {

    return
      this.getQuery(kInsertName).getBoundStatement(
        yearmonthdayuserid,
        logtime,
        logid);
  }

  /**
   * executeAsyncInsert
   * executes Insert Query asynchronously
   * @param yearmonthdayuserid
   * @param logtime
   * @param logid
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncInsert (
    Object yearmonthdayuserid,
    Object logtime,
    Object logid) throws Exception {

    return
      this.getQuery(kInsertName).executeAsync(
        yearmonthdayuserid,
        logtime,
        logid);
  }

  /**
   * executeSyncInsert
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes Insert Query synchronously
   * @param yearmonthdayuserid
   * @param logtime
   * @param logid
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncInsert (
    Object yearmonthdayuserid,
    Object logtime,
    Object logid) throws Exception {

    return
      this.getQuery(kInsertName).executeSync(
        yearmonthdayuserid,
        logtime,
        logid);
  }

  // Query: Select
  // Description:
  //   selects all user's logs during a day 
  // Parepared Statement:
  //   SELECT log_time, log_id FROM ig_logging.daily_users_error_logs 
  //   WHERE year_month_day_user_id = :year_month_day_user_id; 

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
   * @param yearmonthdayuserid
   * @return Select Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableSelect (
    Object yearmonthdayuserid) throws Exception {

    return
      this.getQueryDispatchable(
        kSelectName,
        yearmonthdayuserid);
  }

  /**
   * getBoundStatementSelect
   * @param yearmonthdayuserid
   * @return Select Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementSelect (
    Object yearmonthdayuserid) throws Exception {

    return
      this.getQuery(kSelectName).getBoundStatement(
        yearmonthdayuserid);
  }

  /**
   * executeAsyncSelect
   * executes Select Query asynchronously
   * @param yearmonthdayuserid
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncSelect (
    Object yearmonthdayuserid) throws Exception {

    return
      this.getQuery(kSelectName).executeAsync(
        yearmonthdayuserid);
  }

  /**
   * executeSyncSelect
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes Select Query synchronously
   * @param yearmonthdayuserid
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncSelect (
    Object yearmonthdayuserid) throws Exception {

    return
      this.getQuery(kSelectName).executeSync(
        yearmonthdayuserid);
  }

  // Query: SelectAfter
  // Description:
  //   selects all user's logs during a day after a specific time 
  // Parepared Statement:
  //   SELECT log_time, log_id FROM ig_logging.daily_users_error_logs 
  //   WHERE year_month_day_user_id = :year_month_day_user_id AND 
  //   log_time > :log_time; 

  /**
   * getQuerySelectAfter
   * @return SelectAfter Query in the form of
   *           a Query Object
   * @throws Exception
   */
  public Query getQuerySelectAfter (
    ) throws Exception {

    return this.getQuery(kSelectAfterName);
  }

  /**
   * getQueryDispatchableSelectAfter
   * @param yearmonthdayuserid
   * @param logtime
   * @return SelectAfter Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableSelectAfter (
    Object yearmonthdayuserid,
    Object logtime) throws Exception {

    return
      this.getQueryDispatchable(
        kSelectAfterName,
        yearmonthdayuserid,
        logtime);
  }

  /**
   * getBoundStatementSelectAfter
   * @param yearmonthdayuserid
   * @param logtime
   * @return SelectAfter Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementSelectAfter (
    Object yearmonthdayuserid,
    Object logtime) throws Exception {

    return
      this.getQuery(kSelectAfterName).getBoundStatement(
        yearmonthdayuserid,
        logtime);
  }

  /**
   * executeAsyncSelectAfter
   * executes SelectAfter Query asynchronously
   * @param yearmonthdayuserid
   * @param logtime
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncSelectAfter (
    Object yearmonthdayuserid,
    Object logtime) throws Exception {

    return
      this.getQuery(kSelectAfterName).executeAsync(
        yearmonthdayuserid,
        logtime);
  }

  /**
   * executeSyncSelectAfter
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes SelectAfter Query synchronously
   * @param yearmonthdayuserid
   * @param logtime
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncSelectAfter (
    Object yearmonthdayuserid,
    Object logtime) throws Exception {

    return
      this.getQuery(kSelectAfterName).executeSync(
        yearmonthdayuserid,
        logtime);
  }

  // Query: SelectBefore
  // Description:
  //   selects all user's logs during a day before a specific time 
  // Parepared Statement:
  //   SELECT log_time, log_id FROM ig_logging.daily_users_error_logs 
  //   WHERE year_month_day_user_id = :year_month_day_user_id AND 
  //   log_time < :log_time; 

  /**
   * getQuerySelectBefore
   * @return SelectBefore Query in the form of
   *           a Query Object
   * @throws Exception
   */
  public Query getQuerySelectBefore (
    ) throws Exception {

    return this.getQuery(kSelectBeforeName);
  }

  /**
   * getQueryDispatchableSelectBefore
   * @param yearmonthdayuserid
   * @param logtime
   * @return SelectBefore Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableSelectBefore (
    Object yearmonthdayuserid,
    Object logtime) throws Exception {

    return
      this.getQueryDispatchable(
        kSelectBeforeName,
        yearmonthdayuserid,
        logtime);
  }

  /**
   * getBoundStatementSelectBefore
   * @param yearmonthdayuserid
   * @param logtime
   * @return SelectBefore Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementSelectBefore (
    Object yearmonthdayuserid,
    Object logtime) throws Exception {

    return
      this.getQuery(kSelectBeforeName).getBoundStatement(
        yearmonthdayuserid,
        logtime);
  }

  /**
   * executeAsyncSelectBefore
   * executes SelectBefore Query asynchronously
   * @param yearmonthdayuserid
   * @param logtime
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncSelectBefore (
    Object yearmonthdayuserid,
    Object logtime) throws Exception {

    return
      this.getQuery(kSelectBeforeName).executeAsync(
        yearmonthdayuserid,
        logtime);
  }

  /**
   * executeSyncSelectBefore
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes SelectBefore Query synchronously
   * @param yearmonthdayuserid
   * @param logtime
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncSelectBefore (
    Object yearmonthdayuserid,
    Object logtime) throws Exception {

    return
      this.getQuery(kSelectBeforeName).executeSync(
        yearmonthdayuserid,
        logtime);
  }

  // Query: SelectDuring
  // Description:
  //   selects all user's logs during a day during a specific period 
  // Parepared Statement:
  //   SELECT log_time, log_id FROM ig_logging.daily_users_error_logs 
  //   WHERE year_month_day_user_id = :year_month_day_user_id AND 
  //   log_time >= :log_time_start AND log_time <= :log_time_end; 

  /**
   * getQuerySelectDuring
   * @return SelectDuring Query in the form of
   *           a Query Object
   * @throws Exception
   */
  public Query getQuerySelectDuring (
    ) throws Exception {

    return this.getQuery(kSelectDuringName);
  }

  /**
   * getQueryDispatchableSelectDuring
   * @param yearmonthdayuserid
   * @param logtimestart
   * @param logtimeend
   * @return SelectDuring Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableSelectDuring (
    Object yearmonthdayuserid,
    Object logtimestart,
    Object logtimeend) throws Exception {

    return
      this.getQueryDispatchable(
        kSelectDuringName,
        yearmonthdayuserid,
        logtimestart,
        logtimeend);
  }

  /**
   * getBoundStatementSelectDuring
   * @param yearmonthdayuserid
   * @param logtimestart
   * @param logtimeend
   * @return SelectDuring Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementSelectDuring (
    Object yearmonthdayuserid,
    Object logtimestart,
    Object logtimeend) throws Exception {

    return
      this.getQuery(kSelectDuringName).getBoundStatement(
        yearmonthdayuserid,
        logtimestart,
        logtimeend);
  }

  /**
   * executeAsyncSelectDuring
   * executes SelectDuring Query asynchronously
   * @param yearmonthdayuserid
   * @param logtimestart
   * @param logtimeend
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncSelectDuring (
    Object yearmonthdayuserid,
    Object logtimestart,
    Object logtimeend) throws Exception {

    return
      this.getQuery(kSelectDuringName).executeAsync(
        yearmonthdayuserid,
        logtimestart,
        logtimeend);
  }

  /**
   * executeSyncSelectDuring
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes SelectDuring Query synchronously
   * @param yearmonthdayuserid
   * @param logtimestart
   * @param logtimeend
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncSelectDuring (
    Object yearmonthdayuserid,
    Object logtimestart,
    Object logtimeend) throws Exception {

    return
      this.getQuery(kSelectDuringName).executeSync(
        yearmonthdayuserid,
        logtimestart,
        logtimeend);
  }

}
