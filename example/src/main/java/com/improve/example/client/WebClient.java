package com.improve.example.client;

import sun.misc.Request;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2020/12/8
 */
public class WebClient {
    public static void main(String[] args) {
        String url = "http://wwww.baidu.com";
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url)
                .build();
        final Call call = okHttpClient.newCall(request);
        Response response = call.execute();
    }
}
