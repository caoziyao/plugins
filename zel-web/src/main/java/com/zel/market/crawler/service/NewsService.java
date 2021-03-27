package com.zel.market.crawler.service;

import com.zel.market.common.SysLoggers;
import com.zel.market.crawler.dto.NewsDTO;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2021/3/27
 */
@Service
public class NewsService {

    private static LinkedBlockingQueue<List<NewsDTO>> newsQueue;

    public void takeProcess() throws InterruptedException {
        try {
            Thread.sleep(1000);
            System.out.println("NewsService task!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // put()方法向队列中生产数据，当队列满时，线程阻塞
        // take()方法从队列中消费数据，当队列为空是，线程阻塞
        //List<NewsDTO> newsList = newsQueue.take();
        //if (CollectionUtils.isEmpty(newsList)) {
        //    return;
        //}
        //System.out.println("get task!");
        //
        //for (NewsDTO item : newsList) {
        //    System.out.println(item.getTitle());
        //}

        //List<NewsDTO> newsList = BlockingQueueHelper.take();
        //if (CollectionUtils.isEmpty(newsList)) {
        //    return;
        //}
    }
}
