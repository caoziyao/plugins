package com.zel.market.jobs.monitor;

import com.zel.commonutils.DateUtil;
import com.zel.redis.RedisUtils;
import com.zel.market.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

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
        Date day7 = DateUtil.plusDays(now, -7);
        Date dayLong = DateUtil.plusDays(now, -100);
        Long aLong = redisUtils.zRemRangeByScore(Config.USER_ONLINE_KEY, dayLong.getTime(), day7.getTime());
        System.out.println("删除了: " + aLong + "条数据");
    }
}
