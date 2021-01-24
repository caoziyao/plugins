package com.zel.mq.patterndemo.topic;

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
public class ProviderTopic {
    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendMessage() {
        String key =  "topic.a";
        String context = "topic " +key  + new Date();
        System.out.println("Sender : " + context);
        //RabbitMQ将会忽略第二个参数,把消息分发给所有的队列(每个队列都有消息!)
        this.amqpTemplate.convertAndSend(IRabbitQueue.topicExchange, key, context);
    }
}
