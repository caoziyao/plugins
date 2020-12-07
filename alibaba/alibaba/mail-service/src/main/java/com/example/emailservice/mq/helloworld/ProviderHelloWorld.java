package com.example.emailservice.mq.helloworld;

import com.example.emailservice.config.RabbitQueue;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;

/**
 * Description: helloworld 模式
 * send （自定义消息 Message）
 * Message message = new Message("hello".getBytes(),new MessageProperties());
 * // 发送消息到默认的交换器，默认的路由键
 * rabbitTemplate.send(message);
 * // 发送消息到指定的交换器，指定的路由键
 * rabbitTemplate.send("direct.exchange","key.1",message);
 * // 发送消息到指定的交换器，指定的路由键
 * rabbitTemplate.send("direct.exchange","key.1",message,new CorrelationData(UUID.randomUUID().toString()));
 * <p>
 * <p>
 * convertAndSend（自动 Java 对象包装成 Message 对象，Java 对象需要实现 Serializable 序列化接口）
 * <p>
 * User user = new User("linyuan");
 * // 发送消息到默认的交换器，默认的路由键
 * rabbitTemplate.convertAndSend(user);
 * // 发送消息到指定的交换器，指定的路由键，设置消息 ID
 * rabbitTemplate.convertAndSend("direct.exchange","key.1",user,new CorrelationData(UUID.randomUUID().toString()));
 * // 发送消息到指定的交换器，指定的路由键，在消息转换完成后，通过 MessagePostProcessor 来添加属性
 * rabbitTemplate.convertAndSend("direct.exchange","key.1",user,mes -> {
 * mes.getMessageProperties().setDeliveryMode(MessageDeliveryMode.NON_PERSISTENT);
 * return mes;
 * });
 * <p>
 * durable: 是否持久化
 * exclusive: 是否独占队列
 * autoDelete: 是否在消费完成后自动删除队列， true 为自动删除
 * <p>
 * receive（返回 Message 对象）
 *
 * @author csy
 * @version 1.0.0
 * @since 2020/10/27
 */
@Component
public class ProviderHelloWorld {

    @Autowired
    private RabbitTemplate amqpTemplate;
//    private AmqpTemplate amqpTemplate;

    /**
     * 使用 convertAndSend 方法时的结果：输出时没有顺序，不需要等待，直接运行
     * 使用 convertSendAndReceive 方法时的结果：使用此方法，只有确定消费者接收到消息，才会发送下一条信息，每条消息之间会有间隔时间
     */
    public void sendMessage() {
        String context = "hello " + new Date();
        System.out.println("Sender : " + context);
        this.amqpTemplate.convertAndSend(RabbitQueue.hello, context);
    }

}
