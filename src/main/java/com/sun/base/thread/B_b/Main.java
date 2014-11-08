package com.sun.base.thread.B_b;


/**
 * Created by louis on 2014/11/8.
 */
public class Main {
    public static void main(String[] args) {
        PrintQueue printQueue=new PrintQueue();
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(new Job(printQueue), "Thread_" + i);
        }
        for (int i = 0; i < 10; i++) {
            threads[i].start();
        }
    }
}
