package com.sun.base.thread.A_i;

import java.util.concurrent.TimeUnit;

/**
 * Created by louis on 2014/11/2.
 */
public class StatusMonitor implements Runnable{
    private Thread[] threads;

    public StatusMonitor(Thread[] threads) {
        this.threads=threads;
    }

    private String status(){
        StringBuffer sb=new StringBuffer();
        sb.append("status list:\t\n");
        for (Thread thread : threads) {
            sb.append("\t").append(thread.getName()).append(" status:").append(thread.getState()).append("\n");
        }
        return sb.toString();
    }
    @Override
    public void run() {
        boolean flag=true;
        while(flag) {
            System.out.println(status());
            try {
                TimeUnit.MILLISECONDS.sleep(5);
            } catch (InterruptedException e) {
                flag=false;
            }
        }
    }
}
