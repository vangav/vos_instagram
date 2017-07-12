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
 * Posts represents
 *   Table [posts]
 *   in Keyspace [ig_app_data]
 * 
 * Name: posts
 * Description:
 *   stores posts' info 
 * 
 * Columns:
 *   post_id : uuid
 *   post_time : bigint
 *   photo_id : uuid
 *   caption : varchar
 *   user_id : uuid
 *   latitude : double
 *   longitude : double

 * Partition Keys: post_id
 * Secondary Keys: 
 * Caching: ALL
 * Order By:

 * Queries:
 *   - Name: insert
 *   Description:
 *     inserts a new post's info 
 *   Prepared Statement:
 *     INSERT INTO ig_app_data.posts (post_id, post_time, photo_id, 
 *     caption, user_id, latitude, longitude) VALUES (:post_id, 
 *     :post_time, :photo_id, :caption, :user_id, :latitude, 
 *     :longitude); 
 *   - Name: insert_without_location
 *   Description:
 *     inserts a new post's info without location 
 *   Prepared Statement:
 *     INSERT INTO ig_app_data.posts (post_id, post_time, photo_id, 
 *     caption, user_id) VALUES (:post_id, :post_time, :photo_id, 
 *     :caption, :user_id); 
 *   - Name: select_time
 *   Description:
 *     selects a post's time 
 *   Prepared Statement:
 *     SELECT post_time FROM ig_app_data.posts WHERE post_id = :post_id; 
 *   - Name: select_user_id
 *   Description:
 *     selects a post's owner user_id 
 *   Prepared Statement:
 *     SELECT user_id FROM ig_app_data.posts WHERE post_id = :post_id; 
 *   - Name: select_photo_id
 *   Description:
 *     selects a post's photo_id 
 *   Prepared Statement:
 *     SELECT photo_id FROM ig_app_data.posts WHERE post_id = :post_id; 
 *   - Name: select
 *   Description:
 *     selects a post's info 
 *   Prepared Statement:
 *     SELECT post_time, photo_id, caption, user_id, latitude, longitude 
 *     FROM ig_app_data.posts WHERE post_id = :post_id; 
 * */
public class Posts extends Table {

  private static final String kKeySpaceName =
    "ig_app_data";
  private static final String kTableName =
    "posts";

  public static final String kPostIdColumnName =
    "post_id";
  public static final String kPostTimeColumnName =
    "post_time";
  public static final String kPhotoIdColumnName =
    "photo_id";
  public static final String kCaptionColumnName =
    "caption";
  public static final String kUserIdColumnName =
    "user_id";
  public static final String kLatitudeColumnName =
    "latitude";
  public static final String kLongitudeColumnName =
    "longitude";

  /**
   * Query:
   * Name: insert
   * Description:
   *   inserts a new post's info 
   * Prepared Statement:
   *   INSERT INTO ig_app_data.posts (post_id, post_time, photo_id, 
   *   caption, user_id, latitude, longitude) VALUES (:post_id, 
   *   :post_time, :photo_id, :caption, :user_id, :latitude, 
   *   :longitude); 
   */
  private static final String kInsertName =
    "insert";
  private static final String kInsertDescription =
    "inserts a new post's info ";
  private static final String kInsertPreparedStatement =
    "INSERT INTO ig_app_data.posts (post_id, post_time, photo_id, caption, "
    + "user_id, latitude, longitude) VALUES (:post_id, :post_time, "
    + ":photo_id, :caption, :user_id, :latitude, :longitude); ";

  /**
   * Query:
   * Name: insert_without_location
   * Description:
   *   inserts a new post's info without location 
   * Prepared Statement:
   *   INSERT INTO ig_app_data.posts (post_id, post_time, photo_id, 
   *   caption, user_id) VALUES (:post_id, :post_time, :photo_id, 
   *   :caption, :user_id); 
   */
  private static final String kInsertWithoutLocationName =
    "insert_without_location";
  private static final String kInsertWithoutLocationDescription =
    "inserts a new post's info without location ";
  private static final String kInsertWithoutLocationPreparedStatement =
    "INSERT INTO ig_app_data.posts (post_id, post_time, photo_id, caption, "
    + "user_id) VALUES (:post_id, :post_time, :photo_id, :caption, "
    + ":user_id); ";

  /**
   * Query:
   * Name: select_time
   * Description:
   *   selects a post's time 
   * Prepared Statement:
   *   SELECT post_time FROM ig_app_data.posts WHERE post_id = :post_id; 
   */
  private static final String kSelectTimeName =
    "select_time";
  private static final String kSelectTimeDescription =
    "selects a post's time ";
  private static final String kSelectTimePreparedStatement =
    "SELECT post_time FROM ig_app_data.posts WHERE post_id = :post_id; ";

  /**
   * Query:
   * Name: select_user_id
   * Description:
   *   selects a post's owner user_id 
   * Prepared Statement:
   *   SELECT user_id FROM ig_app_data.posts WHERE post_id = :post_id; 
   */
  private static final String kSelectUserIdName =
    "select_user_id";
  private static final String kSelectUserIdDescription =
    "selects a post's owner user_id ";
  private static final String kSelectUserIdPreparedStatement =
    "SELECT user_id FROM ig_app_data.posts WHERE post_id = :post_id; ";

  /**
   * Query:
   * Name: select_photo_id
   * Description:
   *   selects a post's photo_id 
   * Prepared Statement:
   *   SELECT photo_id FROM ig_app_data.posts WHERE post_id = :post_id; 
   */
  private static final String kSelectPhotoIdName =
    "select_photo_id";
  private static final String kSelectPhotoIdDescription =
    "selects a post's photo_id ";
  private static final String kSelectPhotoIdPreparedStatement =
    "SELECT photo_id FROM ig_app_data.posts WHERE post_id = :post_id; ";

  /**
   * Query:
   * Name: select
   * Description:
   *   selects a post's info 
   * Prepared Statement:
   *   SELECT post_time, photo_id, caption, user_id, latitude, longitude 
   *   FROM ig_app_data.posts WHERE post_id = :post_id; 
   */
  private static final String kSelectName =
    "select";
  private static final String kSelectDescription =
    "selects a post's info ";
  private static final String kSelectPreparedStatement =
    "SELECT post_time, photo_id, caption, user_id, latitude, longitude FROM "
    + "ig_app_data.posts WHERE post_id = :post_id; ";

  /**
   * Constructor Posts
   * @return new Posts Object
   * @throws Exception
   */
  private Posts () throws Exception {

    super (
      kKeySpaceName,
      kTableName,
      new Query (
        kInsertDescription,
        kInsertName,
        kInsertPreparedStatement),
      new Query (
        kInsertWithoutLocationDescription,
        kInsertWithoutLocationName,
        kInsertWithoutLocationPreparedStatement),
      new Query (
        kSelectTimeDescription,
        kSelectTimeName,
        kSelectTimePreparedStatement),
      new Query (
        kSelectUserIdDescription,
        kSelectUserIdName,
        kSelectUserIdPreparedStatement),
      new Query (
        kSelectPhotoIdDescription,
        kSelectPhotoIdName,
        kSelectPhotoIdPreparedStatement),
      new Query (
        kSelectDescription,
        kSelectName,
        kSelectPreparedStatement));
  }

  private static Posts instance = null;

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

      instance = new Posts();
    }
  }

  /**
   * i
   * @return singleton static instance of Posts
   * @throws Exception
   */
  public static Posts i () throws Exception {

    if (instance == null) {

      instance = new Posts();
    }

    return instance;
  }

  // Query: Insert
  // Description:
  //   inserts a new post's info 
  // Parepared Statement:
  //   INSERT INTO ig_app_data.posts (post_id, post_time, photo_id, 
  //   caption, user_id, latitude, longitude) VALUES (:post_id, 
  //   :post_time, :photo_id, :caption, :user_id, :latitude, 
  //   :longitude); 

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
   * @param postid
   * @param posttime
   * @param photoid
   * @param caption
   * @param userid
   * @param latitude
   * @param longitude
   * @return Insert Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableInsert (
    Object postid,
    Object posttime,
    Object photoid,
    Object caption,
    Object userid,
    Object latitude,
    Object longitude) throws Exception {

    return
      this.getQueryDispatchable(
        kInsertName,
        postid,
        posttime,
        photoid,
        caption,
        userid,
        latitude,
        longitude);
  }

  /**
   * getBoundStatementInsert
   * @param postid
   * @param posttime
   * @param photoid
   * @param caption
   * @param userid
   * @param latitude
   * @param longitude
   * @return Insert Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementInsert (
    Object postid,
    Object posttime,
    Object photoid,
    Object caption,
    Object userid,
    Object latitude,
    Object longitude) throws Exception {

    return
      this.getQuery(kInsertName).getBoundStatement(
        postid,
        posttime,
        photoid,
        caption,
        userid,
        latitude,
        longitude);
  }

  /**
   * executeAsyncInsert
   * executes Insert Query asynchronously
   * @param postid
   * @param posttime
   * @param photoid
   * @param caption
   * @param userid
   * @param latitude
   * @param longitude
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncInsert (
    Object postid,
    Object posttime,
    Object photoid,
    Object caption,
    Object userid,
    Object latitude,
    Object longitude) throws Exception {

    return
      this.getQuery(kInsertName).executeAsync(
        postid,
        posttime,
        photoid,
        caption,
        userid,
        latitude,
        longitude);
  }

  /**
   * executeSyncInsert
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes Insert Query synchronously
   * @param postid
   * @param posttime
   * @param photoid
   * @param caption
   * @param userid
   * @param latitude
   * @param longitude
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncInsert (
    Object postid,
    Object posttime,
    Object photoid,
    Object caption,
    Object userid,
    Object latitude,
    Object longitude) throws Exception {

    return
      this.getQuery(kInsertName).executeSync(
        postid,
        posttime,
        photoid,
        caption,
        userid,
        latitude,
        longitude);
  }

  // Query: InsertWithoutLocation
  // Description:
  //   inserts a new post's info without location 
  // Parepared Statement:
  //   INSERT INTO ig_app_data.posts (post_id, post_time, photo_id, 
  //   caption, user_id) VALUES (:post_id, :post_time, :photo_id, 
  //   :caption, :user_id); 

  /**
   * getQueryInsertWithoutLocation
   * @return InsertWithoutLocation Query in the form of
   *           a Query Object
   * @throws Exception
   */
  public Query getQueryInsertWithoutLocation (
    ) throws Exception {

    return this.getQuery(kInsertWithoutLocationName);
  }

  /**
   * getQueryDispatchableInsertWithoutLocation
   * @param postid
   * @param posttime
   * @param photoid
   * @param caption
   * @param userid
   * @return InsertWithoutLocation Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableInsertWithoutLocation (
    Object postid,
    Object posttime,
    Object photoid,
    Object caption,
    Object userid) throws Exception {

    return
      this.getQueryDispatchable(
        kInsertWithoutLocationName,
        postid,
        posttime,
        photoid,
        caption,
        userid);
  }

  /**
   * getBoundStatementInsertWithoutLocation
   * @param postid
   * @param posttime
   * @param photoid
   * @param caption
   * @param userid
   * @return InsertWithoutLocation Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementInsertWithoutLocation (
    Object postid,
    Object posttime,
    Object photoid,
    Object caption,
    Object userid) throws Exception {

    return
      this.getQuery(kInsertWithoutLocationName).getBoundStatement(
        postid,
        posttime,
        photoid,
        caption,
        userid);
  }

  /**
   * executeAsyncInsertWithoutLocation
   * executes InsertWithoutLocation Query asynchronously
   * @param postid
   * @param posttime
   * @param photoid
   * @param caption
   * @param userid
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncInsertWithoutLocation (
    Object postid,
    Object posttime,
    Object photoid,
    Object caption,
    Object userid) throws Exception {

    return
      this.getQuery(kInsertWithoutLocationName).executeAsync(
        postid,
        posttime,
        photoid,
        caption,
        userid);
  }

  /**
   * executeSyncInsertWithoutLocation
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes InsertWithoutLocation Query synchronously
   * @param postid
   * @param posttime
   * @param photoid
   * @param caption
   * @param userid
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncInsertWithoutLocation (
    Object postid,
    Object posttime,
    Object photoid,
    Object caption,
    Object userid) throws Exception {

    return
      this.getQuery(kInsertWithoutLocationName).executeSync(
        postid,
        posttime,
        photoid,
        caption,
        userid);
  }

  // Query: SelectTime
  // Description:
  //   selects a post's time 
  // Parepared Statement:
  //   SELECT post_time FROM ig_app_data.posts WHERE post_id = :post_id; 

  /**
   * getQuerySelectTime
   * @return SelectTime Query in the form of
   *           a Query Object
   * @throws Exception
   */
  public Query getQuerySelectTime (
    ) throws Exception {

    return this.getQuery(kSelectTimeName);
  }

  /**
   * getQueryDispatchableSelectTime
   * @param postid
   * @return SelectTime Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableSelectTime (
    Object postid) throws Exception {

    return
      this.getQueryDispatchable(
        kSelectTimeName,
        postid);
  }

  /**
   * getBoundStatementSelectTime
   * @param postid
   * @return SelectTime Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementSelectTime (
    Object postid) throws Exception {

    return
      this.getQuery(kSelectTimeName).getBoundStatement(
        postid);
  }

  /**
   * executeAsyncSelectTime
   * executes SelectTime Query asynchronously
   * @param postid
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncSelectTime (
    Object postid) throws Exception {

    return
      this.getQuery(kSelectTimeName).executeAsync(
        postid);
  }

  /**
   * executeSyncSelectTime
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes SelectTime Query synchronously
   * @param postid
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncSelectTime (
    Object postid) throws Exception {

    return
      this.getQuery(kSelectTimeName).executeSync(
        postid);
  }

  // Query: SelectUserId
  // Description:
  //   selects a post's owner user_id 
  // Parepared Statement:
  //   SELECT user_id FROM ig_app_data.posts WHERE post_id = :post_id; 

  /**
   * getQuerySelectUserId
   * @return SelectUserId Query in the form of
   *           a Query Object
   * @throws Exception
   */
  public Query getQuerySelectUserId (
    ) throws Exception {

    return this.getQuery(kSelectUserIdName);
  }

  /**
   * getQueryDispatchableSelectUserId
   * @param postid
   * @return SelectUserId Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableSelectUserId (
    Object postid) throws Exception {

    return
      this.getQueryDispatchable(
        kSelectUserIdName,
        postid);
  }

  /**
   * getBoundStatementSelectUserId
   * @param postid
   * @return SelectUserId Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementSelectUserId (
    Object postid) throws Exception {

    return
      this.getQuery(kSelectUserIdName).getBoundStatement(
        postid);
  }

  /**
   * executeAsyncSelectUserId
   * executes SelectUserId Query asynchronously
   * @param postid
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncSelectUserId (
    Object postid) throws Exception {

    return
      this.getQuery(kSelectUserIdName).executeAsync(
        postid);
  }

  /**
   * executeSyncSelectUserId
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes SelectUserId Query synchronously
   * @param postid
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncSelectUserId (
    Object postid) throws Exception {

    return
      this.getQuery(kSelectUserIdName).executeSync(
        postid);
  }

  // Query: SelectPhotoId
  // Description:
  //   selects a post's photo_id 
  // Parepared Statement:
  //   SELECT photo_id FROM ig_app_data.posts WHERE post_id = :post_id; 

  /**
   * getQuerySelectPhotoId
   * @return SelectPhotoId Query in the form of
   *           a Query Object
   * @throws Exception
   */
  public Query getQuerySelectPhotoId (
    ) throws Exception {

    return this.getQuery(kSelectPhotoIdName);
  }

  /**
   * getQueryDispatchableSelectPhotoId
   * @param postid
   * @return SelectPhotoId Query in the form of
   *           a QueryDisbatchable Object
   *           (e.g.: to be passed on to a worker instance)
   * @throws Exception
   */
  public QueryDispatchable getQueryDispatchableSelectPhotoId (
    Object postid) throws Exception {

    return
      this.getQueryDispatchable(
        kSelectPhotoIdName,
        postid);
  }

  /**
   * getBoundStatementSelectPhotoId
   * @param postid
   * @return SelectPhotoId Query in the form of
   *           a BoundStatement ready for execution or to be added to
   *           a BatchStatement
   * @throws Exception
   */
  public BoundStatement getBoundStatementSelectPhotoId (
    Object postid) throws Exception {

    return
      this.getQuery(kSelectPhotoIdName).getBoundStatement(
        postid);
  }

  /**
   * executeAsyncSelectPhotoId
   * executes SelectPhotoId Query asynchronously
   * @param postid
   * @return ResultSetFuture
   * @throws Exception
   */
  public ResultSetFuture executeAsyncSelectPhotoId (
    Object postid) throws Exception {

    return
      this.getQuery(kSelectPhotoIdName).executeAsync(
        postid);
  }

  /**
   * executeSyncSelectPhotoId
   * BLOCKING-METHOD: blocks till the ResultSet is ready
   * executes SelectPhotoId Query synchronously
   * @param postid
   * @return ResultSet
   * @throws Exception
   */
  public ResultSet executeSyncSelectPhotoId (
    Object postid) throws Exception {

    return
      this.getQuery(kSelectPhotoIdName).executeSync(
        postid);
  }

  // Query: Select
  // Description:
  //   selects a post's info 
  // Parepared Statement:
  //   SELECT post_time, photo_id, caption, user_id, latitude, longitude 
  //   FROM ig_app_data.posts WHERE post_id = :post_id; 

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
