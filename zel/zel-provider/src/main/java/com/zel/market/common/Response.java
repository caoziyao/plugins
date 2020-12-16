package com.zel.market.common;

import com.zel.market.common.enumcom.EResponseCode;

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

    /**
     * ok
     * @return
     */
    public static Response OK() {
        return new Response(EResponseCode.C200);
    }

    /**
     * ok
     * @param data
     * @return
     */
    public static Response OK(Object data) {
        Response r = new Response(EResponseCode.C200);
        r.setData(data);
        return r;
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
