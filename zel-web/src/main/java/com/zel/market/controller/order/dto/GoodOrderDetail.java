package com.zel.market.controller.order.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GoodOrderDetail {

    private long goodId;	// 商品id


    private int goodCount;	// 购买数量
}
