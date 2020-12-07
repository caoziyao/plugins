package com.example.emailservice.config;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2020/10/27
 */
public interface RabbitQueue {

    String hello = "hello";
    String work = "work";
    String logsExchange = "logsExchange";
    String routeDirectExchange = "routeDirectExchange";

    String topicExchange = "topicExchange";
    String topicQueue1 = "topicQueue1";
    String topicQueue2 = "topicQueue2";
}
