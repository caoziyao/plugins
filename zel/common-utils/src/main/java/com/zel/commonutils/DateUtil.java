package com.zel.commonutils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    // 建议使用 TimeUtil

    public static final long MILLISECOND = 1000;

    public static final long SECOND = 1;

    public static final long MINUTE = 60 * SECOND;

    public static final long HOUR = 60 * MINUTE;

    public static final long DAY = 24 * HOUR;

    public static final SimpleDateFormat HMS = new SimpleDateFormat("HH:mm:ss");
    public static final SimpleDateFormat YMD_HMS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat YMD = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 当天凌晨
     *
     * @return
     */
    public static Date dawnOfToday() {
        return dawnOfDate(new Date());
    }

    /**
     * 该天凌晨 0 点
     *
     * @param date 日期
     * @return
     */
    public static Date dawnOfDate(Date date) {
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
     * 当天 24 点
     */
    public static Date nightOfDate(Date day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(day);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        calendar.add(Calendar.DAY_OF_WEEK, 1);
        return calendar.getTime();
    }

    /**
     * 格式化
     *
     * @param date
     * @param format
     * @return
     */
    public static String format(Date date, SimpleDateFormat format) {
        return format.format(date);
    }

    /**
     * 时间format
     */
    public static void testFormat() {
        Date now = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
        System.out.println("Current Date: " + ft.format(now));

        String beginTime = "2018-07-28 14:42:32";
        String endTime = "2018-07-29 16:26:32";

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date1 = format.parse(beginTime);
            Date date2 = format.parse(endTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }


    /**
     * 时间比较  getTime
     */
    public static boolean compare(Date d1, Date d2) {
        return d1.getTime() > d2.getTime();
    }

    /**
     * 时间 +- 天数
     */
    public static Date add(Date date, int day) {
        return add(date, 0, day, 0, 0);
    }

    /**
     * 时间 +-
     */
    public static Date add(Date date, int year, int day, int hours, int minute) {
        // Calendar 加减
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.YEAR, year);
        c.add(Calendar.DAY_OF_WEEK, day);
        c.add(Calendar.HOUR, hours);
        c.add(Calendar.MINUTE, minute);
        return c.getTime();
    }

    /**
     * 时间 +-
     */
    public static void testAdd() {
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
     * 时间比较 before, after
     */
    public static void testCompare() throws ParseException {
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
    }


    /**
     * test: 遍历当天0点到24点，间隔 30 min
     */
    public static void test30Min() {
        Date wee = dawnOfToday();
        Date nextDay = add(wee, 1);

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


    /**
     * test
     */
    public static void testTody() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        Date todayDawn = calendar.getTime(); //当天 0 点
        Date now = new Date();

        calendar.add(Calendar.DAY_OF_WEEK, -1);
        Date yesterdayDawn = calendar.getTime(); // 昨天 0 点

        calendar.add(Calendar.DAY_OF_WEEK, -6);
        Date lastWeekDawn = calendar.getTime(); // 昨天晚上

        calendar.add(Calendar.DAY_OF_WEEK, +1);
        Date lastWeekNight = calendar.getTime(); // 昨天晚上

        System.out.println(todayDawn);
        System.out.println(now);
        System.out.println(yesterdayDawn);
        System.out.println(lastWeekDawn);
        System.out.println(lastWeekNight);
    }

    public static void main(String[] args) {
        testTody();
    }
}
