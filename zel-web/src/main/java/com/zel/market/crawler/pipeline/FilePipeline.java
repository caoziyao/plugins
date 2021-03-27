package com.zel.market.crawler.pipeline;

import com.zel.commonutils.FileUtils;
import com.zel.commonutils.crypto.UuidUtils;
import com.zel.market.crawler.pageprocessor.ResultItems;

import java.io.File;
import java.io.IOException;

/**
 * Description: 输出到文件
 *
 * @author csy
 * @version 1.0.0
 * @since 2021/3/28
 */
public class FilePipeline extends FilePersistentBase implements Pipeline {

    private String path;

    public FilePipeline() {
        this.path = "./cache";
    }
    @Override
    public void process(ResultItems resultItems) {
        String path = this.path + File.pathSeparator + UuidUtils.getUUID() + File.pathSeparator;
        try {
            FileUtils.write(path, resultItems.getData());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
