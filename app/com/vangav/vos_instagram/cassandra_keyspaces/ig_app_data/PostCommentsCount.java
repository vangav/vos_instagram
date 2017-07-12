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
 * PostCommentsCount represents
 *   Table [post_comments_count]
 *   in Keyspace [ig_app_data]
 * 
 * Name: post_comments_count
 * Description:
 *   stores the number of comments a post got 
 * 
 * Columns:
 *   post_id : uuid
 *   comments_count : counter

 * Partition Keys: post_id
 * Secondary Keys: 
 * Caching: ALL
 * Order By:

 * Queries:
 *   - Name: increment
 *   Description:
 *     increments the comments a post got by one 
 *   Prepared Statement:
 *     UPDATE ig_app_data.post_comments_count SET comments_count = 
 *     comments_count + 1 WHERE post_id = :post_id; 
 *   - Name: decrement
 *   Description:
 *     decrements the comments a post got by one 
 *   Prepared Statement:
 *     UPDATE ig_app_data.post_comments_count SET comments_count = 
 *     comments_count - 1 WHERE post_id = :post_id; 
 *   - Name: select
 *   Description:
 *     selects the number of comments a post got 
 *   Prepared Statement:
 *     SELECT comments_count FROM ig_app_data.post_comments_count WHERE 
 *     post_id = :post_id; 
 * */
public class PostCommentsCount extends Table {

  private static final String kKeySpaceName =
    "ig_app_data";
  private static final String kTableName =
    "post_comments_count";

  public static final String kPostIdColumnName =
    "post_id";
  public static final String kCommentsCountColumnName =
    "comments_count";

  /**
   * Query:
   * Name: increment
   * Description:
   *   increments the comments a post got by one 
   * Prepared Statement:
   *   UPDATE ig_app_data.post_comments_count SET comments_count = 
   *   comments_count + 1 WHERE post_id = :post_id; 
   */
  private static final String kIncrementName =
    "increment";
  private static final String kIncrementDescription =
    "increments the comments a post got by one ";
  private static final String kIncrementPreparedStatement =
    "UPDATE ig_app_data.post_comments_count SET comments_count = "
    + "comments_count + 1 WHERE post_id = :post_id; ";

  /**
   * Query:
   * Name: decrement
   * Description:
   *   decrements the comments a post got by one 
   * Prepared Statement:
   *   UPDATE ig_app_data.post_comments_count SET comments_count = 
   *   comments_count - 1 WHERE post_id = :post_id; 
   */
  private static final String kDecrementName =
    "decrement";
  private static final String kDecrementDescription =
    "decrements the comments a post got by one ";
  private static final String kDecrementPreparedStatement =
    "UPDATE ig_app_data.post_comments_count SET comments_count = "
    + "comments_count - 1 WHERE post_id = :post_id; ";

  /**
   * Query:
   * Name: select
   * Description:
   *   selects the number of comments a post got 
   * Prepared Statement:
   *   SELECT comments_count FROM ig_app_data.post_comments_count WHERE 
   *   post_id = :post_id; 
   */
  private static final String kSelectName =
    "select";
  private static final String kSelectDescription =
    "selects the number of comments a post got ";
  private static final String kSelectPreparedStatement =
    "SELECT comments_count FROM ig_app_data.post_comments_count WHERE "
    + "post_id = :post_id; ";

  /**
   * Constructor PostCommentsCount
   * @return new PostCommentsCount Object
   * @throws Exception
   */
  private PostCommentsCount () throws Exception {

    super (
      kKeySpaceName,
      kTableName,
      new Query (
        kIncrementDescription,
        kIncrementName,
        kIncrementPreparedStatement),
      new Query (
        kDecrementDescription,
        kDecrementName,
        kDecrementPreparedStatement),
      new Query (
        kSelectDescription,
        kSelectName,
        kSelectPreparedStatement));
  }

  private static PostCommentsCount instance = null;

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

      instance = new PostCommentsCount();
    }
  }

  /**
   * i
   * @return singleton static instance of PostCommentsCount
   * @throws Exception
   */
  public static PostCommentsCount i () throws Exception {

    if (instance == null) {

      instance = new PostCommentsCount();
    }

    return instance;
  }

  // Query: Increment
  // Description:
  //   increments the comments a post got by one 
  // Parepared Statement:
  //   UPDATE ig_app_data.post_comments_count SET comments_count = 
  //   comments_count + 1 WHERE post_id = :post_id; 

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
   * @param postid
   * @return Increment Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableIncrement (
    Object postid) throws Exception {

    return
      this.getQueryDispatchable(
        kIncrementName,
        postid);
  }

  /**
   * getBoundStatementIncrement
   * @param postid
   * @return Increment Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementIncrement (
    Object postid) throws Exception {

    return
      this.getQuery(kIncrementName).getBoundStatement(
        postid);
  }

  /**
   * executeAsyncIncrement
   * executes Increment Query asynchronously
   * @param postid
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncIncrement (
    Object postid) throws Exception {

    return
      this.getQuery(kIncrementName).executeAsync(
        postid);
  }

  /**
   * executeSyncIncrement
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes Increment Query synchronously
   * @param postid
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncIncrement (
    Object postid) throws Exception {

    return
      this.getQuery(kIncrementName).executeSync(
        postid);
  }

  // Query: Decrement
  // Description:
  //   decrements the comments a post got by one 
  // Parepared Statement:
  //   UPDATE ig_app_data.post_comments_count SET comments_count = 
  //   comments_count - 1 WHERE post_id = :post_id; 

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
   * @param postid
   * @return Decrement Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableDecrement (
    Object postid) throws Exception {

    return
      this.getQueryDispatchable(
        kDecrementName,
        postid);
  }

  /**
   * getBoundStatementDecrement
   * @param postid
   * @return Decrement Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementDecrement (
    Object postid) throws Exception {

    return
      this.getQuery(kDecrementName).getBoundStatement(
        postid);
  }

  /**
   * executeAsyncDecrement
   * executes Decrement Query asynchronously
   * @param postid
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncDecrement (
    Object postid) throws Exception {

    return
      this.getQuery(kDecrementName).executeAsync(
        postid);
  }

  /**
   * executeSyncDecrement
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes Decrement Query synchronously
   * @param postid
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncDecrement (
    Object postid) throws Exception {

    return
      this.getQuery(kDecrementName).executeSync(
        postid);
  }

  // Query: Select
  // Description:
  //   selects the number of comments a post got 
  // Parepared Statement:
  //   SELECT comments_count FROM ig_app_data.post_comments_count WHERE 
  //   post_id = :post_id; 

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
   * @param postid
   * @return Select Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableSelect (
    Object postid) throws Exception {

    return
      this.getQueryDispatchable(
        kSelectName,
        postid);
  }

  /**
   * getBoundStatementSelect
   * @param postid
   * @return Select Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementSelect (
    Object postid) throws Exception {

    return
      this.getQuery(kSelectName).getBoundStatement(
        postid);
  }

  /**
   * executeAsyncSelect
   * executes Select Query asynchronously
   * @param postid
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncSelect (
    Object postid) throws Exception {

    return
      this.getQuery(kSelectName).executeAsync(
        postid);
  }

  /**
   * executeSyncSelect
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes Select Query synchronously
   * @param postid
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncSelect (
    Object postid) throws Exception {

    return
      this.getQuery(kSelectName).executeSync(
        postid);
  }

}
