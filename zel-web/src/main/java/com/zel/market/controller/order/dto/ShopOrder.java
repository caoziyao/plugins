package com.zel.market.controller.order.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class ShopOrder {
    /* 支付单号 */
    private String orderid;

    /* 创建人id */
    private Long userId;

    /* 收件人信息 */
    private String receiveDetail;

    /* 商品总数量 */
    private Integer goodCount;

    /* 订单状态 -4已失效；-3已删除； 1待支付；2交易成功*/
    private Integer status;

    /* 订单实际总价 */
    private BigDecimal totalPrice;

    /* 订单原价 */
    private BigDecimal originalPrice;

    /* 使用的优惠券id */
    private Long userCouponId;

    /* 微信openid。只有微信小程序购买才有 */
    private String openid;

    /* 下单时间 */
    private Date createTime;

    /* 最近更新时间 */
    private Date updateTime;

    /* 支付时间 */
    private Date payTime;
}
