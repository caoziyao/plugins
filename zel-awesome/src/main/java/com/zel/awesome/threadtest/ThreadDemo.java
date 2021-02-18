package com.zel.awesome.threadtest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * thread 使用
 */
public class ThreadDemo implements Runnable{

    public ExecutorService threads; //线程池链接
    public Object lock  = new Object(); //锁;//锁

    ThreadDemo() {
        this.threads = Executors.newFixedThreadPool(5);
    }

    @Override
    public void run() {

        synchronized(this.lock) {
            System.out.println("子线程获取锁");

            int i = 0;
            while (true) {
                i += 1;

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("i:" + i);
                if (i == 5) {
                    this.lock.notify();
                    this.threads.shutdown();
                    System.out.println("子线程 notify");
                    //return;
                }
            }

        }
    }


    public static void main(String[] args) {

        ThreadDemo t = new ThreadDemo();


        // 获取 lock 的对象锁
        synchronized(t.lock) {
            System.out.println("main获取锁");
            // 子线程执行
            t.threads.execute(t);
            try {
                //10 * 1000 最多等待10s
                //lock的对象锁释放，主线程进入等待状态
                System.out.println("main进入阻塞状态！");
                t.lock.wait();
                System.out.println("main被唤醒！");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
