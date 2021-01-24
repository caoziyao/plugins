package com.zel.market.utils;

import java.util.concurrent.TimeUnit;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2020/5/22
 */
public class TimeUnitTest {
    public static void main(String[] args) {
        long s = TimeUnit.MINUTES.toMillis(1);  // 转换为毫秒
        System.out.println(s);
    }
}
