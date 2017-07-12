// Keyspace: ig_blobs

DROP KEYSPACE IF EXISTS ig_blobs;

CREATE KEYSPACE ig_blobs
  WITH replication = {
    'class': 'SimpleStrategy', 'replication_factor' : 1
};


USE ig_blobs;

DROP TABLE IF EXISTS profile_pictures_blobs;

CREATE TABLE profile_pictures_blobs (
  picture_id uuid,
  picture blob,
  PRIMARY KEY (picture_id) )
  WITH CACHING = 'KEYS_ONLY';


DROP TABLE IF EXISTS photos_blobs;

CREATE TABLE photos_blobs (
  photo_id uuid,
  photo blob,
  PRIMARY KEY (photo_id) )
  WITH CACHING = 'ALL';


DROP TABLE IF EXISTS photos_thumnbnails_blobs;

CREATE TABLE photos_thumnbnails_blobs (
  photo_id uuid,
  photo_thumbnail blob,
  PRIMARY KEY (photo_id) )
  WITH CACHING = 'ALL';


