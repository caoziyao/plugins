package com.zel.market.app.controller.cart.bussiness;

import com.zel.market.app.controller.cart.dto.EGoodFlag;
import com.zel.market.app.controller.cart.dto.GoodInfo;
import org.springframework.stereotype.Service;

@Service
public class GoodService {

    public GoodInfo findById(Long id) {
        //return goodInfoDao.findById(id); T_GOODINFO
        return new GoodInfo();
    }

    /**
     * 判断是否是限购商品
     *
     * @return
     */
    public boolean limitCondition(GoodInfo good) {
        if (good == null) {
            return false;
        }
        if (EGoodFlag.LIMIT_PURCHASE.isSet(good.getFlag())) {
            return true;
        }

        return false;
    }
}
