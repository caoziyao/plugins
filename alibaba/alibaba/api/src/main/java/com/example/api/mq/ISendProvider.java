package com.example.api.mq;

import com.example.api.mq.dto.MqMailDTO;
import lombok.NonNull;
import org.springframework.cloud.stream.annotation.Output;

/**
 * Description: 发送消息的接口
 *
 * @author csy
 * @version 1.0.0
 * @since 2020/10/24
 */
public interface ISendProvider {
    String OUTPUT = "mailExchange";

    /**
     * 指定输出的交换器名称
     *
     * @return
     */
    @Output(OUTPUT)
    String send(@NonNull MqMailDTO value);
}
