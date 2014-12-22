package com.sun.base.jmx.example3;

import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;

/**
 * Created by louis on 2014/12/22.
 */
public class HelloWorldSetup {
    public HelloWorldSetup(){
        try {
            MBeanServerConnection client=RMIClientFactory.getClient();
            ObjectName hwName = new ObjectName("JMXBookAgent:name=helloWorld");
            client.createMBean("com.sun.base.jmx.example2.HelloWorld",hwName);
            client.invoke(hwName, "printGreeting", null, null);
            client.unregisterMBean(hwName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        HelloWorldSetup setup=new HelloWorldSetup();
    }
}
