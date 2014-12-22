package com.sun.base.jmx.example3;

import com.sun.jdmk.comm.HtmlAdaptorServer;

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;
import javax.management.remote.rmi.RMIConnectorServer;
import java.util.HashMap;

/**
 * Created by louis on 2014/12/22.
 */
public class JMXBookAgent {
    private MBeanServer mbs;
    public JMXBookAgent() {
        System.out.println("\n\tCreate MBeanServer");
        //  Create MBean Server
        mbs = MBeanServerFactory.createMBeanServer("JMXBookAgent");
        //  Add agent connectivity
        startHTMLAdapter();
        startRMIConnector();
    }
    public void startHTMLAdapter() {
        HtmlAdaptorServer adaptor = new HtmlAdaptorServer();
        ObjectName adaptorName=null;
        try {
            adaptor.setPort(9092);
            adaptorName = new ObjectName("JMXBookAgent:name=html,port=9092");
            mbs.registerMBean(adaptor, adaptorName);
            adaptor.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startRMIConnector() {
        JMXConnectorServer connector =null;
        try {
            JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:2099/server");
            connector = JMXConnectorServerFactory.newJMXConnectorServer(url,
                    new HashMap<String,Object>(),mbs);
            connector.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("\n>>> Start of JMXBook Agent");
        System.out.println("\n>>> Create the agent....");
        JMXBookAgent agent=new JMXBookAgent();
        System.out.println("\nAgent is Ready for Service...\n");

    }
}
