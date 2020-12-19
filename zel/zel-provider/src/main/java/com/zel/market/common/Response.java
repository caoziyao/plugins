package com.zel.market.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zel.market.common.enumcom.EResponseCode;

import java.io.Serializable;


public class Response implements Serializable {
    private static final long serialVersionUID = 1107929380704246470L;

    protected String code;
    protected String message;
    protected Object data;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected String debugMessage;

    public Response() {

    }

    public Response(EResponseCode eResponseCode) {
        this.code = eResponseCode.toCode();
        this.message = eResponseCode.toMessage();
    }

    /**
     * ok
     * @return
     */
    public static Response ok() {
        return new Response(EResponseCode.C200);
    }

    /**
     * error
     * @return
     */
    public static Response error(EResponseCode eResponseCode) {
        return new Response(eResponseCode);
    }

    /**
     * error
     * @return
     */
    public static Response error(String message) {
        Response response = new Response(EResponseCode.C500);
        response.setMessage(message);
        return response;
    }

    /**
     * ok
     * @param data
     * @return
     */
    public static Response ok(Object data) {
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

    public String getDebugMessage() {
        return debugMessage;
    }

    public void setDebugMessage(String debugMessage) {
        this.debugMessage = debugMessage;
    }


//    /**
//     * todo JSON
//     *
//     * @return
//     */
//    @Override
//    public String toString() {
//        return "{" +
//                "code='" + code + '\'' +
//                ", message='" + message + '\'' +
//                ", data=" + data +
//                ", detailMessage='" + detailMessage + '\'' +
//                '}';
//    }
}
