package com.zel.mq.patterndemo;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2020/12/29
 */
public interface IRabbitRouteKey {
    String keyHaha = "hahah";
    String keyAbc = "abc";

    String keyTopicHaha = "topic.#";
    String keyTopicAbc = "topic.aaa";
}
