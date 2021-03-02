package com.zel.commonutils.logutil;

import com.zel.commonutils.StrUtil;

import static java.lang.System.err;
import static java.lang.System.out;

/**
 * 类似于Javascript的console.log()方法
 * <p>
 * https://gitee.com/loolly/hutool/blob/v5-master/hutool-core/src/main/java/cn/hutool/core/lang/Console.java
 */
public class log {

    private static final String TEMPLATE_VAR = "{}";


    //public log(Object... obj) {
    //    log.log(obj);
    //}

    /**
     * log byte[]
     */
    private static void logBytes(byte[] arr) {
        StringBuilder buffer = new StringBuilder();
        buffer.append('[');
        for (int i = 0; i < arr.length; i++) {
            buffer.append(arr[i]);
            buffer.append(' ');
        }
        buffer.append(']');
        out.println(buffer.toString());
    }

    /**
     * 同 System.out.println()方法，打印控制台日志
     * log(a, b, c)  ->  a b c
     */
    public static void i(Object... obj) {
        if (obj.length == 0) {
            out.println();
            return;
        }

        String tmp = buildTemplateSplitBySpace(obj.length);
        String s = StrUtil.format(tmp, obj);
        out.println(s);
//        if ((obj[0] instanceof String) && (((String) obj[0]).contains(TEMPLATE_VAR))) {
//            String first = (String) obj[0];
//            //s = StrUtil.format(first, obj);
//        } else {
//            String tmp = buildTemplateSplitBySpace(obj.length);
//            s = StrUtil.format(tmp, obj);
//        }

    }

    /**
     * 构建空格分隔的模板，类似于"{} {} {} {}"
     *
     * @param count 变量数量
     * @return 模板
     */
    private static String buildTemplateSplitBySpace(int count) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < count; i++) {
            builder.append(TEMPLATE_VAR);
            builder.append(StrUtil.SPACE);
        }
        if (count > 0) {
            builder.deleteCharAt(builder.length() - 1);
        }
        return builder.toString();
    }
}
