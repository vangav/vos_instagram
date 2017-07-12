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
 * CurrentJobs represents
 *   Table [current_jobs]
 *   in Keyspace [ig_jobs]
 * 
 * Name: current_jobs
 * Description:
 *   stores jobs to be issued by one service and executed asynchronously 
 *   by another service -- this table is useful to trace jobs that 
 *   didn't get succefully executed and retry executing them at a 
 *   later point in time 
 * 
 * Columns:
 *   job_id : uuid
 *   job_time : bigint
 *   job : blob

 * Partition Keys: job_id
 * Secondary Keys: 
 * Caching: ALL
 * Order By:

 * Queries:
 *   - Name: insert
 *   Description:
 *     inserts a new job 
 *   Prepared Statement:
 *     INSERT INTO ig_jobs.current_jobs (job_id, job_time, job) VALUES 
 *     (:job_id, :job_time, :job); 
 *   - Name: delete
 *   Description:
 *     deletes a job 
 *   Prepared Statement:
 *     DELETE FROM ig_jobs.current_jobs WHERE job_id = :job_id; 
 *   - Name: select
 *   Description:
 *     selects a job 
 *   Prepared Statement:
 *     SELECT job_time, job FROM ig_jobs.current_jobs WHERE job_id = 
 *     :job_id; 
 * */
public class CurrentJobs extends Table {

  private static final String kKeySpaceName =
    "ig_jobs";
  private static final String kTableName =
    "current_jobs";

  public static final String kJobIdColumnName =
    "job_id";
  public static final String kJobTimeColumnName =
    "job_time";
  public static final String kJobColumnName =
    "job";

  /**
   * Query:
   * Name: insert
   * Description:
   *   inserts a new job 
   * Prepared Statement:
   *   INSERT INTO ig_jobs.current_jobs (job_id, job_time, job) VALUES 
   *   (:job_id, :job_time, :job); 
   */
  private static final String kInsertName =
    "insert";
  private static final String kInsertDescription =
    "inserts a new job ";
  private static final String kInsertPreparedStatement =
    "INSERT INTO ig_jobs.current_jobs (job_id, job_time, job) VALUES "
    + "(:job_id, :job_time, :job); ";

  /**
   * Query:
   * Name: delete
   * Description:
   *   deletes a job 
   * Prepared Statement:
   *   DELETE FROM ig_jobs.current_jobs WHERE job_id = :job_id; 
   */
  private static final String kDeleteName =
    "delete";
  private static final String kDeleteDescription =
    "deletes a job ";
  private static final String kDeletePreparedStatement =
    "DELETE FROM ig_jobs.current_jobs WHERE job_id = :job_id; ";

  /**
   * Query:
   * Name: select
   * Description:
   *   selects a job 
   * Prepared Statement:
   *   SELECT job_time, job FROM ig_jobs.current_jobs WHERE job_id = 
   *   :job_id; 
   */
  private static final String kSelectName =
    "select";
  private static final String kSelectDescription =
    "selects a job ";
  private static final String kSelectPreparedStatement =
    "SELECT job_time, job FROM ig_jobs.current_jobs WHERE job_id = :job_id; ";

  /**
   * Constructor CurrentJobs
   * @return new CurrentJobs Object
   * @throws Exception
   */
  private CurrentJobs () throws Exception {

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
        kSelectDescription,
        kSelectName,
        kSelectPreparedStatement));
  }

  private static CurrentJobs instance = null;

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

      instance = new CurrentJobs();
    }
  }

  /**
   * i
   * @return singleton static instance of CurrentJobs
   * @throws Exception
   */
  public static CurrentJobs i () throws Exception {

    if (instance == null) {

      instance = new CurrentJobs();
    }

    return instance;
  }

  // Query: Insert
  // Description:
  //   inserts a new job 
  // Parepared Statement:
  //   INSERT INTO ig_jobs.current_jobs (job_id, job_time, job) VALUES 
  //   (:job_id, :job_time, :job); 

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
   * @param jobid
   * @param jobtime
   * @param job
   * @return Insert Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableInsert (
    Object jobid,
    Object jobtime,
    Object job) throws Exception {

    return
      this.getQueryDispatchable(
        kInsertName,
        jobid,
        jobtime,
        job);
  }

  /**
   * getBoundStatementInsert
   * @param jobid
   * @param jobtime
   * @param job
   * @return Insert Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementInsert (
    Object jobid,
    Object jobtime,
    Object job) throws Exception {

    return
      this.getQuery(kInsertName).getBoundStatement(
        jobid,
        jobtime,
        job);
  }

  /**
   * executeAsyncInsert
   * executes Insert Query asynchronously
   * @param jobid
   * @param jobtime
   * @param job
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncInsert (
    Object jobid,
    Object jobtime,
    Object job) throws Exception {

    return
      this.getQuery(kInsertName).executeAsync(
        jobid,
        jobtime,
        job);
  }

  /**
   * executeSyncInsert
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes Insert Query synchronously
   * @param jobid
   * @param jobtime
   * @param job
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncInsert (
    Object jobid,
    Object jobtime,
    Object job) throws Exception {

    return
      this.getQuery(kInsertName).executeSync(
        jobid,
        jobtime,
        job);
  }

  // Query: Delete
  // Description:
  //   deletes a job 
  // Parepared Statement:
  //   DELETE FROM ig_jobs.current_jobs WHERE job_id = :job_id; 

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
   * @param jobid
   * @return Delete Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableDelete (
    Object jobid) throws Exception {

    return
      this.getQueryDispatchable(
        kDeleteName,
        jobid);
  }

  /**
   * getBoundStatementDelete
   * @param jobid
   * @return Delete Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementDelete (
    Object jobid) throws Exception {

    return
      this.getQuery(kDeleteName).getBoundStatement(
        jobid);
  }

  /**
   * executeAsyncDelete
   * executes Delete Query asynchronously
   * @param jobid
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncDelete (
    Object jobid) throws Exception {

    return
      this.getQuery(kDeleteName).executeAsync(
        jobid);
  }

  /**
   * executeSyncDelete
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes Delete Query synchronously
   * @param jobid
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncDelete (
    Object jobid) throws Exception {

    return
      this.getQuery(kDeleteName).executeSync(
        jobid);
  }

  // Query: Select
  // Description:
  //   selects a job 
  // Parepared Statement:
  //   SELECT job_time, job FROM ig_jobs.current_jobs WHERE job_id = 
  //   :job_id; 

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
   * @param jobid
   * @return Select Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableSelect (
    Object jobid) throws Exception {

    return
      this.getQueryDispatchable(
        kSelectName,
        jobid);
  }

  /**
   * getBoundStatementSelect
   * @param jobid
   * @return Select Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementSelect (
    Object jobid) throws Exception {

    return
      this.getQuery(kSelectName).getBoundStatement(
        jobid);
  }

  /**
   * executeAsyncSelect
   * executes Select Query asynchronously
   * @param jobid
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncSelect (
    Object jobid) throws Exception {

    return
      this.getQuery(kSelectName).executeAsync(
        jobid);
  }

  /**
   * executeSyncSelect
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes Select Query synchronously
   * @param jobid
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncSelect (
    Object jobid) throws Exception {

    return
      this.getQuery(kSelectName).executeSync(
        jobid);
  }

}
