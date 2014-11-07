package com.sun.base.generics.A_e;

import java.util.*;

/**
 * Created by louis on 2014/11/7.
 */

/*
    There's no information about generic parameter types available inside generic code.
 */
public class LostInformation {
    class Frob{}
    class Fnorkle{}
    class Quark{}
    public static void main(String[] args) {
        List<Frob> list = new ArrayList<Frob>();
        Map<Frob,Fnorkle> map = new HashMap<Frob,Fnorkle>();
        System.out.println(Arrays.toString(list.getClass().getTypeParameters()));
        System.out.println(Arrays.toString(map.getClass().getTypeParameters()));
    }
}
