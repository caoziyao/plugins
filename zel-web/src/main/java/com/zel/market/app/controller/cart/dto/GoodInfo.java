package com.zel.market.app.controller.cart.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GoodInfo {
    private Long id;

    private Integer status;	// -1:下架  1:上架  0:非卖品 -2预设上架
    private Long flag;	// 商品属性

    private String name;
}
