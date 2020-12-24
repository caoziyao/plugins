package com.zel.market.jobs;

import com.zel.commonutils.DateUtil;
import com.zel.commonutils.client.HttpRequest;
import com.zel.commonutils.redis.RedisUtils;
import com.zel.market.config.Config;
import com.zel.market.service.mail.MailService;
import com.zel.market.service.ss.SSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description: 清理工作
 *
 * @author csy
 * @version 1.0.0
 * @since 2020/10/25
 */
@Component
public class CleanJobs {

    @Autowired
    private RedisUtils redisUtils;

    @Scheduled(fixedRate = 1 * DateUtil.DAY * DateUtil.MILLISECOND)
    public void reportCurrentTime() {
        // 清理 redis 在线用户
        Date now = new Date();
        Date day7 = DateUtil.add(now, -7);
        Date dayLong = DateUtil.add(now, -100);
        Long aLong = redisUtils.zRemRangeByScore(Config.USER_ONLINE_KEY, dayLong.getTime(), day7.getTime());
        System.out.println("删除了: " + aLong + "条数据");
    }
}
