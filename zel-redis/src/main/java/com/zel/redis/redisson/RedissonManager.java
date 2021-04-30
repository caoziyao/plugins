package com.zel.redis.redisson;

import org.redisson.Redisson;
import org.redisson.config.Config;

public class RedissonManager {


    private Config config = new Config();

    public RedissonManager() {
    }

    public RedissonManager(RedissonProperties redissonProperties) {

        //System.out.println(redissonProperties.getAddress());
        //try {
        //    //通过不同部署方式获得不同cofig实体
        //    config = RedissonConfigFactory.getInstance().createConfig(redissonProperties);
        //    redisson = (Redisson) Redisson.create(config);
        //} catch (Exception e) {
        //    log.error("Redisson init error", e);
        //    throw new IllegalArgumentException("please input correct configurations," +
        //            "connectionType must in standalone/sentinel/cluster/masterslave");
        //}
    }
}
