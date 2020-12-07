package com.example.userservice;

import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cloud.stream.annotation.EnableBinding;
//import org.springframework.cloud.stream.annotation.StreamListener;
//import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2020/10/24
 */
//@Component
//@EnableBinding(Sink.class)
//public class ReceiveMessageListener {
//    @Value("${server.port}")
//    private String serverPort;
//
//    @StreamListener(Sink.INPUT)
//    public void input(Message<String> message) {
//        String msg = message.getPayload();
//        System.out.println("user 消费者收到：" + msg + "port:" + serverPort);
//    }
//}
