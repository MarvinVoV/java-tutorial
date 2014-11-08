package com.sun.base.thread.B_a;

/**
 * Created by louis on 2014/11/8.
 */
public class Job implements Runnable {
    private PrintQueue printQueue;

    public Job(PrintQueue printQueue) {
        this.printQueue = printQueue;
    }

    @Override
    public void run() {
        System.out.printf("%s: Going to print a job\n",
                Thread.currentThread().getName());
        printQueue.printJob();
        System.out.printf("%s: The document has been printed\n",
                Thread.currentThread().getName());
    }
}
