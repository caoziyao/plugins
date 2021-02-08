package com.zel.commonutils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class IpUtil {

    private static Set<String> BLACKSET = new HashSet<String>();
    private static int COUNT = 0;
    private static int LIMIT = 10000;
    private static ExecutorService service = Executors.newFixedThreadPool(1);

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

    public static boolean checkIp(String ip){
        if(StringUtils.isBlank(ip)){
            return false;
        }
        if(ip.startsWith("127") || ip.startsWith("192.168") || ip.startsWith("172.21") || ip.startsWith("10")){
            return true;
        }
        return false;
    }


    /**
     * 根据ip获取真实地理
     * @param strIP
     * @return
     * @throws IOException
     */
    public static String getAddressByIP(String strIP) throws IOException {
        // api
        URL url = new URL("http://ip.taobao.com/service/getIpInfo.php?ip=" + strIP);
        URLConnection conn = url.openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        String line = null;
        StringBuilder result = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            result.append(line);
        }
        reader.close();
        String res = result.toString();
        return res;
    }

    //过滤黑名单ip 每1万次请求刷新一次缓存
    //public static synchronized boolean checkBlackIp(String ip){
    //    COUNT++;
    //    if (COUNT > LIMIT) {
    //        COUNT = 0;
    //        reload();
    //    }
    //    if (BLACKSET.contains(ip)) {
    //        return false;
    //    } else {
    //        return true;
    //    }
    //}
    //
    //public static void reload(){
    //    service.submit(new Runnable() {
    //        @Override
    //        public void run() {
    //            BLACKSET = reids.smembers("keys");
    //        }
    //    });
    //}
}
