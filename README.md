
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
| []() |  |
| []() |  |
| []() |  |
| []() |  |
| []() |  |
| []() |  |

### [instagram dispense](https://github.com/vangav/vos_instagram_dispense)

| controller(s) | feature |
| ------------- | ------- |
| []() |  |
| []() |  |
| []() |  |
| []() |  |
| []() |  |
| []() |  |
| []() |  |
| []() |  |

### [instagram jobs](https://github.com/vangav/vos_instagram_jobs)

| controller(s) | feature |
| ------------- | ------- |
| []() |  |
| []() |  |
| []() |  |
| []() |  |
| []() |  |
| []() |  |
| []() |  |
| []() |  |

### [instagram dash board](https://github.com/vangav/vos_instagram_dash_board)

| controller(s) | feature |
| ------------- | ------- |
| []() |  |
| []() |  |
| []() |  |
| []() |  |
| []() |  |
| []() |  |
| []() |  |
| []() |  |

### [instagram test](https://github.com/vangav/vos_instagram_test)

| controller(s) | feature |
| ------------- | ------- |
| []() |  |
| []() |  |
| []() |  |
| []() |  |
| []() |  |
| []() |  |
| []() |  |
| []() |  |

### [instagram bots](https://github.com/vangav/vos_instagram_bots)

| controller(s) | feature |
| ------------- | ------- |
| []() |  |
| []() |  |
| []() |  |
| []() |  |
| []() |  |
| []() |  |
| []() |  |
| []() |  |





















