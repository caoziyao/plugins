package com.zel.awesome.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 使用@interface定义注解，会自动继承java.lang.annotation.Annotation接口
 *
 * @Target: 注解作用于什么地方
 *
 * 解中的每个方法表示一个配置参数，返回类型即是参数类型，可以通过default标识参数默认值。定义方式如下：
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TestAnnotation {

    int max() default 0;

    int min() default 0;

    String description() default "";

}
