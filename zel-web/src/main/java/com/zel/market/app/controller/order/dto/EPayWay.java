package com.zel.market.app.controller.order.dto;

public enum EPayWay {
    QR_WEIXIN("QR_WEIXIN", "微信扫描支付", "2133213", "wxdss232123"),
    QR_ZHIFUBAO("QR_ZHIFUBAO", "支付宝扫码支付", (String)null, "123123"),
    ;

    private String code;
    private String name;
    private String mchid;
    private String appid;

    private EPayWay(String code, String name, String mchid, String appid) {
        this.code = code;
        this.name = name;
        this.mchid = mchid;
        this.appid = appid;
    }

}
