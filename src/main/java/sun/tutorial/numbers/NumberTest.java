package sun.tutorial.numbers;

import java.text.DecimalFormat;

/**
 * Created by yamorn on 2016/7/22.
 */
public class NumberTest {
    public static void main(String[] args) {
        Integer a = 20;

        /*
            Decode
         */
        System.out.println(Integer.decode("0x11"));
        System.out.println(Integer.valueOf("11", 16));

        System.out.println(Integer.decode("011"));  // OctalDigits

       /*
            Compare
        */

        int compare = Integer.compare(2, 3);
        System.out.println(compare);

        compare = a.compareTo(4);
        System.out.println(compare);

        /*
            to String
         */
        System.out.println(Integer.toOctalString(11));

        System.out.println(Integer.toBinaryString(11));

        System.out.println(Integer.toHexString(11));

        System.out.println(Integer.toString(11, 8));

        /*
            format
         */
        DecimalFormat format = new DecimalFormat("###,###.###");
        System.out.println(format.format(123456.789));


    }
}
