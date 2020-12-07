package com.example.emailservice.annotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Modifier;

/**
 * Description: 注解的本质也是一种广义的语法糖，最终还是要利用Java的反射来进行操作
 *
 * @author csy
 * @version 1.0.0
 * @since 2020/11/6
 */
@Component
@Aspect
public class KthLogAspect {
    @Pointcut("@annotation(com.example.emailservice.annotation.KthLog)")
    private void pointcut() {}

    /**
     * @Before声明了通知内容，在具体的通知中，我们通过@annotation(logger)拿到了自定义的注解对象，
     * 所以就能够获取我们在使用注解时赋予的值了。
     * @param logger
     */
    @Before("pointcut() && @annotation(logger)")
    public void advice(JoinPoint joinPoint, KthLog logger) {
        System.out.println("--- Kth日志的内容为[" + logger.value() + "] ---");
        System.out.println("注解作用的方法名: " + joinPoint.getSignature().getName());

        System.out.println("所在类的简单类名: " + joinPoint.getSignature().getDeclaringType().getSimpleName());

        System.out.println("所在类的完整类名: " + joinPoint.getSignature().getDeclaringType());

        System.out.println("目标方法的声明类型: " + Modifier.toString(joinPoint.getSignature().getModifiers()));
    }
}
