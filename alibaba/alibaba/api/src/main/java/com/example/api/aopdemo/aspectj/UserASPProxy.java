package com.example.api.aopdemo.aspectj;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Description: 增强类
 *
 * @author csy
 * @version 1.0.0
 * @since 2020/10/25
 */
@Component
@Aspect
public class UserASPProxy {

    @Pointcut("execution(public * com.example.api.aopdemo.aspectj.UserASPDemo.*(..))")
    public void webLog(){}

    @Before("webLog()")
    public void before() {
        System.out.println("before");
    }
}
