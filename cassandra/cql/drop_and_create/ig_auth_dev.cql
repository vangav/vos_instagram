// Keyspace: ig_auth

DROP KEYSPACE IF EXISTS ig_auth;

CREATE KEYSPACE ig_auth
  WITH replication = {
    'class': 'SimpleStrategy', 'replication_factor' : 1
};


USE ig_auth;

DROP TABLE IF EXISTS access_tokens;

CREATE TABLE access_tokens (
  user_id uuid,
  device_token varchar,
  access_token varchar,
  time_stamp bigint,
  expired boolean,
  PRIMARY KEY (user_id, device_token) )
  WITH CACHING = 'ALL';


DROP TABLE IF EXISTS auth_codes;

CREATE TABLE auth_codes (
  user_id uuid,
  device_token varchar,
  auth_code varchar,
  access_token varchar,
  refresh_token varchar,
  PRIMARY KEY (user_id, device_token) )
  WITH CACHING = 'ALL';


DROP TABLE IF EXISTS device_tokens;

CREATE TABLE device_tokens (
  device_token varchar,
  user_ids set<uuid>,
  PRIMARY KEY (device_token) )
  WITH CACHING = 'KEYS_ONLY';


DROP TABLE IF EXISTS email_creds;

CREATE TABLE email_creds (
  email varchar,
  password varchar,
  user_id uuid,
  PRIMARY KEY (email) )
  WITH CACHING = 'NONE';


DROP TABLE IF EXISTS facebook_ids;

CREATE TABLE facebook_ids (
  facebook_id varchar,
  user_id uuid,
  PRIMARY KEY (facebook_id) )
  WITH CACHING = 'NONE';


DROP TABLE IF EXISTS refresh_tokens;

CREATE TABLE refresh_tokens (
  user_id uuid,
  device_token varchar,
  refresh_token varchar,
  PRIMARY KEY (user_id, device_token) )
  WITH CACHING = 'ALL';


DROP TABLE IF EXISTS users_cred_ids;

CREATE TABLE users_cred_ids (
  user_id uuid,
  facebook_id varchar,
  email varchar,
  PRIMARY KEY (user_id) )
  WITH CACHING = 'ALL';


