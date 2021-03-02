package com.zel.commonutils.bytes;

import com.zel.commonutils.StrUtil;

import java.util.Arrays;

/**
 * 自定义 byte[]
 */
public class ZByte {
    private byte[] data;

    public ZByte(byte[] data) {
        this.data = data;
    }

    /**
     * [1, 2, 3, 4]
     * cut(0, 2)  ->  [0, 1]
     * @param start
     * @param end
     * @return
     */
    public ZByte cut(int start, int end) {
        byte[] cut = BytesUtil.cut(this.data, start, end);
        return new ZByte(cut);
    }

    /**
     * [1, 2, 3, 4]
     * cut(1)  ->  [2]
     * @return
     */
    public ZByte cut(int index) {
        return cut(index, index+1);
    }

    /**
     * 取 bit 位
     * 0b1111 1111
     * bit(0, 1) -> 0b0000 0011
     * @param start
     * @param end
     * @return
     */
    public int bit(int start, int end) {
        int l = 7 - end;
        int r = start + l;
        int d = data[0];
        d = ((d << l) & 0xff ) >> r;
        return d;
    }

    /**
     * 取 bit 位
     * 0b1111 1111
     * bit(0, 1) -> 0b0000 0011
     * @return
     */
    public int bit(int index) {
        return bit(index, index);
    }

    /**
     * 小端 little_edian  [low, high]
     * 258 -> 小端  [2, 1]
     * 777 -> 小端  [9, 3]
     * @return
     */
    public int toInt() {
        int value = 0;
        int len = this.data.length;
        for (int i = 0; i < len; i++) {
            int e = 8 * i;
            value = (this.data[i] << e) | value;
        }
        return value;
    }

    @Override
    public String toString() {
        return Arrays.toString(data);
    }
}
