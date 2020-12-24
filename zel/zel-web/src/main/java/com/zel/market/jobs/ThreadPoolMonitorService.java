package com.zel.market.jobs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 监控线程类
 */
@Service
public class ThreadPoolMonitorService implements Runnable {

    private Logger logger = LoggerFactory.getLogger(ThreadPoolMonitorService.class);

    private ThreadPoolExecutor executor;

    public void monitorThreadPool() {
        String buffer = "CurrentPoolSize：" + executor.getPoolSize() +
                " - CorePoolSize：" + executor.getCorePoolSize() +
                " - MaximumPoolSize：" + executor.getMaximumPoolSize() +
                " - ActiveTaskCount：" + executor.getActiveCount() +
                " - CompletedTaskCount：" + executor.getCompletedTaskCount() +
                " - TotalTaskCount：" + executor.getTaskCount() +
                " - isTerminated：" + executor.isTerminated();
        System.out.println(buffer);
    }


    @Override
    public void run() {
        try {
            while (true) {
                monitorThreadPool();
                Thread.sleep(30 * 1000);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void setExecutor(ThreadPoolExecutor executor) {
        this.executor = executor;
    }
}
