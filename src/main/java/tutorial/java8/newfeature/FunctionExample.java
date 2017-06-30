/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package tutorial.java8.newfeature;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * @author hufeng
 * @version $Id: FunctionExample, v0.1 2017年06月29日 10:27 PM hufeng Exp $
 */
public class FunctionExample {

    public static <T, R> List<R> map(List<T> list, Function<T, R> f) {
        List<R> result = new ArrayList<>();
        for (T s : list) {
            result.add(f.apply(s));
        }
        return result;
    }

    public static void main(String[] args) {

        List<Integer> list = map(Arrays.asList("lambda", "hello"), (String s) -> s.length());
        System.out.println(Arrays.toString(list.toArray()));
    }
}
