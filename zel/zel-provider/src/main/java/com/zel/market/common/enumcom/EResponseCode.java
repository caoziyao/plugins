package com.zel.market.common.enumcom;

public enum EResponseCode {
    C200("200", "成功"),
    C201("201", "数据不存在"),
    C209("209", "提现金额超上限"),
    C400("400", "参数错误"),
    C403("403", "FORBIDDEN"),
    C402("402", "未指明服务"),
    C417("417", "手机号码为空或错误"),
    C500("500", "服务器错误"),
    C503("503", "服务器繁忙"),
    C404("404", "找不到"),
    C405("405", "请求过期"),
    C406("406", "请求限制"),
    C410("410", "未注册"),
    C415("415", "需要授权登陆");


    private final String code;
    private final String msg;


    EResponseCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String toCode() {
        return this.code;
    }

    public String toMessage() {
        return this.msg;
    }
}
