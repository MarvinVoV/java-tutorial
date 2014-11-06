package com.sun.base.thread.A_l;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by louis on 2014/11/4.
 */
public class PrintQueue {
    private final Lock queueLock=new ReentrantLock();

    public void printJob(Object document) {
        queueLock.lock();
        try {
            Long duration=(long)(Math.random()*10000);
            System.out.printf("%s:PrintQueue: Printing a Job during %d seconds\n",Thread.currentThread().getName(),(duration/1000));
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            queueLock.unlock();
        }
    }
}
