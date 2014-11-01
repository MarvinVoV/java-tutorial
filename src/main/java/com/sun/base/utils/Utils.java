package com.sun.base.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by louis on 2014/11/1.
 */
public class Utils {
    public static String dateFormat(Date date, String... pattern) {
        String dateStr = null;
        if (pattern.length==0) {
            pattern=new String[]{"yyyy-MM-dd HH:mm:ss"};
        }
        SimpleDateFormat sf = new SimpleDateFormat(pattern[0]);
        dateStr = sf.format(date);
        return dateStr;
    }
}
