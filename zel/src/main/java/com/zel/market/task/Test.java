package com.zel.market.task;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

public class Test {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        List<Future<Integer>> resultList = new LinkedList<>();
//        Task task = new Task();
        for (int i = 0; i < 5; i++) {
            Task task = new Task();
            Future<Integer> futureTask  = executor.submit(task);
            resultList.add(futureTask);
        }

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
