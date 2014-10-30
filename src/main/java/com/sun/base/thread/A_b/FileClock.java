package com.sun.base.thread.A_b;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by louis on 2014/10/30.
 */
public class FileClock implements Runnable{
    @Override
    public void run() {
        /*
            每次循环打印一个时间，模拟一个任务，之后休眠1毫秒，
            在sleep时，如果被中断，将会抛出InterruptedException
         */
        for (int i = 0; i < 10; i++) {
            System.out.printf("%s\n",new Date());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                System.out.printf("The FileClock has been interrupted.");
            }
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new FileClock());
        thread.start();

        /*
            主线程等待5毫秒
         */
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        /*
            唤醒FileClock线程
         */
        thread.interrupt();
    }
}
