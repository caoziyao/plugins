package com.zel.market.controller.kafka;

import com.zel.market.config.kafka.KafkaTopic;
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
public class KafkaProducerController {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    /**
     * 不手动创建topic时，执行代码kafkaTemplate.send("topic1", normalMessage)发送消息时，kafka会帮我们自动完成topic的创建工作，
     * 但这种情况下创建的topic默认只有一个分区，分区也没有副本
     *
     * 没patition 和 key 都未指定，则使用kafka默认的分区策略，轮询选出一个 patition
     * @param normalMessage
     */
    // 发送消息
    @GetMapping("/normal/{message}")
    public void sendMessage1(@PathVariable("message") String normalMessage) {
        kafkaTemplate.send(KafkaTopic.topic1, normalMessage);
    }

    /**
     * 如果一个有效的partition属性数值被指定，那么在发送记录时partition属性数值就会被应用。
     * 如果没有partition属性数值被指定，而一个key属性被声明的话，一个partition会通过key的hash而被选中。
     * 如果既没有key也没有partition属性数值被声明，那么一个partition将会被分配以轮询的方式。
     *
     * @param normalMessage
     */
    // 发送消息
    @GetMapping("/normal2/{message}")
    public void sendMessage2(@PathVariable("message") String normalMessage) {
        kafkaTemplate.send(KafkaTopic.topic1, 0, "key1", "message1" );
        kafkaTemplate.send(KafkaTopic.topic1, 1, "key2", "message2");
        kafkaTemplate.send(KafkaTopic.topic1, 1, "key2", "message3");
        kafkaTemplate.send(KafkaTopic.topic1, 1, "key2", "message4");
        //kafkaTemplate.send(KafkaTopic.topic2, 0, "key3", "message3");
        //kafkaTemplate.send(KafkaTopic.topic2, 1, "key4", "message4");
    }
}
