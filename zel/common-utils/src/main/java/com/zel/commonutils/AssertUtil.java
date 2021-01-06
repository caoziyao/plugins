package com.zel.commonutils;

import lombok.extern.slf4j.Slf4j;

import java.util.function.Supplier;

/**
 * https://gitee.com/loolly/hutool/blob/v5-master/hutool-core/src/main/java/cn/hutool/core/lang/Assert.java
 * <p>
 * isTrue 是否True
 * isNull 是否是null值，不为null抛出异常
 * notNull 是否非null值
 * notEmpty 是否非空
 * notBlank 是否非空白符
 * notContain 是否为子串
 * notEmpty 是否非空
 * noNullElements 数组中是否包含null元素
 * isInstanceOf 是否类实例
 * isAssignable 是子类和父类关系
 * state 会抛出IllegalStateException异常
 */
@Slf4j
public class AssertUtil {

    /**
     * 断言是否为真，如果为 {@code false} 抛出给定的异常<br>
     *
     * @param <X>        异常类型
     * @param expression 布尔值
     * @param supplier   指定断言不通过时抛出的异常
     * @param supplier   supplier的中文意思是提供者，跟Consumer类相反，Supplier类用于提供对象，它只有一个get方法，是一个抽象方法，需要编程者自定义想要返回的对象。
     * @param <X>
     * @throws X
     */
    public static <X extends Throwable> void isTrue(boolean expression, Supplier<? extends X> supplier) throws X {
        if (false == expression) {
            throw supplier.get();
        }
    }

    /**
     * @param expression       布尔值
     * @param errorMsgTemplate 错误抛出异常附带的消息模板，变量用{}代替
     * @param params           参数列表
     * @throws IllegalArgumentException if expression is {@code false}
     */
    public static void isTrue(boolean expression, String errorMsgTemplate, Object... params) throws IllegalArgumentException {
        isTrue(expression, () -> new IllegalArgumentException(StrUtil.format(errorMsgTemplate, params)));
    }

    /**
     * 断言对象是否不为{@code null}
     */
    public static <T, X extends Throwable> T notNull(T object, Supplier<X> errorSupplier) throws X {
        if (null == object) {
            throw errorSupplier.get();
        }
        return object;
    }

    /**
     * 断言对象是否不为{@code null}
     */
    public static <T, X extends Throwable> T notNull(T object, String errorMsgTemplate, Object... params) throws X {
        if (null == object) {
            notNull(object, () -> new IllegalArgumentException(StrUtil.format(errorMsgTemplate, params)));
        }
        return object;
    }


    /**
     * 断言给定对象是否是给定类的实例
     */
    public static <T> T isInstanceOf(Class<?> type, T obj, String errorMsgTemplate, Object... params) throws IllegalArgumentException {
        notNull(type, "Type to check against must not be null");

        if (false == type.isInstance(obj)) {
            throw new IllegalArgumentException(StrUtil.format(errorMsgTemplate, params));
        }
        return obj;
    }

    public static void assertEquals(boolean condition, String message) {
        if (!condition) {
            //  log.error(StrUtil.format("TEST ERROR!!! {}", message));
            throw new IllegalArgumentException(StrUtil.format("TEST ERROR!!! {}", message));
        }
    }
}
