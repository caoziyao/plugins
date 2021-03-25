package com.zel.market.controller.order.bussiness;

import com.zel.market.controller.cart.bussiness.GoodService;
import com.zel.market.controller.cart.bussiness.ShopUtil;
import com.zel.market.controller.cart.dto.GoodInfo;
import com.zel.market.controller.order.dto.GoodOrderDetail;
import com.zel.market.controller.order.dto.ShopOrder;
import com.zel.market.exception.BusinessException;
import com.zel.pojo.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class ShopOrderService {

    @Autowired
    private GoodService goodService;


    /**
     * 收件端黑名单用户workerid
     */
    private static final List<Long> CREATOR_BLACKLIST = Arrays.asList(11234471L, 21321313L, 111350L);

    /**
     * 检查订单基本参数
     * @param shopOrder
     */
    public void checkOrderParams(ShopOrder shopOrder) {

        // 校验收件地址
        String receiveDetail = shopOrder.getReceiveDetail();
        if (StringUtils.isBlank(receiveDetail)) {
            throw new BusinessException("收件地址不能为空");
        }
    }

    /**
     * 校验黑名单用户
     * @param user
     */
    public void checkAndSetCreatorInfo(User user) {
        if (checkInBlacklist(user.getId())) {
            throw new BusinessException("已被列入黑名单，无法下单");
        }
    }

    /**
     * 检查用户是否列入黑名单
     */
    private boolean checkInBlacklist(long creator) {
        return CREATOR_BLACKLIST.contains(creator);
    }

    /**
     * 检查限购商品的购买
     */
    public void checkLimitPurchase(User user, List<GoodOrderDetail> odetails) {
        List<Long> goodIds = new ArrayList<>();
        for (GoodOrderDetail odetail : odetails) {
            long goodId = odetail.getGoodId();
            // todo 循环里面不要请求数据库
            GoodInfo good = goodService.findById(goodId);
            if (!goodService.limitCondition(good)) {
                continue;
            }

            if (odetail.getGoodCount() > 1) {
                throw new BusinessException("【" + good.getName() + "】限购1件");
            }

            goodIds.add(good.getId());
        }

        if (goodIds.size() == 0) {
            return;
        }
    }
}
