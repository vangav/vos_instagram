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
 * PhotosThumnbnailsBlobs represents
 *   Table [photos_thumnbnails_blobs]
 *   in Keyspace [ig_blobs]
 * 
 * Name: photos_thumnbnails_blobs
 * Description:
 *   stores posted photos' thumbnails 
 * 
 * Columns:
 *   photo_id : uuid
 *   photo_thumbnail : blob

 * Partition Keys: photo_id
 * Secondary Keys: 
 * Caching: ALL
 * Order By:

 * Queries:
 *   - Name: insert
 *   Description:
 *     inserts a new photo's thumbnail 
 *   Prepared Statement:
 *     INSERT INTO ig_blobs.photos_thumnbnails_blobs (photo_id, 
 *     photo_thumbnail) VALUES (:photo_id, :photo_thumnbnail); 
 *   - Name: delete
 *   Description:
 *     deletes a photo's thumbnail 
 *   Prepared Statement:
 *     DELETE FROM ig_blobs.photos_thumnbnails_blobs WHERE photo_id = 
 *     :photo_id; 
 *   - Name: select
 *   Description:
 *     selects a photo's thumbnail 
 *   Prepared Statement:
 *     SELECT photo_thumbnail FROM ig_blobs.photos_thumnbnails_blobs WHERE 
 *     photo_id = :photo_id; 
 * */
public class PhotosThumnbnailsBlobs extends Table {

  private static final String kKeySpaceName =
    "ig_blobs";
  private static final String kTableName =
    "photos_thumnbnails_blobs";

  public static final String kPhotoIdColumnName =
    "photo_id";
  public static final String kPhotoThumbnailColumnName =
    "photo_thumbnail";

  /**
   * Query:
   * Name: insert
   * Description:
   *   inserts a new photo's thumbnail 
   * Prepared Statement:
   *   INSERT INTO ig_blobs.photos_thumnbnails_blobs (photo_id, 
   *   photo_thumbnail) VALUES (:photo_id, :photo_thumnbnail); 
   */
  private static final String kInsertName =
    "insert";
  private static final String kInsertDescription =
    "inserts a new photo's thumbnail ";
  private static final String kInsertPreparedStatement =
    "INSERT INTO ig_blobs.photos_thumnbnails_blobs (photo_id, "
    + "photo_thumbnail) VALUES (:photo_id, :photo_thumnbnail); ";

  /**
   * Query:
   * Name: delete
   * Description:
   *   deletes a photo's thumbnail 
   * Prepared Statement:
   *   DELETE FROM ig_blobs.photos_thumnbnails_blobs WHERE photo_id = 
   *   :photo_id; 
   */
  private static final String kDeleteName =
    "delete";
  private static final String kDeleteDescription =
    "deletes a photo's thumbnail ";
  private static final String kDeletePreparedStatement =
    "DELETE FROM ig_blobs.photos_thumnbnails_blobs WHERE photo_id = "
    + ":photo_id; ";

  /**
   * Query:
   * Name: select
   * Description:
   *   selects a photo's thumbnail 
   * Prepared Statement:
   *   SELECT photo_thumbnail FROM ig_blobs.photos_thumnbnails_blobs WHERE 
   *   photo_id = :photo_id; 
   */
  private static final String kSelectName =
    "select";
  private static final String kSelectDescription =
    "selects a photo's thumbnail ";
  private static final String kSelectPreparedStatement =
    "SELECT photo_thumbnail FROM ig_blobs.photos_thumnbnails_blobs WHERE "
    + "photo_id = :photo_id; ";

  /**
   * Constructor PhotosThumnbnailsBlobs
   * @return new PhotosThumnbnailsBlobs Object
   * @throws Exception
   */
  private PhotosThumnbnailsBlobs () throws Exception {

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

  private static PhotosThumnbnailsBlobs instance = null;

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

      instance = new PhotosThumnbnailsBlobs();
    }
  }

  /**
   * i
   * @return singleton static instance of PhotosThumnbnailsBlobs
   * @throws Exception
   */
  public static PhotosThumnbnailsBlobs i () throws Exception {

    if (instance == null) {

      instance = new PhotosThumnbnailsBlobs();
    }

    return instance;
  }

  // Query: Insert
  // Description:
  //   inserts a new photo's thumbnail 
  // Parepared Statement:
  //   INSERT INTO ig_blobs.photos_thumnbnails_blobs (photo_id, 
  //   photo_thumbnail) VALUES (:photo_id, :photo_thumnbnail); 

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
   * @param photoid
   * @param photothumnbnail
   * @return Insert Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableInsert (
    Object photoid,
    Object photothumnbnail) throws Exception {

    return
      this.getQueryDispatchable(
        kInsertName,
        photoid,
        photothumnbnail);
  }

  /**
   * getBoundStatementInsert
   * @param photoid
   * @param photothumnbnail
   * @return Insert Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementInsert (
    Object photoid,
    Object photothumnbnail) throws Exception {

    return
      this.getQuery(kInsertName).getBoundStatement(
        photoid,
        photothumnbnail);
  }

  /**
   * executeAsyncInsert
   * executes Insert Query asynchronously
   * @param photoid
   * @param photothumnbnail
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncInsert (
    Object photoid,
    Object photothumnbnail) throws Exception {

    return
      this.getQuery(kInsertName).executeAsync(
        photoid,
        photothumnbnail);
  }

  /**
   * executeSyncInsert
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes Insert Query synchronously
   * @param photoid
   * @param photothumnbnail
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncInsert (
    Object photoid,
    Object photothumnbnail) throws Exception {

    return
      this.getQuery(kInsertName).executeSync(
        photoid,
        photothumnbnail);
  }

  // Query: Delete
  // Description:
  //   deletes a photo's thumbnail 
  // Parepared Statement:
  //   DELETE FROM ig_blobs.photos_thumnbnails_blobs WHERE photo_id = 
  //   :photo_id; 

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
   * @param photoid
   * @return Delete Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableDelete (
    Object photoid) throws Exception {

    return
      this.getQueryDispatchable(
        kDeleteName,
        photoid);
  }

  /**
   * getBoundStatementDelete
   * @param photoid
   * @return Delete Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementDelete (
    Object photoid) throws Exception {

    return
      this.getQuery(kDeleteName).getBoundStatement(
        photoid);
  }

  /**
   * executeAsyncDelete
   * executes Delete Query asynchronously
   * @param photoid
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncDelete (
    Object photoid) throws Exception {

    return
      this.getQuery(kDeleteName).executeAsync(
        photoid);
  }

  /**
   * executeSyncDelete
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes Delete Query synchronously
   * @param photoid
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncDelete (
    Object photoid) throws Exception {

    return
      this.getQuery(kDeleteName).executeSync(
        photoid);
  }

  // Query: Select
  // Description:
  //   selects a photo's thumbnail 
  // Parepared Statement:
  //   SELECT photo_thumbnail FROM ig_blobs.photos_thumnbnails_blobs WHERE 
  //   photo_id = :photo_id; 

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
   * @param photoid
   * @return Select Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableSelect (
    Object photoid) throws Exception {

    return
      this.getQueryDispatchable(
        kSelectName,
        photoid);
  }

  /**
   * getBoundStatementSelect
   * @param photoid
   * @return Select Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementSelect (
    Object photoid) throws Exception {

    return
      this.getQuery(kSelectName).getBoundStatement(
        photoid);
  }

  /**
   * executeAsyncSelect
   * executes Select Query asynchronously
   * @param photoid
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncSelect (
    Object photoid) throws Exception {

    return
      this.getQuery(kSelectName).executeAsync(
        photoid);
  }

  /**
   * executeSyncSelect
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes Select Query synchronously
   * @param photoid
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncSelect (
    Object photoid) throws Exception {

    return
      this.getQuery(kSelectName).executeSync(
        photoid);
  }

}
