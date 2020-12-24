package com.zel.commonutils.client;

import com.zel.commonutils.MessageFormatter;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class RequestUtils {

    public static void saveCookie(String key, String value, HttpServletRequest request
            , HttpServletResponse response) {
        Map<String, Cookie> groupCookie = new HashMap<String, Cookie>();
        if (request.getCookies() != null) {
            for (Cookie c : request.getCookies()) {
                groupCookie.put(c.getName(), c);
            }
        }

        Cookie tokenC = groupCookie.get(key);
        if (tokenC == null) {
            tokenC = new Cookie(key, value);
        }
        tokenC.setValue(value);
        tokenC.setPath("/");
        tokenC.setMaxAge(24 * 3600);
        response.addCookie(tokenC);
    }

    public static String getCookie(HttpServletRequest request, String name) {
        if (request.getCookies() == null) {
            return null;
        }

        for (Cookie c : request.getCookies()) {
            if (c.getName().equals(name)) {
                return c.getValue();
            }
        }
        return null;
    }

    public static Map<String, String> getHeaders(HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();
        Enumeration<String> e = request.getHeaderNames();
        while (e.hasMoreElements()) {
            String key = e.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        return map;
    }

    public static String getBody(HttpServletRequest request) {
        BufferedReader br = null;
        String str, wholeStr = "";
        StringBuilder sb = new StringBuilder();
        InputStream inputStream = null;
        BufferedReader reader = null;

        //try {
        //    inputStream = request.getInputStream();
        //    reader = new BufferedReader(
        //            new InputStreamReader(inputStream, Charset.forName("UTF-8")));
        //
        //    char[] bodyCharBuffer = new char[1024];
        //    int len = 0;
        //    while ((len = reader.read(bodyCharBuffer)) != -1) {
        //        sb.append(new String(bodyCharBuffer, 0, len));
        //    }
        //} catch (IOException e) {
        //    e.printStackTrace();
        //}

        return sb.toString();
    }

    public static String getURL(HttpServletRequest request) {
        StringBuffer url = request.getRequestURL();
        String query = request.getQueryString();
        if (StringUtils.isNotBlank(query)) {
            url.append("?").append(query);
        }

        return url.toString();
    }

    /**
     * 转为对应的curl 请求
     */
    public static String curl(HttpServletRequest request) {

        StringBuilder curl = new StringBuilder();
        curl.append("curl ");
        String url = getURL(request);
        
        Map<String, String> headers = getHeaders(request);
        String body = getBody(request);

        for (String key : headers.keySet()) {
            String value = headers.get(key);
            curl.append(MessageFormatter.formatWithArgs(" -H '{}: {}' ", key, value));
        }

        if (StringUtils.isNotEmpty(body)) {
            curl.append(MessageFormatter.formatWithArgs("--data-raw '{}' ", body));
        }
        curl.append(MessageFormatter.formatWithArgs("--compressed '{}'", url));
        return curl.toString();
    }
}
