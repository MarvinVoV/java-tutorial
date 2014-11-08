package com.sun.base.generics.A_b;

/**
 * Created by louis on 2014/11/7.
 */

/*
    If a method is static,it has no access to the generic type parameters of the class,
    so if it needs to use generics it must be a generic method
 */
public class GenericMethods {
    public <T> void f(T x) {
        System.out.println(x.getClass().getName());
    }

    public static void main(String[] args) {
        GenericMethods gm=new GenericMethods();
        gm.f("");
        gm.f(1);
        gm.f(1.0);
        gm.f('c');
        gm.f(gm);
    }
}
