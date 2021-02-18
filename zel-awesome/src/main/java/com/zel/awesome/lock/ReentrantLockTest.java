package com.zel.awesome.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {
    static Lock lock = new ReentrantLock(true);

    //共享资源
    static int count =0;

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 5; i++) {
            new Thread(new ThreadDemo(i)).start();
        }

    }

    static class ThreadDemo implements Runnable {
        Integer id;

        public ThreadDemo(Integer id) {
            this.id = id;
        }

        @Override

        public void run() {
            for (int i = 0; i < 2; i++) {

                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 获取锁
                lock.lock();
                count += 1;
                System.out.println("获得锁的线程：" + id + " count:" + count);
                lock.unlock();
            }
        }
    }
}
