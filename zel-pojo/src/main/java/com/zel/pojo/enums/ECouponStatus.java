package com.zel.pojo.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * 优惠券状态
 */
public enum ECouponStatus {


    UNUSE(0, "未使用"),

    USED(1, "已使用"),

    EXPIRE(2, "已过期"),

    ;

    private int code;
    private String desc;

    ECouponStatus(int code, String desc) {
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
        ECouponStatus[] values = ECouponStatus.values();
        for (ECouponStatus value : values) {
            codes.add(value.code);
        }
        return codes;
    }
}
