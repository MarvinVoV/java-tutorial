package com.sun.base.generics.A_a;

/**
 * Created by louis on 2014/11/7.
 */
public class TwoTuple<A,B> {
    public final A first;
    public final B second;

    public TwoTuple(A first, B second) {
        this.first = first;
        this.second = second;
    }

    public String toString(){
        return "("+first+", "+second+" )";
    }

    public static void main(String[] args) {
        TwoTuple<Integer,Double> twoTuple=new TwoTuple<>(2,3.0);
        System.out.println(twoTuple.toString());
//        twoTuple.second=5.0;  // Error
    }
}
