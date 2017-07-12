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
 * FollowingCount represents
 *   Table [following_count]
 *   in Keyspace [ig_app_data]
 * 
 * Name: following_count
 * Description:
 *   stores the number of users a user follows 
 * 
 * Columns:
 *   user_id : uuid
 *   following_count : counter

 * Partition Keys: user_id
 * Secondary Keys: 
 * Caching: ALL
 * Order By:

 * Queries:
 *   - Name: increment
 *   Description:
 *     increments the number of users a user is following by one 
 *   Prepared Statement:
 *     UPDATE ig_app_data.following_count SET following_count = 
 *     following_count + 1 WHERE user_id = :user_id; 
 *   - Name: increment_number
 *   Description:
 *     increments the number of users a user is following by (number) - 
 *     useful in cases like follow all facebook friends 
 *   Prepared Statement:
 *     UPDATE ig_app_data.following_count SET following_count = 
 *     following_count + :number WHERE user_id = :user_id; 
 *   - Name: decrement
 *   Description:
 *     decrements the number of users a user is following by one 
 *   Prepared Statement:
 *     UPDATE ig_app_data.following_count SET following_count = 
 *     following_count - 1 WHERE user_id = :user_id; 
 *   - Name: select
 *   Description:
 *     selects the number of users a user is following 
 *   Prepared Statement:
 *     SELECT following_count FROM ig_app_data.following_count WHERE 
 *     user_id = :user_id; 
 * */
public class FollowingCount extends Table {

  private static final String kKeySpaceName =
    "ig_app_data";
  private static final String kTableName =
    "following_count";

  public static final String kUserIdColumnName =
    "user_id";
  public static final String kFollowingCountColumnName =
    "following_count";

  /**
   * Query:
   * Name: increment
   * Description:
   *   increments the number of users a user is following by one 
   * Prepared Statement:
   *   UPDATE ig_app_data.following_count SET following_count = 
   *   following_count + 1 WHERE user_id = :user_id; 
   */
  private static final String kIncrementName =
    "increment";
  private static final String kIncrementDescription =
    "increments the number of users a user is following by one ";
  private static final String kIncrementPreparedStatement =
    "UPDATE ig_app_data.following_count SET following_count = "
    + "following_count + 1 WHERE user_id = :user_id; ";

  /**
   * Query:
   * Name: increment_number
   * Description:
   *   increments the number of users a user is following by (number) - 
   *   useful in cases like follow all facebook friends 
   * Prepared Statement:
   *   UPDATE ig_app_data.following_count SET following_count = 
   *   following_count + :number WHERE user_id = :user_id; 
   */
  private static final String kIncrementNumberName =
    "increment_number";
  private static final String kIncrementNumberDescription =
    "increments the number of users a user is following by (number) - "
    + "useful in cases like follow all facebook friends ";
  private static final String kIncrementNumberPreparedStatement =
    "UPDATE ig_app_data.following_count SET following_count = "
    + "following_count + :number WHERE user_id = :user_id; ";

  /**
   * Query:
   * Name: decrement
   * Description:
   *   decrements the number of users a user is following by one 
   * Prepared Statement:
   *   UPDATE ig_app_data.following_count SET following_count = 
   *   following_count - 1 WHERE user_id = :user_id; 
   */
  private static final String kDecrementName =
    "decrement";
  private static final String kDecrementDescription =
    "decrements the number of users a user is following by one ";
  private static final String kDecrementPreparedStatement =
    "UPDATE ig_app_data.following_count SET following_count = "
    + "following_count - 1 WHERE user_id = :user_id; ";

  /**
   * Query:
   * Name: select
   * Description:
   *   selects the number of users a user is following 
   * Prepared Statement:
   *   SELECT following_count FROM ig_app_data.following_count WHERE 
   *   user_id = :user_id; 
   */
  private static final String kSelectName =
    "select";
  private static final String kSelectDescription =
    "selects the number of users a user is following ";
  private static final String kSelectPreparedStatement =
    "SELECT following_count FROM ig_app_data.following_count WHERE user_id "
    + "= :user_id; ";

  /**
   * Constructor FollowingCount
   * @return new FollowingCount Object
   * @throws Exception
   */
  private FollowingCount () throws Exception {

    super (
      kKeySpaceName,
      kTableName,
      new Query (
        kIncrementDescription,
        kIncrementName,
        kIncrementPreparedStatement),
      new Query (
        kIncrementNumberDescription,
        kIncrementNumberName,
        kIncrementNumberPreparedStatement),
      new Query (
        kDecrementDescription,
        kDecrementName,
        kDecrementPreparedStatement),
      new Query (
        kSelectDescription,
        kSelectName,
        kSelectPreparedStatement));
  }

  private static FollowingCount instance = null;

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

      instance = new FollowingCount();
    }
  }

  /**
   * i
   * @return singleton static instance of FollowingCount
   * @throws Exception
   */
  public static FollowingCount i () throws Exception {

    if (instance == null) {

      instance = new FollowingCount();
    }

    return instance;
  }

  // Query: Increment
  // Description:
  //   increments the number of users a user is following by one 
  // Parepared Statement:
  //   UPDATE ig_app_data.following_count SET following_count = 
  //   following_count + 1 WHERE user_id = :user_id; 

  /**
   * getQueryIncrement
   * @return Increment Query in the form of
   *           a Query Object
   * @throws Exception
   */
  public Query getQueryIncrement (
    ) throws Exception {

    return this.getQuery(kIncrementName);
  }

  /**
   * getQueryDispatchableIncrement
   * @param userid
   * @return Increment Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableIncrement (
    Object userid) throws Exception {

    return
      this.getQueryDispatchable(
        kIncrementName,
        userid);
  }

  /**
   * getBoundStatementIncrement
   * @param userid
   * @return Increment Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementIncrement (
    Object userid) throws Exception {

    return
      this.getQuery(kIncrementName).getBoundStatement(
        userid);
  }

  /**
   * executeAsyncIncrement
   * executes Increment Query asynchronously
   * @param userid
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncIncrement (
    Object userid) throws Exception {

    return
      this.getQuery(kIncrementName).executeAsync(
        userid);
  }

  /**
   * executeSyncIncrement
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes Increment Query synchronously
   * @param userid
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncIncrement (
    Object userid) throws Exception {

    return
      this.getQuery(kIncrementName).executeSync(
        userid);
  }

  // Query: IncrementNumber
  // Description:
  //   increments the number of users a user is following by (number) - 
  //   useful in cases like follow all facebook friends 
  // Parepared Statement:
  //   UPDATE ig_app_data.following_count SET following_count = 
  //   following_count + :number WHERE user_id = :user_id; 

  /**
   * getQueryIncrementNumber
   * @return IncrementNumber Query in the form of
   *           a Query Object
   * @throws Exception
   */
  public Query getQueryIncrementNumber (
    ) throws Exception {

    return this.getQuery(kIncrementNumberName);
  }

  /**
   * getQueryDispatchableIncrementNumber
   * @param number
   * @param userid
   * @return IncrementNumber Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableIncrementNumber (
    Object number,
    Object userid) throws Exception {

    return
      this.getQueryDispatchable(
        kIncrementNumberName,
        number,
        userid);
  }

  /**
   * getBoundStatementIncrementNumber
   * @param number
   * @param userid
   * @return IncrementNumber Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementIncrementNumber (
    Object number,
    Object userid) throws Exception {

    return
      this.getQuery(kIncrementNumberName).getBoundStatement(
        number,
        userid);
  }

  /**
   * executeAsyncIncrementNumber
   * executes IncrementNumber Query asynchronously
   * @param number
   * @param userid
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncIncrementNumber (
    Object number,
    Object userid) throws Exception {

    return
      this.getQuery(kIncrementNumberName).executeAsync(
        number,
        userid);
  }

  /**
   * executeSyncIncrementNumber
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes IncrementNumber Query synchronously
   * @param number
   * @param userid
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncIncrementNumber (
    Object number,
    Object userid) throws Exception {

    return
      this.getQuery(kIncrementNumberName).executeSync(
        number,
        userid);
  }

  // Query: Decrement
  // Description:
  //   decrements the number of users a user is following by one 
  // Parepared Statement:
  //   UPDATE ig_app_data.following_count SET following_count = 
  //   following_count - 1 WHERE user_id = :user_id; 

  /**
   * getQueryDecrement
   * @return Decrement Query in the form of
   *           a Query Object
   * @throws Exception
   */
  public Query getQueryDecrement (
    ) throws Exception {

    return this.getQuery(kDecrementName);
  }

  /**
   * getQueryDispatchableDecrement
   * @param userid
   * @return Decrement Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableDecrement (
    Object userid) throws Exception {

    return
      this.getQueryDispatchable(
        kDecrementName,
        userid);
  }

  /**
   * getBoundStatementDecrement
   * @param userid
   * @return Decrement Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementDecrement (
    Object userid) throws Exception {

    return
      this.getQuery(kDecrementName).getBoundStatement(
        userid);
  }

  /**
   * executeAsyncDecrement
   * executes Decrement Query asynchronously
   * @param userid
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncDecrement (
    Object userid) throws Exception {

    return
      this.getQuery(kDecrementName).executeAsync(
        userid);
  }

  /**
   * executeSyncDecrement
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes Decrement Query synchronously
   * @param userid
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncDecrement (
    Object userid) throws Exception {

    return
      this.getQuery(kDecrementName).executeSync(
        userid);
  }

  // Query: Select
  // Description:
  //   selects the number of users a user is following 
  // Parepared Statement:
  //   SELECT following_count FROM ig_app_data.following_count WHERE 
  //   user_id = :user_id; 

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
   * @param userid
   * @return Select Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableSelect (
    Object userid) throws Exception {

    return
      this.getQueryDispatchable(
        kSelectName,
        userid);
  }

  /**
   * getBoundStatementSelect
   * @param userid
   * @return Select Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementSelect (
    Object userid) throws Exception {

    return
      this.getQuery(kSelectName).getBoundStatement(
        userid);
  }

  /**
   * executeAsyncSelect
   * executes Select Query asynchronously
   * @param userid
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncSelect (
    Object userid) throws Exception {

    return
      this.getQuery(kSelectName).executeAsync(
        userid);
  }

  /**
   * executeSyncSelect
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes Select Query synchronously
   * @param userid
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncSelect (
    Object userid) throws Exception {

    return
      this.getQuery(kSelectName).executeSync(
        userid);
  }

}
