package com.sun.base.generics.A_c;

/**
 * Created by louis on 2014/11/7.
 */
public class CountedObject {
    private static long counter=0;
    private final long id=counter++;
    public long id(){
        return id;
    }

    @Override
    public String toString() {
        return "CountObject "+id;
    }


}
