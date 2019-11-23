/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */

import org.apache.commons.lang3.CharUtils;

/**
 * @author hufeng
 * @version 1.0: ChineseToUnicode.java, v 0.1 2019年11月23日 17:43 hufeng Exp $
 */
public class ChineseToUnicode {
    /**
     * 将字符串中包含的中文转成unicode
     *
     * @param str string
     * @return
     */
    private static String cnToUnicode(String str) {
        char[] chars = str.toCharArray();
        char start = '\u4e00';
        char end = '\u9fa5';
        StringBuilder sb = new StringBuilder();
        for (char c : chars) {
            if ((int) start <= c && (int) end >= c) {
                sb.append(CharUtils.unicodeEscaped(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}