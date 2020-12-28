package com.zel.mq.helloworld;


import com.zel.mq.dto.IRabbitQueue;
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
        queuesToDeclare = @Queue(value = IRabbitQueue.hello, durable = "true")
)
public class ReceiverHelloWorld {

    @RabbitHandler
    public void process(String hello) {
        System.out.println("Receiver : " + hello);
    }

}
