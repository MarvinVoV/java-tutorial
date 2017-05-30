/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package tutorial.java8.newfeature;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.*;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author hufeng
 * @version $Id: Example1, v0.1 2017年05月24日 4:34 PM hufeng Exp $
 */
public class Example1 {

    private List<Integer> intList = new ArrayList<>();

    private List<String> strList = new ArrayList<>();

    @Before
    public void init() {
        intList.addAll(Arrays.asList(1, 2, 3, 5, 6, 7));
        strList.addAll(Arrays.asList("a", "d", "e", "f"));
    }

    @Test
    public void testMethodReference() {
        File[] hiddenFiles = new File(".").listFiles(File::isHidden);
        assert hiddenFiles != null;
        for (File file : hiddenFiles) {
            System.out.println(file.getName());
        }
    }

    @Test
    public void testSequentialStream() {
        intList = intList.stream().filter((Integer it) -> it > 3).collect(toList());
        System.out.println(intList.size());
    }

    @Test
    public void testParallelStream() {
        intList = intList.parallelStream().filter((Integer it) -> it > 3).collect(toList());
        System.out.println(intList.size());
    }

    @Test
    public void testSort() {
        intList.sort(Integer::compareTo);
    }

    @Test
    public void testLambda() {
        Thread t = new Thread(() -> System.out.println("Hello"));
        t.run();
    }

    @Test
    public void testFunctionInterface() {
        Runnable r = () -> System.out.print("run");
        r.run();
    }

    @Test
    public void testUseConsumer() {
        foreach(Arrays.asList(1, 2, 4, 5, 6), System.out::println);
    }

    @Test
    public void testUseFunction() {
        List<Integer> list = map(Arrays.asList("lambda", "in"), String::length);
        System.out.println(Arrays.toString(list.toArray()));
    }

    @Test
    public void testUseIntPredicate() {
        // avoid boxing
        IntPredicate evenNumber = (int i) -> i % 2 == 0;
        boolean r = evenNumber.test(1000);
        assertTrue(r);

        // Note that when a lambda has just one parameter whose type is inferred, the parentheses
        // surrounding the parameter name can also be omitted.
//        Predicate<Integer> oddNumbers = i -> i % 2 == 1;
        Predicate<Integer> oddNumbers = (Integer i) -> i % 2 == 1;
        r = oddNumbers.test(1000);
        assertTrue(!r);
    }

    @Test
    public void testCollectionSort() {
        List<Apple> inventory = new ArrayList<>();
        inventory.sort(comparing(Apple::getWeight).reversed().thenComparing(Apple::getCountry));
    }

    @Test
    public void testMethodReferenceUsage() {
        // construct reference
        Supplier<List> s = ArrayList::new;
        List l = s.get();
        assertNotNull(l);
    }

    private static <T> void foreach(List<T> list, Consumer<T> c) {
        for (T t : list) {
            c.accept(t);
        }
    }

    private static <T, R> List<R> map(List<T> list, Function<T, R> f) {
        List<R> result = new ArrayList<>();
        for (T s : list) {
            result.add(f.apply(s));
        }
        return result;
    }

    class Apple {
        int weight;
        String country;

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }
    }

}
