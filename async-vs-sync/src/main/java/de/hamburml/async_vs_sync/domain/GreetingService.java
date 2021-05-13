package de.hamburml.async_vs_sync.domain;

import de.hamburml.async_vs_sync.domain.model.HelloWorld;
import io.smallrye.mutiny.Uni;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import java.time.Duration;

@ApplicationScoped
public class GreetingService {

    private static final Logger LOG = Logger.getLogger(GreetingService.class);

    public Uni<HelloWorld> generateAsyncHelloWorld(String greeting, String date) {

        int secondsOfDelay = (int) Math.floor(Math.random() * 10) + 1;
        LOG.info(new StringBuilder().append("wait for ").append(secondsOfDelay).append(" seconds").toString());

        return Uni.createFrom().item(new HelloWorld())
            .onItem()
                .transform(helloWorld -> {
                    LOG.info(new StringBuilder().append("set greeting on me... ").append(greeting).toString());
                    helloWorld.setGreeting(greeting);

                    return helloWorld;
                })
            .onItem()
                .delayIt().by(Duration.ofSeconds(secondsOfDelay))
            .onItem()
                .transform(helloWorld -> {
                    LOG.info(new StringBuilder().append("set date on me... ").append(date).toString());
                    helloWorld.setDate(date);
                    return helloWorld;
                });
    }

    public HelloWorld generateSyncHelloWorld(String greeting, String date) throws InterruptedException {

        int secondsOfDelay = (int) Math.floor(Math.random() * 10) + 1;
        LOG.info(new StringBuilder().append("wait for ").append(secondsOfDelay).append(" seconds").toString());

        Thread.sleep(secondsOfDelay * 1000);

        return new HelloWorld(greeting, date);
    }
}
