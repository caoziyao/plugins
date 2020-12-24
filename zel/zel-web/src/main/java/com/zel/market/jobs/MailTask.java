package com.zel.market.jobs;

import com.zel.market.service.mail.MailService;
import com.zel.market.utils.SpringBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 通知任务
 * <p>
 * spring bean 出于线程安全考虑，不得注入bean至线程类（Runnable）
 */
@Component
public class MailTask implements Runnable {
    public  static BlockingQueue<String> queue = new LinkedBlockingQueue<>();

    @Autowired
    private MailService mailService;

    private static int taskId = 0;// 任务id

    @Override
    public void run() {
        System.out.println("start MailRunner..." + mailService);
        // this.mailService = (MailService) SpringBeanUtil.getBean(MailService.class);

        while (true) {
            try {
                while (!queue.isEmpty()) {
                    String po = queue.poll();
                    mailService.mock(po);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * add
     *
     * @param e
     */
    public void add(String e) {
        queue.add(e);
    }
}
