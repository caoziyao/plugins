package com.zel.commonutils.client;

import com.google.common.base.Splitter;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class RequestUtil {


    public static String getString(HttpServletRequest request, String key, String nullMsg) {
        String sVal = request.getParameter(key);
        if (StringUtils.isEmpty(sVal)) {
            return nullMsg;
        } else {
            return sVal;
        }
    }

    public static Long getLong(HttpServletRequest request, String key, Long def) {
        String s = request.getParameter(key);
        if (StringUtils.isBlank(s)) {
            return def;
        } else {
            try {
                return Long.parseLong(s.trim());
            } catch (NumberFormatException var5) {
                System.out.println("NumberFormatException [Long]: " + s);
                return def;
            }
        }
    }

    public static Integer getInteger(HttpServletRequest request, String key, Integer def) {
        String s = request.getParameter(key);
        if (StringUtils.isBlank(s)) {
            return def;
        } else {
            try {
                return Integer.parseInt(s.trim());
            } catch (NumberFormatException var5) {
                System.out.println("NumberFormatException [Long]: " + s);
                return def;
            }
        }
    }

    public static  String getParam(String url, String name) {
        String params = "";
        if (url.contains("?")) {
            params = url.substring(url.indexOf("?") + 1, url.length());
        }

        System.out.println("params:" + params);
        Map<String, String> split = new HashMap<>();

        if (StringUtils.isNotBlank(params) && params.contains("=")) {
            split = Splitter.on("&").withKeyValueSeparator("=").split(params);
        }

        return split.get(name);
    }

    /**
     * @info:通过request 获得用户的真实的Ip
     * @param request
     * @return String
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        if (ip != null && ip.length() != 0) {
            if (ip.indexOf(",") >= 0) {
                String[] array = ip.split(",");
                for (int i = 0; i < array.length; i++) {
                    if (array[i] != null && !("unknown".equalsIgnoreCase(array[i]))) {
                        ip = array[i];
                        break;
                    }
                }
            }
        }
        if (ip == null) {
            ip = "";
        }
        return ip.trim();
    }

    public static String getClientType(String ua) {
        if (org.springframework.util.StringUtils.isEmpty(ua)) {
            return "UNKNOWN";
        }
        ua = ua.toLowerCase();

        if (ua.indexOf("windows nt") >= 0) {
            return "WINDOW";
        }
        if (ua.indexOf("ipad") >= 0) {
            return "IPAD";
        }
        if (ua.indexOf("mac") >= 0) {
            return "MAC";
        }
        if (ua.indexOf("iphone") >= 0) {
            return "IPHONE";
        }
        if (ua.indexOf("android") >= 0) {
            return "ANDROID";
        }
        if (ua.indexOf("linux") >= 0) {
            return "LINUX";
        }
        if (ua.indexOf("ubuntu") >= 0) {
            return "UBUNTU";
        }
        return "OTHER";
    }
}
