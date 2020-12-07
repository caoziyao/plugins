package com.example.api.mq;

import com.example.api.mq.dto.MqMailDTO;
import gua.commons.JsonTool;
import lombok.NonNull;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

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
@EnableBinding(Source.class)  // 定义消息推送的管道
public class ISendProviderImpl implements ISendProvider {

    @Resource
    private MessageChannel output;  // 发送消息管道

//    private String send(String value) {
//
//    }

    /**
     * 发送到 mq
     *
     * @param data
     * @return
     */
    @Override
    public String send(@NonNull MqMailDTO data) {

//        String serial = "发送：" + UUID.randomUUID().toString();

        data.setCreateTime(new Date());
        String str = JsonTool.dumps(data);

        System.out.println(str);
        output.send(MessageBuilder.withPayload(str).build());
        return str;
    }
}
