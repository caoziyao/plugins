package com.zel.market.utils;

import java.util.Random;

/**
 * token
 */
public enum  TokenUtils {
    INSTANCE;

    public String buildToken(String uid) {
        Random random = new Random();
        return random.nextInt() + ":" +uid;
    }
}
