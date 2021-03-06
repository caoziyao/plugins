package com.zel.market.jobs;

import com.zel.market.jobs.monitor.ThreadPoolMonitorRunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

/**
 * load-on-startup
 *
 * @description 实现 CommandLineRunner，会在启动时调用
 * @Date 2020/3/20 17:49
 **/
@Component
public class DataDictInit implements CommandLineRunner {
    private final static Logger log = LogManager.getLogger(DataDictInit.class);

    public static final ExecutorService TASK_SERVICE = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public static final ThreadPoolExecutor  executor = new ThreadPoolExecutor(5, 5, 30, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10) );

    @Autowired
    private ThreadPoolMonitorRunner monitorService;

    @Override
    public void run(String... args) throws Exception {
        //log.info("CommandLineRunner开始" + mailTask);

        executor.allowCoreThreadTimeOut(true);
        monitorService.setExecutor(executor);

        // 监控线程
        Thread monitor = new Thread(monitorService, "ThreadPoolMonitorService");
        monitor.start();

        //  启动发送通知线程
        //for (int i = 1; i <= 10; i++) {
        //    executor.execute(mailTask);
        //}

        //启动发送通知线程
        //  new Thread(mailTask, "TaskNoticeService").start();
        log.info("CommandLineRunner结束");
    }
}