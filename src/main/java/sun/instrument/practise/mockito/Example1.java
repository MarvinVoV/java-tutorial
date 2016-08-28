package sun.instrument.practise.mockito;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * Created by yamorn on 8/27/16.
 */
public class Example1 {

    @Test
    public void testHello(){
        // mock creation
        List mockedList = mock(List.class);

        // using mock object - it does not throw any "unexpected interaction" exception
        mockedList.add("one");
        mockedList.clear();

        // selective, explicit, highly readable verification
        verify(mockedList).add("one");
        verify(mockedList).clear();
    }

    @Test
    public void testWord(){
        // you can mock concrete classes, not only interfaces
        LinkedList mockList = mock(LinkedList.class);

        // stubbing appears before the actual execution
        when(mockList.get(0)).thenReturn("first");

        // the following prints "first"
        System.out.println(mockList.get(0));

        // the following prints "null" because get(999) was not stubbed
        System.out.println(mockList.get(999));
    }
}
