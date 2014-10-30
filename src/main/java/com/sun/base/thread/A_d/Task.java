package com.sun.base.thread.A_d;

/**
 * Created by louis on 2014/10/31.
 */
public class Task implements Runnable {
    @Override
    public void run() {
        // trigger runtime exception
        int numero=Integer.parseInt("TTT");
    }
    public static void main(String[] args) {
        Thread thread = new Thread(new Task());

        //Handle runtime exception in thread
        thread.setUncaughtExceptionHandler(new ExceptionHandler());

        thread.start();
    }
}
