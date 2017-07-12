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

package com.vangav.vos_instagram.cassandra_keyspaces.ig_jobs;

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
 * HourlyCurrentJobs represents
 *   Table [hourly_current_jobs]
 *   in Keyspace [ig_jobs]
 * 
 * Name: hourly_current_jobs
 * Description:
 *   indexes jobs per-hour and sorts them by the oldest 
 * 
 * Columns:
 *   year_month_day_hour : varchar
 *   job_time : bigint
 *   job_id : uuid

 * Partition Keys: year_month_day_hour, job_time, job_id
 * Secondary Keys: 
 * Caching: ALL
 * Order By:
 *   job_time : ASC

 * Queries:
 *   - Name: insert
 *   Description:
 *     inserts a new job 
 *   Prepared Statement:
 *     INSERT INTO ig_jobs.hourly_current_jobs (year_month_day_hour, 
 *     job_time, job_id) VALUES (:year_month_day_hour, :job_time, 
 *     :job_id); 
 *   - Name: delete
 *   Description:
 *     deletes a job 
 *   Prepared Statement:
 *     DELETE FROM ig_jobs.hourly_current_jobs WHERE year_month_day_hour = 
 *     :year_month_day_hour AND job_time = :job_time AND job_id = 
 *     :job_id; 
 *   - Name: select_earliest_limit
 *   Description:
 *     selects earliest jobs during an hour with a limit 
 *   Prepared Statement:
 *     SELECT job_time, job_id FROM ig_jobs.hourly_current_jobs WHERE 
 *     year_month_day_hour = :year_month_day_hour LIMIT 30; 
 *   - Name: select_equal
 *   Description:
 *     selects jobs at a specific time 
 *   Prepared Statement:
 *     SELECT job_time, job_id FROM ig_jobs.hourly_current_jobs WHERE 
 *     year_month_day_hour = :year_month_day_hour AND job_time = 
 *     :job_time; 
 *   - Name: select_at_or_after_limit
 *   Description:
 *     selects jobs at or after a time with a limit 
 *   Prepared Statement:
 *     SELECT job_time, job_id FROM ig_jobs.hourly_current_jobs WHERE 
 *     year_month_day_hour = :year_month_day_hour AND job_time >= 
 *     :job_time LIMIT 30; 
 *   - Name: select_after_limit
 *   Description:
 *     selects jobs after a time with a limit 
 *   Prepared Statement:
 *     SELECT job_time, job_id FROM ig_jobs.hourly_current_jobs WHERE 
 *     year_month_day_hour = :year_month_day_hour AND job_time > 
 *     :job_time LIMIT 30; 
 *   - Name: select
 *   Description:
 *     selects all jobs jobs within an hour, consider using paging when 
 *     using this query 
 *   Prepared Statement:
 *     SELECT job_time, job_id FROM ig_jobs.hourly_current_jobs WHERE 
 *     year_month_day_hour = :year_month_day_hour; 
 * */
public class HourlyCurrentJobs extends Table {

  private static final String kKeySpaceName =
    "ig_jobs";
  private static final String kTableName =
    "hourly_current_jobs";

  public static final String kYearMonthDayHourColumnName =
    "year_month_day_hour";
  public static final String kJobTimeColumnName =
    "job_time";
  public static final String kJobIdColumnName =
    "job_id";

  /**
   * Query:
   * Name: insert
   * Description:
   *   inserts a new job 
   * Prepared Statement:
   *   INSERT INTO ig_jobs.hourly_current_jobs (year_month_day_hour, 
   *   job_time, job_id) VALUES (:year_month_day_hour, :job_time, 
   *   :job_id); 
   */
  private static final String kInsertName =
    "insert";
  private static final String kInsertDescription =
    "inserts a new job ";
  private static final String kInsertPreparedStatement =
    "INSERT INTO ig_jobs.hourly_current_jobs (year_month_day_hour, "
    + "job_time, job_id) VALUES (:year_month_day_hour, :job_time, "
    + ":job_id); ";

  /**
   * Query:
   * Name: delete
   * Description:
   *   deletes a job 
   * Prepared Statement:
   *   DELETE FROM ig_jobs.hourly_current_jobs WHERE year_month_day_hour = 
   *   :year_month_day_hour AND job_time = :job_time AND job_id = 
   *   :job_id; 
   */
  private static final String kDeleteName =
    "delete";
  private static final String kDeleteDescription =
    "deletes a job ";
  private static final String kDeletePreparedStatement =
    "DELETE FROM ig_jobs.hourly_current_jobs WHERE year_month_day_hour = "
    + ":year_month_day_hour AND job_time = :job_time AND job_id = "
    + ":job_id; ";

  /**
   * Query:
   * Name: select_earliest_limit
   * Description:
   *   selects earliest jobs during an hour with a limit 
   * Prepared Statement:
   *   SELECT job_time, job_id FROM ig_jobs.hourly_current_jobs WHERE 
   *   year_month_day_hour = :year_month_day_hour LIMIT 30; 
   */
  private static final String kSelectEarliestLimitName =
    "select_earliest_limit";
  private static final String kSelectEarliestLimitDescription =
    "selects earliest jobs during an hour with a limit ";
  private static final String kSelectEarliestLimitPreparedStatement =
    "SELECT job_time, job_id FROM ig_jobs.hourly_current_jobs WHERE "
    + "year_month_day_hour = :year_month_day_hour LIMIT 30; ";

  /**
   * Query:
   * Name: select_equal
   * Description:
   *   selects jobs at a specific time 
   * Prepared Statement:
   *   SELECT job_time, job_id FROM ig_jobs.hourly_current_jobs WHERE 
   *   year_month_day_hour = :year_month_day_hour AND job_time = 
   *   :job_time; 
   */
  private static final String kSelectEqualName =
    "select_equal";
  private static final String kSelectEqualDescription =
    "selects jobs at a specific time ";
  private static final String kSelectEqualPreparedStatement =
    "SELECT job_time, job_id FROM ig_jobs.hourly_current_jobs WHERE "
    + "year_month_day_hour = :year_month_day_hour AND job_time = "
    + ":job_time; ";

  /**
   * Query:
   * Name: select_at_or_after_limit
   * Description:
   *   selects jobs at or after a time with a limit 
   * Prepared Statement:
   *   SELECT job_time, job_id FROM ig_jobs.hourly_current_jobs WHERE 
   *   year_month_day_hour = :year_month_day_hour AND job_time >= 
   *   :job_time LIMIT 30; 
   */
  private static final String kSelectAtOrAfterLimitName =
    "select_at_or_after_limit";
  private static final String kSelectAtOrAfterLimitDescription =
    "selects jobs at or after a time with a limit ";
  private static final String kSelectAtOrAfterLimitPreparedStatement =
    "SELECT job_time, job_id FROM ig_jobs.hourly_current_jobs WHERE "
    + "year_month_day_hour = :year_month_day_hour AND job_time >= "
    + ":job_time LIMIT 30; ";

  /**
   * Query:
   * Name: select_after_limit
   * Description:
   *   selects jobs after a time with a limit 
   * Prepared Statement:
   *   SELECT job_time, job_id FROM ig_jobs.hourly_current_jobs WHERE 
   *   year_month_day_hour = :year_month_day_hour AND job_time > 
   *   :job_time LIMIT 30; 
   */
  private static final String kSelectAfterLimitName =
    "select_after_limit";
  private static final String kSelectAfterLimitDescription =
    "selects jobs after a time with a limit ";
  private static final String kSelectAfterLimitPreparedStatement =
    "SELECT job_time, job_id FROM ig_jobs.hourly_current_jobs WHERE "
    + "year_month_day_hour = :year_month_day_hour AND job_time > "
    + ":job_time LIMIT 30; ";

  /**
   * Query:
   * Name: select
   * Description:
   *   selects all jobs jobs within an hour, consider using paging when 
   *   using this query 
   * Prepared Statement:
   *   SELECT job_time, job_id FROM ig_jobs.hourly_current_jobs WHERE 
   *   year_month_day_hour = :year_month_day_hour; 
   */
  private static final String kSelectName =
    "select";
  private static final String kSelectDescription =
    "selects all jobs jobs within an hour, consider using paging when using "
    + "this query ";
  private static final String kSelectPreparedStatement =
    "SELECT job_time, job_id FROM ig_jobs.hourly_current_jobs WHERE "
    + "year_month_day_hour = :year_month_day_hour; ";

  /**
   * Constructor HourlyCurrentJobs
   * @return new HourlyCurrentJobs Object
   * @throws Exception
   */
  private HourlyCurrentJobs () throws Exception {

    super (
      kKeySpaceName,
      kTableName,
      new Query (
        kInsertDescription,
        kInsertName,
        kInsertPreparedStatement),
      new Query (
        kDeleteDescription,
        kDeleteName,
        kDeletePreparedStatement),
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

  private static HourlyCurrentJobs instance = null;

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

      instance = new HourlyCurrentJobs();
    }
  }

  /**
   * i
   * @return singleton static instance of HourlyCurrentJobs
   * @throws Exception
   */
  public static HourlyCurrentJobs i () throws Exception {

    if (instance == null) {

      instance = new HourlyCurrentJobs();
    }

    return instance;
  }

  // Query: Insert
  // Description:
  //   inserts a new job 
  // Parepared Statement:
  //   INSERT INTO ig_jobs.hourly_current_jobs (year_month_day_hour, 
  //   job_time, job_id) VALUES (:year_month_day_hour, :job_time, 
  //   :job_id); 

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
   * @param yearmonthdayhour
   * @param jobtime
   * @param jobid
   * @return Insert Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableInsert (
    Object yearmonthdayhour,
    Object jobtime,
    Object jobid) throws Exception {

    return
      this.getQueryDispatchable(
        kInsertName,
        yearmonthdayhour,
        jobtime,
        jobid);
  }

  /**
   * getBoundStatementInsert
   * @param yearmonthdayhour
   * @param jobtime
   * @param jobid
   * @return Insert Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementInsert (
    Object yearmonthdayhour,
    Object jobtime,
    Object jobid) throws Exception {

    return
      this.getQuery(kInsertName).getBoundStatement(
        yearmonthdayhour,
        jobtime,
        jobid);
  }

  /**
   * executeAsyncInsert
   * executes Insert Query asynchronously
   * @param yearmonthdayhour
   * @param jobtime
   * @param jobid
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncInsert (
    Object yearmonthdayhour,
    Object jobtime,
    Object jobid) throws Exception {

    return
      this.getQuery(kInsertName).executeAsync(
        yearmonthdayhour,
        jobtime,
        jobid);
  }

  /**
   * executeSyncInsert
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes Insert Query synchronously
   * @param yearmonthdayhour
   * @param jobtime
   * @param jobid
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncInsert (
    Object yearmonthdayhour,
    Object jobtime,
    Object jobid) throws Exception {

    return
      this.getQuery(kInsertName).executeSync(
        yearmonthdayhour,
        jobtime,
        jobid);
  }

  // Query: Delete
  // Description:
  //   deletes a job 
  // Parepared Statement:
  //   DELETE FROM ig_jobs.hourly_current_jobs WHERE year_month_day_hour = 
  //   :year_month_day_hour AND job_time = :job_time AND job_id = 
  //   :job_id; 

  /**
   * getQueryDelete
   * @return Delete Query in the form of
   *           a Query Object
   * @throws Exception
   */
  public Query getQueryDelete (
    ) throws Exception {

    return this.getQuery(kDeleteName);
  }

  /**
   * getQueryDispatchableDelete
   * @param yearmonthdayhour
   * @param jobtime
   * @param jobid
   * @return Delete Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableDelete (
    Object yearmonthdayhour,
    Object jobtime,
    Object jobid) throws Exception {

    return
      this.getQueryDispatchable(
        kDeleteName,
        yearmonthdayhour,
        jobtime,
        jobid);
  }

  /**
   * getBoundStatementDelete
   * @param yearmonthdayhour
   * @param jobtime
   * @param jobid
   * @return Delete Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementDelete (
    Object yearmonthdayhour,
    Object jobtime,
    Object jobid) throws Exception {

    return
      this.getQuery(kDeleteName).getBoundStatement(
        yearmonthdayhour,
        jobtime,
        jobid);
  }

  /**
   * executeAsyncDelete
   * executes Delete Query asynchronously
   * @param yearmonthdayhour
   * @param jobtime
   * @param jobid
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncDelete (
    Object yearmonthdayhour,
    Object jobtime,
    Object jobid) throws Exception {

    return
      this.getQuery(kDeleteName).executeAsync(
        yearmonthdayhour,
        jobtime,
        jobid);
  }

  /**
   * executeSyncDelete
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes Delete Query synchronously
   * @param yearmonthdayhour
   * @param jobtime
   * @param jobid
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncDelete (
    Object yearmonthdayhour,
    Object jobtime,
    Object jobid) throws Exception {

    return
      this.getQuery(kDeleteName).executeSync(
        yearmonthdayhour,
        jobtime,
        jobid);
  }

  // Query: SelectEarliestLimit
  // Description:
  //   selects earliest jobs during an hour with a limit 
  // Parepared Statement:
  //   SELECT job_time, job_id FROM ig_jobs.hourly_current_jobs WHERE 
  //   year_month_day_hour = :year_month_day_hour LIMIT 30; 

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
   * @param yearmonthdayhour
   * @return SelectEarliestLimit Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableSelectEarliestLimit (
    Object yearmonthdayhour) throws Exception {

    return
      this.getQueryDispatchable(
        kSelectEarliestLimitName,
        yearmonthdayhour);
  }

  /**
   * getBoundStatementSelectEarliestLimit
   * @param yearmonthdayhour
   * @return SelectEarliestLimit Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementSelectEarliestLimit (
    Object yearmonthdayhour) throws Exception {

    return
      this.getQuery(kSelectEarliestLimitName).getBoundStatement(
        yearmonthdayhour);
  }

  /**
   * executeAsyncSelectEarliestLimit
   * executes SelectEarliestLimit Query asynchronously
   * @param yearmonthdayhour
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncSelectEarliestLimit (
    Object yearmonthdayhour) throws Exception {

    return
      this.getQuery(kSelectEarliestLimitName).executeAsync(
        yearmonthdayhour);
  }

  /**
   * executeSyncSelectEarliestLimit
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes SelectEarliestLimit Query synchronously
   * @param yearmonthdayhour
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncSelectEarliestLimit (
    Object yearmonthdayhour) throws Exception {

    return
      this.getQuery(kSelectEarliestLimitName).executeSync(
        yearmonthdayhour);
  }

  // Query: SelectEqual
  // Description:
  //   selects jobs at a specific time 
  // Parepared Statement:
  //   SELECT job_time, job_id FROM ig_jobs.hourly_current_jobs WHERE 
  //   year_month_day_hour = :year_month_day_hour AND job_time = 
  //   :job_time; 

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
   * @param yearmonthdayhour
   * @param jobtime
   * @return SelectEqual Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableSelectEqual (
    Object yearmonthdayhour,
    Object jobtime) throws Exception {

    return
      this.getQueryDispatchable(
        kSelectEqualName,
        yearmonthdayhour,
        jobtime);
  }

  /**
   * getBoundStatementSelectEqual
   * @param yearmonthdayhour
   * @param jobtime
   * @return SelectEqual Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementSelectEqual (
    Object yearmonthdayhour,
    Object jobtime) throws Exception {

    return
      this.getQuery(kSelectEqualName).getBoundStatement(
        yearmonthdayhour,
        jobtime);
  }

  /**
   * executeAsyncSelectEqual
   * executes SelectEqual Query asynchronously
   * @param yearmonthdayhour
   * @param jobtime
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncSelectEqual (
    Object yearmonthdayhour,
    Object jobtime) throws Exception {

    return
      this.getQuery(kSelectEqualName).executeAsync(
        yearmonthdayhour,
        jobtime);
  }

  /**
   * executeSyncSelectEqual
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes SelectEqual Query synchronously
   * @param yearmonthdayhour
   * @param jobtime
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncSelectEqual (
    Object yearmonthdayhour,
    Object jobtime) throws Exception {

    return
      this.getQuery(kSelectEqualName).executeSync(
        yearmonthdayhour,
        jobtime);
  }

  // Query: SelectAtOrAfterLimit
  // Description:
  //   selects jobs at or after a time with a limit 
  // Parepared Statement:
  //   SELECT job_time, job_id FROM ig_jobs.hourly_current_jobs WHERE 
  //   year_month_day_hour = :year_month_day_hour AND job_time >= 
  //   :job_time LIMIT 30; 

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
   * @param yearmonthdayhour
   * @param jobtime
   * @return SelectAtOrAfterLimit Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableSelectAtOrAfterLimit (
    Object yearmonthdayhour,
    Object jobtime) throws Exception {

    return
      this.getQueryDispatchable(
        kSelectAtOrAfterLimitName,
        yearmonthdayhour,
        jobtime);
  }

  /**
   * getBoundStatementSelectAtOrAfterLimit
   * @param yearmonthdayhour
   * @param jobtime
   * @return SelectAtOrAfterLimit Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementSelectAtOrAfterLimit (
    Object yearmonthdayhour,
    Object jobtime) throws Exception {

    return
      this.getQuery(kSelectAtOrAfterLimitName).getBoundStatement(
        yearmonthdayhour,
        jobtime);
  }

  /**
   * executeAsyncSelectAtOrAfterLimit
   * executes SelectAtOrAfterLimit Query asynchronously
   * @param yearmonthdayhour
   * @param jobtime
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncSelectAtOrAfterLimit (
    Object yearmonthdayhour,
    Object jobtime) throws Exception {

    return
      this.getQuery(kSelectAtOrAfterLimitName).executeAsync(
        yearmonthdayhour,
        jobtime);
  }

  /**
   * executeSyncSelectAtOrAfterLimit
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes SelectAtOrAfterLimit Query synchronously
   * @param yearmonthdayhour
   * @param jobtime
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncSelectAtOrAfterLimit (
    Object yearmonthdayhour,
    Object jobtime) throws Exception {

    return
      this.getQuery(kSelectAtOrAfterLimitName).executeSync(
        yearmonthdayhour,
        jobtime);
  }

  // Query: SelectAfterLimit
  // Description:
  //   selects jobs after a time with a limit 
  // Parepared Statement:
  //   SELECT job_time, job_id FROM ig_jobs.hourly_current_jobs WHERE 
  //   year_month_day_hour = :year_month_day_hour AND job_time > 
  //   :job_time LIMIT 30; 

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
   * @param yearmonthdayhour
   * @param jobtime
   * @return SelectAfterLimit Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableSelectAfterLimit (
    Object yearmonthdayhour,
    Object jobtime) throws Exception {

    return
      this.getQueryDispatchable(
        kSelectAfterLimitName,
        yearmonthdayhour,
        jobtime);
  }

  /**
   * getBoundStatementSelectAfterLimit
   * @param yearmonthdayhour
   * @param jobtime
   * @return SelectAfterLimit Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementSelectAfterLimit (
    Object yearmonthdayhour,
    Object jobtime) throws Exception {

    return
      this.getQuery(kSelectAfterLimitName).getBoundStatement(
        yearmonthdayhour,
        jobtime);
  }

  /**
   * executeAsyncSelectAfterLimit
   * executes SelectAfterLimit Query asynchronously
   * @param yearmonthdayhour
   * @param jobtime
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncSelectAfterLimit (
    Object yearmonthdayhour,
    Object jobtime) throws Exception {

    return
      this.getQuery(kSelectAfterLimitName).executeAsync(
        yearmonthdayhour,
        jobtime);
  }

  /**
   * executeSyncSelectAfterLimit
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes SelectAfterLimit Query synchronously
   * @param yearmonthdayhour
   * @param jobtime
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncSelectAfterLimit (
    Object yearmonthdayhour,
    Object jobtime) throws Exception {

    return
      this.getQuery(kSelectAfterLimitName).executeSync(
        yearmonthdayhour,
        jobtime);
  }

  // Query: Select
  // Description:
  //   selects all jobs jobs within an hour, consider using paging when 
  //   using this query 
  // Parepared Statement:
  //   SELECT job_time, job_id FROM ig_jobs.hourly_current_jobs WHERE 
  //   year_month_day_hour = :year_month_day_hour; 

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
   * @param yearmonthdayhour
   * @return Select Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableSelect (
    Object yearmonthdayhour) throws Exception {

    return
      this.getQueryDispatchable(
        kSelectName,
        yearmonthdayhour);
  }

  /**
   * getBoundStatementSelect
   * @param yearmonthdayhour
   * @return Select Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementSelect (
    Object yearmonthdayhour) throws Exception {

    return
      this.getQuery(kSelectName).getBoundStatement(
        yearmonthdayhour);
  }

  /**
   * executeAsyncSelect
   * executes Select Query asynchronously
   * @param yearmonthdayhour
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncSelect (
    Object yearmonthdayhour) throws Exception {

    return
      this.getQuery(kSelectName).executeAsync(
        yearmonthdayhour);
  }

  /**
   * executeSyncSelect
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes Select Query synchronously
   * @param yearmonthdayhour
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncSelect (
    Object yearmonthdayhour) throws Exception {

    return
      this.getQuery(kSelectName).executeSync(
        yearmonthdayhour);
  }

}
