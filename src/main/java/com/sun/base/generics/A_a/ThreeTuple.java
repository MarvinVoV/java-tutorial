package com.sun.base.generics.A_a;



/**
 * Created by louis on 2014/11/7.
 */
public class ThreeTuple<A,B,C> extends TwoTuple<A,B> {
    private final C third;
    public ThreeTuple(A first, B second,C third) {
        super(first, second);
        this.third=third;
    }

    public String toString(){
        return "("+first+" ."+second+" ."+third+" )";
    }
}
