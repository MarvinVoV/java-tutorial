package com.sun.base.thread.D_d;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by yamorn on 2014/11/25.
 */
public class Main {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor=(ThreadPoolExecutor) Executors.newFixedThreadPool(1);
        ProgressReport progressReport = new ProgressReport();

        progressReport.build(threadPoolExecutor, new MyReportHandler(new ProgressStatus())).start();
        System.out.println(threadPoolExecutor.getActiveCount());
        progressReport.build(threadPoolExecutor, new MyReportHandler(new ProgressStatus())).start();
        System.out.println(threadPoolExecutor.getActiveCount());


        System.out.println("Main done.");
    }
    static class MyReportHandler extends ReportHandler{
        private ProgressStatus status;
        public MyReportHandler(ProgressStatus progressStatus) {
            super(progressStatus);
        }

        @Override
        public void init() {
            status=getProgressStatus();
            status.setPassed(0);
            status.setTotal(100);
        }

        @Override
        public void doJob() throws Exception {
            for(int i=0;i<100;i++){
                status.setPassed(status.getPassed()+1);
                TimeUnit.MILLISECONDS.sleep(500);
                System.out.println("doJob:"+status.getPassed());
            }
        }

        @Override
        public void report(long passed) {
            System.out.println("passed:"+passed);
//            try {
//                Thread.sleep(400);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

        }
    }
}

