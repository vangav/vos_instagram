
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






















