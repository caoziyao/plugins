package com.zel.market.common.enumcom;

/**
 * redis key
 */
public enum  ERedisKey {

    EXPRESS("EXPRESS:{1}", "过期");

    private String key;
    private String desc;

    ERedisKey(String key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    /**
     * todo format
     * @param params
     * @return
     */
    public String formatKey(Object... params) {
        if (params != null && params.length != 0) {
            String temp = this.key;

            for(int i = 0; i < params.length; ++i) {
                if (-1 != temp.indexOf("{" + (i + 1) + "}")) {
                    temp = temp.replace("{" + (i + 1) + "}", params[i].toString());
                }
            }
            return temp;
        } else {
            return this.key;
        }
    }

    public static void main(String[] args) {
        System.out.println(ERedisKey.EXPRESS.formatKey("89"));
    }
}
