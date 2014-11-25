package com.sun.base.thread.D_b;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by yamorn on 2014/11/25.
 */
public class FixedPool {
    private static ThreadPoolExecutor executor;
    static {
        executor=(ThreadPoolExecutor) Executors.newFixedThreadPool(5);
    }

    public static void main(String[] args) {
        for(int i=0;i<10;i++){
            executor.execute(new myTask());
        }
        System.out.println("active count="+executor.getActiveCount());
        System.out.println("task count="+executor.getTaskCount());
        executor.shutdown();
        System.out.println(executor.isTerminated());
        while (!executor.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("done");
    }

    static class myTask implements Runnable{
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
