package com.zel.market.common.enumcom;

/**
 * redis key
 */
public enum ERedisKey {

    EXPRESS("EXPRESS:{1}", "过期");

    private String key;
    private String desc;

    ERedisKey(String key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    /**
     * todo format
     *
     * @param params
     * @return
     */
    public String formatKey(Object... params) {
        if (params != null && params.length != 0) {
            String temp = this.key;

            for (int i = 0; i < params.length; ++i) {
                if (-1 != temp.indexOf("{" + (i + 1) + "}")) {
                    temp = temp.replace("{" + (i + 1) + "}", params[i].toString());
                }
            }
            return temp;
        } else {
            return this.key;
        }
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

    /**
     * redis的key
     * 形式为：
     * 表名:主键名:主键值
     *
     * @param tableName     表名
     * @param majorKey      主键名
     * @param majorKeyValue 主键值
     * @return
     */
    public static String getKey(String tableName, String majorKey, String majorKeyValue) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(tableName).append(":");
        buffer.append(majorKey).append(":");
        buffer.append(majorKeyValue).append(":");
        return buffer.toString();
    }

    public static void main(String[] args) {
        System.out.println(ERedisKey.EXPRESS.formatKey("89"));
    }
}
