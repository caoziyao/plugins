package com.zel.commonutils.client;

import com.google.common.base.Splitter;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class RequestUtil {

    public static String getStringDef(HttpServletRequest request, String key, String nullMsg) {
        String sVal = request.getParameter(key);
        if (StringUtils.isEmpty(sVal)) {
            return nullMsg;
        } else {
            return sVal;
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
}
