package com.zel.commonutils;

import com.zel.commonutils.bytes.ZByte;
import com.zel.commonutils.gif.GifDTO;
import com.zel.commonutils.logutil.log;

import java.io.IOException;

public class Gif  {

    /**
     * 1, 解析 gif
     * 2，构建 class
     * 3, 画图
     * 4, 画 gif
     * @throws IOException
     */

    // [71 73 70 56 57 97 73 0 65 0 -10 0 0 4 4 3 20 10 7 28 27 24 40 40 39 55 51 42 55 55 55
    public void gif() throws IOException {
        System.out.println("gif1");
        GifDTO dto = new GifDTO();

        ZByte data = new ZByte(FileUtils.dataFrom("./doge.gif"));
        //Log.logBytes(BytesUtil.cut(data, 0, 10));

        dto.signature = data.cut(0, 3);
        dto.version = data.cut(3, 6);


        log.i(data.cut(6, 8));
        log.i(data.cut(8, 10));
        dto.width = data.cut(6, 8).toInt();
        dto.height = data.cut(8, 10).toInt();

        //# 2 字节宽度，2 字节高度  [73, 0] [65, 0]
        //# 宽 73 高 65
        //log(d[6:10], d[6], d[8])

        log.i("dto", dto);

    }

    public static void main(String[] args) throws IOException {
        Gif gif = new Gif();
        gif.gif();
    }
}

