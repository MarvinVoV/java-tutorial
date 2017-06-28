/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.alipay.utils;

/**
 * @author hufeng
 * @version $Id: Example, v0.1 2017年06月28日 2:24 PM hufeng Exp $
 */
public class Example {

    public static void main(String[] args) {

//        // ====== case1
        String a = "a";
        String b = "b";
        if ("a".equalsIgnoreCase(b))
            System.out.println("a match");
        else
            System.out.println("b match");


        if ("b".equalsIgnoreCase(a))
            System.out.println("bbbb");
        else {
            System.out.println("cccc");
        }

        // ====== case2

        if ("a".equalsIgnoreCase(a) && "b".equalsIgnoreCase("c") &&
                "c".equalsIgnoreCase(a) &&
                 "d".equalsIgnoreCase(b))
            System.out.println("ccc");


        // ====== case 3
        if ("a".equalsIgnoreCase(a) && "b".equalsIgnoreCase("c") &&
                "c".equalsIgnoreCase(a) &&
                "d".equalsIgnoreCase(b)) {
            System.out.println("ccc");
        }

    }
}