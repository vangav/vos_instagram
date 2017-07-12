// GENERATED using CqlScriptsGeneratorMain.java
// Keyspace: ig_blobs

CREATE KEYSPACE IF NOT EXISTS ig_blobs
  WITH replication = {
    'class': 'SimpleStrategy', 'replication_factor' : 1
};

USE ig_blobs;


CREATE TABLE IF NOT EXISTS profile_pictures_blobs (
  picture_id uuid,
  picture blob,
  PRIMARY KEY (picture_id) )
  WITH CACHING = 'KEYS_ONLY';


CREATE TABLE IF NOT EXISTS photos_blobs (
  photo_id uuid,
  photo blob,
  PRIMARY KEY (photo_id) )
  WITH CACHING = 'ALL';


CREATE TABLE IF NOT EXISTS photos_thumnbnails_blobs (
  photo_id uuid,
  photo_thumbnail blob,
  PRIMARY KEY (photo_id) )
  WITH CACHING = 'ALL';


