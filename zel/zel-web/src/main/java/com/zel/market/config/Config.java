package com.zel.market.config;

/**
 * 公共 配置
 */
public enum  Config {
    INSTANCE;

    // 是否开启 ss 账号查询
    private boolean enableSSAccountRequest = false;

    public boolean isEnableSSAccountRequest() {
        return enableSSAccountRequest;
    }

    public void setEnableSSAccountRequest(boolean enableSSAccountRequest) {
        this.enableSSAccountRequest = enableSSAccountRequest;
    }
}
