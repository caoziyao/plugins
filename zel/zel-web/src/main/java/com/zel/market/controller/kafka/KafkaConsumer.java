package com.zel.market.controller.kafka;

import com.zel.market.config.kafka.KafkaConsumerGroup;
import com.zel.market.config.kafka.KafkaTopic;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
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

    /**
     * 使用 topicPartitions 指定 topic、parition、offset
     * 注意：topics 和 topicPartitions 不能同时使用。
     *      一个消费组中的消费者只能和一个分区一一对应
     * @param record
     */
    //@KafkaListener(groupId = KafkaConsumerGroup.group1,
    //        topicPartitions = @TopicPartition(topic = KafkaTopic.topic1, partitions = "0"))
    //public void onMessage2(ConsumerRecord<?, ?> record) {
    //    // 消费的哪个topic、partition的消息,打印出消息内容
    //    log.info("简单消费0：" + record.topic() + "-" + record.partition() + "-" + record.value());
    //}

    // 消费监听
    //@KafkaListener(topics = {KafkaTopic.topic1}, groupId = KafkaConsumerGroup.group1)
    //@KafkaListener(groupId = KafkaConsumerGroup.group1,
    //        topicPartitions = @TopicPartition(topic = KafkaTopic.topic1, partitions = "1"))
    //public void onMessage1(ConsumerRecord<?, ?> record) {
    //    // 消费的哪个topic、partition的消息,打印出消息内容
    //    log.info("简单消费1：" + record.topic() + "-" + record.partition() + "-" + record.value());
    //}
}
