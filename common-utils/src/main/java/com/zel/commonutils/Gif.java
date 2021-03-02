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
        dto.parseLogicalScene(data.cut(6, 13));

        int size = dto.gctSize;
        //for i in range(13, size * 3 + 13, 3):
        //r = d[i]
        //g = d[i+1]
        //b = d[i+2]
        //color = [r, g, b]
        //colorTable.append(color);

        for (int i = 13; i < size * 3 + 13; i += 3) {
            ZByte color = data.cut(i, i + 3);
            dto.globalColorTable.add(color);
        }

        log.i("dto", dto);

    }

    public static void main(String[] args) throws IOException {
        Gif gif = new Gif();
        gif.gif();
    }
}

