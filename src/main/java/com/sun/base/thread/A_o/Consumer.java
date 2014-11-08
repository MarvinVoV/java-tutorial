package com.sun.base.thread.A_o;

import java.util.Random;

/**
 * Created by louis on 2014/11/8.
 */
public class Consumer implements Runnable{
    private MyQueue<Integer> myQueue;

    public Consumer(MyQueue<Integer> myQueue) {
        this.myQueue = myQueue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            myQueue.pull();
            try {
                Random random=new Random();
                Thread.sleep(random.nextInt(500));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
