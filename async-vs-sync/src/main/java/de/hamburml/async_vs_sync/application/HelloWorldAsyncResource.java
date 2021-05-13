package de.hamburml.async_vs_sync.application;

import de.hamburml.async_vs_sync.domain.GreetingService;
import de.hamburml.async_vs_sync.domain.model.HelloWorld;
import io.smallrye.mutiny.Uni;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.Date;

@Path("async/helloworld")
public class HelloWorldAsyncResource {
    @Inject
    GreetingService greetingService;

    @GET
    public Uni<HelloWorld> getHello() {

        var helloWorld = greetingService.generateAsyncHelloWorld("hello non-blocking world", new Date().toString());

        // we could "merge" the async world into the sync world. This would wait till the helloWorld Uni is resolved. But if we do this and the thread-number is 1, this will never be resolved!
        // var helloWorldWithoutUni = helloWorld.await().indefinitely();

        return helloWorld;
    }
}
