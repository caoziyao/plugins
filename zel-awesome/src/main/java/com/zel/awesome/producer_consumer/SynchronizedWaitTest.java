package com.zel.awesome.producer_consumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 经典的生产者-消费者模型
 *
 * 线程1 consumer 判断 queue，如果为空，obj.wait() 阻塞线程，并且释放锁
 *
 * 线程2 producer 生成数据，放入 queue，然后 obj.notify() 唤醒线程1，并且释放锁
 *
 * 线程1 被唤醒，从 queue 拿数据消费
 */
public class SynchronizedWaitTest {

    private int count = 0;
    private Object obj = new Object();
    private BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();

    public void producer()  {
        synchronized (obj){

            // sleep
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            //省略生产逻辑
            count++;
            queue.add(count);
            System.out.println("producer:" + count);

            // 唤醒
            obj.notify();
        }

    }

    public void consumer() throws InterruptedException {
        synchronized (obj){
           while (true) {

               // 如果队列为空，等待线程并且释放锁
               while (queue.isEmpty()){
                   System.out.println("队列空，等待数据");
                   // 等待线程并且释放锁
                   obj.wait();
               }

               //消费逻辑
               Integer count = queue.poll();
               System.out.println("--consumer:" + count);
           }
        }

    }

    public static void main(String[] args) throws InterruptedException {

        SynchronizedWaitTest t = new SynchronizedWaitTest();

        // 消费者
        System.out.println("启动消费者");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    t.consumer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


        // 生产者
        System.out.println("启动生产者");
        new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    t.producer();
                }
            }
        }).start();
    }
}
