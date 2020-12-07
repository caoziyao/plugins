package com.example.emailservice.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2020/10/27
 */
@Configuration
public class RabbitConfig {

    /**
     * topic
     * @return
     */
    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange(RabbitQueue.topicExchange);
    }

    @Bean
    public Queue topicQueue1() {
        return new Queue(RabbitQueue.topicQueue1);
    }

    @Bean
    public Queue topicQueue2() {
        return new Queue(RabbitQueue.topicQueue2);
    }

    @Bean
    Binding bindTopicExchangeA(Queue topicQueue1, TopicExchange directExchange) {
        return BindingBuilder.bind(topicQueue1).to(directExchange).with(RabbitRouteKey.keyTopicHaha);
    }

    @Bean
    Binding bindTopicExchangeB(Queue topicQueue2, TopicExchange directExchange) {
        return BindingBuilder.bind(topicQueue2).to(directExchange).with(RabbitRouteKey.keyTopicAbc);
    }
}
