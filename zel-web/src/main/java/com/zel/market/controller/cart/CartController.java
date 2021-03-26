package com.zel.market.controller.cart;


import com.zel.commonutils.client.RequestUtil;
import com.zel.market.common.Env;
import com.zel.market.common.Response;
import com.zel.market.controller.cart.bussiness.CartService;
import com.zel.market.controller.cart.bussiness.GoodActivityService;
import com.zel.market.controller.cart.bussiness.GoodService;
import com.zel.market.controller.cart.bussiness.ShopUtil;
import com.zel.market.controller.cart.dto.*;
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
public class CartController {

    @Autowired
    private GoodActivityService goodActivityService;

    @Autowired
    private GoodService goodService;

    @Autowired
    private CartService cartService;

    @PostMapping("/addCart")
    public Response addCart(HttpServletRequest request, HttpServletResponse response) {
        User user = Env.getContext().getUser();
        long goodId = RequestUtil.getLong(request, "goodId", 0L);
        int goodCount = RequestUtil.getInteger(request, "goodCount", 0);

        //
         GoodInfo goodInfo = goodService.findById(goodId);
        if (goodInfo == null || goodInfo.getStatus() != EShopGoodStatus.ENABLE.code()) {
            throw new BusinessException("商品不存在或已下架");
        }

        if (ShopUtil.isCustomGood(goodInfo)) {
            throw new BusinessException("非卖商品，无法添加至购物车");
        }

        // 活动商品
        GoodGiftActivity activity = goodActivityService.getGiftByGoodId(goodId);
        if (activity != null && EGoodActivityType.GIFTAND.toCode() == activity.getType()) {
            List<Long> goodIds = new ArrayList<>();
            GoodGiftActivityDetail detail = new GoodGiftActivityDetail();
            goodIds.add(detail.getGoodId());
        }

        CartDetail cartDetail = new CartDetail();
        cartDetail.setUserId(user.getId());
        cartDetail.setGoodId(goodId);
        cartDetail.setGoodCount(goodCount);

        cartService.add(cartDetail);
        return null;
    }
}
