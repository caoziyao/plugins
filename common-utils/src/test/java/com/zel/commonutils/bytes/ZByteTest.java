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

    public void testBit() {
        // 127 ->  111 1111
        ZByte zByte = new ZByte(new byte[]{127});
        int i = zByte.bit(0, 1);
        System.out.println("ii: " + i);

        // [246]      m cr s pixel  1 7 0 6
        zByte = new ZByte(new byte[]{(byte)246});
        int pixel = zByte.bit(0, 2);
        AssertUtil.equal(pixel == 6, "test2 error");
        //System.out.println("pixel: " + pixel);
        int s = zByte.bit(3);
        //System.out.println("s: " + s);
        AssertUtil.equal(s == 0, "test2 error");
        int cr = zByte.bit(4, 6);
        //System.out.println("cr: " + cr);
        AssertUtil.equal(cr == 7, "test2 error");
        int m = zByte.bit(7);
        AssertUtil.equal(m == 1, "test2 error");
        //System.out.println("m: " + m);
    }
}