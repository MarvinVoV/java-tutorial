package com.sun.base.thread.A_b;

import java.util.concurrent.TimeUnit;

/**
 * Created by louis on 2014/10/30.
 */
public class InterruptTest implements Runnable{
    @Override
    public void run() {
        /*
            while的目的是保证在主线执行完thread.interrupt()方法后，才结束while
            执行下面的sleep，可以通过结果的两个输出看出（Before,After）
         */
        long start=System.currentTimeMillis();
        while((System.currentTimeMillis()-start)<5000){
            //empty
        }
        System.out.printf("After:Thread will be sleep.\n");
        try {
            TimeUnit.SECONDS.sleep(200);
            System.out.printf("After sleep.\n");
        } catch (InterruptedException e) {
            System.out.printf("Interrupted.");
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new InterruptTest());
        thread.start();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread.interrupt();
        System.out.printf("Before:Main execute thread.interrupt().\n");
    }
}
