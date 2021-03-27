package com.zel.commonutils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

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

    public static final SimpleDateFormat YMD_HMS_2 = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");

    public static final SimpleDateFormat YMD_HMS_3 = new SimpleDateFormat("MM-dd-HH:mm");
    /**
     * 当天0点
     * @param date
     * @return
     */
    public static Date dawnOf(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return  calendar.getTime();
    }

    /**
     * 当天 23：59:59
     * @param date
     * @return
     */
    public static Date nightOf(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 0);
        return  calendar.getTime();
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

    public static String format(Date date) {
        return YMD_HMS.format(date);
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
     * @param date
     * @param minute
     * @return
     */
    public static Date addMinute(Date date, int minute) {
        return add(date, 0, 0, 0, minute);
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
        Date wee = dawnOf(new Date());
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

    /**
     * java.util.function
     * 按天数遍历
     *
     * https://www.runoob.com/java/java8-functional-interfaces.html
     *
     * Predicate<T>	T	boolean	用来比较操作
     * Consumer<T>	T	void	没有返回值的函数
     * Function<T, R>	T	R	有返回值的函数
     * Supplier<T>	None	T	工厂方法-返回一个对象
     * UnaryOperator<T>	T	T	入参和出参都是相同对象的函数
     * BinaryOperator<T>	(T,T)	T	求两个对象的操作结果
     *
     */
    public static void foreach(Date date1, Date date2, BiConsumer<Calendar, Calendar> func) {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(date1);

        Calendar c2 = Calendar.getInstance();
        c2.setTime(date2);

        while (c1.getTimeInMillis() < c2.getTimeInMillis()) {
            func.accept(c1, c2);
            c1.add(Calendar.DAY_OF_WEEK, 1);
        }
    }

    public static void test() {
        foreach(new Date(), add(new Date(), 7), (c1, c2) -> {
            System.out.println(c1.getTime());
        });
    }

    public static void main(String[] args) {
        long a = new Random().nextInt(100);
        System.out.println(a);
    }
}
