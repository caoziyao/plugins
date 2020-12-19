package com.zel.market.exception;

public class BusinessException extends RuntimeException {

    /**
     * 错误码
     */
    protected String bizCode;
    /**
     * 错误信息
     */
    protected String bizMsg;

    public BusinessException(String message) {
        super(message);
        this.bizMsg = message;
    }

    public BusinessException(String code, String message) {
        super(message);
        this.bizCode = code;
        this.bizMsg = message;
    }


    public String getBizCode() {
        return bizCode;
    }

    public void setBizCode(String bizCode) {
        this.bizCode = bizCode;
    }

    public String getBizMsg() {
        return bizMsg;
    }

    public void setBizMsg(String bizMsg) {
        this.bizMsg = bizMsg;
    }
}
