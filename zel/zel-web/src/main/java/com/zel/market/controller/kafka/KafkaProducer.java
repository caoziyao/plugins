package com.zel.market.controller.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2021/1/1
 */

@RestController
@RequestMapping(value = "/kafka")
public class KafkaProducer {
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;


    /**
     * 不手动创建topic时，执行代码kafkaTemplate.send("topic1", normalMessage)发送消息时，kafka会帮我们自动完成topic的创建工作，
     * 但这种情况下创建的topic默认只有一个分区，分区也没有副本
     *
     * @param normalMessage
     */
    // 发送消息
    @GetMapping("/normal/{message}")
    public void sendMessage1(@PathVariable("message") String normalMessage) {
        kafkaTemplate.send("topic1", normalMessage);
    }

}
