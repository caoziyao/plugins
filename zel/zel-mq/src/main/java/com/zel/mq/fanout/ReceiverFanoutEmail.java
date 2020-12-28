package com.zel.mq.fanout;

import com.rabbitmq.client.Channel;
import com.zel.mq.dto.IRabbitQueue;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

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
                            value = IRabbitQueue.logsExchange,
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
                    exchange = @Exchange(value = IRabbitQueue.logsExchange, type = "fanout")
            )
    })
    public void process2(String hello, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) {
        System.out.println("Receiver fanoutSMS : " + hello);
    }
}
