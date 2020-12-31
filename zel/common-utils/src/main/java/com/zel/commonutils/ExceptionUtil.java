package com.zel.commonutils;

import org.springframework.util.FastByteArrayOutputStream;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 异常工具类
 * <p>
 * https://gitee.com/loolly/hutool/blob/v5-master/hutool-core/src/main/java/cn/hutool/core/exceptions/ExceptionUtil.java
 */
public class ExceptionUtil {

    private static final String NULL = "null";

    /**
     * 获得完整消息，包括异常名, 行号
     *
     * @param e 异常
     * @return 完整消息
     */
    public static String getMessage(Throwable e) {
        if (null == e) {
            return NULL;
        }
        StackTraceElement stack = e.getStackTrace()[0];// 得到异常棧的首个元素
        Map<String, Object> map = new HashMap<>();
        map.put("class", stack.getClassName());
        map.put("method", stack.getMethodName());
        map.put("line", stack.getLineNumber());
        map.put("message", e.toString());

        //return StrUtil.format("{}: {}", e.getClass().getSimpleName(), e.getMessage());
        return StrUtil.format("{class} {method}:{line} {message}", map);

    }


    /**
     * 获取堆栈信息
     *
     * @param throwable
     * @return
     */
    public static String getStackTrace(Throwable throwable) {
        final FastByteArrayOutputStream baos = new FastByteArrayOutputStream();
        throwable.printStackTrace(new PrintStream(baos));
        return baos.toString();
    }


    /**
     * 堆栈转为单行完整字符串
     *
     * @param throwable 异常对象
     * @param limit     限制最大长度
     * @return 堆栈转为的字符串
     */
    public static String stacktraceToOneLineString(Throwable throwable) {
        Map<Character, String> map = new HashMap<>();
        map.put(StrUtil.C_CR, StrUtil.SPACE);
        map.put(StrUtil.C_LF, StrUtil.SPACE);
        map.put(StrUtil.C_TAB, StrUtil.SPACE);

        String message = getStackTrace(throwable);
        int length = message.length();

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            char c = message.charAt(i);
            String value = map.get(c);
            if (null != value) {
                sb.append(value);
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }
}
