package de.hamburml.async_vs_sync.application;

import de.hamburml.async_vs_sync.domain.GreetingService;
import de.hamburml.async_vs_sync.domain.model.HelloWorld;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.Date;

@Path("sync/helloworld")
public class HelloWorldSyncResource {
    @Inject
    GreetingService greetingService;

    @GET
    public HelloWorld getHello() throws InterruptedException {

        var helloWorld = greetingService.generateSyncHelloWorld("hello blocking world", new Date().toString());
        return helloWorld;
    }
}
