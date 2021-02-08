package com.zel.awesome.sync;


/**
 * synchronized的三种应用方式
 *
 * 3, 作用于静态方法，锁的是class本身
 */
public class SynchronizedTest3 implements Runnable {

    //共享资源
    static int count =0;
    /**
     * synchronized 修饰静态方法，锁的是class本身
     */
    public synchronized static void increase(){
        for (int i = 0; i < 10000; i++) {
            count++;
        }
    }

    @Override
    public void run(){
        increase();
    }

    public static void test() throws InterruptedException {
        SynchronizedTest3 test1 = new SynchronizedTest3();
        SynchronizedTest3 test2 = new SynchronizedTest3();
        Thread t1 = new Thread(test1, "thread1");
        Thread t2 = new Thread(test2, "thread2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(count);
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            test();
        }
    }
}
