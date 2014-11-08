package com.sun.base.generics.A_b;

import java.util.List;
import java.util.Map;

/**
 * Created by louis on 2014/11/7.
 */
public class LimitsOfInference {
    static void f(Map<Integer, List<Thread>> map){

    }

    public static void main(String[] args) {
//        f(New.map()); //Does not compiles
        f(New.<Integer,List<Thread>>map()); // Should be like this
    }
}
