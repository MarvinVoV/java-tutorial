/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package tutorial.java8.newfeature;

import tutorial.java8.newfeature.model.Apple;

import java.util.function.Supplier;

/**
 * @author hufeng
 * @version $Id: SupplierExample, v0.1 2017年06月30日 8:37 PM hufeng Exp $
 */
public class SupplierExample {
    public static void main(String[] args) {

        Supplier<Apple> c = Apple::new;
        System.out.println(c.get().getWeight());
    }
}
