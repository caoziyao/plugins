package com.zel.market.controller.order.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 支付信息
 */
@Getter
@Setter
public class PayOrder {
    private EPayStatusCode status;
    private long orderId;
    private BigDecimal price;
    private EPayWay payway;
    private boolean issuccess;
    private String errmgs;
    private long userid;
    private Date payTime;
}
