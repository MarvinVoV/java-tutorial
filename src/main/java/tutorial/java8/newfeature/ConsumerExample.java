/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package tutorial.java8.newfeature;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author hufeng
 * @version $Id: ConsumerExample, v0.1 2017年06月29日 10:22 PM hufeng Exp $
 */
public class ConsumerExample {

    public static <T> void forEach(List<T> list, Consumer<T> c) {
        for (T item : list) {
            c.accept(item);
        }
    }

    public static void main(String[] args) {

        forEach(Arrays.asList("a", "b", "c"), (String s) -> System.out.println(s));

    }
}
