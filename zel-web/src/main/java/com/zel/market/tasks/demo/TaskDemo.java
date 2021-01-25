package com.zel.market.tasks.demo;

import java.util.concurrent.Callable;

public class TaskDemo implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("子线程在进行计算");
        Thread.sleep(3000);
        int sum = 0;
        for(int i=0; i< 100 ; i++) {
            sum += i;
            //throw new NullPointerException("my eee");
        }
        System.out.println("task:" + sum);
        return sum;
    }
}
