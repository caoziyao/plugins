package com.zel.market.controller.order.bussiness;

import com.zel.commonutils.DateUtil;
import com.zel.market.controller.cart.bussiness.CartService;
import com.zel.market.controller.cart.bussiness.GoodService;
import com.zel.market.controller.cart.bussiness.ShopUtil;
import com.zel.market.controller.cart.dto.GoodInfo;
import com.zel.market.controller.order.dto.EPayStatusCode;
import com.zel.market.controller.order.dto.GoodOrderDetail;
import com.zel.market.controller.order.dto.ShopOrder;
import com.zel.market.exception.BusinessException;
import com.zel.pojo.entity.User;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ShopOrderService {

    @Autowired
    private GoodService goodService;

    @Autowired
    private CartService cartService;


    /**
     * 收件端黑名单用户
     */
    private static final List<Long> CREATOR_BLACKLIST = Arrays.asList(1234L, 21321313L, 111350L);

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

    /**
     * 订单入库
     */
    @Transactional
    public void addNewOrderInfo(ShopOrder shopOrder) {
        //shopOrderDao.insert(shopOrder);
        // 批量插入
        //shopSubOrderDao.batchInsert(shopSubOrdersList);
        // 购物车删除商品
        //cartService.removeGoods(shopOrder.getUserId(), goodIdsList);
    }

    /**
     * 查询获取超过 n 天未支付的订单
     */
    public List<ShopOrder> listExpiredOrders(int invalidDay) {
        Date createEnd = DateUtil.add(new Date(), -1 * invalidDay);
        Map<String, Object> param = new HashMap<>(2);
        param.put("createEnd", createEnd);
        param.put("status", EPayStatusCode.WAITPAY.toCode());
        //return shopOrderDao.list(param);
        return new ArrayList<>();
    }


    /**
     * 更新状态
     * @param orderid
     */
    @Transactional
    public void updateExpired(String orderid) {
        int invalid = EPayStatusCode.INVALID.toCode();
        //shopOrderDao.updateStatus(orderid, invalid);
        //shopSubOrderDao.updateStatusByOrderid(orderid, invalid);
        //goodOrderDao.updateStatusByOrderid(orderid, invalid);
    }
}
