package com.sun.base.jmx.example2;

import com.sun.jdmk.comm.HtmlAdaptorServer;

import javax.management.*;

/**
 * Created by louis on 2014/12/22.
 */
public class HelloAgent implements NotificationListener {
    private MBeanServer mbs;
    public HelloAgent() {
        mbs = MBeanServerFactory.createMBeanServer("HelloAgent");
        HtmlAdaptorServer adaptorServer=new HtmlAdaptorServer();
        HelloWorld hw=new HelloWorld();
        ObjectName adapterName=null;
        ObjectName hwName=null;
        try {
            hwName = new ObjectName("HelloAgent:name=helloWorld");
            mbs.registerMBean(hw, hwName);
            hw.addNotificationListener(this,null,null);//add notification listener

            adapterName = new ObjectName("HelloAgent:name=htmladapter,port=9092");
            adaptorServer.setPort(9092);
            mbs.registerMBean(adaptorServer, adapterName);
            adaptorServer.start();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void handleNotification(Notification notification, Object handback) {
        System.out.println( "Receiving notification..." );
        System.out.println( notification.getType() );
        System.out.println( notification.getMessage() );
    }

    public static void main(String[] args) {
        System.out.println("HelloAgent is running.");
        HelloAgent agent = new HelloAgent();
    }
}
