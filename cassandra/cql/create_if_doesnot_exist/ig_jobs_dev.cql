// GENERATED using CqlScriptsGeneratorMain.java
// Keyspace: ig_jobs

CREATE KEYSPACE IF NOT EXISTS ig_jobs
  WITH replication = {
    'class': 'SimpleStrategy', 'replication_factor' : 1
};

USE ig_jobs;


CREATE TABLE IF NOT EXISTS current_jobs (
  job_id uuid,
  job_time bigint,
  job blob,
  PRIMARY KEY (job_id) )
  WITH CACHING = 'ALL';


CREATE TABLE IF NOT EXISTS hourly_current_jobs (
  year_month_day_hour varchar,
  job_time bigint,
  job_id uuid,
  PRIMARY KEY (year_month_day_hour, job_time, job_id) )
  WITH CACHING = 'ALL' AND
  CLUSTERING ORDER BY (job_time ASC);


