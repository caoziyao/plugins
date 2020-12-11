package com.zel.market.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
@Documented
@Inherited
public @interface RoleAnnotation {
    ERoleType[] roles() default {ERoleType.NONE};

    boolean needLogin() default true;
}
