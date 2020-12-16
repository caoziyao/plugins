package com.zel.market.utils;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    /**
     * 当天凌晨
     * @return
     */
    public static Date weeOfDate() {
        return weeOfDate(new Date());
    }

    /**
     * 该天凌晨
     * @param date 日期
     * @return
     */
    public static Date weeOfDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        //当前凌晨日期
        return calendar.getTime();
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
//        while (c1.getTimeInMillis() < c2.getTimeInMillis()) {
//            c1.add(Calendar.MINUTE, 30);
//            System.out.println(c1.getTime());
//        }
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
     */
    public static void add() {
        // Calendar 加减
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 1); // 目前時間加1個月
        c.add(Calendar.HOUR, 3); // 目前時間加3小時
        c.add(Calendar.YEAR, -2); // 目前時間減2年
        c.add(Calendar.DAY_OF_WEEK, 1); // 目前的時間加1天
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

    /**
     * 时间 +-
     */
    public static Date addTime(Date date, int day, int hours, int minute) {
        // Calendar 加减
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_WEEK, day);
        c.add(Calendar.HOUR, hours);
        c.add(Calendar.MINUTE, minute);
        return c.getTime();
    }

    /**
     * test: 遍历当天0点到24点，间隔 30 min
     */
    public static void test30Min() {
        Date wee = weeOfDate();
        Date nextDay = addTime(wee, 1, 0, 0);

        Calendar c1 = Calendar.getInstance();
        c1.setTime(wee);

        Calendar c2 = Calendar.getInstance();
        c2.setTime(nextDay);

        // 半小时遍历时间
        while (c1.getTimeInMillis() <= c2.getTimeInMillis()) {
            System.out.println(c1.getTime());
            c1.add(Calendar.MINUTE, 30);

        }
    }

    public static void main(String[] args) {
        test30Min();
    }
}
