package com.zel.market.config.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2021/1/1
 */

@Configuration
public class KafkaInitialConfiguration {

    // 创建一个名为testtopic的Topic并设置分区数为8，分区副本数为2
    @Bean
    public NewTopic initialTopic() {
        return new NewTopic(KafkaTopic.mail, 2, (short) 1);
    }

    // 如果要修改分区数，只需修改配置值重启项目即可
    // 修改分区数并不会导致数据的丢失，但是分区数只能增大不能减小
    @Bean
    public NewTopic updateTopic() {
        return new NewTopic(KafkaTopic.mail, 3, (short) 1);
    }

    @Bean
    public NewTopic initialTopic1() {
        return new NewTopic(KafkaTopic.topic1, 2, (short) 1);
    }

    @Bean
    public NewTopic initialTopicHTML() {
        return new NewTopic(KafkaTopic.spider, 2, (short) 1);
    }
}
