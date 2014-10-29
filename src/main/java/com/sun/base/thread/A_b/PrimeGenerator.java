package com.sun.base.thread.A_b;

/**
 * Created by louis on 2014/10/29.
 */
public class PrimeGenerator extends Thread {
    @Override
    public void run() {
        long number=1L;
        while(true) {
            if (isPrime(number)) {
                System.out.printf("Number %d is Prime\n", number);
            }
            // 检查是否中断
            if (isInterrupted()) {
                System.out.printf("The Prime Generator has been Interrupted\n");
                return;
            }
            number++;
        }
    }

    private boolean isPrime(long number) {
        if (number <= 2) {
            return true;
        }
        for (long i = 2; i < number; i++) {
            if ((number % 2) == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Thread task=new PrimeGenerator();
        task.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //中断线程
        task.interrupt();
    }
}
