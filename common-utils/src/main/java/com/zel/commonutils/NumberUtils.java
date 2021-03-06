package com.zel.commonutils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class NumberUtils {
    public static void main(String[] args) {
        String v = NumberUtils.rate(1, 13);
    }


    /**
     * rate = a / b
     *
     * @param a
     * @param b
     * @return
     */
    static String rate(int a, int b) {
        if (b == 0) {
            return "0%";
        }

        BigDecimal bd = new BigDecimal((float) a / b * 100);
        String s1 = bd.setScale(2, RoundingMode.HALF_UP) + "%";
        System.out.println(s1);
        return s1;
    }
}
