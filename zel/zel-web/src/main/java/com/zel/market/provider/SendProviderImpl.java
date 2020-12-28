package com.zel.market.provider;


import com.zel.commonutils.JacksonHelper;
import com.zel.market.provider.dto.MqMailDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2020/10/24
 */
@Component // 定义消息推送的管道
public class SendProviderImpl {

    @Autowired
    private RabbitTemplate rabbitTemplate;

//    private String send(String value) {
//
//    }

    /**
     * 发送到 mq
     *
     * @param data
     * @return
     */
    public void send(MqMailDTO data) {
        rabbitTemplate.convertAndSend("hello mmmqq");
    }
}
