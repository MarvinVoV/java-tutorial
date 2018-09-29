package tutorial.lambda;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author hufeng
 * @version ReduceTutorial.java, v 0.1 2018/9/29 1:01 AM Exp $
 */

public class ReduceTutorial {
    static List<Integer> numbers = Lists.newArrayList(1, 2, 3, 5, 4);

    @Test
    public void testSum() {
        int sum = numbers.stream().reduce(0, Integer::sum);
        assertEquals(15, sum);
    }

    @Test
    public void testMax() {
        int max = numbers.stream().reduce(0, Integer::max);
        assertEquals(max, 5);


        IntStream intStream = IntStream.rangeClosed(0, 10).filter(n -> n % 2 == 0);
        System.out.println(intStream.count());


        Stream<int[]> pythagoreanTriples = IntStream.rangeClosed(1, 10).boxed()
                .flatMap(a -> IntStream.rangeClosed(a, 10)
                        .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                        .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)}));

        pythagoreanTriples.forEach(item -> System.out.println(Arrays.toString(item)));
    }


}
