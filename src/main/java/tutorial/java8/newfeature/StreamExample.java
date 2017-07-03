/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package tutorial.java8.newfeature;

import org.junit.Before;
import org.junit.Test;
import tutorial.java8.newfeature.model.Dish;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertTrue;

/**
 * @author hufeng
 * @version $Id: StreamExample, v0.1 2017年05月30日 3:47 PM hufeng Exp $
 */
public class StreamExample {

    private List<Dish> menu;

    @Before
    public void init() {
        menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 700, Dish.Type.MEAT),
                new Dish("french fries", true, 700, Dish.Type.OTHER),
                new Dish("rice", true, 530, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH)
        );
    }

    @Test
    public void testStreamBenefits() {
        List<String> lowCaloricDishesName = menu.parallelStream()
                .filter(d -> d.getCalories() < 400)
                .sorted(Comparator.comparing(Dish::getCalories))
                .map(Dish::getName)
                .collect(Collectors.toList());
        assertTrue(lowCaloricDishesName.size() > 0);
    }

    @Test
    public void testGroupBy() {
        Map<Dish.Type, List<Dish>> dishesByType = menu.stream().collect(Collectors.groupingBy(Dish::getType));
        assertTrue(dishesByType.size() > 0);
    }

    @Test
    public void testStreamLoop() {
        menu.forEach(System.out::println);
    }


    @Test
    public void testFilterUsage() {
        List<Dish> vegetarianDishes = menu.stream().filter(Dish::isVegetarian).collect(Collectors.toList());
        assertTrue(vegetarianDishes.size() > 0);
    }

    @Test
    public void testDistinctUsage() {
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
                .filter(i -> i % 2 == 0)
                .distinct()
                .forEach(System.out::println);
    }

    @Test
    public void testTruncateStreamUsage() {
        List<Dish> dishes = menu.stream()
                .filter(d -> d.getCalories() > 300)
                .limit(3)
                .collect(Collectors.toList());
        assertTrue(dishes.size() > 0);
    }

    @Test
    public void testSkipElementUsage() {
        List<Dish> dishes = menu.stream()
                .filter(d -> d.getCalories() > 300)
                .skip(2)
                .collect(Collectors.toList());
        assertTrue(dishes.size() > 0);
    }

    @Test
    public void testSimpleMappingUsage() {
        List<String> dishNames = menu.stream()
                .map(Dish::getName)
                .collect(Collectors.toList());
        assertTrue(dishNames.size() > 0);
    }

    @Test
    public void testMappingUsage() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> squares = numbers.stream()
                .map(n -> n * n)
                .collect(Collectors.toList());
        System.out.println(Arrays.toString(squares.toArray()));
    }

    @Test
    public void testFlatMapUsage() {
        List<String> words = Arrays.asList("Java8", "Lambdas", "In", "Action");
        List<String> uniqueCharacters = words.stream()
                .map(w -> w.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
        assertTrue(uniqueCharacters.size() > 0);
    }

    /**
     * [1,2,3] and [3,4] -> [(1,3), (1,4),(2,3),(2,4),(3,3),(3,4)]
     */
    @Test
    public void testFlagMapUsage2() {
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);
        List<int[]> pairs = numbers1.stream()
                .flatMap(i -> numbers2.stream().map(j -> new int[]{i, j}))
                .collect(Collectors.toList());
        assertTrue(pairs.size() > 0);
    }

    @Test
    public void testReduce() {
        List<Integer> numbers = Arrays.asList(1, 3, 5);
        int sum = numbers.stream().reduce(0, (a, b) -> a + b);
        assertTrue(sum == 9);

        Optional<Integer> max = numbers.stream().reduce(Integer::max);
        assertTrue(max.isPresent() && max.get() == 5);


        int count = numbers.stream().map(d -> 1).reduce(0, Integer::sum);
        assertTrue(count == 3);

    }


    @Test
    public void testStreamFromValues() {
        Stream<String> stream = Stream.of("java8", "lambda", "action");
        stream.map(String::toUpperCase).forEach(System.out::println);
    }

    @Test
    public void testStreamFromArrays() {
        int[] numbers = {2, 3, 4, 5, 6};
        int sum = Arrays.stream(numbers).sum();
        assertTrue(sum == 20);
    }




}
