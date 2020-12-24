package com.zel.market.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Loggers {
    // public static final Logger catalina_out = LoggerFactory.getLogger(SysLoggers.class);
    //记录方法调用的时间
    public static final Logger interceptor_log = LoggerFactory.getLogger("interceptor_log");
    public static final Logger logic_error = LoggerFactory.getLogger("logic_error");

    public static void main(String[] args) {
        Integer a = null;
        int b = a;
        System.out.println(b);
    }
}
