package com.zel.commonutils.client;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2021/3/27
 */
public class UrlUtil {
    int DEFAULT_TIMEOUT = 3000;
    static Charset DEFAULT_ENCODING = StandardCharsets.UTF_8;
    static String CONTENT_TYPE_JSON = "application/json; charset=utf-8";
    static String CONTENT_TYPE = "Content-Type";
    static String CONTENT_ENCODING = "Content-Encoding";
    static String USER_AGENT = "User-Agent";
    static String USER_AGENT_DATA = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.142 Safari/537.36 simple-http";
    static String EMPTY = "";

    public static String urlEncode(String value) {
        if (value == null) {
            return "";
        } else {
            try {
                String encoded = URLEncoder.encode(value, DEFAULT_ENCODING.displayName());
                return encoded.replace("+", "%20").replace("*", "%2A").replace("~", "%7E").replace("/", "%2F");
            } catch (UnsupportedEncodingException var2) {
                throw new RuntimeException("Failed To Encode Uri", var2);
            }
        }
    }

    public static String urlDecode(String url) throws RuntimeException {
        if (StringUtils.isEmpty(url)) {
            return url;
        } else {
            try {
                return URLDecoder.decode(url, DEFAULT_ENCODING.displayName());
            } catch (UnsupportedEncodingException var2) {
                throw new RuntimeException("Unsupported encoding", var2);
            }
        }
    }

}
