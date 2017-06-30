/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package tutorial.java8.newfeature;

import java.util.function.Function;

/**
 * @author hufeng
 * @version $Id: ComposingFunction, v0.1 2017年06月30日 9:00 PM hufeng Exp $
 */
public class ComposingFunction {
    public static void main(String[] args) {

        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;

        Function<Integer, Integer> h = f.andThen(g); // g(f(x))
        int result = h.apply(3);
        System.out.println(result);


        Function<Integer, Integer> k = f.compose(g); // f(g(x))
        result = k.apply(3);

        System.out.println(result);
    }
}
