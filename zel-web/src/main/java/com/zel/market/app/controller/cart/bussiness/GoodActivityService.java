package com.zel.market.app.controller.cart.bussiness;

import com.zel.market.app.controller.cart.dto.GoodGiftActivity;
import org.springframework.stereotype.Service;

@Service
public class GoodActivityService {

    /**
     * 获取赠品活动
     * @return
     */
    public GoodGiftActivity getGiftByGoodId(Long goodId) {
        if (goodId == null || goodId <= 0) {
            return null;
        }
        //return goodGiftActivityDao.getByGoodId(goodId);  T_GOODGIFTACTIVITY
        return new GoodGiftActivity();
    }
}
