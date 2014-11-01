package com.sun.base.thread.A_f;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by louis on 2014/11/1.
 */
public class SearchTask implements Runnable {
    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.printf("Thread_%s: Start\n", name);
        try {
            doTask();
        } catch (InterruptedException e) {
            System.out.printf("Thread %s: Interrupted\n", name);
        }
        System.out.printf("Thread %s: End\n", name);
    }

    private void doTask() throws InterruptedException {
        Random random = new Random((new Date()).getTime());
        int value = (int) (random.nextDouble() * 100);
        System.out.printf("Thread_%s: %d\n", Thread.currentThread().getName(), value);
        TimeUnit.SECONDS.sleep(value);
    }

    public static void main(String[] args) {
        ThreadGroup threadGroup = new ThreadGroup("Searcher");
        SearchTask searchTask=new SearchTask();
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(searchTask);
            thread.start();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
