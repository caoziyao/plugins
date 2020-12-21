package com.zel;

import java.util.zip.Deflater;
import java.util.zip.Inflater;

/**
 * 数据压缩
 */
public class ZipUtils {

    /**
     * 压缩
     */
    public static byte[] compress(byte[] data) {
        byte[] output = new byte[0];

        Deflater compresser = new Deflater();
        return null;
    }

    /**
     * 解压缩
     *
     */
    public static byte[] decompress(byte[] data) {
        Inflater decompresser = new Inflater();
        return null;
    }
}
