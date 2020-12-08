package com.zel.market.pojo;

import java.io.Serializable;

public class Response implements Serializable {
    private static final long serialVersionUID = 1107929380704246470L;

    protected int code;
    protected String message;
    protected Object data;
}
