package com.zel.market.startup;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * load-on-startup
 *
 * @description 启动时初始化数据
 * @Date 2020/3/20 17:49
 **/
@Component
public class DataDictInit implements CommandLineRunner {
    private final static Logger log = LogManager.getLogger(DataDictInit.class);

    @Override
    public void run(String... args) throws Exception {
        log.info("CommandLineRunner开始");
        try {
            //启动发送通知线程
            new Thread(new MailRunner(), "TaskNoticeService").start();

        } catch (Exception e) {
            log.error("CommandLineRunner信息:{}", e.getMessage());
            e.printStackTrace();
        }
        log.info("CommandLineRunner结束");
    }
}