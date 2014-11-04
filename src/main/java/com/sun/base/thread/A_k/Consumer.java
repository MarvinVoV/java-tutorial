package com.sun.base.thread.A_k;

/**
 * Created by louis on 2014/11/4.
 */
public class Consumer implements Runnable {
    private EventStorage eventStorage;

    public Consumer(EventStorage eventStorage) {
        this.eventStorage = eventStorage;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            eventStorage.get();
        }
    }
}
