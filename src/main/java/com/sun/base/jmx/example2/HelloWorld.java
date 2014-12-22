package com.sun.base.jmx.example2;

import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

/**
 * Created by louis on 2014/12/22.
 */
public class HelloWorld extends NotificationBroadcasterSupport implements HelloWorldMBean {
    private String greeting;
    public HelloWorld() {
        greeting = "Hello World!";
    }
    @Override
    public void setGreeting(String greeting) {
        this.greeting=greeting;

        Notification notification = new Notification("com.sun.base.jmx.example2",
                this, -1, System.currentTimeMillis(), greeting);
        sendNotification(notification);

    }

    @Override
    public String getGreeting() {
        return this.greeting;
    }

    @Override
    public void printGreeting() {
        System.out.println(greeting);
    }
}
