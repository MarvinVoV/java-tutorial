package com.sun.base.thread.D_a;

/**
 * Created by yamorn on 2014/11/24.
 */
public class Main {
    public static void main(String[] args) {

        Server server=new Server();
        for(int i=0;i<100;i++) {
            Task task = new Task("Task_" + i);
            server.executeTask(task);
        }
        server.endTask();
    }
}
