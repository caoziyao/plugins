package com.zel.awesome.threadtest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadDemo implements Runnable{

    public ExecutorService threads; //线程池链接
    private Object lock  = new Object(); //锁;//锁

    ThreadDemo() {
        this.threads = Executors.newFixedThreadPool(5);
    }

    @Override
    public void run() {

        synchronized(this.getLock()) {
            System.out.println("子线程获取锁");

            for (int i = 0; i < 20; i++) {

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("i:" + i);
                if (i == 15) {
                    this.lock.notifyAll();
                    this.threads.shutdown();
                    //return;
                }
            }

        }
    }

    public void setLock(Object lock) {
        this.lock = lock;
    }

    public Object getLock() {
        return lock;
    }

    public static void main(String[] args) {

        ThreadDemo t = new ThreadDemo();
        // 子线程执行
        t.threads.execute(t);

        // 获取 lock 的对象锁
        synchronized(t.getLock()) {
            System.out.println("main获取锁");
            try {
                //10 * 1000 最多等待10s
                //lock的对象锁释放，主线程进入等待状态
                System.out.println("main进入阻塞状态！");
                t.getLock().wait();
                System.out.println("main被唤醒！");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
