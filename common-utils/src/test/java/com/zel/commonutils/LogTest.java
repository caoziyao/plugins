package com.zel.commonutils;

import junit.framework.TestCase;
import org.junit.Test;

public class LogTest extends TestCase {

    @Test
    public void testLog0() {
        Log.log();
    }


    @Test
    public void testLog() {
        Log.log("a", "b", "c");
    }

    @Test
    public void testLog2() {
        Log.log(StrUtil.format("This is Console log for {}.", "test"));
    }
}