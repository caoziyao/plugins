package com.zel.market.jobs;

import com.zel.commonutils.DateUtil;
import com.zel.commonutils.client.HttpRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2020/7/25
 */
@Component
public class UserJobs {

    @Value("${server.port}")
    private int port;

    private static String baseUrl;

    static {
        InetAddress address = null;
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        baseUrl = "http://" + address.getHostAddress() + ":";
    }

    @Scheduled(fixedRate = 1 * DateUtil.MINUTE * DateUtil.MILLISECOND)
    public void reportCurrentTime() {
        String host = baseUrl + port;
        int num = new Random().nextInt(20);
        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (int i = 0; i < num; i++) {
            int finalI = i;
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    HttpRequest.get(host + "/superlogin?num=" + finalI);
                }
            });
        }

    }
}
