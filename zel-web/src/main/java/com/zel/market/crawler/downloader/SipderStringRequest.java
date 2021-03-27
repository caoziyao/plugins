package com.zel.market.crawler.downloader;

import com.zel.commonutils.client.HttpConnectionManager;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2021/3/27
 */
public class SipderStringRequest {

    private String protol = "";
    private Map<String, String> header = new HashMap<>();

    public static final String cookie = "_vwo_uuid_v2=D14B16E4BD1495AD52276CC0A350D9340|594a92c9115db07a712a45a5e7a44abe; douban-fav-remind=1; gr_user_id=3ed2a389-20af-4a48-b85e-de246591f7ca; ll=\"108309\"; bid=EQ1SyATvpxE; __utmv=30149280.16817; __yadk_uid=TQLnEBMLzURvZEbBYtGe8hJctMqHDEyj; __gads=ID=010ef7257a5d70e8:T=1588658626:S=ALNI_MaSHOSUCuNrGC3QUFUW732ikSziaw; dbcl2=\"168178462:DPTfUCxAuyw\"; push_noty_num=0; push_doumail_num=0; douban-profile-remind=1; _ga=GA1.2.510559910.1532074370; ck=poTI; _pk_ref.100001.4cf6=%5B%22%22%2C%22%22%2C1595382730%2C%22https%3A%2F%2Fwww.baidu.com%2Flink%3Furl%3DaNqDnb-2sp2NC829-06O3Qm-4uEy9Xqz1PEXUjM5XQw-BGaYivZMxT3nmeqJL4z9%26wd%3D%26eqid%3Dd9b862ed001a1e03000000065f179bc7%22%5D; __utmc=30149280; __utma=223695111.320804270.1578908758.1595239354.1595382730.12; __utmc=223695111; __utmz=223695111.1595382730.12.11.utmcsr=baidu|utmccn=(organic)|utmcmd=organic; _pk_id.100001.4cf6=9b17b3036f03286d.1578908758.12.1595383392.1595239490.; __utma=30149280.510559910.1532074370.1595382730.1595386012.50; __utmz=30149280.1595386012.50.49.utmcsr=baidu|utmccn=(organic)|utmcmd=organic";


    public SipderStringRequest() {
        header.put("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3");
        //header.put("accept-encoding", "gzip, deflate, br");
        header.put("accept-language", "zh-CN,zh;q=0.9");
        header.put("cache-control", "max-age=0");
        header.put("connection", "keep-alive");
        header.put("upgrade-insecure-requests", "1");
        header.put("user-agent", randomAgent());
        header.put("cookie", cookie);
        //String ip = getRomdomIp();
        //header.put("x-forwarded-for", ip);
    }

    //public HttpURLConnection getConnection(String visitURL) throws Exception {
    //    HttpURLConnection conn = null;
    //
    //    URL url = new URL(visitURL);
    //
    //    if ( protol.equals("https") || (visitURL.contains("https") && protol.isEmpty())) {
    //        trustAllHttpsCertificates();
    //        HostnameVerifier hv = new HostnameVerifier() {
    //            public boolean verify(String urlHostName, SSLSession session) {
    //                return true;
    //            }
    //        };
    //        HttpsURLConnection.setDefaultHostnameVerifier(hv);
    //    }
    //
    //    conn = (HttpURLConnection) url.openConnection();
    //
    //    for (Map.Entry<String, String> entry : header.entrySet()) {
    //        conn.setRequestProperty(entry.getKey(), entry.getValue());
    //    }
    //
    //    conn.setRequestMethod("GET");
    //    conn.setReadTimeout(60000);
    //
    //    return conn;
    //}

    private  void trustAllHttpsCertificates() throws Exception {
        //javax.net.ssl.TrustManager[] trustAllCerts = new javax.net.ssl.TrustManager[1];
        //javax.net.ssl.TrustManager tm = new TrustAnyTrustManager();
        //trustAllCerts[0] = tm;
        //javax.net.ssl.SSLContext sc = javax.net.ssl.SSLContext.getInstance("SSL");
        //sc.init(null, trustAllCerts, null);
        //javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
    }

    public void addHeader(String key, String value) {
        header.put(key, value);
    }


    public String randomAgent() {
        String[] uat_list = {
                "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0)",
                "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.1; WOW64; Trident/5.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; InfoPath.3; .NET4.0C; .NET4.0E) QQBrowser/6.9.11079.201",
                "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.41 Safari/535.1 QQBrowser/6.9.11079.201",
                "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; InfoPath.3; .NET4.0C; .NET4.0E)",
                "Mozilla/5.0 (Windows; U; Windows NT 6.1; en-US) AppleWebKit/534.3 (KHTML, like Gecko) Chrome/6.0.472.33 Safari/534.3 SE 2.X MetaSr 1.0",
                "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.1; WOW64; Trident/5.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; InfoPath.3; .NET4.0C; .NET4.0E; SE 2.X MetaSr 1.0)",
                "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.1; WOW64; Trident/5.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; InfoPath.3; .NET4.0C; .NET4.0E)",
                "Mozilla/5.0 (Windows; U; Windows NT 6.1; ) AppleWebKit/534.12 (KHTML, like Gecko) Maxthon/3.0 Safari/534.12",
                "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)",
                "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)",
                "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.1; Trident/4.0; GTB7.0)",
                "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; WOW64; Trident/4.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; .NET4.0C; InfoPath.3)",
                "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Win64; x64; Trident/5.0; .NET CLR 2.0.50727; SLCC2; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; InfoPath.3; .NET4.0C; Tablet PC 2.0; .NET4.0E)",
                "Opera/9.80 (Windows NT 6.1; U; zh-cn) Presto/2.9.168 Version/11.50",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/534.50 (KHTML, like Gecko) Version/5.1 Safari/534.50",
                "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:6.0) Gecko/20100101 Firefox/6.0",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/14.0.835.163 Safari/535.1",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.1 (KHTML, like Gecko) Chrome/22.0.1207.1 Safari/537.1", "Mozilla/5.0 (X11; CrOS i686 2268.111.0) AppleWebKit/536.11 (KHTML, like Gecko) Chrome/20.0.1132.57 Safari/536.11",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/536.6 (KHTML, like Gecko) Chrome/20.0.1092.0 Safari/536.6",
                "Mozilla/5.0 (Windows NT 6.2) AppleWebKit/536.6 (KHTML, like Gecko) Chrome/20.0.1090.0 Safari/536.6",
                "Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.1 (KHTML, like Gecko) Chrome/19.77.34.5 Safari/537.1",
                "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/536.5 (KHTML, like Gecko) Chrome/19.0.1084.9 Safari/536.5",
                "Mozilla/5.0 (Windows NT 6.0) AppleWebKit/536.5 (KHTML, like Gecko) Chrome/19.0.1084.36 Safari/536.5",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/536.3 (KHTML, like Gecko) Chrome/19.0.1063.0 Safari/536.3",
                "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/536.3 (KHTML, like Gecko) Chrome/19.0.1063.0 Safari/536.3",
                "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_0) AppleWebKit/536.3 (KHTML, like Gecko) Chrome/19.0.1063.0 Safari/536.3",
                "Mozilla/5.0 (Windows NT 6.2) AppleWebKit/536.3 (KHTML, like Gecko) Chrome/19.0.1062.0 Safari/536.3",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/536.3 (KHTML, like Gecko) Chrome/19.0.1062.0 Safari/536.3",
                "Mozilla/5.0 (Windows NT 6.2) AppleWebKit/536.3 (KHTML, like Gecko) Chrome/19.0.1061.1 Safari/536.3",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/536.3 (KHTML, like Gecko) Chrome/19.0.1061.1 Safari/536.3",
                "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/536.3 (KHTML, like Gecko) Chrome/19.0.1061.1 Safari/536.3",
                "Mozilla/5.0 (Windows NT 6.2) AppleWebKit/536.3 (KHTML, like Gecko) Chrome/19.0.1061.0 Safari/536.3",
                "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/535.24 (KHTML, like Gecko) Chrome/19.0.1055.1 Safari/535.24",
                "Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/535.24 (KHTML, like Gecko) Chrome/19.0.1055.1 Safari/535.24",
                "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.131 Safari/537.36"
        };

        Random rand = new Random();
        return uat_list[rand.nextInt(uat_list.length)];
    }

    public  String get(String url) {

        //SipderStringRequest()


        String result = "";
        CloseableHttpResponse response = null;

        CloseableHttpClient httpClient = HttpConnectionManager.getHttpClient();
        HttpGet httpGet = new HttpGet(url);
        // 设置请求头信息，鉴权
        header.forEach((k, v) -> {
            httpGet.setHeader(k, v);
        });

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
}
