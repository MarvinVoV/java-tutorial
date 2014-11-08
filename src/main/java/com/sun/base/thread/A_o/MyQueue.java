package com.sun.base.thread.A_o;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by louis on 2014/11/8.
 */
public class MyQueue<T> {
    private ReentrantLock lock=new ReentrantLock();
    private Condition pullConditon=lock.newCondition();
    private Condition pushCondition=lock.newCondition();
    private int maxSize;
    private LinkedList<T> list=new LinkedList<>();

    public MyQueue(int size) {
        maxSize=size;
    }

    public void push(T t){
        lock.lock();
        try {
            while (list.size()== maxSize) {
                // Current push thread release lock and sleep.
                pushCondition.await();
            }
            list.push(t);
            System.out.printf("%s Push Size %d\n", Thread.currentThread().getName(), list.size());
            // Week up all pull thread
            pullConditon.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public T pull(){
        T t=null;
        lock.lock();
        try {
            while (list.size() == 0) {
                // Current poll thread release lock and sleep.
                pullConditon.await();
            }
            t=list.poll();
            System.out.printf("%s Pull Size %d\n", Thread.currentThread().getName(), list.size());
            //Week up all push threads
            pushCondition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return t;
    }

}
