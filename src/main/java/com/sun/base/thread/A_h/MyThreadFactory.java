package com.sun.base.thread.A_h;

import com.sun.base.utils.Utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by louis on 2014/11/2.
 */
public class MyThreadFactory implements ThreadFactory {

    private AtomicInteger threadNumber = new AtomicInteger(0);

    // Store thread name
    private String name;

    // Store statistical data about the Thread object created
    private List<String> stats;

    private ThreadGroup group;

    public MyThreadFactory(String name) {
        this.name = name;
        stats = new ArrayList<>();

        SecurityManager s = System.getSecurityManager();
        group = (s != null) ? s.getThreadGroup() :
                Thread.currentThread().getThreadGroup();
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(group,r, name + "-Thread_" + threadNumber.getAndIncrement());

        if (t.isDaemon())
            t.setDaemon(false);
        if (t.getPriority() != Thread.NORM_PRIORITY)
            t.setPriority(Thread.NORM_PRIORITY);

        stats.add(String.format("Created thread %d with name %s in group %s, on %s\n", t.getId(), t.getName(),t.getThreadGroup().getName(), Utils.dateFormat(new Date())));
        return t;
    }

    public String getStats() {
        StringBuffer buffer = new StringBuffer();
        for (String stat : stats) {
            buffer.append(stat);
            buffer.append("\n");
        }
        return buffer.toString();
    }
}
