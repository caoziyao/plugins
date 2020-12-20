package com.zel.market.task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskForWeb {
    private final Object lock = new Object(); //锁
    private Map<String, Object> params;//用于保存公用的参数信息
    private ExecutorService threads; //线程池链接
    private List<Task> tasks; //任务

    public TaskForWeb(int size) {
        this.tasks = new ArrayList<Task>(size);
        this.params = new HashMap<String, Object>();
        if (size > 1) {
            this.threads = Executors.newFixedThreadPool(getThreadNum(size));
        } else {
            this.threads = null;
        }
    }

    public void addTask() {
        Task task = new Task();
        this.getTasks().add(task);
    }

    public ExecutorService getThreads() {
        return threads;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public Object getLock() {
        return this.lock;
    }

    /**
     * 根据任务长度，计算需要创建的线程数量  一般为 2， 4， 8， 16
     *
     * @param size
     * @return
     */
    private int getThreadNum(int size) {
        int num = 2;
        if (size < 4) {
            num = 2;
        } else if (size < 16) {
            num = 4;
        } else if (size < 32) {
            num = 8;
        } else if (size < 128) {
            num = 16;
        } else if (size < 256) {
            num = 32;
        } else {
            num = 64;
        }
        return num;
    }
}
