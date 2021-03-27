package com.zel.market.app.service.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2021/1/2
 */
@Service
public class KafkaProducerService {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    /**
     * 生产者自增key
     */
    public static final AtomicLong  PRODUCER_INCR_KEY = new AtomicLong(0);


    public void sendMessageSync(String topic, String message) throws InterruptedException, ExecutionException, TimeoutException {
        kafkaTemplate.send(topic, PRODUCER_INCR_KEY.getAndIncrement(), message).get(10, TimeUnit.SECONDS);
    }

    /**
     * producer 同步方式发送数据
     * @param topic    topic名称
     * @param message  producer发送的数据
     */
    public void sendMessageSync(String topic, String key, String message) throws InterruptedException, ExecutionException, TimeoutException {
        kafkaTemplate.send(topic, key, message).get(10, TimeUnit.SECONDS);
    }

    /**
     * producer 异步方式发送数据
     * @param topic    topic名称
     * @param message  producer发送的数据
     */
    public void sendMessageAsync(String topic, String message) {
        ListenableFuture<SendResult<Integer, String>> future = kafkaTemplate.send(topic, message);
        future.addCallback(new ListenableFutureCallback<SendResult<Integer, String>>() {
            @Override
            public void onSuccess(SendResult<Integer, String> result) {
                System.out.println("success");
            }

            @Override
            public void onFailure(Throwable ex) {
                System.out.println("failure");
            }
        });
    }
}
