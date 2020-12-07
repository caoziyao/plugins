package com.example.emailservice.mq.helloworld;

import com.example.emailservice.config.RabbitQueue;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Description: queuesToDeclare 没有就自动创建
 *  durable 持久化
 *  declare 是否独占
 *
 * @author csy
 * @version 1.0.0
 * @since 2020/10/27
 */
@Component
@RabbitListener(
        queuesToDeclare = @Queue(value = RabbitQueue.hello, durable = "true")
)
public class ReceiverHelloWorld {

    @RabbitHandler
    public void process(String hello) {
        System.out.println("Receiver : " + hello);
    }

}
