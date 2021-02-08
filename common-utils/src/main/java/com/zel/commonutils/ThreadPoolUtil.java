package com.zel.commonutils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolUtil {

    public static ExecutorService taskThreadPool = new ThreadPoolExecutor(3, 8,
            30, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());
}
