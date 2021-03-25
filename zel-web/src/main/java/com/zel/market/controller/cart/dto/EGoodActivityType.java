package com.zel.market.controller.cart.dto;

public enum EGoodActivityType {
    GIFTAND(1, "购买赠送活动"),

    GIFTOR(2, "完成任务赠送活动"),

    REFER_GOOD(4, "用户邀请活动"),

    ;
    private final int code;
    private final String name;

    EGoodActivityType(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int toCode(){
        return code;
    }

    public String toName() {
        return name;
    }

}
