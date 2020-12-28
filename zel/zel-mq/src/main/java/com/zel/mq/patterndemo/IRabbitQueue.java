package com.zel.mq.patterndemo;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2020/12/29
 */
public interface IRabbitQueue {
    String hello = "hello";
    String work = "work";
    String logsExchange = "logsExchange";
    String routeDirectExchange = "routeDirectExchange";

    String topicExchange = "topicExchange";
    String topicQueue1 = "topicQueue1";
    String topicQueue2 = "topicQueue2";
}
