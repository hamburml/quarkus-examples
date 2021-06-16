package de.hamburml;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class HelloResource {

    @ConfigProperty(name="environment")
    String environment;

    @ConfigProperty(name="filename")
    String filename;

    @ConfigProperty(name="anotherKey")
    String anotherKey;

    @ConfigProperty(name="important.env")
    String importantEnv;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello " + environment + ". I use the file " + filename + ". Value of anotherKey is " + anotherKey + ". Value of importantEnv is " + importantEnv;
    }
}