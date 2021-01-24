package com.zel.commonutils;

import junit.framework.TestCase;
import org.junit.Test;

public class ExceptionUtilTest extends TestCase {

    @Test
    public void testGetMessage() {
        try {
            throw new  NullPointerException("sss");
        } catch (Exception e) {
            String msg = ExceptionUtil.getMessage(e);
            System.out.println(msg);
        }

    }

    @Test
    public void testGetMessage2() {
        try {
            throw new  NullPointerException("sss");
        } catch (Exception e) {
            System.out.println(ExceptionUtil.stacktraceToOneLineString(e));
        }
    }

    @Test
    public void testGetMessage3() {
        try {
            throw new  NullPointerException("sss");
        } catch (Exception e) {
            System.out.println(ExceptionUtil.getStackTrace(e));
        }
    }
}