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
 * HourlyControllersErrorLogs represents
 *   Table [hourly_controllers_error_logs]
 *   in Keyspace [ig_logging]
 * 
 * Name: hourly_controllers_error_logs
 * Description:
 *   indexes logs for failed requests per-controller(s) per-hour sorted by 
 *   oldest 
 * 
 * Columns:
 *   year_month_day_hour_controller : varchar
 *   log_time : bigint
 *   log_id : uuid

 * Partition Keys: year_month_day_hour_controller, log_time, log_id
 * Secondary Keys: 
 * Caching: ALL
 * Order By:
 *   log_time : ASC

 * Queries:
 *   - Name: insert
 *   Description:
 *     inserts a new log's index 
 *   Prepared Statement:
 *     INSERT INTO ig_logging.hourly_controllers_error_logs 
 *     (year_month_day_hour_controller, log_time, log_id) VALUES 
 *     (:year_month_day_hour_controller, :log_time, :log_id); 
 *   - Name: select
 *   Description:
 *     selects all controller(s)' logs during an hour 
 *   Prepared Statement:
 *     SELECT log_time, log_id FROM 
 *     ig_logging.hourly_controllers_error_logs WHERE 
 *     year_month_day_hour_controller = 
 *     :year_month_day_hour_controller; 
 *   - Name: select_after
 *   Description:
 *     selects all controller(s)' logs during an hour after a specific 
 *     time 
 *   Prepared Statement:
 *     SELECT log_time, log_id FROM 
 *     ig_logging.hourly_controllers_error_logs WHERE 
 *     year_month_day_hour_controller = 
 *     :year_month_day_hour_controller AND log_time > :log_time; 
 *   - Name: select_before
 *   Description:
 *     selects all controller(s)' logs during an hour before a specific 
 *     time 
 *   Prepared Statement:
 *     SELECT log_time, log_id FROM 
 *     ig_logging.hourly_controllers_error_logs WHERE 
 *     year_month_day_hour_controller = 
 *     :year_month_day_hour_controller AND log_time < :log_time; 
 *   - Name: select_during
 *   Description:
 *     selects all controller(s)' logs during an hour during a specific 
 *     period 
 *   Prepared Statement:
 *     SELECT log_time, log_id FROM 
 *     ig_logging.hourly_controllers_error_logs WHERE 
 *     year_month_day_hour_controller = 
 *     :year_month_day_hour_controller AND log_time >= 
 *     :log_time_start AND log_time <= :log_time_end; 
 * */
public class HourlyControllersErrorLogs extends Table {

  private static final String kKeySpaceName =
    "ig_logging";
  private static final String kTableName =
    "hourly_controllers_error_logs";

  public static final String kYearMonthDayHourControllerColumnName =
    "year_month_day_hour_controller";
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
   *   INSERT INTO ig_logging.hourly_controllers_error_logs 
   *   (year_month_day_hour_controller, log_time, log_id) VALUES 
   *   (:year_month_day_hour_controller, :log_time, :log_id); 
   */
  private static final String kInsertName =
    "insert";
  private static final String kInsertDescription =
    "inserts a new log's index ";
  private static final String kInsertPreparedStatement =
    "INSERT INTO ig_logging.hourly_controllers_error_logs "
    + "(year_month_day_hour_controller, log_time, log_id) VALUES "
    + "(:year_month_day_hour_controller, :log_time, :log_id); ";

  /**
   * Query:
   * Name: select
   * Description:
   *   selects all controller(s)' logs during an hour 
   * Prepared Statement:
   *   SELECT log_time, log_id FROM 
   *   ig_logging.hourly_controllers_error_logs WHERE 
   *   year_month_day_hour_controller = 
   *   :year_month_day_hour_controller; 
   */
  private static final String kSelectName =
    "select";
  private static final String kSelectDescription =
    "selects all controller(s)' logs during an hour ";
  private static final String kSelectPreparedStatement =
    "SELECT log_time, log_id FROM ig_logging.hourly_controllers_error_logs "
    + "WHERE year_month_day_hour_controller = "
    + ":year_month_day_hour_controller; ";

  /**
   * Query:
   * Name: select_after
   * Description:
   *   selects all controller(s)' logs during an hour after a specific 
   *   time 
   * Prepared Statement:
   *   SELECT log_time, log_id FROM 
   *   ig_logging.hourly_controllers_error_logs WHERE 
   *   year_month_day_hour_controller = 
   *   :year_month_day_hour_controller AND log_time > :log_time; 
   */
  private static final String kSelectAfterName =
    "select_after";
  private static final String kSelectAfterDescription =
    "selects all controller(s)' logs during an hour after a specific time ";
  private static final String kSelectAfterPreparedStatement =
    "SELECT log_time, log_id FROM ig_logging.hourly_controllers_error_logs "
    + "WHERE year_month_day_hour_controller = "
    + ":year_month_day_hour_controller AND log_time > :log_time; ";

  /**
   * Query:
   * Name: select_before
   * Description:
   *   selects all controller(s)' logs during an hour before a specific 
   *   time 
   * Prepared Statement:
   *   SELECT log_time, log_id FROM 
   *   ig_logging.hourly_controllers_error_logs WHERE 
   *   year_month_day_hour_controller = 
   *   :year_month_day_hour_controller AND log_time < :log_time; 
   */
  private static final String kSelectBeforeName =
    "select_before";
  private static final String kSelectBeforeDescription =
    "selects all controller(s)' logs during an hour before a specific time ";
  private static final String kSelectBeforePreparedStatement =
    "SELECT log_time, log_id FROM ig_logging.hourly_controllers_error_logs "
    + "WHERE year_month_day_hour_controller = "
    + ":year_month_day_hour_controller AND log_time < :log_time; ";

  /**
   * Query:
   * Name: select_during
   * Description:
   *   selects all controller(s)' logs during an hour during a specific 
   *   period 
   * Prepared Statement:
   *   SELECT log_time, log_id FROM 
   *   ig_logging.hourly_controllers_error_logs WHERE 
   *   year_month_day_hour_controller = 
   *   :year_month_day_hour_controller AND log_time >= 
   *   :log_time_start AND log_time <= :log_time_end; 
   */
  private static final String kSelectDuringName =
    "select_during";
  private static final String kSelectDuringDescription =
    "selects all controller(s)' logs during an hour during a specific "
    + "period ";
  private static final String kSelectDuringPreparedStatement =
    "SELECT log_time, log_id FROM ig_logging.hourly_controllers_error_logs "
    + "WHERE year_month_day_hour_controller = "
    + ":year_month_day_hour_controller AND log_time >= :log_time_start "
    + "AND log_time <= :log_time_end; ";

  /**
   * Constructor HourlyControllersErrorLogs
   * @return new HourlyControllersErrorLogs Object
   * @throws Exception
   */
  private HourlyControllersErrorLogs () throws Exception {

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

  private static HourlyControllersErrorLogs instance = null;

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

      instance = new HourlyControllersErrorLogs();
    }
  }

  /**
   * i
   * @return singleton static instance of HourlyControllersErrorLogs
   * @throws Exception
   */
  public static HourlyControllersErrorLogs i () throws Exception {

    if (instance == null) {

      instance = new HourlyControllersErrorLogs();
    }

    return instance;
  }

  // Query: Insert
  // Description:
  //   inserts a new log's index 
  // Parepared Statement:
  //   INSERT INTO ig_logging.hourly_controllers_error_logs 
  //   (year_month_day_hour_controller, log_time, log_id) VALUES 
  //   (:year_month_day_hour_controller, :log_time, :log_id); 

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
   * @param yearmonthdayhourcontroller
   * @param logtime
   * @param logid
   * @return Insert Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableInsert (
    Object yearmonthdayhourcontroller,
    Object logtime,
    Object logid) throws Exception {

    return
      this.getQueryDispatchable(
        kInsertName,
        yearmonthdayhourcontroller,
        logtime,
        logid);
  }

  /**
   * getBoundStatementInsert
   * @param yearmonthdayhourcontroller
   * @param logtime
   * @param logid
   * @return Insert Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementInsert (
    Object yearmonthdayhourcontroller,
    Object logtime,
    Object logid) throws Exception {

    return
      this.getQuery(kInsertName).getBoundStatement(
        yearmonthdayhourcontroller,
        logtime,
        logid);
  }

  /**
   * executeAsyncInsert
   * executes Insert Query asynchronously
   * @param yearmonthdayhourcontroller
   * @param logtime
   * @param logid
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncInsert (
    Object yearmonthdayhourcontroller,
    Object logtime,
    Object logid) throws Exception {

    return
      this.getQuery(kInsertName).executeAsync(
        yearmonthdayhourcontroller,
        logtime,
        logid);
  }

  /**
   * executeSyncInsert
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes Insert Query synchronously
   * @param yearmonthdayhourcontroller
   * @param logtime
   * @param logid
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncInsert (
    Object yearmonthdayhourcontroller,
    Object logtime,
    Object logid) throws Exception {

    return
      this.getQuery(kInsertName).executeSync(
        yearmonthdayhourcontroller,
        logtime,
        logid);
  }

  // Query: Select
  // Description:
  //   selects all controller(s)' logs during an hour 
  // Parepared Statement:
  //   SELECT log_time, log_id FROM 
  //   ig_logging.hourly_controllers_error_logs WHERE 
  //   year_month_day_hour_controller = 
  //   :year_month_day_hour_controller; 

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
   * @param yearmonthdayhourcontroller
   * @return Select Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableSelect (
    Object yearmonthdayhourcontroller) throws Exception {

    return
      this.getQueryDispatchable(
        kSelectName,
        yearmonthdayhourcontroller);
  }

  /**
   * getBoundStatementSelect
   * @param yearmonthdayhourcontroller
   * @return Select Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementSelect (
    Object yearmonthdayhourcontroller) throws Exception {

    return
      this.getQuery(kSelectName).getBoundStatement(
        yearmonthdayhourcontroller);
  }

  /**
   * executeAsyncSelect
   * executes Select Query asynchronously
   * @param yearmonthdayhourcontroller
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncSelect (
    Object yearmonthdayhourcontroller) throws Exception {

    return
      this.getQuery(kSelectName).executeAsync(
        yearmonthdayhourcontroller);
  }

  /**
   * executeSyncSelect
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes Select Query synchronously
   * @param yearmonthdayhourcontroller
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncSelect (
    Object yearmonthdayhourcontroller) throws Exception {

    return
      this.getQuery(kSelectName).executeSync(
        yearmonthdayhourcontroller);
  }

  // Query: SelectAfter
  // Description:
  //   selects all controller(s)' logs during an hour after a specific 
  //   time 
  // Parepared Statement:
  //   SELECT log_time, log_id FROM 
  //   ig_logging.hourly_controllers_error_logs WHERE 
  //   year_month_day_hour_controller = 
  //   :year_month_day_hour_controller AND log_time > :log_time; 

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
   * @param yearmonthdayhourcontroller
   * @param logtime
   * @return SelectAfter Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableSelectAfter (
    Object yearmonthdayhourcontroller,
    Object logtime) throws Exception {

    return
      this.getQueryDispatchable(
        kSelectAfterName,
        yearmonthdayhourcontroller,
        logtime);
  }

  /**
   * getBoundStatementSelectAfter
   * @param yearmonthdayhourcontroller
   * @param logtime
   * @return SelectAfter Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementSelectAfter (
    Object yearmonthdayhourcontroller,
    Object logtime) throws Exception {

    return
      this.getQuery(kSelectAfterName).getBoundStatement(
        yearmonthdayhourcontroller,
        logtime);
  }

  /**
   * executeAsyncSelectAfter
   * executes SelectAfter Query asynchronously
   * @param yearmonthdayhourcontroller
   * @param logtime
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncSelectAfter (
    Object yearmonthdayhourcontroller,
    Object logtime) throws Exception {

    return
      this.getQuery(kSelectAfterName).executeAsync(
        yearmonthdayhourcontroller,
        logtime);
  }

  /**
   * executeSyncSelectAfter
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes SelectAfter Query synchronously
   * @param yearmonthdayhourcontroller
   * @param logtime
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncSelectAfter (
    Object yearmonthdayhourcontroller,
    Object logtime) throws Exception {

    return
      this.getQuery(kSelectAfterName).executeSync(
        yearmonthdayhourcontroller,
        logtime);
  }

  // Query: SelectBefore
  // Description:
  //   selects all controller(s)' logs during an hour before a specific 
  //   time 
  // Parepared Statement:
  //   SELECT log_time, log_id FROM 
  //   ig_logging.hourly_controllers_error_logs WHERE 
  //   year_month_day_hour_controller = 
  //   :year_month_day_hour_controller AND log_time < :log_time; 

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
   * @param yearmonthdayhourcontroller
   * @param logtime
   * @return SelectBefore Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableSelectBefore (
    Object yearmonthdayhourcontroller,
    Object logtime) throws Exception {

    return
      this.getQueryDispatchable(
        kSelectBeforeName,
        yearmonthdayhourcontroller,
        logtime);
  }

  /**
   * getBoundStatementSelectBefore
   * @param yearmonthdayhourcontroller
   * @param logtime
   * @return SelectBefore Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementSelectBefore (
    Object yearmonthdayhourcontroller,
    Object logtime) throws Exception {

    return
      this.getQuery(kSelectBeforeName).getBoundStatement(
        yearmonthdayhourcontroller,
        logtime);
  }

  /**
   * executeAsyncSelectBefore
   * executes SelectBefore Query asynchronously
   * @param yearmonthdayhourcontroller
   * @param logtime
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncSelectBefore (
    Object yearmonthdayhourcontroller,
    Object logtime) throws Exception {

    return
      this.getQuery(kSelectBeforeName).executeAsync(
        yearmonthdayhourcontroller,
        logtime);
  }

  /**
   * executeSyncSelectBefore
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes SelectBefore Query synchronously
   * @param yearmonthdayhourcontroller
   * @param logtime
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncSelectBefore (
    Object yearmonthdayhourcontroller,
    Object logtime) throws Exception {

    return
      this.getQuery(kSelectBeforeName).executeSync(
        yearmonthdayhourcontroller,
        logtime);
  }

  // Query: SelectDuring
  // Description:
  //   selects all controller(s)' logs during an hour during a specific 
  //   period 
  // Parepared Statement:
  //   SELECT log_time, log_id FROM 
  //   ig_logging.hourly_controllers_error_logs WHERE 
  //   year_month_day_hour_controller = 
  //   :year_month_day_hour_controller AND log_time >= 
  //   :log_time_start AND log_time <= :log_time_end; 

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
   * @param yearmonthdayhourcontroller
   * @param logtimestart
   * @param logtimeend
   * @return SelectDuring Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableSelectDuring (
    Object yearmonthdayhourcontroller,
    Object logtimestart,
    Object logtimeend) throws Exception {

    return
      this.getQueryDispatchable(
        kSelectDuringName,
        yearmonthdayhourcontroller,
        logtimestart,
        logtimeend);
  }

  /**
   * getBoundStatementSelectDuring
   * @param yearmonthdayhourcontroller
   * @param logtimestart
   * @param logtimeend
   * @return SelectDuring Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementSelectDuring (
    Object yearmonthdayhourcontroller,
    Object logtimestart,
    Object logtimeend) throws Exception {

    return
      this.getQuery(kSelectDuringName).getBoundStatement(
        yearmonthdayhourcontroller,
        logtimestart,
        logtimeend);
  }

  /**
   * executeAsyncSelectDuring
   * executes SelectDuring Query asynchronously
   * @param yearmonthdayhourcontroller
   * @param logtimestart
   * @param logtimeend
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncSelectDuring (
    Object yearmonthdayhourcontroller,
    Object logtimestart,
    Object logtimeend) throws Exception {

    return
      this.getQuery(kSelectDuringName).executeAsync(
        yearmonthdayhourcontroller,
        logtimestart,
        logtimeend);
  }

  /**
   * executeSyncSelectDuring
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes SelectDuring Query synchronously
   * @param yearmonthdayhourcontroller
   * @param logtimestart
   * @param logtimeend
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncSelectDuring (
    Object yearmonthdayhourcontroller,
    Object logtimestart,
    Object logtimeend) throws Exception {

    return
      this.getQuery(kSelectDuringName).executeSync(
        yearmonthdayhourcontroller,
        logtimestart,
        logtimeend);
  }

}
