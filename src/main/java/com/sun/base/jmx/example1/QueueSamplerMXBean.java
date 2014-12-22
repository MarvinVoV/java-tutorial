package com.sun.base.jmx.example1;

/**
 * Created by louis on 2014/12/22.
 */
public interface QueueSamplerMXBean {
    public QueueSample getQueueSample();
    public void clearQueue();
}
