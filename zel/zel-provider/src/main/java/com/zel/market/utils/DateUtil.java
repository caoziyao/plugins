package com.zel.market.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    /**
     * 时间format
     */
    public static void format() {
        Date now = new Date( );
        SimpleDateFormat ft = new SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
        System.out.println("Current Date: " + ft.format(now));
    }

    /**
     * 时间比较 before, after
     */
    public static void compare() throws ParseException {
        String beginTime = "2018-07-28 14:42:32";
        String endTime = "2018-07-29 16:26:32";

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = format.parse(beginTime);
        Date date2 = format.parse(endTime);

        boolean before = date1.before(date2);
        System.out.println(before);

        Calendar c1 = Calendar.getInstance();
        c1.setTime(date1);

        Calendar c2 = Calendar.getInstance();
        c2.setTime(date2);


        // 半小时遍历时间
        while (c1.getTimeInMillis() < c2.getTimeInMillis()) {
            c1.add(Calendar.MINUTE, 30);
            System.out.println(c1.getTime());
        }

    }


    /**
     * 时间比较  getTime
     */
    public static void compare2() throws ParseException {
        String beginTime = "2018-07-28 14:42:32";
        String endTime = "2018-07-29 12:26:32";

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = format.parse(beginTime);
        Date date2 = format.parse(endTime);

        long beginMillisecond = date1.getTime();
        long endMillisecond = date2.getTime();

        System.out.println(beginMillisecond > endMillisecond);

        boolean before = date1.before(date2);
        System.out.println(before);


    }

    /**
     * 时间 +-
     * @param add
     */
    public static void add() {
        // Calendar 加减
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 1); // 目前時間加1個月
        c.add(Calendar.HOUR, 3); // 目前時間加3小時
        c.add(Calendar.YEAR, -2); // 目前時間減2年
        c.add(Calendar.DAY_OF_WEEK, 7); // 目前的時間加7天
        c.add(Calendar.MINUTE, +2);  // 加2分钟
        System.out.println(c.getTime());

        // Date 转化 Calendar 在加减
        String beginTime = "2018-07-28 14:42:32";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date1 = format.parse(beginTime);
            c.setTime(date1);
            c.add(Calendar.MINUTE, +2);  // 加2分钟
            System.out.println(c.getTime());

        } catch (ParseException e) {
            e.printStackTrace();
        }




    }

    public static void main(String[] args) {


            try {
                DateUtil.compare();
                add();
            } catch (ParseException e) {
                e.printStackTrace();
            }

    }
}
