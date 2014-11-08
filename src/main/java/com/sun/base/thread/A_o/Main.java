package com.sun.base.thread.A_o;

/**
 * Created by louis on 2014/11/8.
 */
public class Main {
    public static void main(String[] args) {
        MyQueue<Integer> myQueue = new MyQueue<>(100);
        Thread[] producer = new Thread[3];
        Thread[] consumer = new Thread[3];
        for (int i=0;i<producer.length;i++) {
            producer[i] = new Thread(new Producer(myQueue));
        }
        for (int j=0;j<consumer.length;j++) {
            consumer[j] = new Thread(new Consumer(myQueue));
        }

        for (Thread c : consumer) {
            c.start();
        }
        for (Thread p : producer) {
            p.start();
        }

    }
}
