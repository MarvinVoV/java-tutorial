package com.sun.base.jmx.example2;

/**
 * Created by louis on 2014/12/22.
 */
public interface HelloWorldMBean {
    public void setGreeting(String greeting);

    public String getGreeting();

    public void printGreeting();

}
