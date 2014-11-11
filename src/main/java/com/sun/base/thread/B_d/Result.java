package com.sun.base.thread.B_d;

/**
 * Created by louis on 2014/11/11.
 */

/**
 * Auxiliary class
 */
public class Result {
    private int data[];

    public Result(int size) {
        this.data = new int[size];
    }

    public void setData(int position,int value){
        data[position]=value;
    }

    public int[] getData(){
        return data;
    }
}
