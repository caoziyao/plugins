package com.zel.market.crawler.demo;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Future;

public enum WebTaskUtil {
    INSTANCE;

    /**
     * Description: 提交任务
     * 如果只有一个任务，则主线程处理
     * 如果多个任务，则使用线程池处理，主线程阻塞，等待任务完成被唤醒，最多40秒超时
     *
     * @param web
     */
    public void submit(TaskForWeb web) {

        synchronized (web.getLock()) {
            List<Future<Integer>> resultList = new LinkedList<>();
            try {
                for (TaskDemo sub : web.getTasks()) {
                    // web.getThreads().execute(sub);
                    Future<Integer> futureTask = web.getThreads().submit(sub);
                    resultList.add(futureTask);
                }
                //最多等待55s
                //web.getLock().wait(55000);
                web.getLock().wait(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                //执行完或超时，则关闭线程池
                if (!web.getThreads().isShutdown()) {
                    web.getThreads().shutdown();
                } else {

                }
            }

            // 结果
            for (Future<Integer> future : resultList) {
                try {
                    Integer integer = future.get();
                    System.out.println("result:" + integer);
                } catch (Exception e) {
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    e.printStackTrace(new PrintStream(baos));
                    String exception = baos.toString();
                    System.out.println(exception);
                }
            }
        }

    }

    public static void main(String[] args) {
        TaskForWeb web = new TaskForWeb(5);
        web.addTask();
        web.addTask();
        WebTaskUtil.INSTANCE.submit(web);
    }
}
