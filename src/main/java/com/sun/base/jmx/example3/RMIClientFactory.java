package com.sun.base.jmx.example3;

import javax.management.MBeanServerConnection;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

/**
 * Created by louis on 2014/12/22.
 */
public class RMIClientFactory {
    public static MBeanServerConnection getClient(){
        MBeanServerConnection connection=null;
        try {
            JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:2099/server");
            JMXConnector jmxConnector = JMXConnectorFactory.connect(url);
            connection=jmxConnector.getMBeanServerConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
