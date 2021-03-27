package com.zel.market.app.controller.cart.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CartDetail {
    /* 用户id */
    private Long userId;

    /* 商品id */
    private Long goodId;

    /* 商品数量 */
    private Integer goodCount;


    /* 添加时间 */
    private Date createTime;


    //附加信息
    /* 商品信息 */
    private GoodInfo goodInfo;

    /* 商品活动 */
    private GoodGiftActivity gift;
}
