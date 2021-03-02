package com.zel.commonutils.gif;

import com.zel.commonutils.bytes.ZByte;

import java.util.Arrays;

/**
 * https://www.jianshu.com/p/38743ef278ac
 */
public class GifDTO {

    // GIF署名（Signature） GIF, [71 73 70]
    public ZByte signature;

    // 版本号（Version） [56, 57, 97]
    public ZByte version;

    // 2 字节宽度 [73, 0]
    public int width;

    //2 字节高度 [65, 0]
    public int height;

    @Override
    public String toString() {
        return "GifDTO{" +
                "signature=" + signature +
                ", version=" + version +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
