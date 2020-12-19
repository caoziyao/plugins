package com.zel;

import org.apache.commons.lang3.StringUtils;

import java.util.Random;

/**
 * token
 */
public enum  TokenUtils {
    INSTANCE;

    /**
     * 验证token 是否有效
     * @param token
     * @return
     */
    public static boolean validateToken(String token) {
        if (StringUtils.isEmpty(token)) {
            return false;
        }
        // String existToken = JedisUtil.getJedisCluster().get(token)
        return true;
    }

    /**
     * 构建 token
     * @param uid
     * @return
     */
    public String buildToken(String uid) {
        Random random = new Random();
        return random.nextInt() + ":" +uid;
    }
}
