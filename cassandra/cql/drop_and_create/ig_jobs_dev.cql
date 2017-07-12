// Keyspace: ig_jobs

DROP KEYSPACE IF EXISTS ig_jobs;

CREATE KEYSPACE ig_jobs
  WITH replication = {
    'class': 'SimpleStrategy', 'replication_factor' : 1
};


USE ig_jobs;

DROP TABLE IF EXISTS current_jobs;

CREATE TABLE current_jobs (
  job_id uuid,
  job_time bigint,
  job blob,
  PRIMARY KEY (job_id) )
  WITH CACHING = 'ALL';


DROP TABLE IF EXISTS hourly_current_jobs;

CREATE TABLE hourly_current_jobs (
  year_month_day_hour varchar,
  job_time bigint,
  job_id uuid,
  PRIMARY KEY (year_month_day_hour, job_time, job_id) )
  WITH CACHING = 'ALL' AND
  CLUSTERING ORDER BY (job_time ASC);


