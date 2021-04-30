package com.zel.redis.redisson;

import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.config.Config;

public class RedissonManager {


    private Config config = new Config();

    private Redisson redisson = null;


    public RedissonManager() {

        config.useSingleServer().setAddress("redis://49.234.12.16:6379");
        config.useSingleServer().setDatabase(1);
        // 密码可以为空
        if (StringUtils.isNotBlank("zy1230")) {
            config.useSingleServer().setPassword("zy1230");
        }

        redisson = (Redisson) Redisson.create(config);

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

    public Redisson getRedisson() {
        return redisson;
    }
}
