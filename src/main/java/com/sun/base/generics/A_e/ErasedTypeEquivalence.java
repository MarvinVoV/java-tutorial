package com.sun.base.generics.A_e;

import java.util.ArrayList;

/**
 * Created by louis on 2014/11/7.
 */
public class ErasedTypeEquivalence {
    public static void main(String[] args) {
        Class c1 = new ArrayList<String>().getClass();
        Class c2 = new ArrayList<Integer>().getClass();
        System.out.println(c1 == c2); // true
        ///:~
    }
}
