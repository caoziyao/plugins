package com.zel.market.app.aspect;


import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IpLimiter {

    /**
     * 限流ip
     */
    //String ipAdress() default "127.0.0.1";

    /**
     * 单位时间限制通过请求数
     */
    int limit() default 5;

    /**
     * 单位时间，单位秒
     */
    int time() default 10;

    /**
     * 达到限流提示语
     */
    String message() default "请求失败,你的IP访问太频繁";
}
