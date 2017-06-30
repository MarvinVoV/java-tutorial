/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package tutorial.java8.newfeature;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author hufeng
 * @version $Id: PredicateExample, v0.1 2017年06月29日 10:12 PM hufeng Exp $
 */
public class PredicateExample {

    public static <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> result = new ArrayList<>();
        for (T item : list) {
            if (p.test(item)) {
                result.add(item);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.addAll(Arrays.asList("hello", "world", "", "like"));

        List<String> nonEmpty = filter(list, (String s) -> !s.isEmpty());
        System.out.println(Arrays.toString(nonEmpty.toArray()));


    }
}
