package com.zel.pojo.enums;

public enum EOrderStatus {

    DELETE(-3, "已删除"),

    INVALID(-4, "失效订单"),

    REFUND_ERROR(-5, "退款异常订单"),

    UNPAYGIFT(-6, "未支付赠品"),

    APP_BACK_PAYED(-2, "app端回调已支付"),

    WAITPAY(1, "待支付"),

    PAYED(2, "已支付"),

    CHECK_PASS(4, "已审核"),

    CHECK_UNPASS(5, "审核拒绝"),

    SENT(8, "已发货"),

    PAYED_REFUND_APPLY(9, "已支付申请退款"),

    SENT_REFUND_APPLY(10, "已发货申请退款"),

    REFUND_CHECKED(11, "审核通过"),

    FILLBACK_KUAIDINUM(14, "回填快递单号"),

    RECEIVE_CHECKED(15, "确认收货"),

    REFUNDED(16, "确认退款"),

    REFUND_REFUSE(18, "拒绝退款"),

    ;
    private final int code;
    private final String name;

    public int toCode(){
        return code;
    }

    public String toName() {
        return name;
    }

    EOrderStatus(int code, String name) {
        this.code = code;
        this.name = name;
    }


}
