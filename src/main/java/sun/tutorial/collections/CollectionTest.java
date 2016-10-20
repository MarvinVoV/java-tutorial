package sun.tutorial.collections;

import java.util.*;

/**
 * Created by yamorn on 2016/7/22.
 */
public class CollectionTest {
    public static void main(String[] args) {
        List<Integer> list = new LinkedList<>();
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(8);
        list.add(6);
        list.add(12);
        list.add(7);
        list.add(24);
        list.add(60);

        /*
            Traverse 1
//         */
//        list.stream().filter(e -> e % 2 == 0)
//                .forEach(e -> System.out.println(e.toString()));
//
//        /*
//            Traverse 2
//         */
//        for (Iterator<Integer> iterator = list.iterator(); iterator.hasNext(); ) {
//            if (iterator.hasNext()) {
//                System.out.println(iterator.next());
//            }
//        }
//
//        list.removeAll(Collections.singleton(3));
//        System.out.println(list);
//
//        Integer[] a = list.toArray(new Integer[0]);
//        System.out.println(Arrays.toString(a));
//
//        Integer[] temp = new Integer[10];
//        list.toArray(temp);
//        System.out.println(Arrays.toString(temp));
//
//        list.sort((o1, o2) -> o1 > o2 ? 1 : (Objects.equals(o1, o2) ? 0 : -1));
//
//        System.out.println(list);

    }

}
