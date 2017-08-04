
> **why?** instagram is the biggest [vangav backend](https://github.com/vangav/vos_backend) template designed for big scale and covering all the core features/utilities: service oriented architecture, worker service, multi-entry-point api, multi-keysapce database, oauth 2, facebook authentication, facebook graph api, rest jobs, periodic jobs, vangav mighty, push notifications, logging, analytics, client generator, test service, bots service, ...

# instagram

+ [instagram](https://github.com/vangav/vos_instagram), [instagram dispense](https://github.com/vangav/vos_instagram_dispense), [instagram jobs](https://github.com/vangav/vos_instagram_jobs), [instagram worker](https://github.com/vangav/vos_instagram_worker) and [instagram dash board](https://github.com/vangav/vos_instagram_dash_board) services work together and are generated using [vangav backend](https://github.com/vangav/vos_backend)

+ [instagram test](https://github.com/vangav/vos_instagram_test) is used to test the services above and [instagram bots](https://github.com/vangav/vos_instagram_bots) is used to simulate normal users' behavior; both services are generated using [vangav backend](https://github.com/vangav/vos_backend)

## prerequisite

+ [vangav backend tutorials](https://github.com/vangav/vos_backend)

## functionality

### [instagram](https://github.com/vangav/vos_instagram)

+ signup/login using email/facebook
+ request authentication using oauth 2
+ fetching/updating users' information
+ follow/unfollow and get followers/following lists for users
+ posting photos
+ get posts by feed and by user
+ interacting with posts (like, comment, ...)
+ get top users/posts
+ searching for users
+ get server time

### [instagram dispense](https://github.com/vangav/vos_instagram_dispense)

+ is a helper service for [instagram](https://github.com/vangav/vos_instagram); responsible for distributing posted photos to followers (because on photo can be posted to tens of millions of followers; such an expensive operation should be distributable and shouldn't overload the main service)

### [instagram jobs](https://github.com/vangav/vos_instagram_jobs)

+ crawls for failed jobs in the database to retry executing them (e.g.: a dispense job, ...)
+ uses [vangav mighty](http://vangav.com/) to update top users (per geo grid, per country and world wide) every week
+ uses [vangav mighty](http://vangav.com/) to update top posts (per geo grid, per country and world wide) every day

### [instagram worker](https://github.com/vangav/vos_instagram_worker)

+ writes logs and analytics in the database
+ sends push notifications (ios and android)

### [instagram dash board](https://github.com/vangav/vos_instagram_dash_board)

+ handles fetching logs and analytics for system admin

### [instagram test](https://github.com/vangav/vos_instagram_test)

+ has a full-coverage test for all the services above

### [instagram bots](https://github.com/vangav/vos_instagram_bots)

+ simulates incremental usage behavior from many cities around the world with some celebrity users

## overview

+ this service is based on vangav backend's [instagram template](https://github.com/vangav/vos_backend/tree/master/vangav_backend_templates/instagram)
+ this service has the 90+% of the vangav backend's generated code + the 10-% of the logic code needed to complete the service

## try this service

1. *for first timers* - follow the steps in the [system requirements tutorial](https://github.com/vangav/vos_backend#system-requirements)
2. *for first timers* - follow the steps in the [workspace initialization tutorial](https://github.com/vangav/vos_backend#init)
3. download [instagram](https://github.com/vangav/vos_instagram), [instagram dispense](https://github.com/vangav/vos_instagram_dispense), [instagram jobs](https://github.com/vangav/vos_instagram_jobs), [instagram worker](https://github.com/vangav/vos_instagram_worker) and [instagram dash board](https://github.com/vangav/vos_instagram_dash_board) projects (from the green `clone or download` button) inside the workspace directory created previously (`my_services`) and unzip them
4. **rename** unzipped directories, remove the `-master` from their names
5. in the terminal `cd` to `vos_instagram/cassandra/cql/`
6. execute `./_start_cassandra.sh` to start cassandra
7. `cd` to `vos_instagram/cassandra/cql/drop_and_create/`
8. execute the commands `./_execute_cql.sh ig_....cql` to initialize the services' database tables; repeat this step for all the [`.cql` files](https://github.com/vangav/vos_instagram/tree/master/cassandra/cql/drop_and_create)
9. `cd` to `vos_instagram_worker` and execute `./_run.sh` to start the instagram worker service on port 8000
10. `cd` to `vos_instagram_dispense` and execute `./_run.sh 3000` to start the instagram dispense service on port 3000
11. `cd` to `vos_instagram_jobs` and execute `./_run.sh 4000` to start the instagram jobs service on port 4000
12. `cd` to `vos_instagram_dash_board` and execute `./_run.sh 5000` to start the instagram dash board service on port 5000
13. `cd` to `vos_instagram` and execute `./_run.sh` to start the instagram service on port 9000
14. from your prefered client (*we recommned* [postman](https://www.getpostman.com/docs/postman/launching_postman/installation_and_updates)) start trying the service; refer to the **features** and **service references** sections for reference
+ at the end to stop the services: press `control + d` in the terminal session where each service was started in (9, 10, 11, 12 and 13)
+ to stop cassandra: execute `ps auwx | grep cassandra` to get cassandra's `(pid)` then `kill -9 (pid)` to stop cassandra

## covered topics

+ generate multiple services (main + dispense + jobs + worker + dash board) to work together in a service oriented architecture
+ generate a multi-keyspace database
+ oauth 2 and facebook authentication
+ facebook graph api
+ rest jobs
+ periodic jobs
+ vangav mighty
+ push notifications
+ logging
+ analytics
+ client generator
+ test and bots services

## features

### [instagram](https://github.com/vangav/vos_instagram)

| controller(s) | feature |
| ------------- | ------- |
| [signup email](https://github.com/vangav/vos_instagram/tree/master/app/com/vangav/vos_instagram/controllers/signup_email), [login email](https://github.com/vangav/vos_instagram/tree/master/app/com/vangav/vos_instagram/controllers/login_email), [login facebook](https://github.com/vangav/vos_instagram/tree/master/app/com/vangav/vos_instagram/controllers/login_facebook), [get access token](https://github.com/vangav/vos_instagram/tree/master/app/com/vangav/vos_instagram/controllers/get_access_token), [refresh access token](https://github.com/vangav/vos_instagram/tree/master/app/com/vangav/vos_instagram/controllers/refresh_access_token) and [logout](https://github.com/vangav/vos_instagram/tree/master/app/com/vangav/vos_instagram/controllers/logout) | handle user signup, loging and requests' authentication |
| [update facebook info](https://github.com/vangav/vos_instagram/tree/master/app/com/vangav/vos_instagram/controllers/update_facebook_info), [update last active time](https://github.com/vangav/vos_instagram/tree/master/app/com/vangav/vos_instagram/controllers/update_last_active_time), [update location](https://github.com/vangav/vos_instagram/tree/master/app/com/vangav/vos_instagram/controllers/update_location), [update profile picture](https://github.com/vangav/vos_instagram/tree/master/app/com/vangav/vos_instagram/controllers/update_profile_picture), [get user info](https://github.com/vangav/vos_instagram/tree/master/app/com/vangav/vos_instagram/controllers/get_user_info) and [get profile picture](https://github.com/vangav/vos_instagram/tree/master/app/com/vangav/vos_instagram/controllers/get_profile_picture) | handle setting/updating users' info and getting it |
| [follow](https://github.com/vangav/vos_instagram/tree/master/app/com/vangav/vos_instagram/controllers/follow), [unfollow](https://github.com/vangav/vos_instagram/tree/master/app/com/vangav/vos_instagram/controllers/unfollow), [get followers](https://github.com/vangav/vos_instagram/tree/master/app/com/vangav/vos_instagram/controllers/get_followers) and [get following](https://github.com/vangav/vos_instagram/tree/master/app/com/vangav/vos_instagram/controllers/get_following) | handle building the social graph and exploring it |
| [post photo](https://github.com/vangav/vos_instagram/tree/master/app/com/vangav/vos_instagram/controllers/post_photo) | handles posting a new photo to a user's followers |
| [get feed posts](https://github.com/vangav/vos_instagram/tree/master/app/com/vangav/vos_instagram/controllers/get_feed_posts), [get user posts](https://github.com/vangav/vos_instagram/tree/master/app/com/vangav/vos_instagram/controllers/get_user_posts), [get post info](https://github.com/vangav/vos_instagram/tree/master/app/com/vangav/vos_instagram/controllers/get_post_info), [get post photo id](https://github.com/vangav/vos_instagram/tree/master/app/com/vangav/vos_instagram/controllers/get_post_photo_id), [get photo](https://github.com/vangav/vos_instagram/tree/master/app/com/vangav/vos_instagram/controllers/get_photo), [get photo thumbnail](https://github.com/vangav/vos_instagram/tree/master/app/com/vangav/vos_instagram/controllers/get_photo_thumbnail), [get post likes](https://github.com/vangav/vos_instagram/tree/master/app/com/vangav/vos_instagram/controllers/get_post_likes) and [get post comments](https://github.com/vangav/vos_instagram/tree/master/app/com/vangav/vos_instagram/controllers/get_post_comments) | handle getting feed/users' posts and posts' info/content/likes/comments |
| [like post](https://github.com/vangav/vos_instagram/tree/master/app/com/vangav/vos_instagram/controllers/like_post), [unlike post](https://github.com/vangav/vos_instagram/tree/master/app/com/vangav/vos_instagram/controllers/unlike_post), [comment](https://github.com/vangav/vos_instagram/tree/master/app/com/vangav/vos_instagram/controllers/comment) and [delete comment](https://github.com/vangav/vos_instagram/tree/master/app/com/vangav/vos_instagram/controllers/delete_comment) | handle interacting with posts |
| [get top users](https://github.com/vangav/vos_instagram/tree/master/app/com/vangav/vos_instagram/controllers/get_top_users) and [get top posts](https://github.com/vangav/vos_instagram/tree/master/app/com/vangav/vos_instagram/controllers/get_top_posts) | handle exploring top users/posts |
| [search by email](https://github.com/vangav/vos_instagram/tree/master/app/com/vangav/vos_instagram/controllers/search_by_email) | handles searching for users by their e-mail |
| [get server time](https://github.com/vangav/vos_instagram/tree/master/app/com/vangav/vos_instagram/controllers/get_server_time) | getting server's time is important for various reasons like enabling the client to set how old a post is (2h, 1w, ...) |

### [instagram dispense](https://github.com/vangav/vos_instagram_dispense)

| controller | feature |
| ------------- | ------- |
| [post photo to followers](https://github.com/vangav/vos_instagram_dispense/tree/master/app/com/vangav/vos_instagram_dispense/controllers/post_photo_to_followers) | handles distributing new posts to followers; used by [instagram](https://github.com/vangav/vos_instagram): [post photo](https://github.com/vangav/vos_instagram/tree/master/app/com/vangav/vos_instagram/controllers/post_photo) |

### [instagram jobs](https://github.com/vangav/vos_instagram_jobs)

| controller | feature |
| ------------- | ------- |
| [start jobs](https://github.com/vangav/vos_instagram_jobs/tree/master/app/com/vangav/vos_instagram_jobs/controllers/start_jobs) | stars the periodic job for retrying failed rest jobs and the periodic jobs for ranking users and posts |

### [instagram dash board](https://github.com/vangav/vos_instagram_dash_board)

| controller(s) | feature |
| ------------- | ------- |
| [get daily users error logs](https://github.com/vangav/vos_instagram_dash_board/tree/master/app/com/vangav/vos_instagram_dash_board/controllers/get_daily_users_error_logs), [get daily users logs](https://github.com/vangav/vos_instagram_dash_board/tree/master/app/com/vangav/vos_instagram_dash_board/controllers/get_daily_users_logs) and [get hourly controllers error logs](https://github.com/vangav/vos_instagram_dash_board/tree/master/app/com/vangav/vos_instagram_dash_board/controllers/get_hourly_controllers_error_logs) | handle getting logs per user/controller |
| [get annual regional counters](https://github.com/vangav/vos_instagram_dash_board/tree/master/app/com/vangav/vos_instagram_dash_board/controllers/get_annual_regional_counters), [get daily regional counters](https://github.com/vangav/vos_instagram_dash_board/tree/master/app/com/vangav/vos_instagram_dash_board/controllers/get_daily_regional_counters), [get daily requests counters](https://github.com/vangav/vos_instagram_dash_board/tree/master/app/com/vangav/vos_instagram_dash_board/controllers/get_daily_requests_counters) and [get hourly requests counters](https://github.com/vangav/vos_instagram_dash_board/tree/master/app/com/vangav/vos_instagram_dash_board/controllers/get_hourly_requests_counters) | handle getting backend-wide analytics |
| [get top users](https://github.com/vangav/vos_instagram_dash_board/tree/master/app/com/vangav/vos_instagram_dash_board/controllers/get_top_users) and [get top posts](https://github.com/vangav/vos_instagram_dash_board/tree/master/app/com/vangav/vos_instagram_dash_board/controllers/get_top_posts) | gets in-depth information about top users/posts on specified dates/locations |

### [instagram test](https://github.com/vangav/vos_instagram_test)

| controller | feature |
| ------------- | ------- |
| [test all controllers](https://github.com/vangav/vos_instagram_test/tree/master/app/com/vangav/vos_instagram_test/controllers/test_all_controllers) | runs a full-coverage test for the instagram services above and returns a full report (success, failure, request-to-response time, ...) |

### [instagram bots](https://github.com/vangav/vos_instagram_bots)

| controller | feature |
| ------------- | ------- |
| [start bots](https://github.com/vangav/vos_instagram_bots/tree/master/app/com/vangav/vos_instagram_bots/controllers/start_bots) | starts simulating incremental usage behavior from many cities around the world with some celebrity users |

## service references

### [instagram](https://github.com/vangav/vos_instagram)

| reference | explanation |
| --------- | ----------- |
| [routes](https://github.com/vangav/vos_instagram/blob/master/conf/routes) | api routes |
| [prop](https://github.com/vangav/vos_instagram/tree/master/conf/prop) | in addition to vangav backend's properties files, this directory contains instagram properties files: [`constants`](https://github.com/vangav/vos_instagram/blob/master/conf/prop/constants_properties.prop) and [`dispense`](https://github.com/vangav/vos_instagram/blob/master/conf/prop/dispense_properties.prop) |
| [reverse_geo_coding](https://github.com/vangav/vos_instagram/tree/master/conf/data/geo/reverse_geo_coding) | reverse geo coding data |
| [controllers.json](https://github.com/vangav/vos_instagram/blob/master/generator_config/controllers.json) | api request/response's elements |
| [ig_auth.keyspace](https://github.com/vangav/vos_instagram/blob/master/generator_config/ig_auth.keyspace) | `ig_auth` is the keyspace used for all authentication-related tables |
| [ig_app_data.keyspace](https://github.com/vangav/vos_instagram/blob/master/generator_config/ig_app_data.keyspace) | `ig_app_data` is the keyspace used for all user-info-related tables which are directly relevant to the app's functionality (e.g.: posts, followers, ...) |
| [ig_blobs.keyspace](https://github.com/vangav/vos_instagram/blob/master/generator_config/ig_blobs.keyspace) | `ig_blobs` is the keyspace used for all blob-related tables (photos, thumbnails, ...) |
| [ig_jobs.keyspace](https://github.com/vangav/vos_instagram/blob/master/generator_config/ig_jobs.keyspace) | `ig_jobs` is the keyspace used for all jobs-related tables -- a job is an operation that's either executed at a later time or issued by one service and executed by another where a jobs table keeps track of unsuccessful jobs which need to be reexecuted |
| [ig_logging.keyspace](https://github.com/vangav/vos_instagram/blob/master/generator_config/ig_logging.keyspace) | `ig_logging` is the keyspace used for all logging-related tables |
| [ig_analytics.keyspace](https://github.com/vangav/vos_instagram/blob/master/generator_config/ig_analytics.keyspace) | `ig_analytics` is the keyspace used for all analytics-related tables |
| [Global.java](https://github.com/vangav/vos_instagram/blob/master/app/Global.java) | initializes reverse geo coding data |
| [common](https://github.com/vangav/vos_instagram/tree/master/app/com/vangav/vos_instagram/common) | handles common operations like authentication, cassandra's frequent-operations, omitting blobs from requests/responses for logging, ... |
| [controllers](https://github.com/vangav/vos_instagram/tree/master/app/com/vangav/vos_instagram/controllers) | api implementation |
| [ig_auth](https://github.com/vangav/vos_instagram/tree/master/app/com/vangav/vos_instagram/cassandra_keyspaces/ig_auth) | `ig_auth` cassandra's keyspace client |
| [ig_app_data](https://github.com/vangav/vos_instagram/tree/master/app/com/vangav/vos_instagram/cassandra_keyspaces/ig_app_data) | `ig_app_data` cassandra's keyspace client |
| [ig_blobs](https://github.com/vangav/vos_instagram/tree/master/app/com/vangav/vos_instagram/cassandra_keyspaces/ig_blobs) | `ig_blobs` cassandra's keyspace client |
| [ig_jobs](https://github.com/vangav/vos_instagram/tree/master/app/com/vangav/vos_instagram/cassandra_keyspaces/ig_jobs) | `ig_jobs` cassandra's keyspace client |
| [ig_logging](https://github.com/vangav/vos_instagram/tree/master/app/com/vangav/vos_instagram/cassandra_keyspaces/ig_logging) | `ig_logging` cassandra's keyspace client |
| [ig_analytics](https://github.com/vangav/vos_instagram/tree/master/app/com/vangav/vos_instagram/cassandra_keyspaces/ig_analytics) | `ig_analytics` cassandra's keyspace client |

### [instagram dispense](https://github.com/vangav/vos_instagram_dispense)

| reference | explanation |
| --------- | ----------- |
| [routes](https://github.com/vangav/vos_instagram_dispense/blob/master/conf/routes) | api routes |
| [controllers.json](https://github.com/vangav/vos_instagram_dispense/blob/master/generator_config/controllers.json) | api request/response's elements |
| [ig_auth.keyspace](https://github.com/vangav/vos_instagram_dispense/blob/master/generator_config/ig_auth.keyspace) | `ig_auth` is the keyspace used for all authentication-related tables |
| [ig_app_data.keyspace](https://github.com/vangav/vos_instagram_dispense/blob/master/generator_config/ig_app_data.keyspace) | `ig_app_data` is the keyspace used for all user-info-related tables which are directly relevant to the app's functionality (e.g.: posts, followers, ...) |
| [ig_jobs.keyspace](https://github.com/vangav/vos_instagram_dispense/blob/master/generator_config/ig_jobs.keyspace) | `ig_jobs` is the keyspace used for all jobs-related tables -- a job is an operation that's either executed at a later time or issued by one service and executed by another where a jobs table keeps track of unsuccessful jobs which need to be reexecuted |
| [ig_logging.keyspace](https://github.com/vangav/vos_instagram_dispense/blob/master/generator_config/ig_logging.keyspace) | `ig_logging` is the keyspace used for all logging-related tables |
| [ig_analytics.keyspace](https://github.com/vangav/vos_instagram_dispense/blob/master/generator_config/ig_analytics.keyspace) | `ig_analytics` is the keyspace used for all analytics-related tables |
| [controllers](https://github.com/vangav/vos_instagram_dispense/tree/master/app/com/vangav/vos_instagram_dispense/controllers) | api implementation |
| [ig_auth](https://github.com/vangav/vos_instagram_dispense/tree/master/app/com/vangav/vos_instagram_dispense/cassandra_keyspaces/ig_auth) | `ig_auth` cassandra's keyspace client |
| [ig_app_data](https://github.com/vangav/vos_instagram_dispense/tree/master/app/com/vangav/vos_instagram_dispense/cassandra_keyspaces/ig_app_data) | `ig_app_data` cassandra's keyspace client |
| [ig_jobs](https://github.com/vangav/vos_instagram_dispense/tree/master/app/com/vangav/vos_instagram_dispense/cassandra_keyspaces/ig_jobs) | `ig_jobs` cassandra's keyspace client |
| [ig_logging](https://github.com/vangav/vos_instagram_dispense/tree/master/app/com/vangav/vos_instagram_dispense/cassandra_keyspaces/ig_logging) | `ig_logging` cassandra's keyspace client |
| [ig_analytics](https://github.com/vangav/vos_instagram_dispense/tree/master/app/com/vangav/vos_instagram_dispense/cassandra_keyspaces/ig_analytics) | `ig_analytics` cassandra's keyspace client |

### [instagram jobs](https://github.com/vangav/vos_instagram_jobs)

| reference | explanation |
| --------- | ----------- |
| [routes](https://github.com/vangav/vos_instagram_jobs/blob/master/conf/routes) | api routes |
| [prop](https://github.com/vangav/vos_instagram_jobs/tree/master/conf/prop) | in addition to vangav backend's properties files, this directory contains instagram jobs properties files: [`constants`](https://github.com/vangav/vos_instagram_jobs/blob/master/conf/prop/constants_properties.prop), [`users rank`](https://github.com/vangav/vos_instagram_jobs/blob/master/conf/prop/users_rank_properties.prop) and [`posts rank`](https://github.com/vangav/vos_instagram_jobs/blob/master/conf/prop/posts_rank_properties.prop) |
| [reverse_geo_coding](https://github.com/vangav/vos_instagram_jobs/tree/master/conf/data/geo/reverse_geo_coding) | reverse geo coding data |
| [controllers.json](https://github.com/vangav/vos_instagram_jobs/blob/master/generator_config/controllers.json) | api request/response's elements |
| [ig_app_data.keyspace](https://github.com/vangav/vos_instagram_jobs/blob/master/generator_config/ig_app_data.keyspace) | `ig_app_data` is the keyspace used for all user-info-related tables which are directly relevant to the app's functionality (e.g.: posts, followers, ...) |
| [ig_jobs.keyspace](https://github.com/vangav/vos_instagram_jobs/blob/master/generator_config/ig_jobs.keyspace) | `ig_jobs` is the keyspace used for all jobs-related tables -- a job is an operation that's either executed at a later time or issued by one service and executed by another where a jobs table keeps track of unsuccessful jobs which need to be reexecuted |
| [ig_logging.keyspace](https://github.com/vangav/vos_instagram_jobs/blob/master/generator_config/ig_logging.keyspace) | `ig_logging` is the keyspace used for all logging-related tables |
| [Global.java](https://github.com/vangav/vos_instagram_jobs/blob/master/app/Global.java) | initializes reverse geo coding data and periodic jobs |
| [common](https://github.com/vangav/vos_instagram_jobs/tree/master/app/com/vangav/vos_instagram_jobs/common) | handles common operations like instagram-jobs-service's properties |
| [periodic_jobs](https://github.com/vangav/vos_instagram_jobs/tree/master/app/com/vangav/vos_instagram_jobs/periodic_jobs) | handles retrying failed jobs as well as ranking users and posts |
| [controllers](https://github.com/vangav/vos_instagram_jobs/tree/master/app/com/vangav/vos_instagram_jobs/controllers) | api implementation |
| [ig_app_data](https://github.com/vangav/vos_instagram_jobs/tree/master/app/com/vangav/vos_instagram_jobs/cassandra_keyspaces/ig_app_data) | `ig_app_data` cassandra's keyspace client |
| [ig_jobs](https://github.com/vangav/vos_instagram_jobs/tree/master/app/com/vangav/vos_instagram_jobs/cassandra_keyspaces/ig_jobs) | `ig_jobs` cassandra's keyspace client |
| [ig_logging](https://github.com/vangav/vos_instagram_jobs/tree/master/app/com/vangav/vos_instagram_jobs/cassandra_keyspaces/ig_logging) | `ig_logging` cassandra's keyspace client |

### [instagram dash board](https://github.com/vangav/vos_instagram_dash_board)

| reference | explanation |
| --------- | ----------- |
| [routes](https://github.com/vangav/vos_instagram_dash_board/blob/master/conf/routes) | api routes |
| [controllers.json](https://github.com/vangav/vos_instagram_dash_board/blob/master/generator_config/controllers.json) | api request/response's elements |
| [ig_auth.keyspace](https://github.com/vangav/vos_instagram_dash_board/blob/master/generator_config/ig_auth.keyspace) | `ig_auth` is the keyspace used for all authentication-related tables |
| [ig_app_data.keyspace](https://github.com/vangav/vos_instagram_dash_board/blob/master/generator_config/ig_app_data.keyspace) | `ig_app_data` is the keyspace used for all user-info-related tables which are directly relevant to the app's functionality (e.g.: posts, followers, ...) |
| [ig_logging.keyspace](https://github.com/vangav/vos_instagram_dash_board/blob/master/generator_config/ig_logging.keyspace) | `ig_logging` is the keyspace used for all logging-related tables |
| [ig_analytics.keyspace](https://github.com/vangav/vos_instagram_dash_board/blob/master/generator_config/ig_analytics.keyspace) | `ig_analytics` is the keyspace used for all analytics-related tables |
| [common](https://github.com/vangav/vos_instagram_dash_board/tree/master/app/com/vangav/vos_instagram_dash_board/common) | handles common operations like service-wide constants |
| [controllers](https://github.com/vangav/vos_instagram_dash_board/tree/master/app/com/vangav/vos_instagram_dash_board/controllers) | api implementation |
| [ig_auth](https://github.com/vangav/vos_instagram_dash_board/tree/master/app/com/vangav/vos_instagram_dash_board/cassandra_keyspaces/ig_auth) | `ig_auth` cassandra's keyspace client |
| [ig_app_data](https://github.com/vangav/vos_instagram_dash_board/tree/master/app/com/vangav/vos_instagram_dash_board/cassandra_keyspaces/ig_app_data) | `ig_app_data` cassandra's keyspace client |
| [ig_logging](https://github.com/vangav/vos_instagram_dash_board/tree/master/app/com/vangav/vos_instagram_dash_board/cassandra_keyspaces/ig_logging) | `ig_logging` cassandra's keyspace client |
| [ig_analytics](https://github.com/vangav/vos_instagram_dash_board/tree/master/app/com/vangav/vos_instagram_dash_board/cassandra_keyspaces/ig_analytics) | `ig_analytics` cassandra's keyspace client |

### [instagram test](https://github.com/vangav/vos_instagram_test)

| reference | explanation |
| --------- | ----------- |
| [routes](https://github.com/vangav/vos_instagram_test/blob/master/conf/routes) | api routes |
| [prop](https://github.com/vangav/vos_instagram_test/tree/master/conf/prop) | in addition to vangav backend's properties files, this directory contains the clients properties file [`clients`](https://github.com/vangav/vos_instagram_test/blob/master/conf/prop/clients_properties.prop) |
| [controllers.json](https://github.com/vangav/vos_instagram_test/blob/master/generator_config/controllers.json) | api request/response's elements |
| [vos_instagram.client_java](https://github.com/vangav/vos_instagram_test/blob/master/generator_config/vos_instagram.client_java) | instagram client's api request/response's elements |
| [vos_instagram_dash_board.client_java](https://github.com/vangav/vos_instagram_test/blob/master/generator_config/vos_instagram_dash_board.client_java) | instagram dash board client's api request/response's elements |
| [common](https://github.com/vangav/vos_instagram_test/tree/master/app/com/vangav/vos_instagram_test/common) | handles common operations like representing instagram users |
| [clients](https://github.com/vangav/vos_instagram_test/tree/master/app/com/vangav/vos_instagram_test/clients) | contains the generated clients for instagram and instagram dash board services |
| [controllers](https://github.com/vangav/vos_instagram_test/tree/master/app/com/vangav/vos_instagram_test/controllers) | api implementation |

### [instagram bots](https://github.com/vangav/vos_instagram_bots)

| reference | explanation |
| --------- | ----------- |
| [routes](https://github.com/vangav/vos_instagram_bots/blob/master/conf/routes) | api routes |
| [setup_data](https://github.com/vangav/vos_instagram_bots/tree/master/conf/setup_data) | contains a one-time setup data with all the cities (id, location, population, ...) where the bots are created |
| [prop](https://github.com/vangav/vos_instagram_bots/tree/master/conf/prop) | in addition to vangav backend's properties files, this directory contains the clients properties file [`clients`](https://github.com/vangav/vos_instagram_bots/blob/master/conf/prop/clients_properties.prop) |
| [controllers.json](https://github.com/vangav/vos_instagram_bots/blob/master/generator_config/controllers.json) | api request/response's elements |
| [vos_instagram.client_java](https://github.com/vangav/vos_instagram_bots/blob/master/generator_config/vos_instagram.client_java) | instagram client's api request/response's elements |
| [ig_bots.keyspace](https://github.com/vangav/vos_instagram_bots/blob/master/generator_config/ig_bots.keyspace) | `ig_bots` is the keyspace used for all bots-related vos_instagream tables |
| [Global.java](https://github.com/vangav/vos_instagram_bots/blob/master/app/Global.java) | initializes cities and bots |
| [cities](https://github.com/vangav/vos_instagram_bots/tree/master/app/com/vangav/vos_instagram_bots/cities) | has the cities' json representation and loader |
| [bots](https://github.com/vangav/vos_instagram_bots/tree/master/app/com/vangav/vos_instagram_bots/bots) | has the bots' periodic jobs and the bots creator |
| [clients](https://github.com/vangav/vos_instagram_bots/tree/master/app/com/vangav/vos_instagram_bots/clients) | contains the generated clients for instagram service |
| [controllers](https://github.com/vangav/vos_instagram_bots/tree/master/app/com/vangav/vos_instagram_bots/controllers) | api implementation |
| [ig_bots](https://github.com/vangav/vos_instagram_bots/tree/master/app/com/vangav/vos_instagram_bots/cassandra_keyspaces/ig_bots) | `ig_bots` cassandra's keyspace client |

## change log

+ this section lists the 10-% code added after vangav backend generated 90+% of the code

### [instagram](https://github.com/vangav/vos_instagram)

| file/dir | change |
| -------- | ------ |
| [prop](https://github.com/vangav/vos_instagram/tree/master/conf/prop) | added instagram properties files: [`constants`](https://github.com/vangav/vos_instagram/blob/master/conf/prop/constants_properties.prop) and [`dispense`](https://github.com/vangav/vos_instagram/blob/master/conf/prop/dispense_properties.prop) |
| [reverse_geo_coding](https://github.com/vangav/vos_instagram/tree/master/conf/data/geo/reverse_geo_coding) | added reverse geo coding data |
| [Global.java](https://github.com/vangav/vos_instagram/blob/master/app/Global.java) | added the initialization of reverse geo coding data |
| [common](https://github.com/vangav/vos_instagram/tree/master/app/com/vangav/vos_instagram/common) | added common operations like authentication, cassandra's frequent-operations, omitting blobs from requests/responses for logging, ... |
| [controllers](https://github.com/vangav/vos_instagram/tree/master/app/com/vangav/vos_instagram/controllers) | added the implementation of request processing logic under `controller_name/HandlerControllerName.java` classes and nested response json structures under `controller_name/response_json` packages |

### [instagram dispense](https://github.com/vangav/vos_instagram_dispense)

| file/dir | change |
| -------- | ------ |
| [controllers](https://github.com/vangav/vos_instagram_dispense/tree/master/app/com/vangav/vos_instagram_dispense/controllers) | added the implementation of request processing logic under `controller_name/HandlerControllerName.java` classes and nested response json structures under `controller_name/response_json` packages |

### [instagram jobs](https://github.com/vangav/vos_instagram_jobs)

| file/dir | change |
| -------- | ------ |
| [prop](https://github.com/vangav/vos_instagram_jobs/tree/master/conf/prop) | added instagram jobs properties files: [`constants`](https://github.com/vangav/vos_instagram_jobs/blob/master/conf/prop/constants_properties.prop), [`users rank`](https://github.com/vangav/vos_instagram_jobs/blob/master/conf/prop/users_rank_properties.prop) and [`posts rank`](https://github.com/vangav/vos_instagram_jobs/blob/master/conf/prop/posts_rank_properties.prop) |
| [reverse_geo_coding](https://github.com/vangav/vos_instagram_jobs/tree/master/conf/data/geo/reverse_geo_coding) | added reverse geo coding data |
| [Global.java](https://github.com/vangav/vos_instagram_jobs/blob/master/app/Global.java) | added the initialization of reverse geo coding data and periodic jobs |
| [common](https://github.com/vangav/vos_instagram_jobs/tree/master/app/com/vangav/vos_instagram_jobs/common) | added common operations like instagram-jobs-service's properties |
| [periodic_jobs](https://github.com/vangav/vos_instagram_jobs/tree/master/app/com/vangav/vos_instagram_jobs/periodic_jobs) | added the periodic jobs for retrying failed jobs as well as ranking users and posts |
| [controllers](https://github.com/vangav/vos_instagram_jobs/tree/master/app/com/vangav/vos_instagram_jobs/controllers) | added the implementation of request processing logic under `controller_name/HandlerControllerName.java` classes and nested response json structures under `controller_name/response_json` packages |

### [instagram dash board](https://github.com/vangav/vos_instagram_dash_board)

| file/dir | change |
| -------- | ------ |
| [common](https://github.com/vangav/vos_instagram_dash_board/tree/master/app/com/vangav/vos_instagram_dash_board/common) | added common operations like service-wide constants |
| [controllers](https://github.com/vangav/vos_instagram_dash_board/tree/master/app/com/vangav/vos_instagram_dash_board/controllers) | added the implementation of request processing logic under `controller_name/HandlerControllerName.java` classes and nested response json structures under `controller_name/response_json` packages |

### [instagram test](https://github.com/vangav/vos_instagram_test)

| file/dir | change |
| -------- | ------ |
| [common](https://github.com/vangav/vos_instagram_test/tree/master/app/com/vangav/vos_instagram_test/common) | added common operations like representing instagram users |
| [controllers](https://github.com/vangav/vos_instagram_test/tree/master/app/com/vangav/vos_instagram_test/controllers) | added the implementation of request processing logic under `controller_name/HandlerControllerName.java` classes and nested response json structures under `controller_name/response_json` packages |

### [instagram bots](https://github.com/vangav/vos_instagram_bots)

| file/dir | change |
| -------- | ------ |
| [setup_data](https://github.com/vangav/vos_instagram_bots/tree/master/conf/setup_data) | added a one-time setup data with all the cities (id, location, population, ...) where the bots are created |
| [Global.java](https://github.com/vangav/vos_instagram_bots/blob/master/app/Global.java) | added the initialization of cities and bots |
| [cities](https://github.com/vangav/vos_instagram_bots/tree/master/app/com/vangav/vos_instagram_bots/cities) | added the cities' json representation and loader |
| [bots](https://github.com/vangav/vos_instagram_bots/tree/master/app/com/vangav/vos_instagram_bots/bots) | added the bots' periodic jobs and the bots creator |
| [controllers](https://github.com/vangav/vos_instagram_bots/tree/master/app/com/vangav/vos_instagram_bots/controllers) | added the implementation of request processing logic under `controller_name/HandlerControllerName.java` classes and nested response json structures under `controller_name/response_json` packages |





















