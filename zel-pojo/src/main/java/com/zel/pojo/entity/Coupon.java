package com.zel.pojo.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 优惠券
 */
@Setter
@Getter
public class Coupon {
    private long id;

    private String couponNo;   //优惠券编号

    private long couponid;  //优惠券id

    /**
     * 优惠券状态
     * {@link com.zel.pojo.enums.ECouponStatus}
     */
    private Integer status;

    private Date created;    //获取优惠券的时间

    private Integer gotWay;     //获取方式。二维码/短链/兑换码

    private Date useTime;       //使用优惠券的时间

    private Date beginTime;     //起始时间

    private Date expireTime;    //过期时间

    private Long userid;        //用户id

    private long flag;        //通知用户的标记。包括短信、微信推送通知

    private BigDecimal discount;    //对商品使用后的优惠金额


    //附加信息
    private int count;  //已领取couponid对应的优惠券的数量

    private String workingMobile;  //用户手机号

    /**
     * 优惠券使用范围
     * {@link com.zel.pojo.enums.ECouponScope}
     */
    //private List<CouponGood> couponGoods;
    private Integer scope;


    /**
     * 类型。满减/折扣
     * {@link com.zel.pojo.enums.ECouponType}
     */
    private Integer type;

    private BigDecimal discountRate;    //折扣率

    private BigDecimal maxDiscount; //最高优惠金额

    private BigDecimal reduce;      //满减金额

    private BigDecimal startPrice;  //满减最低价格

    private String name;    //优惠券名称

    private String desc;    //优惠券描述

    private String url;     //优惠券短链

}
