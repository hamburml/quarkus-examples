package de.hamburml.async_vs_sync.domain.model;

public class HelloWorld {

    private String greeting;
    private String date;

    public HelloWorld(String greeting, String date) {
        this.greeting = greeting;
        this.date = date;
    }

    public HelloWorld() {
    }

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
