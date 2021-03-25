package com.zel.market.controller.cart.bussiness;

import com.zel.market.controller.cart.dto.EGoodFlag;
import com.zel.market.controller.cart.dto.GoodInfo;

public class ShopUtil {

    /**
     * 是否非卖商品
     * @return
     */
    public static boolean isCustomGood(GoodInfo goodInfo) {

        boolean isCustom = false;

        if (goodInfo != null) {

            Long val = goodInfo.getFlag() == null ? 0L : goodInfo.getFlag();
            if(EGoodFlag.RECOMMEND.isSet(val)
                    || EGoodFlag.SHORT_MSG_ACTIVITY.isSet(val)
                    || EGoodFlag.LIMIT_PURCHASE.isSet(val) ){
                isCustom = true;
            }
        }
        return isCustom;
    }
}
