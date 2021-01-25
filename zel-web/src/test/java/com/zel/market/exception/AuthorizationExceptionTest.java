package com.zel.market.exception;

import com.zel.market.BaseTest;
import org.junit.jupiter.api.Test;

class AuthorizationExceptionTest extends BaseTest {

    @Test
    void test() {
        try {
            throw new AuthorizationException("adsf");
        } catch (Exception e) {
            if (e instanceof AuthorizationException) {
                System.out.println("new AuthorizationException");
            } else {
                System.out.println("new dddd");
            }
        }
    }

}