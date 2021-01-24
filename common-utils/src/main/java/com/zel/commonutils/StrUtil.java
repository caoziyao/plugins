package com.zel.commonutils;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * Description:  Slf4j
 *
 * @author csy
 * @version 1.0.0
 * @since 2020/12/23
 */
public class StrUtil {

    public static final char C_CR = '\r';
    public static final char C_LF = '\n';
    public static final char C_TAB = '	';
    public final static String SPACE = " ";

    private StrUtil() {
    }

    private static String buildBundleKey(String originKey) {
        return "{" + originKey + "}";
    }

    public static final String formatWithKey(String originMessage, String key0, Object value0) {
        return originMessage.replace(buildBundleKey(key0), String.valueOf(value0));
    }

    public static final String formatWithKey(String originMessage,
                                      String key0, Object value0,
                                      String key1, Object value1) {
        String str0 = format(originMessage, key0, value0);
        return format(str0, key1, value1);
    }

    public static final String formatWithKey(String originMessage,
                                      String key0, Object value0,
                                      String key1, Object value1,
                                      String key2, Object value2) {
        String str1 = format(originMessage, key0, value0, key1, value1);
        return format(str1, key2, value2);
    }

    /**
     * 按关键字替换文本中的占位符
     *
     *  Map<String, Object> map = new HashMap<>();
     *  map.put("name", "zhangsan");
     *  map.put("age", 18);
     *
     *  StrUtil.format("hello {name} {age}", map)
     *  > hello zhangsan 18
     *
     * @param message 原始文本.如 hello {name},welcome to {country}
     * @param args    参数map,{"name":"adi","country":"china"}
     * @return 替换好的文本
     */
    public static final String format(String message, Map<String, Object> args) {
        if (message == null || args == null || args.isEmpty()) {
            return message;
        }
        for (Map.Entry<String, Object> kv : args.entrySet()) {
            message = formatWithKey(message, kv.getKey(), kv.getValue());
        }
        return message;
    }

    /**
     * 按顺序替换文本中的占位符
     *
     * StrUtil.format("hello {} {}", "abc", "ccc");
     * > hello abc ccc
     *
     * @param message 原始文本，其中包含"{}"占位符.如 hello {},welcome to {}
     * @param args    可变参列表
     * @return 替换好的文本
     */
    public static final String format(String message, Object... args) {
        // 🤔👍🍓🤔
        if (message == null || args == null || args.length <= 0) {
            return message;
        }
        String[] wArr = message.split("\\{\\}", args.length + 1);
        StringBuilder sbf = new StringBuilder();
        for (int i = 0, l = wArr.length; i < l; i++) {
            sbf.append(wArr[i]);
            if (i < l - 1) {
                sbf.append(args[i]);
            }
        }
        return sbf.toString();
    }

    /**
     * 按顺序替换文本中的占位符
     *
     * @param message 原始文本，其中包含"{}"占位符.如 hello {},welcome to {}
     * @param args    可变参列表
     * @return 替换好的文本
     */
//    public static final String formatWithArgs(String message, Object... args) {
//        if (message == null || args == null || args.length <= 0) {
//            return message;
//        }
//        String[] wArr = message.split("\\{\\}", args.length + 1);
//        StringBuilder sbf = new StringBuilder();
//        for (int i = 0, l = wArr.length; i < l; i++) {
//            sbf.append(wArr[i]);
//            if (i < l - 1) {
//                sbf.append(args[i]);
//            }
//        }
//        return sbf.toString();
//    }

    /**
     * 按索引替换文本中的占位符
     *
     * @param message 原始文本，如 hello {0},welcome to {1},其中索引从0开始
     * @param args    可变参列表
     * @return 替换好的文本
     */
    public static final String formatByIndex(String message, Object... args) {
        if (message == null || args == null || args.length <= 0) {
            return message;
        }
        String rStr = message;
        for (int i = 0; i < args.length; i++) {
            rStr = format(rStr, String.valueOf(i), args[i]);
        }
        return rStr;
    }
}
