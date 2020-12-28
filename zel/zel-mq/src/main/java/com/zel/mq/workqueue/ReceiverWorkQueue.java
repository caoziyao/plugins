package com.zel.mq.workqueue;

import com.rabbitmq.client.Channel;
import com.zel.mq.dto.IRabbitQueue;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;


/**
 * Description: AcknowledgeMode.MANUAL
 *  AcknowledgeMode.NONE：自动确认
 *  AcknowledgeMode.AUTO：根据情况确认
 *  AcknowledgeMode.MANUAL：手动确认
 *
 * @author csy
 * @version 1.0.0
 * @since 2020/10/27
 */
@Component
//@RabbitListener(queues = RabbitQueue.work, ackMode = "MANUAL", concurrency="1"  )
public class ReceiverWorkQueue {

    // work1 处理比较慢
//    @RabbitHandler
    @RabbitListener(queuesToDeclare = @Queue(value = IRabbitQueue.work), ackMode = "MANUAL")
    public void process(String hello, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) {
        System.out.println("Receiver1 : " + hello);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            channel.basicAck(tag,false);            // 确认消息
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    @RabbitHandler
    @RabbitListener(queuesToDeclare = @Queue(value = IRabbitQueue.work))
    public void process2(String hello) {
        System.out.println("Receiver2 : " + hello);
    }
}
