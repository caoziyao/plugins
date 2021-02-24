package com.zel.commonutils.client;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.TimeUnit;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
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

    public void download(String url, File destFile) throws Exception {
        HttpClient httpClient = HttpConnectionManager.getHttpClient();
//        httpClient.getParams().setIntParameter("http.socket.timeout", 200000);
//        httpClient.getParams().setIntParameter("http.connection.timeout", 10000);
        HttpGet method = new HttpGet(url);
        InputStream is = null;
        FileOutputStream fos = null;

        try {
            HttpResponse response = httpClient.execute(method);
            HttpEntity entity = response.getEntity();
            if (entity == null) {
                throw new Exception("HttpRequest no response");
            } else {
                is = entity.getContent();
                fos = new FileOutputStream(destFile);
                byte[] buf = new byte[1024];
                boolean var10 = true;

                int len;
                while((len = is.read(buf)) != -1) {
                    fos.write(buf, 0, len);
                }

                EntityUtils.consume(entity);
            }
        } catch (Exception var21) {
            method.abort();
            throw var21;
        } finally {
            if (null != fos) {
                try {
                    fos.close();
                } catch (Exception var20) {
                }
            }

            if (null != is) {
                try {
                    is.close();
                } catch (Exception var19) {
                }
            }

            httpClient.getConnectionManager().closeExpiredConnections();
            httpClient.getConnectionManager().closeIdleConnections(1L, TimeUnit.SECONDS);
        }
    }

    /*
     * 执行ping操作,判断节点是否可用,及延时时间
     */
    public static String ping(String ip) throws IOException {
        //执行ping命令
        Process p = Runtime.getRuntime().exec("ping "+ ip);
        //接受返回的数据
        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(),"GBK"));
        //BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(), StandardCharsets.UTF_8));
        String line;
        StringBuilder sb = new StringBuilder();
        while((line=br.readLine())!=null) {
            sb.append(line);
            sb.append("\n");
        }
        //使用正则表达式ping结果
        return sb.toString();
    }
}
