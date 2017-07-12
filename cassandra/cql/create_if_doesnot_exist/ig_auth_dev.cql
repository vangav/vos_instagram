// GENERATED using CqlScriptsGeneratorMain.java
// Keyspace: ig_auth

CREATE KEYSPACE IF NOT EXISTS ig_auth
  WITH replication = {
    'class': 'SimpleStrategy', 'replication_factor' : 1
};

USE ig_auth;


CREATE TABLE IF NOT EXISTS access_tokens (
  user_id uuid,
  device_token varchar,
  access_token varchar,
  time_stamp bigint,
  expired boolean,
  PRIMARY KEY (user_id, device_token) )
  WITH CACHING = 'ALL';


CREATE TABLE IF NOT EXISTS auth_codes (
  user_id uuid,
  device_token varchar,
  auth_code varchar,
  access_token varchar,
  refresh_token varchar,
  PRIMARY KEY (user_id, device_token) )
  WITH CACHING = 'ALL';


CREATE TABLE IF NOT EXISTS device_tokens (
  device_token varchar,
  user_ids set<uuid>,
  PRIMARY KEY (device_token) )
  WITH CACHING = 'KEYS_ONLY';


CREATE TABLE IF NOT EXISTS email_creds (
  email varchar,
  password varchar,
  user_id uuid,
  PRIMARY KEY (email) )
  WITH CACHING = 'NONE';


CREATE TABLE IF NOT EXISTS facebook_ids (
  facebook_id varchar,
  user_id uuid,
  PRIMARY KEY (facebook_id) )
  WITH CACHING = 'NONE';


CREATE TABLE IF NOT EXISTS refresh_tokens (
  user_id uuid,
  device_token varchar,
  refresh_token varchar,
  PRIMARY KEY (user_id, device_token) )
  WITH CACHING = 'ALL';


CREATE TABLE IF NOT EXISTS users_cred_ids (
  user_id uuid,
  facebook_id varchar,
  email varchar,
  PRIMARY KEY (user_id) )
  WITH CACHING = 'ALL';


