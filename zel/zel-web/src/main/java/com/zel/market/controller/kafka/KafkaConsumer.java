package com.zel.market.controller.kafka;

import com.zel.market.config.kafka.KafkaConsumerGroup;
import com.zel.market.config.kafka.KafkaTopic;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Description:  不同消费者组 实现广播效果
 *
 * @author csy
 * @version 1.0.0
 * @since 2021/1/1
 */
@Slf4j
@Component
public class KafkaConsumer {


    // 消费监听
    @KafkaListener(topics = {KafkaTopic.topic1}, groupId = KafkaConsumerGroup.group1)
    public void onMessage1(ConsumerRecord<?, ?> record) {
        // 消费的哪个topic、partition的消息,打印出消息内容
        log.info("简单消费：" + record.topic() + "-" + record.partition() + "-" + record.value());
    }

    @KafkaListener(topics = {KafkaTopic.topic1}, groupId = KafkaConsumerGroup.group1)
    public void onMessage2(ConsumerRecord<?, ?> record) {
        // 消费的哪个topic、partition的消息,打印出消息内容
        log.info("简单消费2：" + record.topic() + "-" + record.partition() + "-" + record.value());
    }

}
