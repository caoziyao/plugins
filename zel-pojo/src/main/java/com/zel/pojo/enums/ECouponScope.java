package com.zel.pojo.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * 优惠券使用商品范围
 */
public enum ECouponScope {


    ALL(1, "所有商品"),

    TYPE(2, "指定品类商品"),

    GOOD(3, "具体商品"),

    ACTIVITY(4, "活动"),


    ;

    private int code;
    private String desc;

    ECouponScope(int code, String desc) {
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
        ECouponScope[] values = ECouponScope.values();
        for (ECouponScope value : values) {
            codes.add(value.code);
        }
        return codes;
    }

}
