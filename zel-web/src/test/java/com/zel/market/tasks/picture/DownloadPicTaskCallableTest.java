package com.zel.market.tasks.picture;

import com.zel.commonutils.JsonHelper;
import com.zel.market.BaseTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static org.junit.jupiter.api.Assertions.*;

// extends BaseTest
class DownloadPicTaskCallableTest  {

    @Test
    void testCall() {
        List<Future<DownloadPicTask>> submitList = new ArrayList<>();

        List<String> urls = Arrays.asList(
                "https://img-blog.csdnimg.cn/20201114223315323.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQ0NDAwNzIz,size_16,color_FFFFFF,t_70#pic_center"
        );
        for (String url : urls) {
            DownloadPicTask task = new DownloadPicTask();
            task.setUrl(url);
            // task
            DownloadPicTaskCallable callable = new DownloadPicTaskCallable(task);
            submitList.add(DownloadPicTaskCallable.service.submit(callable));
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
}