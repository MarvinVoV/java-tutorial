package com.sun.base.jmx.example1;

/**
 * Created by louis on 2014/12/22.
 */
public interface HelloMBean {
    public void sayHello();
    public int add(int x, int y);

    public String getName();

    public int getCacheSize();
    public void setCacheSize(int size);
}
