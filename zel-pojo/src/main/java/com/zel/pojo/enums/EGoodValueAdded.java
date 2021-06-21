package com.zel.pojo.enums;

import com.zel.pojo.entity.GoodInfo;

public enum EGoodValueAdded {


    RECOMMEND(10, "推荐商品"),

    HOT(11, "热门商品"),

    SHORT_MSG_ACTIVITY(12, "赠短信活动商品"),

    LIMIT_PURCHASE(14, "限购商品"),

    NEW_USER_EXCLUSIVE(15, "新用户专属商品"),

    ;

    final long code;

    final String name;

    EGoodValueAdded(long code, String name) {
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

    /**
     * 是否限购商品
     * @param goodInfo
     * @return
     */
    public boolean isLimitGood(GoodInfo goodInfo) {
        if (EGoodValueAdded.LIMIT_PURCHASE.isSet(goodInfo.getValueAdded())) {
            return true;
        }

        return false;
    }
}
