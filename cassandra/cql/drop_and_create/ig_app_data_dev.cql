// Keyspace: ig_app_data

DROP KEYSPACE IF EXISTS ig_app_data;

CREATE KEYSPACE ig_app_data
  WITH replication = {
    'class': 'SimpleStrategy', 'replication_factor' : 1
};


USE ig_app_data;

DROP TABLE IF EXISTS users_info;

CREATE TABLE users_info (
  user_id uuid,
  name varchar,
  device_tokens map<varchar, varchar>,
  profile_picture_id uuid,
  registration_time bigint,
  last_active_time bigint,
  last_latitude double,
  last_longitude double,
  PRIMARY KEY (user_id) )
  WITH CACHING = 'ALL';


DROP TABLE IF EXISTS following;

CREATE TABLE following (
  user_id uuid,
  following_time bigint,
  following_user_id uuid,
  PRIMARY KEY (user_id, following_time, following_user_id) )
  WITH CACHING = 'ALL' AND
  CLUSTERING ORDER BY (following_time DESC);


DROP TABLE IF EXISTS following_time;

CREATE TABLE following_time (
  user_id uuid,
  following_user_id uuid,
  following_time bigint,
  PRIMARY KEY (user_id, following_user_id) )
  WITH CACHING = 'ALL';


DROP TABLE IF EXISTS following_count;

CREATE TABLE following_count (
  user_id uuid,
  following_count counter,
  PRIMARY KEY (user_id) )
  WITH CACHING = 'ALL';


DROP TABLE IF EXISTS follower;

CREATE TABLE follower (
  user_id uuid,
  follower_time bigint,
  follower_user_id uuid,
  PRIMARY KEY (user_id, follower_time, follower_user_id) )
  WITH CACHING = 'ALL' AND
  CLUSTERING ORDER BY (follower_time DESC);


DROP TABLE IF EXISTS follower_time;

CREATE TABLE follower_time (
  user_id uuid,
  follower_user_id uuid,
  follower_time bigint,
  PRIMARY KEY (user_id, follower_user_id) )
  WITH CACHING = 'ALL';


DROP TABLE IF EXISTS follower_count;

CREATE TABLE follower_count (
  user_id uuid,
  follower_count counter,
  PRIMARY KEY (user_id) )
  WITH CACHING = 'ALL';


DROP TABLE IF EXISTS user_posts;

CREATE TABLE user_posts (
  user_id uuid,
  post_time bigint,
  post_id uuid,
  PRIMARY KEY (user_id, post_time, post_id) )
  WITH CACHING = 'ALL' AND
  CLUSTERING ORDER BY (post_time DESC);


DROP TABLE IF EXISTS user_posts_count;

CREATE TABLE user_posts_count (
  user_id uuid,
  posts_count counter,
  PRIMARY KEY (user_id) )
  WITH CACHING = 'ALL';


DROP TABLE IF EXISTS user_feed_posts;

CREATE TABLE user_feed_posts (
  user_id uuid,
  post_time bigint,
  post_id uuid,
  PRIMARY KEY (user_id, post_time, post_id) )
  WITH CACHING = 'ALL' AND
  CLUSTERING ORDER BY (post_time DESC);


DROP TABLE IF EXISTS posts;

CREATE TABLE posts (
  post_id uuid,
  post_time bigint,
  photo_id uuid,
  caption varchar,
  user_id uuid,
  latitude double,
  longitude double,
  PRIMARY KEY (post_id) )
  WITH CACHING = 'ALL';


DROP TABLE IF EXISTS post_likes;

CREATE TABLE post_likes (
  post_id uuid,
  like_time bigint,
  user_id uuid,
  PRIMARY KEY (post_id, like_time, user_id) )
  WITH CACHING = 'ALL' AND
  CLUSTERING ORDER BY (like_time DESC);


DROP TABLE IF EXISTS post_likes_time;

CREATE TABLE post_likes_time (
  post_id uuid,
  user_id uuid,
  like_time bigint,
  PRIMARY KEY (post_id, user_id) )
  WITH CACHING = 'ALL';


DROP TABLE IF EXISTS post_likes_count;

CREATE TABLE post_likes_count (
  post_id uuid,
  likes_count counter,
  PRIMARY KEY (post_id) )
  WITH CACHING = 'ALL';


DROP TABLE IF EXISTS post_comments;

CREATE TABLE post_comments (
  post_id uuid,
  comment_time bigint,
  user_id uuid,
  comment varchar,
  PRIMARY KEY (post_id, comment_time, user_id) )
  WITH CACHING = 'ALL' AND
  CLUSTERING ORDER BY (comment_time DESC);


DROP TABLE IF EXISTS post_comments_time;

CREATE TABLE post_comments_time (
  post_id uuid,
  user_id uuid,
  comment_time bigint,
  PRIMARY KEY (post_id, user_id) )
  WITH CACHING = 'ALL';


DROP TABLE IF EXISTS post_comments_count;

CREATE TABLE post_comments_count (
  post_id uuid,
  comments_count counter,
  PRIMARY KEY (post_id) )
  WITH CACHING = 'ALL';


DROP TABLE IF EXISTS users_index;

CREATE TABLE users_index (
  year_month_day varchar,
  registration_time bigint,
  user_id uuid,
  PRIMARY KEY (year_month_day, registration_time, user_id) )
  WITH CACHING = 'ALL' AND
  CLUSTERING ORDER BY (registration_time ASC);


DROP TABLE IF EXISTS count_per_week;

CREATE TABLE count_per_week (
  user_id_year_week varchar,
  follower_count counter,
  unfollower_count counter,
  posts_count counter,
  likes_received_count counter,
  comments_received_count counter,
  PRIMARY KEY (user_id_year_week) )
  WITH CACHING = 'ALL';


DROP TABLE IF EXISTS count_total;

CREATE TABLE count_total (
  user_id uuid,
  likes_received_count counter,
  comments_received_count counter,
  PRIMARY KEY (user_id) )
  WITH CACHING = 'ALL';


DROP TABLE IF EXISTS users_rank_world;

CREATE TABLE users_rank_world (
  year_week varchar,
  rank double,
  user_id uuid,
  PRIMARY KEY (year_week, rank, user_id) )
  WITH CACHING = 'ALL' AND
  CLUSTERING ORDER BY (rank DESC);


DROP TABLE IF EXISTS users_rank_country;

CREATE TABLE users_rank_country (
  year_week_country_code varchar,
  rank double,
  user_id uuid,
  PRIMARY KEY (year_week_country_code, rank, user_id) )
  WITH CACHING = 'ALL' AND
  CLUSTERING ORDER BY (rank DESC);


DROP TABLE IF EXISTS users_rank_grid;

CREATE TABLE users_rank_grid (
  year_week_grid_id varchar,
  rank double,
  user_id uuid,
  PRIMARY KEY (year_week_grid_id, rank, user_id) )
  WITH CACHING = 'ALL' AND
  CLUSTERING ORDER BY (rank DESC);


DROP TABLE IF EXISTS posts_index;

CREATE TABLE posts_index (
  year_month_day_grid_id varchar,
  post_time bigint,
  post_id uuid,
  PRIMARY KEY (year_month_day_grid_id, post_time, post_id) )
  WITH CACHING = 'ALL' AND
  CLUSTERING ORDER BY (post_time ASC);


DROP TABLE IF EXISTS posts_rank_world;

CREATE TABLE posts_rank_world (
  year_month_day varchar,
  rank double,
  post_id uuid,
  PRIMARY KEY (year_month_day, rank, post_id) )
  WITH CACHING = 'ALL' AND
  CLUSTERING ORDER BY (rank DESC);


DROP TABLE IF EXISTS posts_rank_country;

CREATE TABLE posts_rank_country (
  year_month_day_country_code varchar,
  rank double,
  post_id uuid,
  PRIMARY KEY (year_month_day_country_code, rank, post_id) )
  WITH CACHING = 'ALL' AND
  CLUSTERING ORDER BY (rank DESC);


DROP TABLE IF EXISTS posts_rank_grid;

CREATE TABLE posts_rank_grid (
  year_month_day_grid_id varchar,
  rank double,
  post_id uuid,
  PRIMARY KEY (year_month_day_grid_id, rank, post_id) )
  WITH CACHING = 'ALL' AND
  CLUSTERING ORDER BY (rank DESC);


