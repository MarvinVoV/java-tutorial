/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package sun.utils;


import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by hufeng on 16/11/2.
 */
public class MyTest {
    public static void main(String[] args) {
        List<Integer> list = new LinkedList<>();
        for(int i=0;i<=16;i++){
            list.add(i);
        }
        System.out.println(Arrays.toString(list.toArray()));

        int size = list.size();
        if(size > 5){
            int p = 0;
            while( p < size){
                int q = p + 5;
                List<Integer> sub = list.subList(p, q > size ? size : q);
                System.out.println(Arrays.toString(sub.toArray()));
                p = q;
            }

        }

    }
}
