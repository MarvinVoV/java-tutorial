/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package sun.tutorial.guava;
import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.*;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Created by hufeng on 16/11/1.
 */
public class ListsExample {
    public static void main(String[] args) {

        // create ArrayList
        List<String> arrayList = Lists.newArrayList("a", "b", "c");
        print(arrayList);

        // create linkList
        List<String> linkList = Lists.newLinkedList();


        // create HashSet
        Set<Integer> hashSet = Sets.newHashSet(1, 2, 3, 4, 5);

        // create LinkedHashSet
        Set<Integer> linkedHashSet = Sets.newLinkedHashSet();

        hashSet = Sets.filter(hashSet, new Predicate<Integer>() {
            @Override
            public boolean apply(Integer input) {
                return input % 2 == 0;
            }
        });
        print(hashSet);


        Map<String, Integer> hashMap = Maps.newHashMap();
        hashMap.put("a", 1);
        hashMap.put("b", 2);
        hashMap.put("c", 3);
        hashMap = Maps.filterEntries(hashMap, new Predicate<Map.Entry<String, Integer>>() {
            @Override
            public boolean apply(Map.Entry<String, Integer> input) {
                int value = input.getValue();
                return value % 2 == 0;
            }
        });

        System.out.println(hashMap.size());




    }

    private static void print(Collection collection){
        System.out.println(Arrays.toString(collection.toArray()));
    }





}
