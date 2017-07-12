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

package com.vangav.vos_instagram.cassandra_keyspaces.ig_blobs;

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
 * ProfilePicturesBlobs represents
 *   Table [profile_pictures_blobs]
 *   in Keyspace [ig_blobs]
 * 
 * Name: profile_pictures_blobs
 * Description:
 *   stores users' profile pictures thumbnails 
 * 
 * Columns:
 *   picture_id : uuid
 *   picture : blob

 * Partition Keys: picture_id
 * Secondary Keys: 
 * Caching: KEYS_ONLY
 * Order By:

 * Queries:
 *   - Name: insert
 *   Description:
 *     inserts/updates a user's profile picture 
 *   Prepared Statement:
 *     INSERT INTO ig_blobs.profile_pictures_blobs (picture_id, picture) 
 *     VALUES (:picture_id, :picture); 
 *   - Name: delete
 *   Description:
 *     deletes a user's profile picture 
 *   Prepared Statement:
 *     DELETE FROM ig_blobs.profile_pictures_blobs WHERE picture_id = 
 *     :picture_id; 
 *   - Name: select
 *   Description:
 *     selects a user's profile picture 
 *   Prepared Statement:
 *     SELECT picture FROM ig_blobs.profile_pictures_blobs WHERE 
 *     picture_id = :picture_id; 
 * */
public class ProfilePicturesBlobs extends Table {

  private static final String kKeySpaceName =
    "ig_blobs";
  private static final String kTableName =
    "profile_pictures_blobs";

  public static final String kPictureIdColumnName =
    "picture_id";
  public static final String kPictureColumnName =
    "picture";

  /**
   * Query:
   * Name: insert
   * Description:
   *   inserts/updates a user's profile picture 
   * Prepared Statement:
   *   INSERT INTO ig_blobs.profile_pictures_blobs (picture_id, picture) 
   *   VALUES (:picture_id, :picture); 
   */
  private static final String kInsertName =
    "insert";
  private static final String kInsertDescription =
    "inserts/updates a user's profile picture ";
  private static final String kInsertPreparedStatement =
    "INSERT INTO ig_blobs.profile_pictures_blobs (picture_id, picture) "
    + "VALUES (:picture_id, :picture); ";

  /**
   * Query:
   * Name: delete
   * Description:
   *   deletes a user's profile picture 
   * Prepared Statement:
   *   DELETE FROM ig_blobs.profile_pictures_blobs WHERE picture_id = 
   *   :picture_id; 
   */
  private static final String kDeleteName =
    "delete";
  private static final String kDeleteDescription =
    "deletes a user's profile picture ";
  private static final String kDeletePreparedStatement =
    "DELETE FROM ig_blobs.profile_pictures_blobs WHERE picture_id = "
    + ":picture_id; ";

  /**
   * Query:
   * Name: select
   * Description:
   *   selects a user's profile picture 
   * Prepared Statement:
   *   SELECT picture FROM ig_blobs.profile_pictures_blobs WHERE 
   *   picture_id = :picture_id; 
   */
  private static final String kSelectName =
    "select";
  private static final String kSelectDescription =
    "selects a user's profile picture ";
  private static final String kSelectPreparedStatement =
    "SELECT picture FROM ig_blobs.profile_pictures_blobs WHERE picture_id = "
    + ":picture_id; ";

  /**
   * Constructor ProfilePicturesBlobs
   * @return new ProfilePicturesBlobs Object
   * @throws Exception
   */
  private ProfilePicturesBlobs () throws Exception {

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

  private static ProfilePicturesBlobs instance = null;

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

      instance = new ProfilePicturesBlobs();
    }
  }

  /**
   * i
   * @return singleton static instance of ProfilePicturesBlobs
   * @throws Exception
   */
  public static ProfilePicturesBlobs i () throws Exception {

    if (instance == null) {

      instance = new ProfilePicturesBlobs();
    }

    return instance;
  }

  // Query: Insert
  // Description:
  //   inserts/updates a user's profile picture 
  // Parepared Statement:
  //   INSERT INTO ig_blobs.profile_pictures_blobs (picture_id, picture) 
  //   VALUES (:picture_id, :picture); 

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
   * @param pictureid
   * @param picture
   * @return Insert Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableInsert (
    Object pictureid,
    Object picture) throws Exception {

    return
      this.getQueryDispatchable(
        kInsertName,
        pictureid,
        picture);
  }

  /**
   * getBoundStatementInsert
   * @param pictureid
   * @param picture
   * @return Insert Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementInsert (
    Object pictureid,
    Object picture) throws Exception {

    return
      this.getQuery(kInsertName).getBoundStatement(
        pictureid,
        picture);
  }

  /**
   * executeAsyncInsert
   * executes Insert Query asynchronously
   * @param pictureid
   * @param picture
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncInsert (
    Object pictureid,
    Object picture) throws Exception {

    return
      this.getQuery(kInsertName).executeAsync(
        pictureid,
        picture);
  }

  /**
   * executeSyncInsert
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes Insert Query synchronously
   * @param pictureid
   * @param picture
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncInsert (
    Object pictureid,
    Object picture) throws Exception {

    return
      this.getQuery(kInsertName).executeSync(
        pictureid,
        picture);
  }

  // Query: Delete
  // Description:
  //   deletes a user's profile picture 
  // Parepared Statement:
  //   DELETE FROM ig_blobs.profile_pictures_blobs WHERE picture_id = 
  //   :picture_id; 

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
   * @param pictureid
   * @return Delete Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableDelete (
    Object pictureid) throws Exception {

    return
      this.getQueryDispatchable(
        kDeleteName,
        pictureid);
  }

  /**
   * getBoundStatementDelete
   * @param pictureid
   * @return Delete Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementDelete (
    Object pictureid) throws Exception {

    return
      this.getQuery(kDeleteName).getBoundStatement(
        pictureid);
  }

  /**
   * executeAsyncDelete
   * executes Delete Query asynchronously
   * @param pictureid
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncDelete (
    Object pictureid) throws Exception {

    return
      this.getQuery(kDeleteName).executeAsync(
        pictureid);
  }

  /**
   * executeSyncDelete
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes Delete Query synchronously
   * @param pictureid
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncDelete (
    Object pictureid) throws Exception {

    return
      this.getQuery(kDeleteName).executeSync(
        pictureid);
  }

  // Query: Select
  // Description:
  //   selects a user's profile picture 
  // Parepared Statement:
  //   SELECT picture FROM ig_blobs.profile_pictures_blobs WHERE 
  //   picture_id = :picture_id; 

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
   * @param pictureid
   * @return Select Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableSelect (
    Object pictureid) throws Exception {

    return
      this.getQueryDispatchable(
        kSelectName,
        pictureid);
  }

  /**
   * getBoundStatementSelect
   * @param pictureid
   * @return Select Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementSelect (
    Object pictureid) throws Exception {

    return
      this.getQuery(kSelectName).getBoundStatement(
        pictureid);
  }

  /**
   * executeAsyncSelect
   * executes Select Query asynchronously
   * @param pictureid
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncSelect (
    Object pictureid) throws Exception {

    return
      this.getQuery(kSelectName).executeAsync(
        pictureid);
  }

  /**
   * executeSyncSelect
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes Select Query synchronously
   * @param pictureid
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncSelect (
    Object pictureid) throws Exception {

    return
      this.getQuery(kSelectName).executeSync(
        pictureid);
  }

}
