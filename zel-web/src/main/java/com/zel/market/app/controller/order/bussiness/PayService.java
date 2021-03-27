package com.zel.market.app.controller.order.bussiness;

import com.zel.market.common.Env;
import com.zel.market.app.controller.order.dto.EPayWay;
import com.zel.market.app.controller.order.dto.PayOrder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PayService {
    /**
     * 支付系统订单id生成
     */
    public long refreshWuliaoOrderId(BigDecimal price) {
        PayOrder payOrder = new PayOrder();
        payOrder.setUserid(Env.getContext().getUserId());
        payOrder.setPrice(price);
        payOrder.setPayway(EPayWay.QR_WEIXIN);

        PayOrder payOrder1 = newOrder(payOrder);
        return payOrder1.getOrderId();
    }

    /**
     * todo
     * @param order
     * @return
     */
    public PayOrder newOrder(PayOrder order) {
        return order;
    }

}
