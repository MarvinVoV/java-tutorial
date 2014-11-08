package com.sun.base.thread.B_a;

import java.util.concurrent.Semaphore;

/**
 * Created by louis on 2014/11/8.
 */

public class PrintQueue {
    private final Semaphore semaphore;

    public PrintQueue() {
        // binary semaphore
        this.semaphore = new Semaphore(1);
    }

    public void printJob(){
        try {
            semaphore.acquire();
            long duration=(long)(Math.random()*10);
            System.out.printf("%s: PrintQueue: Printing a Job during %d seconds\n",
                    Thread.currentThread().getName(),duration);
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            semaphore.release();
        }
    }
}
