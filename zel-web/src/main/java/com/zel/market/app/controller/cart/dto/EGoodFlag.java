package com.zel.market.app.controller.cart.dto;

public enum EGoodFlag {

    RECOMMEND(10, "非卖商品"),

    SHORT_MSG_ACTIVITY(12, "赠短信活动商品"),

    LIMIT_PURCHASE(14, "限购商品"),

    ;
    final long code;

    final String name;

    EGoodFlag(long code, String name) {
        this.code = code;
        this.name = name;
    }

    public long toCode() {
        return code;
    }

    public String toName() {
        return name;
    }

    /**
     * 判断是否设置该值
     *
     * @param val
     * @return
     */
    public boolean isSet(long val) {
        return 1 == (val >> code & 1);
    }

    /**
     * 将某个位设置为1
     *
     * @param val
     * @return
     */
    public long setVal(long val) {
        return val | (1 << code);
    }

}
