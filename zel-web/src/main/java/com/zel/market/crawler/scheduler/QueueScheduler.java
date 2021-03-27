package com.zel.market.crawler.scheduler;

import com.zel.market.crawler.CrawRequest;
import com.zel.market.crawler.CrawTask;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2021/3/28
 */
public class QueueScheduler implements Scheduler{

    private BlockingQueue<CrawRequest> queue = new LinkedBlockingQueue<>();

    /**
     * , CrawTask task
     * @param request
     */
    @Override
    public void push(CrawRequest request) {
        queue.add(request);
    }


    // CrawTask task
    @Override
    public CrawRequest poll() {
        return queue.poll();
    }

    /**
     *  put()方法向队列中生产数据，当队列满时，线程阻塞
     *  take()方法从队列中消费数据，当队列为空是，线程阻塞
     */
    @Override
    public CrawRequest take() throws InterruptedException {
        return queue.take();
    }
}
