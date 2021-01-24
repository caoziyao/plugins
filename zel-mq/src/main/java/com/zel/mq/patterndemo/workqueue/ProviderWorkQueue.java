package com.zel.mq.patterndemo.workqueue;


import com.zel.mq.patterndemo.IRabbitQueue;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2020/10/27
 */
@Component
public class ProviderWorkQueue {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendMessage() {

        for (int i = 0; i < 20; i++) {
            String context = "work " + i + " " + new Date();
            System.out.println("Sender : " + context);
            this.amqpTemplate.convertAndSend(IRabbitQueue.work, context);
        }

    }
}
