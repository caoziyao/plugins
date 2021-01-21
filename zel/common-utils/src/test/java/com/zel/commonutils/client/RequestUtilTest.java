package com.zel.commonutils.client;

import com.google.common.base.Splitter;
import com.zel.commonutils.AssertUtil;
import com.zel.commonutils.StrUtil;
import junit.framework.TestCase;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class RequestUtilTest extends TestCase {

    @Test
    public void testGetStringDef() {
    }

    @Test
    public void testGetParam() {

        String url = "sdfwef?abc=123";
        String abc = RequestUtil.getParam(url, "abc");
        AssertUtil.equal("123".equals(abc), StrUtil.format("abc output={}", abc));

        String newurl = url.substring(0, url.indexOf("?"));
        AssertUtil.equal("sdfwef".equals(newurl), StrUtil.format("newurl output={}", newurl));

        String asdfew = RequestUtil.getParam("vsav", "asdfew");
        AssertUtil.equal(asdfew == null, StrUtil.format("asdfew output={}", asdfew));


        String ccc = RequestUtil.getParam("vsav?abc=123&ccc=jb", "asdfew");
        AssertUtil.equal("jb".equals(ccc), StrUtil.format("ccc output={}", ccc));
    }

}