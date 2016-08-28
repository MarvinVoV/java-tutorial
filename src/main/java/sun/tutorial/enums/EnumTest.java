package sun.tutorial.enums;

/**
 * Created by yamorn on 2016/7/22.
 */
public class EnumTest {
    public static void main(String[] args) {
        for (Day day : Day.values()) {
            System.out.println(day);
        }

        Day sunday = Day.valueOf("SUNDAY");
        System.out.println(sunday);


    }
}
