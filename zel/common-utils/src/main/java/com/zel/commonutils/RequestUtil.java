package com.zel.commonutils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class RequestUtil {

    public static void saveCookie(String key, String value, HttpServletRequest request
            , HttpServletResponse response){
        Map<String, Cookie> groupCookie = new HashMap<String, Cookie>();
        if (request.getCookies() != null) {
            for (Cookie c : request.getCookies()) {
                groupCookie.put(c.getName(), c);
            }
        }

        Cookie tokenC = groupCookie.get(key);
        if(tokenC == null){
            tokenC = new Cookie(key, value);
        }
        tokenC.setValue(value);
        tokenC.setPath("/");
        tokenC.setMaxAge(24 * 3600);
        response.addCookie(tokenC);
    }

    public static String getCookie(HttpServletRequest request, String name){
        if(request.getCookies() == null){
            return null;
        }

        for (Cookie c : request.getCookies()) {
            if(c.getName().equals(name)){
                return c.getValue();
            }
        }
        return null;
    }
}
