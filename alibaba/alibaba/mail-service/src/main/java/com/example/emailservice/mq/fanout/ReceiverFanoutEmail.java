package com.example.emailservice.mq.fanout;

import com.example.emailservice.config.RabbitQueue;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Description: 广播模式
 *
 * @author csy
 * @version 1.0.0
 * @since 2020/10/27
 */
@Component
//@RabbitListener(queues = RabbitQueue.fanoutEmail)
public class ReceiverFanoutEmail {

    //    @RabbitHandler
    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue, // 临时队列
                    exchange = @Exchange(
                            value = RabbitQueue.logsExchange,
                            type = ExchangeTypes.FANOUT
                    )
            )
    })
    public void process(String hello, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) {
        System.out.println("Receiver fanoutEmail : " + hello);
    }

    //    @RabbitHandler
    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue, // 临时队列
                    exchange = @Exchange(value = RabbitQueue.logsExchange, type = "fanout")
            )
    })
    public void process2(String hello, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) {
        System.out.println("Receiver fanoutSMS : " + hello);
    }
}
