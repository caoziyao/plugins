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
    C415("415", "需要授权登陆"),

    /** 业务异常  */
    OK("0", "成功"),
    ERROR("10000", "失败"),
    
    /** 登录  */
    LOGIN_CODE_EXPIRE("10701", "验证码已过期"),
    LOGIN_CODE_ERR("10702", "验证码错误"),
    LOGIN_MOBILE_ERR("10702", "手机号错误"),
    NOT_REGISTER("10703", "手机号未注册"),
    LOGIN_CODE_FREQUENT("10704", "验证码发送频繁"),
    PASSWORD_EMPTY("10705", "密码为空"),
    PASSWORD_ERR("10706", "密码错误"),
    PASSWORD_NOT_EQUAL("10707", "密码不一致"),
    NO_MENU_AUTHORITY("10708", "没有菜单权限"),
    STAFF_STOP("10709", "账号已停用")
    ;

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
