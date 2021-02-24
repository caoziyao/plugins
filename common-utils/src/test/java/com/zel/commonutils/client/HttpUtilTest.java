package com.zel.commonutils.client;

import junit.framework.TestCase;

import java.io.IOException;

public class HttpUtilTest extends TestCase {

    public void testDownload() {
    }

    public void testGetPingTime() {

        try {
            String pingTime = HttpUtil.ping("www.baidu.com");
            System.out.println(pingTime);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}