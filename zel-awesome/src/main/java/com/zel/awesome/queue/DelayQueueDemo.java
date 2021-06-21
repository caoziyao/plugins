package com.zel.awesome.queue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

public class DelayQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        OrderDemo Order1 = new OrderDemo("Order1", 5, TimeUnit.SECONDS);
        OrderDemo Order2 = new OrderDemo("Order2", 10, TimeUnit.SECONDS);
        OrderDemo Order3 = new OrderDemo("Order3", 15, TimeUnit.SECONDS);
        OrderDemo Order4 = new OrderDemo("Order4", 5, TimeUnit.SECONDS);

        DelayQueue<OrderDemo> delayQueue = new DelayQueue<>();
        delayQueue.put(Order2);
        delayQueue.put(Order1);
        delayQueue.put(Order3);
        delayQueue.put(Order4);

        System.out.println("订单延迟队列开始时间:" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        while (true) {

            //if (delayQueue.size() == 0) {
            //    System.out.format("队列为空..等待\n");
            //    Thread.sleep(1000);
            //}
            /**
             * 取队列头部元素是否过期
             * poll() 为非阻塞获取，没有到期的元素直接返回null；
             * take() 阻塞方式获取，没有到期的元素线程将会等待。
             */
            OrderDemo task = delayQueue.take();
            System.out.format("订单:{%s}被取消, 取消时间:{%s}\n", task.name, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }
    }
}
