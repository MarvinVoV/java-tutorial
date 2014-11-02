package com.sun.base.thread.A_h;

import java.util.concurrent.TimeUnit;

/**
 * Created by louis on 2014/11/2.
 */
public class Task implements Runnable {
    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MyThreadFactory threadFactory = new MyThreadFactory("MyThreadFactory");
        Task task=new Task();
        Thread thread;
        System.out.printf("Starting the Threads\n");
        for (int i=0; i<10; i++){
            thread=threadFactory.newThread(task);
            thread.start();
        }
        System.out.printf("Factory stats:\n");
        System.out.printf("%s\n",threadFactory.getStats());
    }
}
