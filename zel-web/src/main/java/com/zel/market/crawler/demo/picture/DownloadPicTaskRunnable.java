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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DownloadPicTaskRunnable implements Runnable{


    public static final Logger log = LoggerFactory.getLogger(DownloadPicTaskRunnable.class);

    public static final ExecutorService executor = Executors.newFixedThreadPool(8);

    private DownloadPicTask task;

    DownloadPicTaskRunnable(DownloadPicTask task) {
        this.task = task;
    }

    @Override
    public void run() {
        if (task == null) {
            return ;
        }

        String url = task.getUrl();

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
                task.setFilePath(filePath);
            } catch (Exception e) {
                e.printStackTrace();
                log.error("下载图片失败|url={}, e={}", url, ExceptionUtil.stacktraceToOneLineString(e));
            }
        }

//        synchronized (this) {
//            this.notify();
//        }
    }

}
