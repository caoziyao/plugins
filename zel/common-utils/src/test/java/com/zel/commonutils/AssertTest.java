package com.zel.commonutils;


import junit.framework.TestCase;
import org.junit.Test;

public class AssertTest extends TestCase {

    @Test
    public void testIsTrue() {
        Assert.isTrue(false, () -> {
            return new RuntimeException("dsdf");
        });
    }

    @Test
    public void testIsTrue2() {
        Assert.isTrue(false, "throw {}", "abc");
    }
}