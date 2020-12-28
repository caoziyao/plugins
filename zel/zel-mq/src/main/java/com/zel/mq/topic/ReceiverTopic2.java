package com.zel.mq.topic;

import com.zel.mq.dto.IRabbitQueue;
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
@RabbitListener(queues = IRabbitQueue.topicQueue2)
public class ReceiverTopic2 {

    @RabbitHandler
    public void process(String hello) {
        System.out.println("Receiver Topic2: " + hello);
    }
}
