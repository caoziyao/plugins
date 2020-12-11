package com.zel.market.config;

import java.io.Serializable;

public class Response implements Serializable {
    private static final long serialVersionUID = 1107929380704246470L;

    protected String code;
    protected String message;
    protected Object data;

    public Response() {

    }

    public Response(EResponseCode EResponseCode) {
        this.code = EResponseCode.toCode();
        this.message = EResponseCode.toMessage();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    /**
     * todo JSON
     * @return
     */
    @Override
    public String toString() {
        return "{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
