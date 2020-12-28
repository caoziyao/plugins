package com.zel.mq.routing;

import com.zel.mq.dto.IRabbitQueue;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2020/10/27
 */
@Component
//@RabbitListener(queues = RabbitQueue.routeDirectQueue1)
public class ReceiverRouting {

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue, // 临时队列
                    exchange = @Exchange(value = IRabbitQueue.routeDirectExchange, type = "direct"),
                    key = {"info"}
            )
    })
    public void process(String hello) {
        System.out.println("Receiver routeDirectQueue1: " + hello);
    }


    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue, // 临时队列
                    exchange = @Exchange(value = IRabbitQueue.routeDirectExchange, type = "direct"),
                    key = {"info", "error"}
            )
    })
    public void process2(String hello) {
        System.out.println("Receiver routeDirectQueue2: " + hello);
    }
}
