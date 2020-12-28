package com.zel.mq.confirmtest;

import com.zel.mq.dto.IRabbitQueue;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.Date;

/**
 * Description: channel的confirm模式
 * https://blog.csdn.net/xwnxwn/article/details/80462576
 *
 * @author csy
 * @version 1.0.0
 * @since 2020/10/28
 */
public class ProviderConfirmTest implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {

    @Autowired
    private RabbitTemplate amqpTemplate;
//    private AmqpTemplate amqpTemplate;

    @PostConstruct
    public void postConstruct() {
        System.out.println("postConstruct");
        // 打开注释
//        amqpTemplate.setConfirmCallback(this);
//        amqpTemplate.setReturnCallback(this);
    }

    /**
     * 使用 convertAndSend 方法时的结果：输出时没有顺序，不需要等待，直接运行
     * 使用 convertSendAndReceive 方法时的结果：使用此方法，只有确定消费者接收到消息，才会发送下一条信息，每条消息之间会有间隔时间
     */
    public void sendMessage() {
        String context = "hello " + new Date();
        System.out.println("Sender : " + context);
        this.amqpTemplate.convertAndSend(IRabbitQueue.hello, context);
    }

    /**
     * 服务端发送到 broker 可达时的回调
     * https://blog.csdn.net/qq_38181949/article/details/108153856
     * <p>
     * spring.rabbitmq.publisher-confirm-type=correlated
     *
     * @param correlationData
     * @param ack
     * @param s
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String s) {
        assert correlationData != null;  // + correlationData.getId()
        System.out.println("消息发送成功,发送ack确认,id=");
        if (ack) {
            System.out.println("发送成功");
        } else {
            System.out.println("发送失败");
        }
    }

    /**
     * rabbitmq 交换机  发送到  queue 不可达的回调
     * <p>
     * spring.rabbitmq.publisher-returns=true
     *
     * @param message
     * @param i
     * @param s
     * @param s1
     * @param s2
     */
    @Override
    public void returnedMessage(Message message, int i, String s, String s1, String s2) {
        System.out.println("消息丢失, 没有投递成功" + message);
    }
}
