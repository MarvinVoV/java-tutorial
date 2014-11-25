package com.sun.base.thread.D_d;

/**
 * Created by yamorn on 2014/11/25.
 */
public abstract class ReportHandler {
    private ProgressStatus progressStatus;
    public ReportHandler(ProgressStatus progressStatus) {
        this.progressStatus=progressStatus;
    }
    public ProgressStatus getProgressStatus(){
        return this.progressStatus;
    }
    public abstract void init();
    public abstract void doJob() throws Exception;
    public abstract void report(long passed);
}
