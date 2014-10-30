package com.sun.base.thread.A_c;

import java.util.Date;
import java.util.Deque;
import java.util.concurrent.TimeUnit;

/**
 * Created by louis on 2014/10/31.
 */
public class WriterTask implements Runnable {
    private Deque<Event> deque;

    public WriterTask(Deque<Event> deque) {
        this.deque = deque;
    }

    @Override
    public void run() {
        System.out.println("WriterTask start");
        for (int i = 0; i < 10; i++) {
            Event event=new Event();
            event.setDate(new Date());
            event.setEvent("Event_" + Thread.currentThread().getId());
            deque.addFirst(event);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("WriterTask done");
    }
}
