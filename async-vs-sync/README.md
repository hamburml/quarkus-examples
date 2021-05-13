# async-vs-sync example

I read the following websites to understand non-blocking java (especially how it should be done in quarkus)
- https://quarkus.io/guides/getting-started-reactive
- https://quarkus.io/blog/mutiny-vertx/
- https://quarkus.io/guides/vertx
- https://smallrye.io/smallrye-mutiny/guides

Now I know that quarkus is based on vert.x, can do imperative and reactive quite well. If I understood it correctly even imperative code is managed very well in quarkus so it's not always necessary to use reactive. But I didn't get it quite well...
So I wrote an example to demonstrate the difference for myself :D

# Overview

In quarkus every incoming request is executed by a thread. This thread is called "executor-thread". You can see it in the logs of the app if you log something (executor-thread-<number>).
Normally quarkus creates many threads (see https://quarkus.io/guides/all-config#quarkus-core_quarkus.thread-pool.max-threads) to allow many requests.
For our example we only allow 1 thread (see `applicatoin.properties`).

This application creates HelloWorld messages via blocking and non-blocking code.

# Resources

- HelloWorldAsyncResource via localhost:8080/async/helloworld
  - returns `Uni<HelloWorld>`

- HelloWorldSyncResource via localhost:8080/sync/helloworld
  - returns `HelloWorld`
    
# GreetingService

- generateAsyncHelloWorld
  - waits a random number of seconds (use delayIt())
  - returns `Uni<HelloWorld>`

- generateSyncHelloWorld
    - waits a random number of seconds (use Thread.sleep())
    - returns `HelloWorld`
    
# How to use

- start application
- open two or three tabs and surf to `http://localhost:8080/async/helloworld`
- check logs and see that the `executor-thread-1` handles the request
- recognize that after n seconds the response is send out


- open one tab and surf to `http://localhost:8080/sync/helloworld`
- open another tab and surf to the `http://localhost:8080/sync/helloworld`
- check logs and see that the first request is handled by `executor-thread-1`
- The second request is not shown in the logs. After the first request is completed, the second requests is shown in the log.


- open some tabs with `http://localhost:8080/async/helloworld`
- open one tab with `http://localhost:8080/sync/helloworld`
- Remember, we only have one thread! If the sync/helloworld is executed the thread will wait - even the async-requests will now stuck.
