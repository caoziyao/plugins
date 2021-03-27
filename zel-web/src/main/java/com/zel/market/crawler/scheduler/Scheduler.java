package com.zel.market.crawler.scheduler;

import com.zel.market.crawler.CrawRequest;
import com.zel.market.crawler.CrawTask;

/**
 * Description: Scheduler负责管理待抓取的URL，以及一些去重的工作。
 *
 * @author csy
 * @version 1.0.0
 * @since 2021/3/28
 */
public interface Scheduler {
    /**
     * add a url to fetch , CrawTask task
     */
    public void push(CrawRequest request);

    /**
     * get an url to crawl
     * 不阻塞
     */
    public CrawRequest poll();

    /**
     * get an url to crawl
     * 阻塞
     */
    public CrawRequest take() throws InterruptedException;
}
