package com.zel.market.jobs.runner;

import com.zel.market.dto.MailTaskDTO;
import com.zel.market.service.mail.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @deprecated  该类已经过期，建议不使用
 * mail 任务
 */
@Deprecated
@Component
public class MailTaskRunner implements Runnable {
    public static BlockingQueue<MailTaskDTO> queue = new LinkedBlockingQueue<>();

    @Autowired
    private MailService mailService;

    @Override
    public void run() {
        System.out.println("start MailTask..." + mailService + " " +  Thread.currentThread().getName());
        // this.mailService = (MailService) SpringBeanUtil.getBean(MailService.class);

        while (true) {
            try {
                // 阻塞
                MailTaskDTO task = queue.poll(5, TimeUnit.SECONDS);
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
     * @param task
     */
    public void add(MailTaskDTO task) {
        queue.add(task);
    }
}
