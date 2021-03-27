package com.zel.market.crawler.demo.picture;

import com.zel.commonutils.JsonHelper;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

// extends BaseTest
class DownloadPicTaskCallableTest  {

    @Test
    void testCallbale() {
        List<Future<DownloadPicTask>> submitList = new ArrayList<>();

        List<String> urls = Arrays.asList(
                "https://img-blog.csdnimg.cn/20201114223315323.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQ0NDAwNzIz,size_16,color_FFFFFF,t_70#pic_center",
                "https://img-blog.csdn.net/20170601225950586?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvZnB4dHk=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center"
        );
        for (String url : urls) {
            DownloadPicTask task = new DownloadPicTask();
            task.setUrl(url);
            // task
            DownloadPicTaskCallable callable = new DownloadPicTaskCallable(task);
            submitList.add(DownloadPicTaskCallable.executor.submit(callable));
        }

        // 执行
        for (Future<DownloadPicTask> future : submitList) {
            try {
                DownloadPicTask task = future.get();
                System.out.println("task=" + JsonHelper.write(task));
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    void testRunable() {

        List<String> urls = Arrays.asList(
                "https://img-blog.csdnimg.cn/20201114223315323.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQ0NDAwNzIz,size_16,color_FFFFFF,t_70#pic_center",
                "https://img-blog.csdn.net/20170601225950586?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvZnB4dHk=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center"
        );

        List<DownloadPicTaskRunnable> runableList = new ArrayList<>();
        for (String url : urls) {
            DownloadPicTask task = new DownloadPicTask();
            task.setUrl(url);
            // task
            DownloadPicTaskRunnable runable = new DownloadPicTaskRunnable(task);
            runableList.add(runable);
        }

        // 执行
        for (DownloadPicTaskRunnable runnable : runableList) {
            DownloadPicTaskRunnable.executor.execute(runnable);
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}