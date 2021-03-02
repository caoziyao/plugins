package com.zel.commonutils.bytes;

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
