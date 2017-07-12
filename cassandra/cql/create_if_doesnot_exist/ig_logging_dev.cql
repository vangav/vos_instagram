// GENERATED using CqlScriptsGeneratorMain.java
// Keyspace: ig_logging

CREATE KEYSPACE IF NOT EXISTS ig_logging
  WITH replication = {
    'class': 'SimpleStrategy', 'replication_factor' : 1
};

USE ig_logging;


CREATE TABLE IF NOT EXISTS daily_requests_counters (
  year_month_day_controller varchar,
  requests counter,
  ok_responses counter,
  bad_request_responses counter,
  internal_error_responses counter,
  run_time_milli_seconds counter,
  PRIMARY KEY (year_month_day_controller) )
  WITH CACHING = 'ALL';


CREATE TABLE IF NOT EXISTS hourly_requests_counters (
  year_month_day_hour_controller varchar,
  requests counter,
  ok_responses counter,
  bad_request_responses counter,
  internal_error_responses counter,
  run_time_milli_seconds counter,
  PRIMARY KEY (year_month_day_hour_controller) )
  WITH CACHING = 'ALL';


CREATE TABLE IF NOT EXISTS error_logs (
  log_id uuid,
  log_time bigint,
  controller_name varchar,
  user_id uuid,
  http_status_code int,
  request varchar,
  error_response varchar,
  PRIMARY KEY (log_id) )
  WITH CACHING = 'ALL';


CREATE TABLE IF NOT EXISTS hourly_controllers_error_logs (
  year_month_day_hour_controller varchar,
  log_time bigint,
  log_id uuid,
  PRIMARY KEY (year_month_day_hour_controller, log_time, log_id) )
  WITH CACHING = 'ALL' AND
  CLUSTERING ORDER BY (log_time ASC);


CREATE TABLE IF NOT EXISTS daily_users_error_logs (
  year_month_day_user_id varchar,
  log_time bigint,
  log_id uuid,
  PRIMARY KEY (year_month_day_user_id, log_time, log_id) )
  WITH CACHING = 'ALL' AND
  CLUSTERING ORDER BY (log_time ASC);


CREATE TABLE IF NOT EXISTS daily_users_logs (
  year_month_day_user_id varchar,
  log_time bigint,
  request_id uuid,
  controller_name varchar,
  request varchar,
  response_status_code varchar,
  response varchar,
  run_time_milli_seconds int,
  PRIMARY KEY (year_month_day_user_id, log_time, request_id) )
  WITH CACHING = 'ALL' AND
  CLUSTERING ORDER BY (log_time ASC);


