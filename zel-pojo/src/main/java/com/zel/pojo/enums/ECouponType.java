package com.zel.pojo.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * 优惠券类型
 */
public enum  ECouponType {
    DISCOUNT(1, "折扣"),

    REDUCE(2, "满减"),;

    private int code;
    private String desc;

    ECouponType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int code() {
        return code;
    }

    public String desc() {
        return desc;
    }

    public static List<Integer> allCodes() {
        List<Integer> codes = new ArrayList<>();
        ECouponType[] types = ECouponType.values();
        for (ECouponType type : types) {
            codes.add(type.code);
        }
        return codes;
    }
}
