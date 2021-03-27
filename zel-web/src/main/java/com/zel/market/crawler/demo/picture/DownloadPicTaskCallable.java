package com.zel.market.crawler.demo.picture;

import com.zel.commonutils.ExceptionUtil;
import com.zel.commonutils.FileUtils;
import com.zel.commonutils.client.HttpUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * 批量下载图片任务
 */
public class DownloadPicTaskCallable implements Callable<DownloadPicTask> {

    public static final Logger log = LoggerFactory.getLogger(DownloadPicTaskCallable.class);

    public static final ExecutorService executor = Executors.newFixedThreadPool(8);

    private DownloadPicTask task;

    public DownloadPicTaskCallable(DownloadPicTask task) {
        this.task = task;
    }


    @Override
    public DownloadPicTask call() throws Exception {
        if (this.task == null) {
            return null;
        }

        String url = this.task.getUrl();

        String filePath = "";
        if (StringUtils.isNotBlank(url)) {
            // url 下载图片
            int index = url.lastIndexOf(".");// 最后一个后缀点出现的下标
            String suffix = url.substring(index + 1, url.length()).toLowerCase();// 获取文件后缀名
            List<String> suffixList = Arrays.asList(".jpg", ".png");
            if (!suffixList.contains(suffix)) {
                suffix = ".jpg";
            }
            filePath = FileUtils.getFilePath(suffix);
            File saveFile = new File(filePath);
            FileUtils.checkAndCreateParentDir(saveFile);

            try {
                new HttpUtil().download(url, saveFile);
                this.task.setFilePath(filePath);
            } catch (Exception e) {
                e.printStackTrace();
                log.error("下载图片失败|url={}, e={}", url, ExceptionUtil.stacktraceToOneLineString(e));
            }
        }
        return this.task;
    }
}