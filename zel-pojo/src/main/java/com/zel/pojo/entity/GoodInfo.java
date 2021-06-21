package com.zel.pojo.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 商品
 */
@Getter
@Setter
public class GoodInfo {

    private Long id;

    private String name;

    private String desc;

    // todo
    //可支持视频 [{"img":"",url:"", type:1}]      type = 1图片    type=2视频
    private String imageType;


    // 商品列表展示图片
    private String image;

    /**
     *  {@link com.zel.pojo.enums.EShopGoodStatus}
     *  -1:下架  1:上架  0:非卖品 -2预设上架  EShopGoodStatus
     */
    private Integer status;

    // 类型id
    private Long typeId;

    // 类型名称
    private String typeName;

    // 价格
    private BigDecimal price;

    //单位
    private String unit;

    //// 标识定制物料属性
    //private Long flag;

    /**
     * 商品增值属性
     * {@link com.zel.pojo.enums.EGoodValueAdded}
     */
    private Long valueAdded;

    private Date createTime;

    private Date updateTime;

    //private String secondForm;	// 第二种形态{"price":999}

    // 运费
    //private double carriage;

    //预设定上架时间
    private Date enableTime;

    // 附加参数
    private String extra;

    //商品主题。针对定制商品的
    //private String topic;

    //营销类型
    //private Integer marketType;

    //商品原价
    private BigDecimal originalPrice;

    //是否为推荐商品
    private Integer recommend;

    //分享购买成功获得的返现金额
    private BigDecimal reward;

    // 赠送活动
    private GoodInfo giftActivity;

    // 1:重新计算过价格
    private int isCalculate;


    /* 是否属于定制物品。1是；0否 */
    private int custom;

    /* 商品别名 */
    private String aliasName;

}
