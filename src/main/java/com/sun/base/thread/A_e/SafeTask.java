package com.sun.base.thread.A_e;

import com.sun.base.utils.Utils;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by louis on 2014/11/1.
 */
public class SafeTask implements Runnable {
    private static ThreadLocal<Date> startDate=new ThreadLocal<Date>(){
        @Override
        protected Date initialValue() {
            return new Date();
        }
    };

    @Override
    public void run() {
        System.out.printf("Starting Thread_%s : %s\n", Thread.currentThread().getId(),
                Utils.dateFormat(startDate.get()));
        try {
            TimeUnit.SECONDS.sleep((int) Math.rint(Math.random() * 10+5));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Thread finished_%s : %s\n", Thread.currentThread().getId(),
                Utils.dateFormat(startDate.get()));
    }

    public static void main(String[] args) {
        SafeTask safeTask = new SafeTask();
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(safeTask);
            thread.start();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
