package com.zel.commonutils;

import junit.framework.TestCase;
import org.junit.Test;

public class ConsoleTest extends TestCase {

    @Test
    public void testLog0() {
        Console.log();
    }


    @Test
    public void testLog() {
        Console.log("a", "b", "c");
    }

    @Test
    public void testLog2() {
        Console.log(StrUtil.format("This is Console log for {}.", "test"));
    }
}