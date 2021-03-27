package com.zel.market.crawler;

import com.zel.commonutils.DateUtil;
import com.zel.commonutils.FileUtils;
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

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    /**
     * 下载线程
     */
    protected CountableThreadPool downloadThreadPool;

    /**
     * 解析网页线程
     */
    protected CountableThreadPool pageThreadPool;

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
        CrawRequest request = CrawRequest.builder().url(url).build();
        scheduler.push(request);

        downloadThreadPool = new CountableThreadPool(5);
        pageThreadPool = new CountableThreadPool(5);

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
        // 解析网页
        pageThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                    Date start = new Date();
                    // 读取文件
                    try {
                        Files.walk(Paths.get("./cache/crawler")).forEach((path -> {
                            File file = path.toFile();
                            if (file.isDirectory()) {
                                // return起到的作用和continue是相同的
                                return;
                            }

                            String fileName = file.getName();
                            String filePath = path.toAbsolutePath().toString();

                            boolean unread = fileName.contains("_readed_");
                            if (!unread) {
                                onParse(filePath);

                                String format = DateUtil.format(new Date(), DateUtil.YMD_HMS_3);
                                String newName = FileUtils.caselsh(fileName) + "_readed_" + format  + FileUtils.subffix(fileName);

                                FileUtils.rename(filePath, newName);
                            }
                        }));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //onDownloadSuccess(request, page);
                    waitNewUrl(1, TimeUnit.MINUTES);
                }

            }
        });

        // 下载网页
        while (!Thread.currentThread().isInterrupted()) {
            Date start = new Date();

            final CrawRequest request = scheduler.poll();
            if (request == null) {
                waitNewUrl();
            } else {
                // 线程执行
                downloadThreadPool.execute(new Runnable() {
                    @Override
                    public void run() {
                        processRequest(request);
                    }
                });
            }
        }
    }

    /**
     * 处理任务
     * @param request
     */
    private void processRequest(CrawRequest request) {
        Page page = downloader.download(request);
        System.out.println(page);
    }

    /**
     * 处理下载完成的网页
     * @param request
     * @param page
     */
    private void onParse(String path) {
        Page page = new Page();
        try {
            String data = FileUtils.read(path);
            page.setData(data);
        } catch (IOException e) {
            e.printStackTrace();
        }

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

    private void waitNewUrl(long time, TimeUnit unit) {
        newUrlLock.lock();
        try {
            //double check
            //if (threadPool.getThreadAlive() == 0) {
            //    return;
            //}
            newUrlCondition.await(time, unit);
        } catch (InterruptedException e) {
            System.out.println("waitNewUrl - interrupted, error e=" + e);
        } finally {
            newUrlLock.unlock();
        }
    }
}
