package com.zel.commonutils.gif;

import com.zel.commonutils.AssertUtil;
import com.zel.commonutils.bytes.ZByte;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://www.jianshu.com/p/38743ef278ac
 */
//@ToString
public class GifDTO {

    // GIF署名（Signature） GIF, [71 73 70]
    public ZByte signature;

    // 版本号（Version） [56, 57, 97]
    public ZByte version;

    // 逻辑屏幕标识符(Logical Screen Descriptor)
    public LogicalScene logicalScene;

    // 全局颜色列表（Global Color Table）大小 2 ^ (pixel + 1)
    public int gctSize;

    public List<ZByte> globalColorTable = new ArrayList<>();

    public void parseLogicalScene(ZByte data) {
        LogicalScene ls = new LogicalScene();
        ls.width = data.cut(0, 2).toInt();
        ls.height = data.cut(2, 4).toInt();

        //  m cr s pixel
        ZByte d2 = data.cut(4);
        ls.pixel = d2.bit(0, 2);
        ls.s = d2.bit(3);
        ls.cr = d2.bit(4, 6);
        ls.m = d2.bit(7);

        //
        ls.background = data.cut(5).toInt();
        ls.radio = data.cut(6).toInt();

        this.logicalScene = ls;

        // 全局颜色列表
        this.gctSize = (int)Math.pow(2, ls.pixel + 1);
    }


    /**
     * 逻辑屏幕标识符(Logical Screen Descriptor)
     *
     * [246]      m cr s pixel  1 7 0 6
     * m - 全局颜色列表标志(Global Color Table Flag)，当置位时表示有全局颜色列表，pixel值有意义;
     * cr - 颜色深度(Color ResoluTion)，cr+1确定图象的颜色深度;
     * s - 分类标志(Sort Flag)，如果置位表示全局颜色列表分类排列;
     * pixel - 全局颜色列表大小，pixel+1确定颜色列表的索引数（2^(pixel+1)）;
     * 背景颜色：背景颜色在全局颜色列表中的索引（PS:是索引而不是RGB值，所以如果没有全局颜色列表时，该值没有意义）;
     */
    @ToString
    public static class LogicalScene {
        // 2 字节宽度 [73, 0]
        public int width;

        //2 字节高度 [65, 0]
        public int height;

        // 1 字节
        public int m;
        public int cr;
        public int s;
        public int pixel;

        // 1 字节 背景色
        public int background;

        // 1 字节 像素宽高比
        public int radio;
    }

    @Override
    public String toString() {
        return "GifDTO{" +
                "signature=" + signature +
                ", version=" + version +
                ", logicalScene=" + logicalScene +
                ", gctSize=" + gctSize +
                '}';
    }
}
