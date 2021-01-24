package com.zel.market.common.enumcom;

import com.zel.commonutils.StrUtil;

/**
 * redis key
 */
public enum ERedisKey {

    HTML_SS("HTML:ss", "ss页面"),

    ARTICLE_PAGE("ARTICLE:page{}:limit{}", "文章页面"),

    EXPRESS("EXPRESS:{}", "过期"),
    USER_ID("USER:ID:{}", "user id"),
    USER_ONLINE("USER:ONLINE", "用户在线数"),
    ;

    private String key;
    private String desc;

    ERedisKey(String key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    /**
     * redis的key
     * 形式为：
     * 表名:主键名:主键值
     *
     * @return
     */
    public String getKey() {
        return this.key;
    }

    /**
     * todo format
     *
     * @param params
     * @return
     */
    public String formatKey(Object... params) {
        return StrUtil.format(this.key, params);

        //if (params != null && params.length != 0) {
        //    String temp = this.key;
        //
        //    for (int i = 0; i < params.length; ++i) {
        //        if (-1 != temp.indexOf("{" + (i + 1) + "}")) {
        //            temp = temp.replace("{" + (i + 1) + "}", params[i].toString());
        //        }
        //    }
        //    return temp;
        //} else {
        //    return this.key;
        //}
    }

    /**
     * redis的key
     * 形式为：
     * 表名:主键名:主键值:列名
     *
     * @param tableName     表名
     * @param majorKey      主键名
     * @param majorKeyValue 主键值
     * @param column        列名
     * @return
     */
    public static String getKeyWithColumn(String tableName, String majorKey, String majorKeyValue, String column) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(tableName).append(":");
        buffer.append(majorKey).append(":");
        buffer.append(majorKeyValue).append(":");
        buffer.append(column);
        return buffer.toString();
    }


    public static void main(String[] args) {
        System.out.println(ERedisKey.EXPRESS.formatKey("89"));
    }
}
