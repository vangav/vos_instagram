
### [![YouTube Play Icon](http://youtube.com/favicon.ico)](https://www.youtube.com/watch?v=PnAepsoA_bs&list=PLTcKayTjao6rOj02gtRdiVhvzB1SWGyhv&index=9) **on [YouTube](https://www.youtube.com/watch?v=PnAepsoA_bs&list=PLTcKayTjao6rOj02gtRdiVhvzB1SWGyhv&index=9)**

> **why?** instagram is the biggest [vangav backend](https://github.com/vangav/vos_backend) template designed for big scale and covering all the core features/utilities: service oriented architecture, worker service, multi-entry-point api, multi-keysapce database, oauth 2, facebook authentication, facebook graph api, rest jobs, periodic jobs, vangav mighty, push notifications, logging, analytics, client generator, test service, bots service, ...

# instagram

+ [instagram](https://github.com/vangav/vos_instagram), [instagram dispense](https://github.com/vangav/vos_instagram_dispense), [instagram jobs](https://github.com/vangav/vos_instagram_jobs), [instagram worker](https://github.com/vangav/vos_instagram_worker) and [instagram dash board](https://github.com/vangav/vos_instagram_dash_board) services work together and are generated using [vangav backend](https://github.com/vangav/vos_backend)

+ [instagram test](https://github.com/vangav/vos_instagram_test) is used to test the services above and [instagram bots](https://github.com/vangav/vos_instagram_bots) is used to simulate normal users' behavior; both services are generated using [vangav backend](https://github.com/vangav/vos_backend)

![instagram backend design](https://scontent-ams4-1.xx.fbcdn.net/v/t31.0-8/20988194_1976184039263906_6099093544043617330_o.png?_nc_cat=103&_nc_oc=AQm2KvFzuT0txSVe6YFPcXqkqsTlJwVD5p4plUc63HRK5Z_Y-7PE5Sb82yxCUc1i1B0&_nc_ht=scontent-ams4-1.xx&oh=66aa58aad89e48635586ef76b12a04ec&oe=5E2C3133)

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
14. make a call to the [`start_jobs`](https://github.com/vangav/vos_instagram_jobs/tree/master/app/com/vangav/vos_instagram_jobs/controllers/start_jobs) controller from the `vos_instagram_jobs` backend service in order to start the periodic jobs
15. from your prefered client (*we recommned* [postman](https://www.getpostman.com/docs/postman/launching_postman/installation_and_updates)) start trying the service; refer to the **features** and **service references** sections for reference
+ at the end to stop the services: press `control + d` in the terminal session where each service was started in (9, 10, 11, 12 and 13)
+ to stop cassandra: execute `ps auwx | grep cassandra` to get cassandra's `(pid)` then `kill -9 (pid)` to stop cassandra

## eclipse

+ follow the following steps to import the downloaded backend service in eclipse

1. `cd` to the service's directory and execute the `./_eclipsify.sh` script
2. file **>** import **>** general **>** existing projects into workspace **>** next **>** set "select root directory" to my_services **>** under projects make sure that vos_calculate_sum is selected **>** finish
3. double check the java version used for compiling the project: right click the project **>** properties **>** java compiler **>** enable project specific settings **>** compiler compliance level **>** 1.7 or 1.8

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

## error codes

+ following are the error codes of instagram services

### [instagram](https://github.com/vangav/vos_instagram)

| class | code : sub_code | explanation |
| ----- | --------------- | ----------- |
| [Constants](https://github.com/vangav/vos_instagram/blob/master/app/com/vangav/vos_instagram/common/Constants.java) |  |  |
|  | 300 : 1 | couldn't initialize kAuthCodeLifeTime |
|  | 300 : 2 | couldn't initialize kAccessTokenLifeTime |
|  | 300 : 3 | couldn't initialize kFacebookAppId |
|  | 300 : 4 | couldn't initialize kDefaultRegion |
|  | 300 : 5 | couldn't initialize kWorldRegion |
|  | 300 : 6 | couldn't initialize kDefaultGridId |
|  | 300 : 7 | couldn't initialize kGeoGridDimensionMetres |
|  | 300 : 8 | couldn't initialize kGeoGridsConfig |
|  | 300 : 9 | couldn't initialize kThumbnailDimension |
| [CommonPlayHandler](https://github.com/vangav/vos_instagram/blob/master/app/com/vangav/vos_instagram/controllers/CommonPlayHandler.java) |  |  |
|  | 400 : 1 | no access token for user id and device token |
|  | 400 : 2 | wrong access token |
|  | 400 : 3 | already expired access token |
|  | 400 : 4 | just expired access token |
| [HandlerComment](https://github.com/vangav/vos_instagram/blob/master/app/com/vangav/vos_instagram/controllers/comment/HandlerComment.java) |  |  |
|  | 401 : 1 | can't comment on a post that doesn't exist |
|  | 401 : 2 | *non-fatal*: invalid device type |
| [DeleteComment](https://github.com/vangav/vos_instagram/blob/master/app/com/vangav/vos_instagram/controllers/delete_comment/HandlerDeleteComment.java) |  |  |
|  | 402 : 1 | post/comment doesn't exist |
| [HandlerFollow](https://github.com/vangav/vos_instagram/blob/master/app/com/vangav/vos_instagram/controllers/follow/HandlerFollow.java) |  |  |
|  | 403 : 1 | can't follow self |
|  | 403 : 2 | can't follow an non-existing user |
|  | 403 : 3 | already following user |
|  | 403 : 4 | *non-fatal*: invalid device type |
| [HandlerGetAccessToken](https://github.com/vangav/vos_instagram/blob/master/app/com/vangav/vos_instagram/controllers/get_access_token/HandlerGetAccessToken.java) |  |  |
|  | 404 : 1 | auth code doesn't exist |
|  | 404 : 2 | wrong auth code |
| [HandlerGetFollowers](https://github.com/vangav/vos_instagram/blob/master/app/com/vangav/vos_instagram/controllers/get_followers/HandlerGetFollowers.java) |  |  |
|  | 406 : 1 | can't get followers for non-existing user |
| [HandlerGetFollowing](https://github.com/vangav/vos_instagram/blob/master/app/com/vangav/vos_instagram/controllers/get_following/HandlerGetFollowing.java) |  |  |
|  | 407 : 1 | can't get following for non-existing user |
| [HandlerGetPhoto](https://github.com/vangav/vos_instagram/blob/master/app/com/vangav/vos_instagram/controllers/get_photo/HandlerGetPhoto.java) |  |  |
|  | 408 : 1 | photo doesn't exist |
| [HandlerGetPhotoThumbnail](https://github.com/vangav/vos_instagram/blob/master/app/com/vangav/vos_instagram/controllers/get_photo_thumbnail/HandlerGetPhotoThumbnail.java) |  |  |
|  | 409 : 1 | photo doesn't exist |
| [HandlerGetPostComments](https://github.com/vangav/vos_instagram/blob/master/app/com/vangav/vos_instagram/controllers/get_post_comments/HandlerGetPostComments.java) |  |  |
|  | 410 : 1 | post doesn't exist |
| [HandlerGetPostInfo](https://github.com/vangav/vos_instagram/blob/master/app/com/vangav/vos_instagram/controllers/get_post_info/HandlerGetPostInfo.java) |  |  |
|  | 411 : 1 | post doesn't exist |
| [HandlerGetPostLikes](https://github.com/vangav/vos_instagram/blob/master/app/com/vangav/vos_instagram/controllers/get_post_likes/HandlerGetPostLikes.java) |  |  |
|  | 412 : 1 | post doesn't exist |
| [HandlerGetPostPhotoId](https://github.com/vangav/vos_instagram/blob/master/app/com/vangav/vos_instagram/controllers/get_post_photo_id/HandlerGetPostPhotoId.java) |  |  |
|  | 413 : 1 | post doesn't exist |
| [HandlerGetProfilePicture](https://github.com/vangav/vos_instagram/blob/master/app/com/vangav/vos_instagram/controllers/get_profile_picture/HandlerGetProfilePicture.java) |  |  |
|  | 414 : 1 | user doesn't exist |
| [HandlerGetUserInfo](https://github.com/vangav/vos_instagram/blob/master/app/com/vangav/vos_instagram/controllers/get_user_info/HandlerGetUserInfo.java) |  |  |
|  | 418 : 1 | user doesn't exist |
| [HandlerGetUserPosts](https://github.com/vangav/vos_instagram/blob/master/app/com/vangav/vos_instagram/controllers/get_user_posts/HandlerGetUserPosts.java) |  |  |
|  | 419 : 1 | user doesn't exist |
| [HandlerLikePost](https://github.com/vangav/vos_instagram/blob/master/app/com/vangav/vos_instagram/controllers/like_post/HandlerLikePost.java) |  |  |
|  | 420 : 1 | post doesn't exist |
|  | 420 : 2 | already liked |
|  | 420 : 3 | *non-fatal*: invalid device type |
| [HandlerLoginEmail](https://github.com/vangav/vos_instagram/blob/master/app/com/vangav/vos_instagram/controllers/login_email/HandlerLoginEmail.java) |  |  |
|  | 421 : 1 | e-mail isn't signed up |
|  | 421 : 2 | wrong password |
|  | 421 : 3 | e-mail isn't signed up - triggers in case authentication is disabled |
| [HandlerLoginFacebook](https://github.com/vangav/vos_instagram/blob/master/app/com/vangav/vos_instagram/controllers/login_facebook/HandlerLoginFacebook.java) |  |  |
|  | 422 : 1 | couldn't get user's name from facebook graph api |
| [HandlerLogout](https://github.com/vangav/vos_instagram/blob/master/app/com/vangav/vos_instagram/controllers/logout/HandlerLogout.java) |  |  |
|  | 423 : 1 | no refresh token for this user/device_token |
|  | 423 : 2 | wrong refresh token |
| [HandlerRefreshAccessToken](https://github.com/vangav/vos_instagram/blob/master/app/com/vangav/vos_instagram/controllers/refresh_access_token/HandlerRefreshAccessToken.java) |  |  |
|  | 425 : 1 | no access token for user |
|  | 425 : 2 | wrong access token |
|  | 425 : 3 | no refresh token |
|  | 425 : 4 | wrong refresh token |
| [HandlerSignupEmail](https://github.com/vangav/vos_instagram/blob/master/app/com/vangav/vos_instagram/controllers/signup_email/HandlerSignupEmail.java) |  |  |
|  | 427 : 1 | e-mail already signed up |
| [HandlerUnfollow](https://github.com/vangav/vos_instagram/blob/master/app/com/vangav/vos_instagram/controllers/unfollow/HandlerUnfollow.java) |  |  |
|  | 428 : 1 | can't unfollow a user that doesn't exist |
|  | 428 : 2 | a user can't unfollow another unless she/he is following that user already |
| [HandlerUnlikePost](https://github.com/vangav/vos_instagram/blob/master/app/com/vangav/vos_instagram/controllers/unlike_post/HandlerUnlikePost.java) |  |  |
|  | 429 : 1 | can't unlike a post that doesn't exist |
|  | 429 : 2 | user didn't like this post before to unlike it |

### [instagram dash board](https://github.com/vangav/vos_instagram_dash_board)

| class | code : sub_code | explanation |
| ----- | --------------- | ----------- |
| [HandlerGetDailyUsersErrorLogs](https://github.com/vangav/vos_instagram_dash_board/blob/master/app/com/vangav/vos_instagram_dash_board/controllers/get_daily_users_error_logs/HandlerGetDailyUsersErrorLogs.java) |  |  |
|  | 404 : 1 | e-mail isn't registered |
| [HandlerGetDailyUsersLogs](https://github.com/vangav/vos_instagram_dash_board/blob/master/app/com/vangav/vos_instagram_dash_board/controllers/get_daily_users_logs/HandlerGetDailyUsersLogs.java) |  |  |
|  | 405 : 1 | e-mail isn't registered |

### [instagram test](https://github.com/vangav/vos_instagram_test)

| class | code : sub_code | explanation |
| ----- | --------------- | ----------- |
| [Constants](https://github.com/vangav/vos_instagram_test/blob/master/app/com/vangav/vos_instagram_test/clients/Constants.java) |  |  |
|  | 1000 : 1 | couldn't initialize kVosInstagramUrl |
|  | 1000 : 2 | couldn't initialize kVosInstagramDashBoardUrl |

### [instagram bots](https://github.com/vangav/vos_instagram_bots)

| class | code : sub_code | explanation |
| ----- | --------------- | ----------- |
| [Constants](https://github.com/vangav/vos_instagram_bots/blob/master/app/com/vangav/vos_instagram_bots/clients/Constants.java) |  |  |
|  | 1000 : 1 | couldn't initialize kVosInstagramUrl |

## notes

+ [instagram](https://github.com/vangav/vos_instagram), [instagram dispense](https://github.com/vangav/vos_instagram_dispense), [instagram jobs](https://github.com/vangav/vos_instagram_jobs), [instagram worker](https://github.com/vangav/vos_instagram_worker), [instagram dash board](https://github.com/vangav/vos_instagram_dash_board), [instagram test](https://github.com/vangav/vos_instagram_test) and [instagram bots](https://github.com/vangav/vos_instagram_bots) services has no connection with [Instagram Inc.](https://www.instagram.com/); these services are just a [vangav backend](https://github.com/vangav/vos_backend) template to demonstrate using some features/utilities in the context of photo sharing apps
+ the "instagram" name is used for users to easier understand the services' design, features and source code

# share

[![facebook share](https://www.prekindle.com/images/social/facebook.png)](https://www.facebook.com/sharer/sharer.php?u=https%3A//github.com/vangav/vos_backend)  [![twitter share](http://www.howickbaptist.org.nz/wordpress/media/twitter-64-black.png)](https://twitter.com/home?status=vangav%20backend%20%7C%20build%20big%20tech%2010x%20faster%20%7C%20https%3A//github.com/vangav/vos_backend)  [![pinterest share](http://d7ab823tjbf2qywyt3grgq63.wpengine.netdna-cdn.com/wp-content/themes/velominati/images/share_icons/pinterest-black.png)](https://pinterest.com/pin/create/button/?url=https%3A//github.com/vangav/vos_backend&media=https%3A//scontent-mad1-1.xx.fbcdn.net/v/t31.0-8/20645143_1969408006608176_5289565717021239224_o.png?oh=acf20113a3673409d238924cfec648d2%26oe=5A3414B5&description=)  [![google plus share](http://e-airllc.com/wp-content/themes/nebula/images/social_black/google.png)](https://plus.google.com/share?url=https%3A//github.com/vangav/vos_backend)  [![linkedin share](http://e-airllc.com/wp-content/themes/nebula/images/social_black/linkedin.png)](https://www.linkedin.com/shareArticle?mini=true&url=https%3A//github.com/vangav/vos_backend&title=vangav%20backend%20%7C%20build%20big%20tech%2010x%20faster&summary=&source=)

# free consulting

[![vangav's consultant](http://www.footballhighlights247.com/images/mobile-share/fb-messenger-64x64.png)](https://www.facebook.com/mustapha.abdallah)
