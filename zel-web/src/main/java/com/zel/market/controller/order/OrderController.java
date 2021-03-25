package com.zel.market.controller.order;

import com.zel.commonutils.client.RequestUtil;
import com.zel.market.common.Env;
import com.zel.market.common.Response;
import com.zel.market.common.SysLoggers;
import com.zel.market.controller.cart.bussiness.GoodService;
import com.zel.market.controller.cart.dto.EShopGoodStatus;
import com.zel.market.controller.cart.dto.GoodInfo;
import com.zel.market.controller.order.bussiness.ShopOrderService;
import com.zel.market.controller.order.dto.GoodOrderDetail;
import com.zel.market.controller.order.dto.ShopOrder;
import com.zel.market.exception.BusinessException;
import com.zel.pojo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private ShopOrderService shopOrderService;
    @Autowired
    private GoodService goodService;

    /**
     * 创建订单
     *
     * @param request
     * @param response
     * @return
     */
    @PostMapping("/createOrder")
    public Response createOrder(HttpServletRequest request, HttpServletResponse response) {
        long start = System.currentTimeMillis();
        User user = Env.getContext().getUser();
        Long cartId = RequestUtil.getLong(request, "cartId", 0L);

        ShopOrder shopOrder = new ShopOrder();
        shopOrder.setUserId(user.getId());

        SysLoggers.order_log.info("创建订单");

        //检查订单基本参数
        shopOrderService.checkOrderParams(shopOrder);
        shopOrderService.checkAndSetCreatorInfo(user);

        List<GoodOrderDetail> odetails = new ArrayList<>();

        for (GoodOrderDetail odetail : odetails) {
            long goodId = odetail.getGoodId();
            // todo 循环里面不要请求数据库
            GoodInfo good = goodService.findById(goodId);
            if (good == null || good.getStatus() != EShopGoodStatus.ENABLE.code() ) {
                throw new BusinessException("商品在不存或已下架");
            }

        }

        //判断限购
        shopOrderService.checkLimitPurchase(user, odetails);

        return null;
    }
}
