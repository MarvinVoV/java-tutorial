package com.sun.base.thread.D_d;

import java.util.concurrent.*;

/**
 * Created by yamorn on 2014/11/25.
 */
public class ProgressReport {
    private ReportHandler reportHandler;
    private ThreadPoolExecutor threadPoolExecutor;
    private ProgressReportTask reportTask;
    public ProgressReport(){}
    public ProgressReport(ThreadPoolExecutor threadPoolExecutor,ReportHandler reportHandler){
        this.reportHandler=reportHandler;
        this.reportTask=new ProgressReportTask(reportHandler);
        this.threadPoolExecutor=threadPoolExecutor;
    }
    public ProgressReport(ThreadPoolExecutor threadPoolExecutor,ReportHandler reportHandler,long interval){
        this.reportHandler=reportHandler;
        this.reportTask=new ProgressReportTask(reportHandler,interval);
        this.threadPoolExecutor=threadPoolExecutor;
    }
    public ProgressReport build(ThreadPoolExecutor threadPoolExecutor,ReportHandler reportHandler){
        this.threadPoolExecutor=threadPoolExecutor;
        this.reportHandler=reportHandler;
        this.reportTask=new ProgressReportTask(reportHandler);
        return this;
    }
    public ProgressReport build(ThreadPoolExecutor threadPoolExecutor,ReportHandler reportHandler,long interval){
        this.threadPoolExecutor=threadPoolExecutor;
        this.reportHandler=reportHandler;
        this.reportTask=new ProgressReportTask(reportHandler,interval);
        return this;
    }
    public void start(){
        reportHandler.init();
        Future future=threadPoolExecutor.submit(reportTask);
        try {
            reportHandler.doJob();
            Thread.sleep(reportTask.getInterval());
            if(!future.isDone()) {
                future.get(reportTask.getInterval(), TimeUnit.MILLISECONDS);
            }
        } catch (TimeoutException e) {
            //e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Release report thread
            future.cancel(true);
        }
    }
    public void destory(){
        threadPoolExecutor.shutdown();
    }

    private class ProgressReportTask implements Runnable{
        private ProgressStatus progressStatus;
        private long interval=200;// Default
        private ReportHandler reportHandler;

        public ProgressReportTask(ReportHandler reportHandler) {
            this.reportHandler=reportHandler;
            this.progressStatus=reportHandler.getProgressStatus();
        }
        public ProgressReportTask(ReportHandler reportHandler, long interval) {
            if(interval!=0){
                this.interval = interval;
            }
            this.progressStatus=reportHandler.getProgressStatus();
            this.reportHandler=reportHandler;
        }
        public long getInterval() {
            return interval;
        }
        @Override
        public void run() {
            boolean flag = true;
            while (flag) {
                if(progressStatus.getPassed()==0){
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        flag = false;
                    }
                    continue;
                }
                reportHandler.report(progressStatus.getPassed());
                if (progressStatus.getPassed() == progressStatus.getTotal()) {
                    flag = false;
                }
                try {
                    Thread.sleep(interval);
                } catch (InterruptedException e) {
                    flag = false;
                }
            }
            System.out.printf("Thread release.");
        }
    }
}
