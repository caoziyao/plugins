package com.zel.market.jobs.mail;

import com.zel.market.service.mail.MailService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 通知任务
 */
@Component
public class MailTaskRunner implements Runnable {
    public static BlockingQueue<MailTask> queue = new LinkedBlockingQueue<>();

    @Autowired
    private MailService mailService;

    @Override
    public void run() {
        System.out.println("start MailTask..." + mailService);
        // this.mailService = (MailService) SpringBeanUtil.getBean(MailService.class);

        while (true) {
            try {
                MailTask task = queue.poll(5, TimeUnit.SECONDS);
                //  System.out.println("queue poll: " + po  + ": "+ new Date() + ":" + Thread.currentThread().getName());
                if (task != null) {
                    String to = task.getTo();
                    String content = task.getContent();
                    String subject = task.getSubject();
                    mailService.sendSimpleMail(to, subject, content);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }

    /**
     * add
     *
     * @param task
     */
    public void add(MailTask task) {
        queue.add(task);
    }
}
