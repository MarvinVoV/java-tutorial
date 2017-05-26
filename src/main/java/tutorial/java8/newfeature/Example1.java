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

import static java.util.stream.Collectors.toList;

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


}
