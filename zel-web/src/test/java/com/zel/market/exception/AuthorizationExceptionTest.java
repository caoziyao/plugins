package com.zel.market.exception;

import com.zel.market.BaseTest;
import org.junit.jupiter.api.Test;

class AuthorizationExceptionTest extends BaseTest {

    @Test
    void test() {
        try {
            throw new AuthException("adsf");
        } catch (Exception e) {
            if (e instanceof AuthException) {
                System.out.println("new AuthorizationException");
            } else {
                System.out.println("new dddd");
            }
        }
    }

}