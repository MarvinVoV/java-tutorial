package com.sun.base.generics.A_f;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by louis on 2014/11/7.
 */
public class ArrayMaker<T> {
    private Class<T> kind;

    public ArrayMaker(Class<T> kind) {
        this.kind = kind;
    }
    @SuppressWarnings("unchecked")
    T[] create(int size) {
        return (T[]) Array.newInstance(kind, size);
    }

    public static void main(String[] args) {
        ArrayMaker<String> stringMaker = new ArrayMaker<>(String.class);
        String[] stringArray = stringMaker.create(9);
        // output [null, null, null, null, null, null, null, null, null]
        System.out.println(Arrays.toString(stringArray));
    }
}
