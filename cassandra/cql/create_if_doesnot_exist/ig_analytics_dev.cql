// GENERATED using CqlScriptsGeneratorMain.java
// Keyspace: ig_analytics

CREATE KEYSPACE IF NOT EXISTS ig_analytics
  WITH replication = {
    'class': 'SimpleStrategy', 'replication_factor' : 1
};

USE ig_analytics;


CREATE TABLE IF NOT EXISTS annual_regional_counters (
  year_region varchar,
  new_users counter,
  new_posts counter,
  sent_follows counter,
  received_follows counter,
  sent_unfollows counter,
  received_unfollows counter,
  sent_likes counter,
  received_likes counter,
  sent_comments counter,
  received_comments counter,
  PRIMARY KEY (year_region) )
  WITH CACHING = 'ALL';


CREATE TABLE IF NOT EXISTS daily_regional_counters (
  year_month_day_region varchar,
  new_users counter,
  new_posts counter,
  sent_follows counter,
  received_follows counter,
  sent_unfollows counter,
  received_unfollows counter,
  sent_likes counter,
  received_likes counter,
  sent_comments counter,
  received_comments counter,
  PRIMARY KEY (year_month_day_region) )
  WITH CACHING = 'ALL';


