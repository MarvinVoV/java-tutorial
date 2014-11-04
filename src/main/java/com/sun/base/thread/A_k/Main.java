package com.sun.base.thread.A_k;

/**
 * Created by louis on 2014/11/4.
 */
public class Main {
    public static void main(String[] args) {
        EventStorage storage=new EventStorage();

        Producer producer=new Producer(storage);
        Thread thread1=new Thread(producer);

        Consumer consumer=new Consumer(storage);
        Thread thread2=new Thread(consumer);

        thread2.start();
        thread1.start();
    }
}
