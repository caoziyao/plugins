package com.zel.awesome.producer_consumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 经典的生产者-消费者模型， 使用  Condition 实现
 * <p>
 * 线程1 consumer 判断 queue，如果为空，obj.wait() 阻塞线程，并且释放锁
 * <p>
 * 线程2 producer 生成数据，放入 queue，然后 obj.notify() 唤醒线程1，并且释放锁
 * <p>
 * 线程1 被唤醒，从 queue 拿数据消费
 */
public class ConditionTest {

    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    private int count = 0;

    private BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();

    public void producer() {

        lock.lock();

        // sleep
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //省略生产逻辑
        count++;
        queue.add(count);
        System.out.println("producer:" + count);

        // 唤醒
        condition.signal();

        lock.unlock();

    }

    public void consumer() throws InterruptedException {

        while (true) {

            lock.lock();

            // 如果队列为空，等待线程并且释放锁
            while (queue.isEmpty()) {
                System.out.println("队列空，等待数据");
                // 等待线程并且释放锁
                condition.await();
            }
            //System.out.println("开始消费");

            //消费逻辑
            Integer count = queue.poll();
            System.out.println("--consumer:" + count);

            //condition.signal();
            lock.unlock();
        }


    }

    public static void main(String[] args) throws InterruptedException {

        ConditionTest t = new ConditionTest();

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
