/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package tutorial.java8.newfeature;

import tutorial.java8.newfeature.model.Apple;

import java.util.Comparator;

/**
 * @author hufeng
 * @version $Id: ComparatorExample, v0.1 2017年06月30日 8:14 PM hufeng Exp $
 */
public class ComparatorExample {
    public static void main(String[] args) {

        Comparator<Apple> c = (Apple a1, Apple a2) -> a1.getWeight() > a2.getWeight() ? 1 : -1;

        Comparator<Apple> c2 = (a1, a2) -> a1.getWeight() > a2.getWeight() ? 1 : -1;
        
    }
}
