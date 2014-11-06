package com.sun.base.thread.A_m;

/**
 * Created by louis on 2014/11/6.
 */
public class Reader implements Runnable{
    private PricesInfo pricesInfo;

    public Reader(PricesInfo pricesInfo) {
        this.pricesInfo = pricesInfo;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.printf("%s: Price B: %f\n",
                    Thread.currentThread().getName(),pricesInfo.getPriceB());
            System.out.printf("%s: Price A: %f\n",
                    Thread.currentThread().getName(),pricesInfo.getPriceA());
        }
    }
}
