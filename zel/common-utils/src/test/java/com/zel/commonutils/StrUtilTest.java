package com.zel.commonutils;

import com.zel.commonutils.client.RequestUtil;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class StrUtilTest extends TestCase {

    @Test
    public void testTestFormat() {
        String a = "logTime=1611123769890";
//        System.out.println(a.length());

        int index = a.indexOf("logTime");
        System.out.println(index);

//        RequestUtil.getParam(a, "logTime")
//        String b = a.substring(0, index -1 );
//        System.out.println(b);
//        System.out.println( a.indexOf("logTime"));
//
//        String c = a.substring(index + "logTime".length() + 1 , index + "logTime".length() + 1 + 13 );
//        System.out.println(c);
//        String format = StrUtil.format("hell {} {}", "abc", "ccc");
//        System.out.println(format);
//
//        Map<String, Object> map = new HashMap<>();
//        map.put("name", "zhangsan");
//        map.put("age", 18);
//
//        System.out.println(StrUtil.format("hello {name} {age}", map));
    }
}