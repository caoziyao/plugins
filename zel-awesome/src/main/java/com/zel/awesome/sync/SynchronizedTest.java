package com.zel.awesome.sync;


/**
 * synchronized的三种应用方式
 */
public class SynchronizedTest implements Runnable{

    //共享资源
    static int count =0;
    /**
     * 没有加锁
     */
    public  void increase(){
        for (int i = 0; i < 10000; i++) {
            count++;
            //System.out.println(Thread.currentThread().getName() + ":" + count++);
            //try {
            //    Thread.sleep(500);
            //} catch (InterruptedException e) {
            //    e.printStackTrace();
            //}
        }
    }

    @Override
    public void run(){
        increase();
    }

    public static void test() throws InterruptedException {
        SynchronizedTest test = new SynchronizedTest();
        Thread t1 = new Thread(test, "thread1");
        Thread t2 = new Thread(test, "thread2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        // 打印结果不一定是 20000
        System.out.println(count);
    }
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            test();
        }
    }
}
