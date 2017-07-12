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
 * ErrorLogs represents
 *   Table [error_logs]
 *   in Keyspace [ig_logging]
 * 
 * Name: error_logs
 * Description:
 *   stores logs for failed requests (because of bad request or internal 
 *   error) 
 * 
 * Columns:
 *   log_id : uuid
 *   log_time : bigint
 *   controller_name : varchar
 *   user_id : uuid
 *   http_status_code : int
 *   request : varchar
 *   error_response : varchar

 * Partition Keys: log_id
 * Secondary Keys: 
 * Caching: ALL
 * Order By:

 * Queries:
 *   - Name: insert
 *   Description:
 *     inserts a new log 
 *   Prepared Statement:
 *     INSERT INTO ig_logging.error_logs (log_id, log_time, 
 *     controller_name, user_id, http_status_code, request, 
 *     error_response) VALUES (:log_id, :log_time, 
 *     :controller_name, :user_id, :http_status_code, :request, 
 *     :error_response); 
 *   - Name: select
 *   Description:
 *     selects a log 
 *   Prepared Statement:
 *     SELECT log_time, controller_name, user_id, http_status_code, 
 *     request, error_response FROM ig_logging.error_logs WHERE 
 *     log_id = :log_id; 
 * */
public class ErrorLogs extends Table {

  private static final String kKeySpaceName =
    "ig_logging";
  private static final String kTableName =
    "error_logs";

  public static final String kLogIdColumnName =
    "log_id";
  public static final String kLogTimeColumnName =
    "log_time";
  public static final String kControllerNameColumnName =
    "controller_name";
  public static final String kUserIdColumnName =
    "user_id";
  public static final String kHttpStatusCodeColumnName =
    "http_status_code";
  public static final String kRequestColumnName =
    "request";
  public static final String kErrorResponseColumnName =
    "error_response";

  /**
   * Query:
   * Name: insert
   * Description:
   *   inserts a new log 
   * Prepared Statement:
   *   INSERT INTO ig_logging.error_logs (log_id, log_time, 
   *   controller_name, user_id, http_status_code, request, 
   *   error_response) VALUES (:log_id, :log_time, 
   *   :controller_name, :user_id, :http_status_code, :request, 
   *   :error_response); 
   */
  private static final String kInsertName =
    "insert";
  private static final String kInsertDescription =
    "inserts a new log ";
  private static final String kInsertPreparedStatement =
    "INSERT INTO ig_logging.error_logs (log_id, log_time, controller_name, "
    + "user_id, http_status_code, request, error_response) VALUES "
    + "(:log_id, :log_time, :controller_name, :user_id, "
    + ":http_status_code, :request, :error_response); ";

  /**
   * Query:
   * Name: select
   * Description:
   *   selects a log 
   * Prepared Statement:
   *   SELECT log_time, controller_name, user_id, http_status_code, 
   *   request, error_response FROM ig_logging.error_logs WHERE 
   *   log_id = :log_id; 
   */
  private static final String kSelectName =
    "select";
  private static final String kSelectDescription =
    "selects a log ";
  private static final String kSelectPreparedStatement =
    "SELECT log_time, controller_name, user_id, http_status_code, request, "
    + "error_response FROM ig_logging.error_logs WHERE log_id = "
    + ":log_id; ";

  /**
   * Constructor ErrorLogs
   * @return new ErrorLogs Object
   * @throws Exception
   */
  private ErrorLogs () throws Exception {

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
        kSelectPreparedStatement));
  }

  private static ErrorLogs instance = null;

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

      instance = new ErrorLogs();
    }
  }

  /**
   * i
   * @return singleton static instance of ErrorLogs
   * @throws Exception
   */
  public static ErrorLogs i () throws Exception {

    if (instance == null) {

      instance = new ErrorLogs();
    }

    return instance;
  }

  // Query: Insert
  // Description:
  //   inserts a new log 
  // Parepared Statement:
  //   INSERT INTO ig_logging.error_logs (log_id, log_time, 
  //   controller_name, user_id, http_status_code, request, 
  //   error_response) VALUES (:log_id, :log_time, 
  //   :controller_name, :user_id, :http_status_code, :request, 
  //   :error_response); 

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
   * @param logid
   * @param logtime
   * @param controllername
   * @param userid
   * @param httpstatuscode
   * @param request
   * @param errorresponse
   * @return Insert Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableInsert (
    Object logid,
    Object logtime,
    Object controllername,
    Object userid,
    Object httpstatuscode,
    Object request,
    Object errorresponse) throws Exception {

    return
      this.getQueryDispatchable(
        kInsertName,
        logid,
        logtime,
        controllername,
        userid,
        httpstatuscode,
        request,
        errorresponse);
  }

  /**
   * getBoundStatementInsert
   * @param logid
   * @param logtime
   * @param controllername
   * @param userid
   * @param httpstatuscode
   * @param request
   * @param errorresponse
   * @return Insert Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementInsert (
    Object logid,
    Object logtime,
    Object controllername,
    Object userid,
    Object httpstatuscode,
    Object request,
    Object errorresponse) throws Exception {

    return
      this.getQuery(kInsertName).getBoundStatement(
        logid,
        logtime,
        controllername,
        userid,
        httpstatuscode,
        request,
        errorresponse);
  }

  /**
   * executeAsyncInsert
   * executes Insert Query asynchronously
   * @param logid
   * @param logtime
   * @param controllername
   * @param userid
   * @param httpstatuscode
   * @param request
   * @param errorresponse
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncInsert (
    Object logid,
    Object logtime,
    Object controllername,
    Object userid,
    Object httpstatuscode,
    Object request,
    Object errorresponse) throws Exception {

    return
      this.getQuery(kInsertName).executeAsync(
        logid,
        logtime,
        controllername,
        userid,
        httpstatuscode,
        request,
        errorresponse);
  }

  /**
   * executeSyncInsert
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes Insert Query synchronously
   * @param logid
   * @param logtime
   * @param controllername
   * @param userid
   * @param httpstatuscode
   * @param request
   * @param errorresponse
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncInsert (
    Object logid,
    Object logtime,
    Object controllername,
    Object userid,
    Object httpstatuscode,
    Object request,
    Object errorresponse) throws Exception {

    return
      this.getQuery(kInsertName).executeSync(
        logid,
        logtime,
        controllername,
        userid,
        httpstatuscode,
        request,
        errorresponse);
  }

  // Query: Select
  // Description:
  //   selects a log 
  // Parepared Statement:
  //   SELECT log_time, controller_name, user_id, http_status_code, 
  //   request, error_response FROM ig_logging.error_logs WHERE 
  //   log_id = :log_id; 

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
   * @param logid
   * @return Select Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableSelect (
    Object logid) throws Exception {

    return
      this.getQueryDispatchable(
        kSelectName,
        logid);
  }

  /**
   * getBoundStatementSelect
   * @param logid
   * @return Select Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementSelect (
    Object logid) throws Exception {

    return
      this.getQuery(kSelectName).getBoundStatement(
        logid);
  }

  /**
   * executeAsyncSelect
   * executes Select Query asynchronously
   * @param logid
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncSelect (
    Object logid) throws Exception {

    return
      this.getQuery(kSelectName).executeAsync(
        logid);
  }

  /**
   * executeSyncSelect
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes Select Query synchronously
   * @param logid
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncSelect (
    Object logid) throws Exception {

    return
      this.getQuery(kSelectName).executeSync(
        logid);
  }

}
