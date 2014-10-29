package com.sun.base.thread.A_a;

/**
 * Created by louis on 2014/10/29.
 */
public class Calculator2 implements Runnable {
    private int number;

    public Calculator2(int number) {
        this.number = number;
    }

    @Override
    public void run() {
        for (int i = 0; i < number; i++) {
            System.out.printf("%s: %d\n", Thread.currentThread().getName(), i * number);
        }
    }

    public static void main(String[] args) {
        Calculator2 calculator2 = new Calculator2(5);
        Thread thread = new Thread(calculator2);
        thread.start();
    }
}
