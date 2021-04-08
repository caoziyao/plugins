package com.zel.market.jobs.order;

import com.zel.commonutils.redis.RedisUtils;
import com.zel.market.common.enumcom.EResponseCode;
import com.zel.market.app.controller.order.bussiness.ShopOrderService;
import com.zel.market.app.controller.order.dto.EPayStatusCode;
import com.zel.market.app.controller.order.dto.ShopOrder;
import com.zel.market.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 订单定时任务
 */
@Component
public class OrderJob {

    private String KEY = "order:expired:order";

    @Autowired
    private ShopOrderService shopOrderService;

    @Autowired
    private RedisUtils redisUtils;

    @Scheduled(cron = "10 0 0 * * ?")
    //@Scheduled(fixedRate = 10 * DateUtil.MILLISECOND)
    public void invalidExpiredOrder(){
        //String value = JedisUtil.getJedisCluster().set(KEY, "1", "nx", "ex", 2000);
        Boolean hasKey = redisUtils.setnx(KEY, "1", 2L, TimeUnit.SECONDS);

        // 未获取到锁，直接返回
        if(!hasKey){
            System.out.println("OrderJob 获锁失败 " + new Date());
            return;
        }

        System.out.println("OrderJob 获得锁成功 " + new Date());
        List<ShopOrder> shopOrders = shopOrderService.listExpiredOrders(7);
        for (ShopOrder shopOrder : shopOrders) {
            if (shopOrder.getStatus() != EPayStatusCode.WAITPAY.toCode()) {
                throw new BusinessException(EResponseCode.C500);
            }
            String orderid = shopOrder.getOrderid();
            shopOrderService.updateExpired(orderid);
        }
    }
}
