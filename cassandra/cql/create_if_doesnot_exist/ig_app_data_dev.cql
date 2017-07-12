// GENERATED using CqlScriptsGeneratorMain.java
// Keyspace: ig_app_data

CREATE KEYSPACE IF NOT EXISTS ig_app_data
  WITH replication = {
    'class': 'SimpleStrategy', 'replication_factor' : 1
};

USE ig_app_data;


CREATE TABLE IF NOT EXISTS users_info (
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


CREATE TABLE IF NOT EXISTS following (
  user_id uuid,
  following_time bigint,
  following_user_id uuid,
  PRIMARY KEY (user_id, following_time, following_user_id) )
  WITH CACHING = 'ALL' AND
  CLUSTERING ORDER BY (following_time DESC);


CREATE TABLE IF NOT EXISTS following_time (
  user_id uuid,
  following_user_id uuid,
  following_time bigint,
  PRIMARY KEY (user_id, following_user_id) )
  WITH CACHING = 'ALL';


CREATE TABLE IF NOT EXISTS following_count (
  user_id uuid,
  following_count counter,
  PRIMARY KEY (user_id) )
  WITH CACHING = 'ALL';


CREATE TABLE IF NOT EXISTS follower (
  user_id uuid,
  follower_time bigint,
  follower_user_id uuid,
  PRIMARY KEY (user_id, follower_time, follower_user_id) )
  WITH CACHING = 'ALL' AND
  CLUSTERING ORDER BY (follower_time DESC);


CREATE TABLE IF NOT EXISTS follower_time (
  user_id uuid,
  follower_user_id uuid,
  follower_time bigint,
  PRIMARY KEY (user_id, follower_user_id) )
  WITH CACHING = 'ALL';


CREATE TABLE IF NOT EXISTS follower_count (
  user_id uuid,
  follower_count counter,
  PRIMARY KEY (user_id) )
  WITH CACHING = 'ALL';


CREATE TABLE IF NOT EXISTS user_posts (
  user_id uuid,
  post_time bigint,
  post_id uuid,
  PRIMARY KEY (user_id, post_time, post_id) )
  WITH CACHING = 'ALL' AND
  CLUSTERING ORDER BY (post_time DESC);


CREATE TABLE IF NOT EXISTS user_posts_count (
  user_id uuid,
  posts_count counter,
  PRIMARY KEY (user_id) )
  WITH CACHING = 'ALL';


CREATE TABLE IF NOT EXISTS user_feed_posts (
  user_id uuid,
  post_time bigint,
  post_id uuid,
  PRIMARY KEY (user_id, post_time, post_id) )
  WITH CACHING = 'ALL' AND
  CLUSTERING ORDER BY (post_time DESC);


CREATE TABLE IF NOT EXISTS posts (
  post_id uuid,
  post_time bigint,
  photo_id uuid,
  caption varchar,
  user_id uuid,
  latitude double,
  longitude double,
  PRIMARY KEY (post_id) )
  WITH CACHING = 'ALL';


CREATE TABLE IF NOT EXISTS post_likes (
  post_id uuid,
  like_time bigint,
  user_id uuid,
  PRIMARY KEY (post_id, like_time, user_id) )
  WITH CACHING = 'ALL' AND
  CLUSTERING ORDER BY (like_time DESC);


CREATE TABLE IF NOT EXISTS post_likes_time (
  post_id uuid,
  user_id uuid,
  like_time bigint,
  PRIMARY KEY (post_id, user_id) )
  WITH CACHING = 'ALL';


CREATE TABLE IF NOT EXISTS post_likes_count (
  post_id uuid,
  likes_count counter,
  PRIMARY KEY (post_id) )
  WITH CACHING = 'ALL';


CREATE TABLE IF NOT EXISTS post_comments (
  post_id uuid,
  comment_time bigint,
  user_id uuid,
  comment varchar,
  PRIMARY KEY (post_id, comment_time, user_id) )
  WITH CACHING = 'ALL' AND
  CLUSTERING ORDER BY (comment_time DESC);


CREATE TABLE IF NOT EXISTS post_comments_time (
  post_id uuid,
  user_id uuid,
  comment_time bigint,
  PRIMARY KEY (post_id, user_id) )
  WITH CACHING = 'ALL';


CREATE TABLE IF NOT EXISTS post_comments_count (
  post_id uuid,
  comments_count counter,
  PRIMARY KEY (post_id) )
  WITH CACHING = 'ALL';


CREATE TABLE IF NOT EXISTS users_index (
  year_month_day varchar,
  registration_time bigint,
  user_id uuid,
  PRIMARY KEY (year_month_day, registration_time, user_id) )
  WITH CACHING = 'ALL' AND
  CLUSTERING ORDER BY (registration_time ASC);


CREATE TABLE IF NOT EXISTS count_per_week (
  user_id_year_week varchar,
  follower_count counter,
  unfollower_count counter,
  posts_count counter,
  likes_received_count counter,
  comments_received_count counter,
  PRIMARY KEY (user_id_year_week) )
  WITH CACHING = 'ALL';


CREATE TABLE IF NOT EXISTS count_total (
  user_id uuid,
  likes_received_count counter,
  comments_received_count counter,
  PRIMARY KEY (user_id) )
  WITH CACHING = 'ALL';


CREATE TABLE IF NOT EXISTS users_rank_world (
  year_week varchar,
  rank double,
  user_id uuid,
  PRIMARY KEY (year_week, rank, user_id) )
  WITH CACHING = 'ALL' AND
  CLUSTERING ORDER BY (rank DESC);


CREATE TABLE IF NOT EXISTS users_rank_country (
  year_week_country_code varchar,
  rank double,
  user_id uuid,
  PRIMARY KEY (year_week_country_code, rank, user_id) )
  WITH CACHING = 'ALL' AND
  CLUSTERING ORDER BY (rank DESC);


CREATE TABLE IF NOT EXISTS users_rank_grid (
  year_week_grid_id varchar,
  rank double,
  user_id uuid,
  PRIMARY KEY (year_week_grid_id, rank, user_id) )
  WITH CACHING = 'ALL' AND
  CLUSTERING ORDER BY (rank DESC);


CREATE TABLE IF NOT EXISTS posts_index (
  year_month_day_grid_id varchar,
  post_time bigint,
  post_id uuid,
  PRIMARY KEY (year_month_day_grid_id, post_time, post_id) )
  WITH CACHING = 'ALL' AND
  CLUSTERING ORDER BY (post_time ASC);


CREATE TABLE IF NOT EXISTS posts_rank_world (
  year_month_day varchar,
  rank double,
  post_id uuid,
  PRIMARY KEY (year_month_day, rank, post_id) )
  WITH CACHING = 'ALL' AND
  CLUSTERING ORDER BY (rank DESC);


CREATE TABLE IF NOT EXISTS posts_rank_country (
  year_month_day_country_code varchar,
  rank double,
  post_id uuid,
  PRIMARY KEY (year_month_day_country_code, rank, post_id) )
  WITH CACHING = 'ALL' AND
  CLUSTERING ORDER BY (rank DESC);


CREATE TABLE IF NOT EXISTS posts_rank_grid (
  year_month_day_grid_id varchar,
  rank double,
  post_id uuid,
  PRIMARY KEY (year_month_day_grid_id, rank, post_id) )
  WITH CACHING = 'ALL' AND
  CLUSTERING ORDER BY (rank DESC);


