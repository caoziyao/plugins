package com.zel.commonutils.client;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpUtil {
    private Map<String, String> headerMap = new HashMap();

    public HttpUtil() {
    }

    public HttpUtil addHeader(String name, String value) {
        this.headerMap.put(name, value);
        return this;
    }


//    public static String post(String url, Map<String, String> params) {
//        List<NameValuePair> formParams = new ArrayList<>();
//        Iterator<String> it = params.keySet().iterator();
//        while (it.hasNext()) {
//            String key = it.next();
//            formParams.add(new BasicNameValuePair(key, params.get(key)));
//        }
//
//        HttpPost httpPost = new HttpPost(url);
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        try {
//            httpPost.setEntity(new UrlEncodedFormEntity(formParams, Constants.DEFAULT_CHARSET));
//            CloseableHttpResponse response = httpClient.execute(httpPost);
//            return EntityUtils.toString(response.getEntity(), Constants.DEFAULT_CHARSET);
//        } catch (Exception e) {
//            throw new BusinessException(EBaseResponseCode.C502, e);
//        } finally {
//            try {
//                httpClient.close();
//            } catch (IOException e) {
//                log.error("httpClient close:", e);
//            }
//        }
//    }
//
//    public static String get(String url, String charset) {
//        return get(url, null, charset);
//    }
//
//    public static String get(String url) {
//        return get(url, null, Constants.DEFAULT_CHARSET);
//    }
//
//    public static String get(String url, Header[] headers, String charset) {
//        HttpGet httpGet = new HttpGet(url);
//        RequestConfig requestConfig = RequestConfig.custom()
//                .setConnectTimeout(50000)
//                .setConnectionRequestTimeout(10000) //从connect Manager(连接池)获取Connection 超时时间，单位毫秒。
//                .setSocketTimeout(50000).build();
//        if (headers != null) {
//            httpGet.setHeaders(headers);
//        }
//        httpGet.setConfig(requestConfig);
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        try {
//            CloseableHttpResponse response = httpClient.execute(httpGet);
//            return EntityUtils.toString(response.getEntity(), Constants.DEFAULT_CHARSET);
//        } catch (Exception e) {
//            throw new BusinessException(EBaseResponseCode.C502, e);
//        } finally {
//            try {
//                httpClient.close();
//            } catch (IOException e) {
//                log.error("httpClient close:", e);
//            }
//        }
//    }

    public static String get(String url) {
        String result = "";
        CloseableHttpResponse response = null;

        CloseableHttpClient httpClient = HttpConnectionManager.getHttpClient();
        HttpGet httpGet = new HttpGet(url);
        // 设置请求头信息，鉴权
        httpGet.setHeader("Authorization", "Bearer da3efcbf-0845-4fe3-8aba-ee040be542c0");
        // 设置配置请求参数
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(35000)// 连接主机服务超时时间
                .setConnectionRequestTimeout(35000)// 请求超时时间
                .setSocketTimeout(60000)// 数据读取超时时间
                .build();
        // 为httpGet实例设置配置
        httpGet.setConfig(requestConfig);
        // 执行get请求得到返回对象
        try {
            response = httpClient.execute(httpGet);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 通过返回对象获取返回数据
        HttpEntity entity = response.getEntity();
        // 通过EntityUtils中的toString方法将结果转换为字符串
        try {
            result = EntityUtils.toString(entity);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static String post(String url, Map<String, Object> paramMap) {
        String result = "";
        CloseableHttpResponse response = null;

        CloseableHttpClient httpClient = HttpConnectionManager.getHttpClient();
        HttpPost httpPost = new HttpPost(url);
        // 设置请求头信息，鉴权
        httpPost.setHeader("Authorization", "Bearer da3efcbf-0845-4fe3-8aba-ee040be542c0");
        // 设置配置请求参数
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(35000)// 连接主机服务超时时间
                .setConnectionRequestTimeout(35000)// 请求超时时间
                .setSocketTimeout(60000)// 数据读取超时时间
                .build();
        // 为httpPost实例设置配置
        httpPost.setConfig(requestConfig);
        // 执行 post 请求得到返回对象
        // 封装post请求参数
        if (null != paramMap && paramMap.size() > 0) {
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            // 通过map集成entrySet方法获取entity
            Set<Map.Entry<String, Object>> entrySet = paramMap.entrySet();

            // 循环遍历，获取迭代器
            Iterator<Map.Entry<String, Object>> i$ = entrySet.iterator();
            while (i$.hasNext()) {
                Map.Entry<String, Object> mapEntry = i$.next();
                nvps.add(new BasicNameValuePair(mapEntry.getKey(), mapEntry.getValue().toString()));
            }

            // 为httpPost设置封装好的请求参数
            try {
                httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        try {
            response = httpClient.execute(httpPost);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 通过返回对象获取返回数据
        HttpEntity entity = response.getEntity();
        // 通过EntityUtils中的toString方法将结果转换为字符串
        try {
            result = EntityUtils.toString(entity);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
