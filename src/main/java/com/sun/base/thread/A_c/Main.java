package com.sun.base.thread.A_c;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;

/**
 * Created by louis on 2014/10/31.
 */
public class Main {
    public static void main(String[] args) {
        Deque<Event> deque=new ArrayDeque<>();
        WriterTask writer=new WriterTask(deque);
        Thread thread[]=new Thread[3];
        for (int i=0; i<3; i++){
            thread[i]=new Thread(writer);
            thread[i].start();
        }
        CleanerTask cleaner=new CleanerTask(deque);
        cleaner.start();
        //等待写线程结束，然后结束清理线程，否则清理线程将无限循环
        for (Thread aThread : thread) {
            try {
                aThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //当生产者结束后，通知消费则终止
        cleaner.interrupt();
    }
}
