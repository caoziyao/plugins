package com.zel.market.crawler;

import com.zel.commonutils.StrUtil;
import com.zel.commonutils.crypto.UuidUtils;
import com.zel.market.crawler.downloader.Downloader;
import com.zel.market.crawler.downloader.HttpClientDownloader;
import com.zel.market.crawler.pageprocessor.Page;
import com.zel.market.crawler.pageprocessor.PageProcessor;
import com.zel.market.crawler.pipeline.Pipeline;
import com.zel.market.crawler.scheduler.QueueScheduler;
import com.zel.market.crawler.scheduler.Scheduler;
import com.zel.market.crawler.thread.CountableThreadPool;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2021/3/28
 */
public class Spider implements Runnable {

    protected Scheduler scheduler = new QueueScheduler();

    private ReentrantLock newUrlLock = new ReentrantLock();

    private Condition newUrlCondition = newUrlLock.newCondition();

    // 任务执行线程
    protected CountableThreadPool threadPool;

    /**
     * 下载器，用于下载网页
     */
    protected Downloader downloader;

    /**
     * 页面处理，用于处理下载完成的网页
     */
    protected PageProcessor pageProcessor;

    /**
     * 后续处理，用于处理页面完成的数据
     */
    protected List<Pipeline> pipelines = new ArrayList<>();

    // 空闲等待时间
    private int emptySleepTime = 10 * 1000;

    public Spider(String url, PageProcessor pageProcessor) {
        CrawRequest request = CrawRequest.builder().url("http://www.baidu.com").build();
        scheduler.push(request);

        threadPool = new CountableThreadPool(5);
        downloader = new HttpClientDownloader();
        this.pageProcessor = pageProcessor;
    }

    public Spider addPipeline(Pipeline pipeline) {
        //checkIfRunning();
        this.pipelines.add(pipeline);
        return this;
    }

    public void runAsync() {
        Thread thread = new Thread(this);
        thread.setDaemon(false);
        thread.start();
    }

    public void start() {
        runAsync();
    }

    public void stop() {
        System.out.println("stop");
    }

    @Override
    public void run() {

        System.out.println("Spider {} run!" + UuidUtils.getUUID());
        while (!Thread.currentThread().isInterrupted()) {
            Date start = new Date();

            System.out.println(StrUtil.format("Spider {} started! {}", UuidUtils.getUUID(),  start));

            final CrawRequest request = scheduler.poll();
            if (request == null) {
                waitNewUrl();
            } else {
                // 线程执行
                threadPool.execute(new Runnable() {
                    @Override
                    public void run() {
                        processRequest(request);
                    }
                });
            }
        }

        System.out.println("Spider  end!" + new Date());
    }

    /**
     * 处理任务
     * @param request
     */
    private void processRequest(CrawRequest request) {

        Page page = downloader.download(request);

        System.out.println(page);

        onDownloadSuccess(request, page);
    }

    /**
     * 处理下载完成的网页
     * @param request
     * @param page
     */
    private void onDownloadSuccess(CrawRequest request, Page page) {
        pageProcessor.process(page);
        for (Pipeline pipeline : pipelines) {
            pipeline.process(page.getResultItems());
        }
    }

    /**
     * 处理下载失败的网页
     * @param request
     * @param page
     */
    private void onDownloaderFail(CrawRequest request) {
        //for cycle retry
        //doCycleRetry(request);
        sleep(100);
        scheduler.push(request);
    }

    /**
     * 封装 sleep
     * @param time
     */
    protected void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted when sleep" + e );
        }
    }

    private void signalNewUrl() {
        try {
            newUrlLock.lock();
            newUrlCondition.signalAll();
        } finally {
            newUrlLock.unlock();
        }
    }

    /**
     * 阻塞等待 emptySleepTime 时间
     */
    private void waitNewUrl() {
        newUrlLock.lock();
        try {
            //double check
            //if (threadPool.getThreadAlive() == 0) {
            //    return;
            //}
            newUrlCondition.await(emptySleepTime, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            System.out.println("waitNewUrl - interrupted, error e=" + e);
        } finally {
            newUrlLock.unlock();
        }
    }
}
