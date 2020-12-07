package com.example.emailservice.mq.topic;

import com.example.emailservice.config.RabbitQueue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2020/10/27
 */
@Component
@RabbitListener(queues = RabbitQueue.topicQueue1)
public class ReceiverTopic {

    @RabbitHandler
    public void process(String hello) {
        System.out.println("Receiver Topic1: " + hello);
    }
}
