package com.zel;

import java.math.BigDecimal;

public class MathUtils {
    public static void main(String[] args) {
        BigDecimal b1 = new BigDecimal(Double.toString(2));
        BigDecimal b2 = new BigDecimal(Double.toString(3));
        String v = b1.divide(b2, 4, BigDecimal.ROUND_HALF_UP).doubleValue() * 100 + "%";
        System.out.println(v);
    }
}
