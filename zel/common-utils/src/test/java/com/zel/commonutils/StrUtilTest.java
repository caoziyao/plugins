package com.zel.commonutils;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class StrUtilTest extends TestCase {

    @Test
    public void testTestFormat() {
        String format = StrUtil.format("hell {} {}", "abc", "ccc");
        System.out.println(format);

        Map<String, Object> map = new HashMap<>();
        map.put("name", "zhangsan");
        map.put("age", 18);

        System.out.println(StrUtil.format("hello {name} {age}", map));
    }
}