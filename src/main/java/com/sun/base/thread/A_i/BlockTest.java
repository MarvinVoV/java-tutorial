package com.sun.base.thread.A_i;

import java.util.concurrent.TimeUnit;

/**
 * Created by louis on 2014/11/2.
 */
public class BlockTest extends Thread {
    private Block block;

    public BlockTest(Block block) {
        this.block=block;
    }

    @Override
    public void run() {
        block.read();
    }

    public static void main(String[] args) {
        Block block=new Block();
        BlockTest thread1=new BlockTest(block);
        BlockTest thread2=new BlockTest(block);
        thread1.start();
        thread2.start();

    }
    private static class Block{
        public void read(){
            System.out.printf("Thread %s start to read.\n", Thread.currentThread().getName());
            synchronized (this){
                System.out.printf("Current thread:%s\n",Thread.currentThread().getName());
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.printf("Thread %s read over.\n", Thread.currentThread().getName());
        }
    }
}
