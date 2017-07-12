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

package com.vangav.vos_instagram.cassandra_keyspaces.ig_auth;

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
 * EmailCreds represents
 *   Table [email_creds]
 *   in Keyspace [ig_auth]
 * 
 * Name: email_creds
 * Description:
 *   used to map a user's email to her/his password and user id 
 * 
 * Columns:
 *   email : varchar
 *   password : varchar
 *   user_id : uuid

 * Partition Keys: email
 * Secondary Keys: 
 * Caching: NONE
 * Order By:

 * Queries:
 *   - Name: insert
 *   Description:
 *     inserts new user's email creds upon signup 
 *   Prepared Statement:
 *     INSERT INTO ig_auth.email_creds (email, password, user_id) VALUES 
 *     (:email, :password, :user_id); 
 *   - Name: delete
 *   Description:
 *     deletes a user's password and user_id based on her/his email 
 *   Prepared Statement:
 *     DELETE FROM ig_auth.email_creds WHERE email = :email; 
 *   - Name: select
 *   Description:
 *     selects a user's password and user_id based on her/his email 
 *   Prepared Statement:
 *     SELECT password, user_id FROM ig_auth.email_creds WHERE email = 
 *     :email; 
 * */
public class EmailCreds extends Table {

  private static final String kKeySpaceName =
    "ig_auth";
  private static final String kTableName =
    "email_creds";

  public static final String kEmailColumnName =
    "email";
  public static final String kPasswordColumnName =
    "password";
  public static final String kUserIdColumnName =
    "user_id";

  /**
   * Query:
   * Name: insert
   * Description:
   *   inserts new user's email creds upon signup 
   * Prepared Statement:
   *   INSERT INTO ig_auth.email_creds (email, password, user_id) VALUES 
   *   (:email, :password, :user_id); 
   */
  private static final String kInsertName =
    "insert";
  private static final String kInsertDescription =
    "inserts new user's email creds upon signup ";
  private static final String kInsertPreparedStatement =
    "INSERT INTO ig_auth.email_creds (email, password, user_id) VALUES "
    + "(:email, :password, :user_id); ";

  /**
   * Query:
   * Name: delete
   * Description:
   *   deletes a user's password and user_id based on her/his email 
   * Prepared Statement:
   *   DELETE FROM ig_auth.email_creds WHERE email = :email; 
   */
  private static final String kDeleteName =
    "delete";
  private static final String kDeleteDescription =
    "deletes a user's password and user_id based on her/his email ";
  private static final String kDeletePreparedStatement =
    "DELETE FROM ig_auth.email_creds WHERE email = :email; ";

  /**
   * Query:
   * Name: select
   * Description:
   *   selects a user's password and user_id based on her/his email 
   * Prepared Statement:
   *   SELECT password, user_id FROM ig_auth.email_creds WHERE email = 
   *   :email; 
   */
  private static final String kSelectName =
    "select";
  private static final String kSelectDescription =
    "selects a user's password and user_id based on her/his email ";
  private static final String kSelectPreparedStatement =
    "SELECT password, user_id FROM ig_auth.email_creds WHERE email = "
    + ":email; ";

  /**
   * Constructor EmailCreds
   * @return new EmailCreds Object
   * @throws Exception
   */
  private EmailCreds () throws Exception {

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

  private static EmailCreds instance = null;

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

      instance = new EmailCreds();
    }
  }

  /**
   * i
   * @return singleton static instance of EmailCreds
   * @throws Exception
   */
  public static EmailCreds i () throws Exception {

    if (instance == null) {

      instance = new EmailCreds();
    }

    return instance;
  }

  // Query: Insert
  // Description:
  //   inserts new user's email creds upon signup 
  // Parepared Statement:
  //   INSERT INTO ig_auth.email_creds (email, password, user_id) VALUES 
  //   (:email, :password, :user_id); 

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
   * @param email
   * @param password
   * @param userid
   * @return Insert Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableInsert (
    Object email,
    Object password,
    Object userid) throws Exception {

    return
      this.getQueryDispatchable(
        kInsertName,
        email,
        password,
        userid);
  }

  /**
   * getBoundStatementInsert
   * @param email
   * @param password
   * @param userid
   * @return Insert Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementInsert (
    Object email,
    Object password,
    Object userid) throws Exception {

    return
      this.getQuery(kInsertName).getBoundStatement(
        email,
        password,
        userid);
  }

  /**
   * executeAsyncInsert
   * executes Insert Query asynchronously
   * @param email
   * @param password
   * @param userid
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncInsert (
    Object email,
    Object password,
    Object userid) throws Exception {

    return
      this.getQuery(kInsertName).executeAsync(
        email,
        password,
        userid);
  }

  /**
   * executeSyncInsert
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes Insert Query synchronously
   * @param email
   * @param password
   * @param userid
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncInsert (
    Object email,
    Object password,
    Object userid) throws Exception {

    return
      this.getQuery(kInsertName).executeSync(
        email,
        password,
        userid);
  }

  // Query: Delete
  // Description:
  //   deletes a user's password and user_id based on her/his email 
  // Parepared Statement:
  //   DELETE FROM ig_auth.email_creds WHERE email = :email; 

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
   * @param email
   * @return Delete Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableDelete (
    Object email) throws Exception {

    return
      this.getQueryDispatchable(
        kDeleteName,
        email);
  }

  /**
   * getBoundStatementDelete
   * @param email
   * @return Delete Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementDelete (
    Object email) throws Exception {

    return
      this.getQuery(kDeleteName).getBoundStatement(
        email);
  }

  /**
   * executeAsyncDelete
   * executes Delete Query asynchronously
   * @param email
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncDelete (
    Object email) throws Exception {

    return
      this.getQuery(kDeleteName).executeAsync(
        email);
  }

  /**
   * executeSyncDelete
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes Delete Query synchronously
   * @param email
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncDelete (
    Object email) throws Exception {

    return
      this.getQuery(kDeleteName).executeSync(
        email);
  }

  // Query: Select
  // Description:
  //   selects a user's password and user_id based on her/his email 
  // Parepared Statement:
  //   SELECT password, user_id FROM ig_auth.email_creds WHERE email = 
  //   :email; 

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
   * @param email
   * @return Select Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableSelect (
    Object email) throws Exception {

    return
      this.getQueryDispatchable(
        kSelectName,
        email);
  }

  /**
   * getBoundStatementSelect
   * @param email
   * @return Select Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementSelect (
    Object email) throws Exception {

    return
      this.getQuery(kSelectName).getBoundStatement(
        email);
  }

  /**
   * executeAsyncSelect
   * executes Select Query asynchronously
   * @param email
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncSelect (
    Object email) throws Exception {

    return
      this.getQuery(kSelectName).executeAsync(
        email);
  }

  /**
   * executeSyncSelect
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes Select Query synchronously
   * @param email
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncSelect (
    Object email) throws Exception {

    return
      this.getQuery(kSelectName).executeSync(
        email);
  }

}
