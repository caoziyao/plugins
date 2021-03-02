package com.zel.commonutils.bytes;

import com.zel.commonutils.AssertUtil;
import junit.framework.TestCase;

public class ZByteTest extends TestCase {

    public void testToInt() {
        //258 -> 小端  [2, 1]
        ZByte zByte = new ZByte(new byte[]{2, 1});
        int i = zByte.toInt();
        AssertUtil.equal(i == 258, "test error");

        // 777  -> 小端   [9, 3]
        zByte = new ZByte(new byte[]{9, 3});
        i = zByte.toInt();
        AssertUtil.equal(i == 777, "test2 error");

    }
}