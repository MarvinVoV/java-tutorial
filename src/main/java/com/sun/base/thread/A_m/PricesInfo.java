package com.sun.base.thread.A_m;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by louis on 2014/11/6.
 */
public class PricesInfo {
    private double priceA;
    private double priceB;

    private ReadWriteLock lock=new ReentrantReadWriteLock();

    public PricesInfo() {
        this(1.0, 2.0);
    }
    public PricesInfo(double priceA, double priceB) {
        this.priceA = priceA;
        this.priceB = priceB;
    }

    public double getPriceA() {
        lock.readLock().lock();
        System.out.printf("%s: PriceA readLock: lock\n",Thread.currentThread().getName());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        double value=priceA;
        System.out.printf("%s: PriceA readLock: unlock\n",Thread.currentThread().getName());
        lock.readLock().unlock();

        return value;
    }

    public double getPriceB() {
        lock.readLock().lock();
        double value=priceB;
        lock.readLock().unlock();
        return value;
    }

    public void setPrices(double priceA, double priceB) {
        lock.writeLock().lock();
        System.out.printf("%s :Writer locked.\n",Thread.currentThread().getName());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.priceA=priceA;
        this.priceB=priceB;
        lock.writeLock().unlock();
        System.out.printf("%s :Writer unlock.\n", Thread.currentThread().getName());
    }
}
