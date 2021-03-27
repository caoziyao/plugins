package com.zel.market.app.controller.cart.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class GoodGiftActivity {
    private int type;	// 1:赠送活动

    private String name;	// 活动名称

    private int status;	// 1:上线; -1：下线

    private Date startTime;	// 开始时间

    private Date endTime;	// 结束时间
}
