package com.example.emailservice.annotation;

import java.lang.annotation.*;

/**
 * Description: 自定义的注解类KthLog
 *
 * @Documented：注解信息会被添加到Java文档中
 * @Retention：注解的生命周期，表示注解会被保留到什么阶段，可以选择编译阶段、类加载阶段，或运行阶段
 * @Target：注解作用的位置，ElementType.METHOD表示该注解仅能作用于方法上
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface KthLog {
    String value() default "";
}
