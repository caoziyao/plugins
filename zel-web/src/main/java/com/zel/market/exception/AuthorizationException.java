package com.zel.market.exception;

public class AuthorizationException extends RuntimeException {
    /**
     * 错误码
     */
    protected String bizCode;
    /**
     * 错误信息
     */
    protected String bizMsg;

    public AuthorizationException(String message) {
        super(message);
        this.bizMsg = message;
    }
}
