package com.sun.base.thread.A_c;

import java.util.Date;
import java.util.Deque;
import java.util.concurrent.TimeUnit;

/**
 * Created by louis on 2014/10/31.
 */
public class CleanerTask extends Thread {
    private Deque<Event> deque;

    public CleanerTask(Deque<Event> deque) {
        this.deque = deque;
    }

    @Override
    public void run() {
        System.out.println("clean start.");
        Date date=new Date();
        boolean flag=true;
        while (flag) {
            if(deque.size()==0) continue;
            Event e=deque.getLast();
            if((date.getTime()-e.getDate().getTime())>10000){
                System.out.printf("Cleaner:%s\n", e.getEvent());
                deque.removeLast();
                System.out.printf("Cleaner: Size of the queue: %d\n",deque.size());
            }
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e1) {
                flag=false;
            }
        }
        System.out.println("clean end");
    }
}
