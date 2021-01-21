package com.zel.commonutils;


import junit.framework.TestCase;
import org.junit.Test;

public class AssertUtilTest extends TestCase {

    @Test
    public void testIsTrue() {
        AssertUtil.isTrue(false, () -> {
            return new RuntimeException("dsdf");
        });
    }

    @Test
    public void testIsTrue2() {
        AssertUtil.isTrue(false, "throw {}", "abc");
    }


    @Test
    public  void test2() {
        AssertUtil.equal(false, "testee");
    }
}