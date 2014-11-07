package com.sun.base.generics.A_b;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by louis on 2014/11/7.
 */
public class New {
    public static <K,V> Map<K,V> map(){
        return new HashMap<K,V>();
    }
    public static <T> List<T> list(){
        return new ArrayList<T>();
    }
}
