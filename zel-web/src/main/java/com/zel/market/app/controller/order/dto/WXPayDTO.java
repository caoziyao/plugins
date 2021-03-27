package com.zel.market.app.controller.order.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WXPayDTO {
    private String status;
    private String appId;
    private String timeStamp;
}
