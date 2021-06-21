package com.zel.awesome.queue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 要实现DelayQueue延时队列，队中元素要implements Delayed 接口，
 * 这哥接口里只有一个getDelay方法，用于设置延期时间。
 * Order类中compareTo方法负责对队列中的元素进行排序。
 */
public class OrderDemo implements Delayed {

    private long time;

    public String name;

    public OrderDemo(String name, long time, TimeUnit unit) {
        this.name = name;
        this.time = System.currentTimeMillis() + (time > 0 ? unit.toMillis(time) : 0);
    }

    /**
     * 设置延期时间
     * @param unit
     * @return
     */
    @Override
    public long getDelay(TimeUnit unit) {
        return time - System.currentTimeMillis();
    }

    /**
     * 排序
     * @param o
     * @return
     */
    @Override
    public int compareTo(Delayed o) {
        OrderDemo Order = (OrderDemo) o;
        long diff = this.time - Order.time;
        if (diff <= 0) {
            return -1;
        } else {
            return 1;
        }
    }
}
