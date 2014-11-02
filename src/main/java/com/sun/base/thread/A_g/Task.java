package com.sun.base.thread.A_g;

import java.util.Random;

/**
 * Created by louis on 2014/11/2.
 */
public class Task implements Runnable {
    @Override
    public void run() {
        int result;
        Random random=new Random(Thread.currentThread().getId());
        while (true) {
            result=1000/((int)(random.nextDouble()*1000));
            // This line will throw IllegalFormatConversionException
            System.out.printf("%s : %f\n",Thread.currentThread().getId(),result);
            if (Thread.currentThread().isInterrupted()) {
                System.out.printf("%d : Interrupted\n",Thread.currentThread().getId());
                return;
            }
        }
    }

    public static void main(String[] args) {
        MyThreadGroup threadGroup=new MyThreadGroup("MyThreadGroup");
        Task task=new Task();
        for (int i=0; i<2; i++){
            Thread t=new Thread(threadGroup,task);
            t.start();
        }
    }
}
