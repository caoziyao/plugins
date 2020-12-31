package com.zel.commonutils;

import static java.lang.System.err;
import static java.lang.System.out;

/**
 * 类似于Javascript的console.log()方法
 * <p>
 * https://gitee.com/loolly/hutool/blob/v5-master/hutool-core/src/main/java/cn/hutool/core/lang/Console.java
 */
public class Console {

    private static final String TEMPLATE_VAR = "{}";

    /**
     * 同 System.out.println()方法，打印控制台日志
     */
    public static void log(Object... obj) {
        if (obj.length == 0) {
            out.println();
            return;
        }

        String tmp = buildTemplateSplitBySpace(obj.length);
        String s = StrUtil.format(tmp, obj);

//        if ((obj[0] instanceof String) && (((String) obj[0]).contains(TEMPLATE_VAR))) {
//            String first = (String) obj[0];
//            //s = StrUtil.format(first, obj);
//        } else {
//            String tmp = buildTemplateSplitBySpace(obj.length);
//            s = StrUtil.format(tmp, obj);
//        }

        out.println(s);
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
