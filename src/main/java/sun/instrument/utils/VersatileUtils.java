package sun.instrument.utils;

import java.util.HashMap;

/**
 * Created by 264929 on 2016/5/11.
 *
 * Versatile utility
 */
public class VersatileUtils {

    private VersatileUtils(){
        throw new AssertionError();
    }

    public static <K, V> HashMap<K, V> newHashMap(){
        return new HashMap<K, V>();
    }

    public static void main(String[] args) {
        VersatileUtils utils = new VersatileUtils();

    }
}
