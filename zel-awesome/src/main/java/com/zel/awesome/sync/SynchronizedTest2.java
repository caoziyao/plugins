package com.zel.awesome.sync;

/**
 * synchronized的三种应用方式
 * <p>
 * 2, 同步方法块，锁是括号里面的对象/类，对给定对象加锁，进入同步代码库前要获得给定对象的锁。
 */
public class SynchronizedTest2 implements Runnable {
    private volatile static int count = 0;

    @Override
    public void run() {
        synchronized (SynchronizedTest2.class) {
            for (int j =0 ; j<10000;j++){
                count += 1;
            }
        }
    }

    public static void test()  throws InterruptedException {
        SynchronizedTest2 test = new SynchronizedTest2();
        SynchronizedTest2 test2 = new SynchronizedTest2();
        Thread t1 = new Thread(test, "thread1");
        Thread t2 = new Thread(test2, "thread2");
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
