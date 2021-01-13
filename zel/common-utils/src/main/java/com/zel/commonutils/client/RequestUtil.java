package com.zel.commonutils.client;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class RequestUtil {


    public static String getStringDef(HttpServletRequest request, String key, String nullMsg) {
        String sVal = request.getParameter(key);
        if (StringUtils.isEmpty(sVal)) {
            return nullMsg;
        } else {
            return sVal;
        }
    }

}
