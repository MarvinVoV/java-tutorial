package com.sun.base.thread.A_a;

/**
 * Created by louis on 2014/10/29.
 */
public class Calculator extends Thread {
    private int number;

    public Calculator(int number) {
        this.number=number;
    }

    @Override
    public void run() {
        for (int i = 0; i < number; i++) {
            System.out.printf("%s: %d\n",Thread.currentThread().getName(),i*number);
        }
    }

    public static void main(String[] args) {
        Calculator calculator=new Calculator(5);
        calculator.start();
    }
}
