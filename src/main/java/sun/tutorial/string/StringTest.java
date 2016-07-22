package sun.tutorial.string;

import java.io.Console;

/**
 * Created by yamorn on 2016/7/22.
 */
public class StringTest {
    public static void main(String[] args) {
        /*
            Format
         */
        int i = 2;
        double r = Math.sqrt(i);

        // n : outputs a platform-specific line terminator
        System.out.format("The square root of %d is %f.%n", i, r);

        Console c = System.console();
        if(c == null){
            System.err.println("No console");
            System.exit(-1);
        }
        String line = c.readLine();
        System.out.println(line);
    }
}
