package com.sun.base.thread.D_a;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by yamorn on 2014/11/25.
 */
public class Simple {
    private static ThreadPoolExecutor executorService;
    static {
        executorService= (ThreadPoolExecutor)Executors.newCachedThreadPool();
    }

    public static void main(String[] args) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 20; i++) {
                    System.out.println("....");
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        System.out.println("active count=" + executorService.getActiveCount());
    }
}
