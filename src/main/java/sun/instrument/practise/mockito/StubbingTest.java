package sun.instrument.practise.mockito;

import org.junit.Test;
import org.mockito.Matchers;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.argThat;
import static org.mockito.Matchers.isNotNull;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by yamorn on 8/27/16.
 */
public class StubbingTest {

    @Test
    public void shouldReturnGivenValue(){
        Flower mockFlower = mock(Flower.class);

        when(mockFlower.getLeafsNumber()).thenReturn(5);

        int num = mockFlower.getLeafsNumber();

        assertEquals(num, 5);
    }

    @Test
    public void testArgumentsMatchers(){
        List listMock = mock(List.class);
        when(listMock.get(anyInt())).thenReturn("element");

        System.out.println(listMock.get(999));
    }

    /*
        Behavior Driven Development
     */
    @Test
    public void shouldReturnGivenValueUsingBDDSemantics(){
        Flower flowerMock = mock(Flower.class);

        // given
        given(flowerMock.getLeafsNumber()).willReturn(5);

        // when
        int num = flowerMock.getLeafsNumber();

        // then
        assertEquals(num, 5);

    }

    @Test(expected = RuntimeException.class)
    public void testStubVoidMethod(){
        Flower flowerMock = mock(Flower.class);
        doThrow(RuntimeException.class).when(flowerMock).doSelfCheck();

        flowerMock.doSelfCheck();
    }



}
