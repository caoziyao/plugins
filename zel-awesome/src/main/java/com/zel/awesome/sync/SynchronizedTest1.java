package com.zel.awesome.sync;

/**
 * synchronized的三种应用方式
 *
 * 1, 普通同步方法（实例方法），锁的是即方法的调用者
 */
public class SynchronizedTest1 implements Runnable{

    //共享资源
    static int count =0;
    /**
     * synchronized 修饰实例方法，锁的是即方法的调用者，即调用 increase方法的对象
     */
    public synchronized void increase(){
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

    public static void test()  throws InterruptedException {
        SynchronizedTest1 test = new SynchronizedTest1();
        Thread t1 = new Thread(test, "thread1");
        Thread t2 = new Thread(test, "thread2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        // 打印 20000
        System.out.println(count);
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            test();
        }
    }
}
