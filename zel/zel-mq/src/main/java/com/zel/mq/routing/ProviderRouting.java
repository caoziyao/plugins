package com.zel.mq.routing;


import com.zel.mq.dto.IRabbitQueue;
import com.zel.mq.dto.IRabbitRouteKey;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Description: 路由
 *
 * @author csy
 * @version 1.0.0
 * @since 2020/10/27
 */
@Component
public class ProviderRouting {
    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendMessage() {
        String context = "route " + IRabbitRouteKey.keyHaha  + new Date();
        System.out.println("Sender : " + context);
        //RabbitMQ将会忽略第二个参数,把消息分发给所有的队列(每个队列都有消息!)
        this.amqpTemplate.convertAndSend(IRabbitQueue.routeDirectExchange, "info", context);
        this.amqpTemplate.convertAndSend(IRabbitQueue.routeDirectExchange, "error", context);
    }
}
