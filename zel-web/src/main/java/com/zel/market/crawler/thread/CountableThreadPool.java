package com.zel.market.crawler.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description: 封装线程池
 *
 * @author csy
 * @version 1.0.0
 * @since 2021/3/28
 */
public class CountableThreadPool {

    private int threadNum;

    private ReentrantLock reentrantLock = new ReentrantLock();

    private Condition condition = reentrantLock.newCondition();

    private ExecutorService executorService;

    public CountableThreadPool(int threadNum) {
        this.threadNum = threadNum;
        this.executorService = Executors.newFixedThreadPool(threadNum);
    }

    /**
     * 线程执行
     * @param runnable
     */
    public void execute(final Runnable runnable) {
        executorService.execute(new Runnable() {

            @Override
            public void run() {
                runnable.run();
            }
        });
    }
}
