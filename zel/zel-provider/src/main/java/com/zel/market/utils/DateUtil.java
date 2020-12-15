package com.zel.market.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    public static void test() {
        // 不传参数默认当前日期
        Date nowDate = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);

        //当前凌晨日期
        Date startDate = calendar.getTime();
        System.out.println(startDate);
    }
}
