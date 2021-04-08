package com.zel.commonutils;

import java.math.BigDecimal;

/**
 * 用于价格
 */
public class BigDecimalUtil {

    public static void demo() {
        //BigDecimal(int)  创建一个具有参数所指定整数值的对象。
        new BigDecimal(1231);
        //不推荐使用
        //BigDecimal(double) 创建一个具有参数所指定双精度值的对象。
        // 如果是 double 推荐 valueOf 这种
        BigDecimal.valueOf(23.998);
        //BigDecimal(long) 创建一个具有参数所指定长整数值的对象。
        BigDecimal.valueOf(123L);
        //推荐使用 ，创建一个具有参数所指定以字符串表示的数值的对象。
        //BigDecimal(String)
        BigDecimal bigDecimal = new BigDecimal("123");

        // 保留两个小数
        double v = new BigDecimal(0.2).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();// 0.2

    }

    public static void main(String[] args) {
        BigDecimal t1 = BigDecimal.valueOf(123L);
        BigDecimal t2 = BigDecimal.valueOf(123L);
        t1 = t1.add(t2);
        System.out.println(t1);
    }
}
