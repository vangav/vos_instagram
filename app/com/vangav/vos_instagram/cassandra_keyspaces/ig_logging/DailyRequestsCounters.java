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
 * DailyRequestsCounters represents
 *   Table [daily_requests_counters]
 *   in Keyspace [ig_logging]
 * 
 * Name: daily_requests_counters
 * Description:
 *   stores counters per-day for all controllers and per-controller 
 * 
 * Columns:
 *   year_month_day_controller : varchar
 *   requests : counter
 *   ok_responses : counter
 *   bad_request_responses : counter
 *   internal_error_responses : counter
 *   run_time_milli_seconds : counter

 * Partition Keys: year_month_day_controller
 * Secondary Keys: 
 * Caching: ALL
 * Order By:

 * Queries:
 *   - Name: increment_ok_responses
 *   Description:
 *     increments requests with OK responses 
 *   Prepared Statement:
 *     UPDATE ig_logging.daily_requests_counters SET requests = requests + 
 *     1, ok_responses = ok_responses + 1, run_time_milli_seconds = 
 *     run_time_milli_seconds + :run_time_milli_seconds WHERE 
 *     year_month_day_controller = :year_month_day_controller; 
 *   - Name: increment_bad_request_responses
 *   Description:
 *     increments requests with BAD_REQUEST responses 
 *   Prepared Statement:
 *     UPDATE ig_logging.daily_requests_counters SET requests = requests + 
 *     1, bad_request_responses = bad_request_responses + 1, 
 *     run_time_milli_seconds = run_time_milli_seconds + 
 *     :run_time_milli_seconds WHERE year_month_day_controller = 
 *     :year_month_day_controller; 
 *   - Name: increment_internal_error_responses
 *   Description:
 *     increments requests with INTERNAL_ERROR responses 
 *   Prepared Statement:
 *     UPDATE ig_logging.daily_requests_counters SET requests = requests + 
 *     1, internal_error_responses = internal_error_responses + 1, 
 *     run_time_milli_seconds = run_time_milli_seconds + 
 *     :run_time_milli_seconds WHERE year_month_day_controller = 
 *     :year_month_day_controller; 
 *   - Name: select
 *   Description:
 *     selects counters per-hour 
 *   Prepared Statement:
 *     SELECT requests, ok_responses, bad_request_responses, 
 *     internal_error_responses, run_time_milli_seconds FROM 
 *     ig_logging.daily_requests_counters WHERE 
 *     year_month_day_controller = :year_month_day_controller; 
 * */
public class DailyRequestsCounters extends Table {

  private static final String kKeySpaceName =
    "ig_logging";
  private static final String kTableName =
    "daily_requests_counters";

  public static final String kYearMonthDayControllerColumnName =
    "year_month_day_controller";
  public static final String kRequestsColumnName =
    "requests";
  public static final String kOkResponsesColumnName =
    "ok_responses";
  public static final String kBadRequestResponsesColumnName =
    "bad_request_responses";
  public static final String kInternalErrorResponsesColumnName =
    "internal_error_responses";
  public static final String kRunTimeMilliSecondsColumnName =
    "run_time_milli_seconds";

  /**
   * Query:
   * Name: increment_ok_responses
   * Description:
   *   increments requests with OK responses 
   * Prepared Statement:
   *   UPDATE ig_logging.daily_requests_counters SET requests = requests + 
   *   1, ok_responses = ok_responses + 1, run_time_milli_seconds = 
   *   run_time_milli_seconds + :run_time_milli_seconds WHERE 
   *   year_month_day_controller = :year_month_day_controller; 
   */
  private static final String kIncrementOkResponsesName =
    "increment_ok_responses";
  private static final String kIncrementOkResponsesDescription =
    "increments requests with OK responses ";
  private static final String kIncrementOkResponsesPreparedStatement =
    "UPDATE ig_logging.daily_requests_counters SET requests = requests + 1, "
    + "ok_responses = ok_responses + 1, run_time_milli_seconds = "
    + "run_time_milli_seconds + :run_time_milli_seconds WHERE "
    + "year_month_day_controller = :year_month_day_controller; ";

  /**
   * Query:
   * Name: increment_bad_request_responses
   * Description:
   *   increments requests with BAD_REQUEST responses 
   * Prepared Statement:
   *   UPDATE ig_logging.daily_requests_counters SET requests = requests + 
   *   1, bad_request_responses = bad_request_responses + 1, 
   *   run_time_milli_seconds = run_time_milli_seconds + 
   *   :run_time_milli_seconds WHERE year_month_day_controller = 
   *   :year_month_day_controller; 
   */
  private static final String kIncrementBadRequestResponsesName =
    "increment_bad_request_responses";
  private static final String kIncrementBadRequestResponsesDescription =
    "increments requests with BAD_REQUEST responses ";
  private static final String kIncrementBadRequestResponsesPreparedStatement =
    "UPDATE ig_logging.daily_requests_counters SET requests = requests + 1, "
    + "bad_request_responses = bad_request_responses + 1, "
    + "run_time_milli_seconds = run_time_milli_seconds + "
    + ":run_time_milli_seconds WHERE year_month_day_controller = "
    + ":year_month_day_controller; ";

  /**
   * Query:
   * Name: increment_internal_error_responses
   * Description:
   *   increments requests with INTERNAL_ERROR responses 
   * Prepared Statement:
   *   UPDATE ig_logging.daily_requests_counters SET requests = requests + 
   *   1, internal_error_responses = internal_error_responses + 1, 
   *   run_time_milli_seconds = run_time_milli_seconds + 
   *   :run_time_milli_seconds WHERE year_month_day_controller = 
   *   :year_month_day_controller; 
   */
  private static final String kIncrementInternalErrorResponsesName =
    "increment_internal_error_responses";
  private static final String kIncrementInternalErrorResponsesDescription =
    "increments requests with INTERNAL_ERROR responses ";
  private static final String kIncrementInternalErrorResponsesPreparedStatement =
    "UPDATE ig_logging.daily_requests_counters SET requests = requests + 1, "
    + "internal_error_responses = internal_error_responses + 1, "
    + "run_time_milli_seconds = run_time_milli_seconds + "
    + ":run_time_milli_seconds WHERE year_month_day_controller = "
    + ":year_month_day_controller; ";

  /**
   * Query:
   * Name: select
   * Description:
   *   selects counters per-hour 
   * Prepared Statement:
   *   SELECT requests, ok_responses, bad_request_responses, 
   *   internal_error_responses, run_time_milli_seconds FROM 
   *   ig_logging.daily_requests_counters WHERE 
   *   year_month_day_controller = :year_month_day_controller; 
   */
  private static final String kSelectName =
    "select";
  private static final String kSelectDescription =
    "selects counters per-hour ";
  private static final String kSelectPreparedStatement =
    "SELECT requests, ok_responses, bad_request_responses, "
    + "internal_error_responses, run_time_milli_seconds FROM "
    + "ig_logging.daily_requests_counters WHERE "
    + "year_month_day_controller = :year_month_day_controller; ";

  /**
   * Constructor DailyRequestsCounters
   * @return new DailyRequestsCounters Object
   * @throws Exception
   */
  private DailyRequestsCounters () throws Exception {

    super (
      kKeySpaceName,
      kTableName,
      new Query (
        kIncrementOkResponsesDescription,
        kIncrementOkResponsesName,
        kIncrementOkResponsesPreparedStatement),
      new Query (
        kIncrementBadRequestResponsesDescription,
        kIncrementBadRequestResponsesName,
        kIncrementBadRequestResponsesPreparedStatement),
      new Query (
        kIncrementInternalErrorResponsesDescription,
        kIncrementInternalErrorResponsesName,
        kIncrementInternalErrorResponsesPreparedStatement),
      new Query (
        kSelectDescription,
        kSelectName,
        kSelectPreparedStatement));
  }

  private static DailyRequestsCounters instance = null;

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

      instance = new DailyRequestsCounters();
    }
  }

  /**
   * i
   * @return singleton static instance of DailyRequestsCounters
   * @throws Exception
   */
  public static DailyRequestsCounters i () throws Exception {

    if (instance == null) {

      instance = new DailyRequestsCounters();
    }

    return instance;
  }

  // Query: IncrementOkResponses
  // Description:
  //   increments requests with OK responses 
  // Parepared Statement:
  //   UPDATE ig_logging.daily_requests_counters SET requests = requests + 
  //   1, ok_responses = ok_responses + 1, run_time_milli_seconds = 
  //   run_time_milli_seconds + :run_time_milli_seconds WHERE 
  //   year_month_day_controller = :year_month_day_controller; 

  /**
   * getQueryIncrementOkResponses
   * @return IncrementOkResponses Query in the form of
   *           a Query Object
   * @throws Exception
   */
  public Query getQueryIncrementOkResponses (
    ) throws Exception {

    return this.getQuery(kIncrementOkResponsesName);
  }

  /**
   * getQueryDispatchableIncrementOkResponses
   * @param runtimemilliseconds
   * @param yearmonthdaycontroller
   * @return IncrementOkResponses Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableIncrementOkResponses (
    Object runtimemilliseconds,
    Object yearmonthdaycontroller) throws Exception {

    return
      this.getQueryDispatchable(
        kIncrementOkResponsesName,
        runtimemilliseconds,
        yearmonthdaycontroller);
  }

  /**
   * getBoundStatementIncrementOkResponses
   * @param runtimemilliseconds
   * @param yearmonthdaycontroller
   * @return IncrementOkResponses Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementIncrementOkResponses (
    Object runtimemilliseconds,
    Object yearmonthdaycontroller) throws Exception {

    return
      this.getQuery(kIncrementOkResponsesName).getBoundStatement(
        runtimemilliseconds,
        yearmonthdaycontroller);
  }

  /**
   * executeAsyncIncrementOkResponses
   * executes IncrementOkResponses Query asynchronously
   * @param runtimemilliseconds
   * @param yearmonthdaycontroller
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncIncrementOkResponses (
    Object runtimemilliseconds,
    Object yearmonthdaycontroller) throws Exception {

    return
      this.getQuery(kIncrementOkResponsesName).executeAsync(
        runtimemilliseconds,
        yearmonthdaycontroller);
  }

  /**
   * executeSyncIncrementOkResponses
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes IncrementOkResponses Query synchronously
   * @param runtimemilliseconds
   * @param yearmonthdaycontroller
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncIncrementOkResponses (
    Object runtimemilliseconds,
    Object yearmonthdaycontroller) throws Exception {

    return
      this.getQuery(kIncrementOkResponsesName).executeSync(
        runtimemilliseconds,
        yearmonthdaycontroller);
  }

  // Query: IncrementBadRequestResponses
  // Description:
  //   increments requests with BAD_REQUEST responses 
  // Parepared Statement:
  //   UPDATE ig_logging.daily_requests_counters SET requests = requests + 
  //   1, bad_request_responses = bad_request_responses + 1, 
  //   run_time_milli_seconds = run_time_milli_seconds + 
  //   :run_time_milli_seconds WHERE year_month_day_controller = 
  //   :year_month_day_controller; 

  /**
   * getQueryIncrementBadRequestResponses
   * @return IncrementBadRequestResponses Query in the form of
   *           a Query Object
   * @throws Exception
   */
  public Query getQueryIncrementBadRequestResponses (
    ) throws Exception {

    return this.getQuery(kIncrementBadRequestResponsesName);
  }

  /**
   * getQueryDispatchableIncrementBadRequestResponses
   * @param runtimemilliseconds
   * @param yearmonthdaycontroller
   * @return IncrementBadRequestResponses Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableIncrementBadRequestResponses (
    Object runtimemilliseconds,
    Object yearmonthdaycontroller) throws Exception {

    return
      this.getQueryDispatchable(
        kIncrementBadRequestResponsesName,
        runtimemilliseconds,
        yearmonthdaycontroller);
  }

  /**
   * getBoundStatementIncrementBadRequestResponses
   * @param runtimemilliseconds
   * @param yearmonthdaycontroller
   * @return IncrementBadRequestResponses Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementIncrementBadRequestResponses (
    Object runtimemilliseconds,
    Object yearmonthdaycontroller) throws Exception {

    return
      this.getQuery(kIncrementBadRequestResponsesName).getBoundStatement(
        runtimemilliseconds,
        yearmonthdaycontroller);
  }

  /**
   * executeAsyncIncrementBadRequestResponses
   * executes IncrementBadRequestResponses Query asynchronously
   * @param runtimemilliseconds
   * @param yearmonthdaycontroller
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncIncrementBadRequestResponses (
    Object runtimemilliseconds,
    Object yearmonthdaycontroller) throws Exception {

    return
      this.getQuery(kIncrementBadRequestResponsesName).executeAsync(
        runtimemilliseconds,
        yearmonthdaycontroller);
  }

  /**
   * executeSyncIncrementBadRequestResponses
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes IncrementBadRequestResponses Query synchronously
   * @param runtimemilliseconds
   * @param yearmonthdaycontroller
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncIncrementBadRequestResponses (
    Object runtimemilliseconds,
    Object yearmonthdaycontroller) throws Exception {

    return
      this.getQuery(kIncrementBadRequestResponsesName).executeSync(
        runtimemilliseconds,
        yearmonthdaycontroller);
  }

  // Query: IncrementInternalErrorResponses
  // Description:
  //   increments requests with INTERNAL_ERROR responses 
  // Parepared Statement:
  //   UPDATE ig_logging.daily_requests_counters SET requests = requests + 
  //   1, internal_error_responses = internal_error_responses + 1, 
  //   run_time_milli_seconds = run_time_milli_seconds + 
  //   :run_time_milli_seconds WHERE year_month_day_controller = 
  //   :year_month_day_controller; 

  /**
   * getQueryIncrementInternalErrorResponses
   * @return IncrementInternalErrorResponses Query in the form of
   *           a Query Object
   * @throws Exception
   */
  public Query getQueryIncrementInternalErrorResponses (
    ) throws Exception {

    return this.getQuery(kIncrementInternalErrorResponsesName);
  }

  /**
   * getQueryDispatchableIncrementInternalErrorResponses
   * @param runtimemilliseconds
   * @param yearmonthdaycontroller
   * @return IncrementInternalErrorResponses Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableIncrementInternalErrorResponses (
    Object runtimemilliseconds,
    Object yearmonthdaycontroller) throws Exception {

    return
      this.getQueryDispatchable(
        kIncrementInternalErrorResponsesName,
        runtimemilliseconds,
        yearmonthdaycontroller);
  }

  /**
   * getBoundStatementIncrementInternalErrorResponses
   * @param runtimemilliseconds
   * @param yearmonthdaycontroller
   * @return IncrementInternalErrorResponses Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementIncrementInternalErrorResponses (
    Object runtimemilliseconds,
    Object yearmonthdaycontroller) throws Exception {

    return
      this.getQuery(kIncrementInternalErrorResponsesName).getBoundStatement(
        runtimemilliseconds,
        yearmonthdaycontroller);
  }

  /**
   * executeAsyncIncrementInternalErrorResponses
   * executes IncrementInternalErrorResponses Query asynchronously
   * @param runtimemilliseconds
   * @param yearmonthdaycontroller
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncIncrementInternalErrorResponses (
    Object runtimemilliseconds,
    Object yearmonthdaycontroller) throws Exception {

    return
      this.getQuery(kIncrementInternalErrorResponsesName).executeAsync(
        runtimemilliseconds,
        yearmonthdaycontroller);
  }

  /**
   * executeSyncIncrementInternalErrorResponses
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes IncrementInternalErrorResponses Query synchronously
   * @param runtimemilliseconds
   * @param yearmonthdaycontroller
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncIncrementInternalErrorResponses (
    Object runtimemilliseconds,
    Object yearmonthdaycontroller) throws Exception {

    return
      this.getQuery(kIncrementInternalErrorResponsesName).executeSync(
        runtimemilliseconds,
        yearmonthdaycontroller);
  }

  // Query: Select
  // Description:
  //   selects counters per-hour 
  // Parepared Statement:
  //   SELECT requests, ok_responses, bad_request_responses, 
  //   internal_error_responses, run_time_milli_seconds FROM 
  //   ig_logging.daily_requests_counters WHERE 
  //   year_month_day_controller = :year_month_day_controller; 

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
   * @param yearmonthdaycontroller
   * @return Select Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableSelect (
    Object yearmonthdaycontroller) throws Exception {

    return
      this.getQueryDispatchable(
        kSelectName,
        yearmonthdaycontroller);
  }

  /**
   * getBoundStatementSelect
   * @param yearmonthdaycontroller
   * @return Select Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementSelect (
    Object yearmonthdaycontroller) throws Exception {

    return
      this.getQuery(kSelectName).getBoundStatement(
        yearmonthdaycontroller);
  }

  /**
   * executeAsyncSelect
   * executes Select Query asynchronously
   * @param yearmonthdaycontroller
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncSelect (
    Object yearmonthdaycontroller) throws Exception {

    return
      this.getQuery(kSelectName).executeAsync(
        yearmonthdaycontroller);
  }

  /**
   * executeSyncSelect
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes Select Query synchronously
   * @param yearmonthdaycontroller
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncSelect (
    Object yearmonthdaycontroller) throws Exception {

    return
      this.getQuery(kSelectName).executeSync(
        yearmonthdaycontroller);
  }

}
