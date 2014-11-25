package com.sun.base.thread.D_c;

import java.util.concurrent.*;

/**
 * Created by yamorn on 2014/11/25.
 */

public class CancelTask {
    private static int[] count=new int[]{0};
    public static void main(String[] args) {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(3);
        Future future= executor.submit(new Monitor());

        for(int i=0;i<100;i++){
            count[0]=i+1;
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            TimeUnit.SECONDS.sleep(5);
            future.get();
            System.out.println(future.isDone());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("done");
//        executor.shutdown();
    }
    static class Monitor implements Runnable{

        @Override
        public void run() {
            boolean flag=true;
            while(flag){
                System.out.println("count="+count[0]);
                if(count[0]==100){
                    flag=false;
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    System.out.printf("Interrupt");
                    flag=false;
                }
            }
        }
    }
}
