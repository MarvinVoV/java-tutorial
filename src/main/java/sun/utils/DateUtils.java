/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package sun.utils;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.Test;

import java.util.*;

/**
 * Created by hufeng on 16/11/3.
 */
public class DateUtils {
    public static void main(String[] args) {
        Date date = getCurrentHour();
        String str = DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss");
        System.out.println(str);
    }

    /**
     * 获取一天的开始 即零点
     *
     * @return
     */
    public static Date getStartOfToday() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取一天的结束 即24点
     *
     * @return
     */
    public static Date getEndOfDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 24);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }


    /**
     * 获取当前的整点时刻
     */
    private static Date getCurrentHour(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

}
