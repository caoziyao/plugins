package com.example.emailservice.mq.fanout;

import com.example.emailservice.config.RabbitQueue;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Description:  Publish/Subscribe
 * 广播模式
 * @author csy
 * @version 1.0.0
 * @since 2020/10/27
 */
@Component
public class ProviderFanout {
    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendMessage() {
        String context = "fanout " + new Date();
        System.out.println("Sender : " + context);
        // RabbitMQ将会忽略第二个参数, 把消息分发给所有的队列(每个队列都有消息!)
        this.amqpTemplate.convertAndSend(RabbitQueue.logsExchange, "",context);
    }
}
