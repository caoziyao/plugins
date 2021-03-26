package com.zel.market.exception;

import com.zel.market.common.enumcom.EResponseCode;

public class AuthException extends BusinessException {
    /**
     * 错误码
     */
    protected String bizCode;
    /**
     * 错误信息
     */
    protected String bizMsg;

    public AuthException(String message) {
        super(EResponseCode.C500);
        this.bizMsg = message;
    }

    public AuthException(EResponseCode responseCode) {
        super(responseCode.toMessage());
        this.bizMsg = responseCode.toMessage();
    }
}
