package com.sun.base.thread.A_b;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by louis on 2014/10/31.
 */
public class ResourceLoader implements Runnable {
    //模拟资源加载
    @Override
    public void run() {
        System.out.printf("Beginning data sources loadding:%s\n", new Date());
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Data source loading has finished:%s\n", new Date());
    }

    public static void main(String[] args) {
        Thread thread1 = new Thread(new ResourceLoader(), "localResourceLoader");
        Thread thread2 = new Thread(new ResourceLoader(), "remoteResourceLoader");
        thread1.start();
        thread2.start();
        //主线程等待资源加载完毕
        try {
            thread1.join();

            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Main:Resource has been loaded:%s\n", new Date());

    }
}
