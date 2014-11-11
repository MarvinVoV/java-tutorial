package com.sun.base.thread.B_d;

/**
 * Created by louis on 2014/11/11.
 */
public class Grouper implements Runnable {
    private Result result;

    public Grouper(Result result) {
        this.result = result;
    }


    @Override
    public void run() {
        int finalResult=0;
        System.out.printf("Grouper: Processing results...\n");
        int data[]=result.getData();
        for(int number:data){
            finalResult+=number;
        }
        System.out.printf("Grouper: Total result: %d.\n",finalResult);
    }
}
