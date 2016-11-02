/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package sun.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hufeng on 16/11/1.
 */
public class MapUtil {

    /**
     * Retrieves a value from map by key with default value
     *
     * @param map Map to retrieve item from
     * @param key The key whose associate value is to be returned.
     * @param def Value to return if item was not found.
     * @param <K> The type of key.
     * @param <V> The type of value.
     * @return Value that was found at the location key.
     */
    public static <K, V> V get(Map<K, V> map, K key, V def) {
        if (isEmpty(map)) return null;

        V val = map.get(key);
        return val == null ? def : val;
    }

    /**
     * Return null safe isEmpty check for map
     *
     * @param map map to be checked
     * @return return true if map is empty or null.
     */
    public static boolean isEmpty(Map map) {
        return map == null || map.isEmpty();
    }

    public static void main(String[] args) {
        Map map = new HashMap();
        map.put("a", "b");
        System.out.println(MapUtil.isEmpty(map));
    }
}
