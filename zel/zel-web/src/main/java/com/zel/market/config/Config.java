package com.zel.market.config;

import com.zel.market.common.enumcom.ERedisKey;
import org.springframework.beans.factory.annotation.Value;

/**
 * 公共 配置
 */
public class Config {

    //运行环境
    @Value("${env}")
    private String env;

    // 是否开启 ss 账号查询
    public static boolean ENABLE_SS_ACCOUNT_REQUEST = false;

    public static final String USER_ONLINE_KEY = ERedisKey.USER_ONLINE.getKey();

    public String getEnv() {
        return env;
    }
}
