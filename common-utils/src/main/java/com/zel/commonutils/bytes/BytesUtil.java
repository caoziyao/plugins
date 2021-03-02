package com.zel.commonutils.bytes;

public class BytesUtil {

    /**
     * [1, 2, 3, 4]
     * cut(0, 2)  ->  [0, 1]
     * @param data
     * @param start
     * @param end
     * @return
     */
    public static byte[] cut(byte[] data, int start, int end) {
        if (end <= start) {
            return new byte[0];
        }

        byte[] res = new byte[end-start];

        int index = 0;
        for (int i = start; i < end; i++) {
            res[index] = data[i];
            index += 1;
        }
        return res;
    }

}
