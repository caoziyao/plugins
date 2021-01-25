package com.zel.market.tasks.demo;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Test {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        List<Future<Integer>> resultList = new LinkedList<>();
        // Task task = new Task();
        for (int i = 0; i < 5; i++) {
            TaskDemo task = new TaskDemo();
            Future<Integer> futureTask  = executor.submit(task);
            resultList.add(futureTask);
        }

        /**
         * ExecutorService.shutdown()
         * 在终止时，执行程序没有任务在执行，也没有任务在等待执行，并且无法提交新任务。
         * 关闭未使用的 ExecutorService 以允许回收其资源。
         */
        executor.shutdown();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main over");

        int sum = 0;
        for (Future<Integer> future: resultList) {
            try {
                // future.get()是阻塞的
                Integer integer = future.get();
                sum += integer;
            }  catch (Exception e) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                e.printStackTrace(new PrintStream(baos));
                String exception = baos.toString();
                System.out.println(exception);
            }
        }
        System.out.println("task运行结果:" + sum);
//        try {
//            Integer integer = futureTask.get();
//            System.out.println("task运行结果:" + integer);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }

        System.out.println("over");
    }
}
