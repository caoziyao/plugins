package com.zel.market.controller.order.dto;

public enum EPayStatusCode {
    INVALID(-4, "失效订单"),
    WAITPAY(1, "等待收款"),
    WAITUSERPAY(2, "等待用户付款"),
    CHECKED(3, "已确认"),
    PAYED(4, "已付款"),
    REFUNDING(5, "退款中"),
    REFUND(6, "已退款");

    private int code;
    private String name;

    EPayStatusCode(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int toCode(){
        return code;
    }
}
