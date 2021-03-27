package com.zel.market.app.controller.cart.dto;

import java.util.ArrayList;
import java.util.List;

public enum  EShopGoodStatus {

    SCHEDULE_ENABLE(-2, "预设上架"),

    NOT_FOR_SALE(0, "非卖品"),

    UNENABLE(-1, "下架"),

    ENABLE(1, "上架"),

    ;


    private final int code;
    private final String desc;

    EShopGoodStatus(int code, String desc) {
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
        EShopGoodStatus[] values = EShopGoodStatus.values();
        for (EShopGoodStatus value : values) {
            codes.add(value.code);
        }
        return codes;
    }
}
