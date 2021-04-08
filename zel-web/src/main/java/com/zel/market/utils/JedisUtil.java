package com.zel.market.utils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

public class JedisUtil {
    protected static Logger logger = Logger.getLogger("redis");

    //private static ResourceBundle bundle = null;
    //
    ///**
    // * 获取cluster   单例
    // * @return
    // */
    //public static JedisCluster getJedisCluster(){
    //    return jc;
    //}
    //
    //private JedisUtil() {}
    //
    ///**
    // * redis过期时间,以秒为单位
    // */
    //public final static int EXRP_HOUR = 60*60;          //一小时
    //public final static int EXRP_DAY = 60*60*24;        //一天
    //public final static int EXRP_MONTH = 60*60*24*30;   //一个月
    //
    //private static JedisCluster jc = null;
    //
    ///**
    // * 初始化Redis连接池
    // */
    //static {
    //    try {
    //        bundle = ResourceBundle.getBundle("redis");
    //    } catch (MissingResourceException e) {
    //        // 尝试加载外部config目录下的配置
    //        String proFilePath ="redis.properties";
    //        System.out.println("======= 从 " + proFilePath + " 加载redis配置");
    //        BufferedInputStream inputStream = null;
    //        try {
    //            inputStream = new BufferedInputStream(new FileInputStream(proFilePath));
    //            bundle = new PropertyResourceBundle(inputStream);
    //        } catch (Exception ee) {
    //            ee.printStackTrace();
    //        } finally {
    //            if (null != inputStream) {
    //                try {
    //                    inputStream.close();
    //                } catch (IOException e1) {
    //                    e1.printStackTrace();
    //                }
    //            }
    //        }
    //    }
    //
    //    try {
    //        Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
    //        // 只需要配置集群中的一个结点，连接成功后，自动获取点集群中其他结点信息
    //        int rediscount = 3;
    //        if (bundle.containsKey("redis.count")) {
    //            rediscount = Integer.parseInt(bundle.getString("redis.count"));
    //        }
    //
    //        for (int i = 1; i <= rediscount; i++) {
    //            jedisClusterNodes.add(new HostAndPort(bundle.getString("redis.ip" + i), Integer.valueOf(bundle.getString("redis.port" + i))));
    //        }
    //
    //        JedisPoolConfig config = new JedisPoolConfig();
    //        config.setMaxTotal(Integer.valueOf(bundle.getString("redis.pool.maxActive")));
    //        config.setMaxIdle(Integer.valueOf(bundle.getString("redis.pool.maxIdle")));
    //        config.setMaxWaitMillis(Long.valueOf(bundle.getString("redis.pool.maxWait")));
    //        config.setTestOnBorrow(Boolean.valueOf(bundle.getString("redis.pool.testOnBorrow")));
    //        config.setTestOnReturn(Boolean.valueOf(bundle.getString("redis.pool.testOnReturn")));
    //
    //        String password = null;
    //        if (bundle.containsKey("redis.auth")) {
    //            password  = bundle.getString("redis.auth");
    //        }
    //        jc = new JedisCluster(jedisClusterNodes, 2000, 5000, 5, password, config);
    //    } catch (Exception e) {
    //        e.printStackTrace();
    //    }
    //}


}
