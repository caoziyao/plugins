package com.zel.market.utils;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class JedisUtils {
    protected Logger logger = LoggerFactory.getLogger(JedisUtils.class);

    @Autowired
    private StringRedisTemplate redisTemplate;

//    @Autowired
//    private RedisTemplate<String, Object> redisTemplate;


    /**
     * redis过期时间,以秒为单位
     */
    public final static int EXR_30S = 30;          //30秒
    public final static int EXR_60S = 60;          //60秒
    public final static int EXR_120S = 120;          //120秒 打印机token和session超时时间
    public final static int EXR_300S = 300;          //300秒
    public final static int EXR_HOUR = 60 * 60;          //一小时
    public final static int EXR_TEN_MIS = 10 * 60;       //10分钟
    public final static int EXR_DAY = 60 * 60 * 24;        //一天
    public final static int EXR_MONTH = 60 * 60 * 24 * 30;   //一个月

    /**
     * 不设置过期时长
     */
    public static final long NOT_EXPIRE = -1;

    /**
     * 是否存在
     * @param key
     * @return
     */
    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 重名名key，如果newKey已经存在，则newKey的原值被覆盖
     *
     * @param oldKey
     * @param newKey
     */
    public void renameKey(String oldKey, String newKey) {
        redisTemplate.rename(oldKey, newKey);
    }

    /**
     * newKey不存在时才重命名
     *
     * @param oldKey
     * @param newKey
     * @return 修改成功返回true
     */
    public boolean renameIfAbsent(String oldKey, String newKey) {
        return redisTemplate.renameIfAbsent(oldKey, newKey);
    }

    /**
     * 删除key
     *
     * @param key
     */
    public void deleteKey(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 删除多个key
     *
     * @param keys
     */
    public void deleteKey(String... keys) {
        Set<String> kSet = Arrays.stream(keys).collect(Collectors.toSet());
        redisTemplate.delete(kSet);
    }

    /**
     * 删除Key的集合
     *
     * @param keys
     */
    public void deleteKey(Collection<String> keys) {
        Set<String> kSet = keys.stream().map(k -> k).collect(Collectors.toSet());
        redisTemplate.delete(kSet);
    }

    /**
     * 设置key的生命周期
     *
     * @param key
     * @param time
     * @param timeUnit
     */
    public void expire(String key, long time, TimeUnit timeUnit) {
        redisTemplate.expire(key, time, timeUnit);
    }

    /**
     * 指定key在指定的日期过期
     *
     * @param key
     * @param date
     */
    public void expireAt(String key, Date date) {
        redisTemplate.expireAt(key, date);
    }

    /**
     * 查询key的生命周期
     *
     * @param key
     * @param timeUnit
     * @return
     */
    public long getExpire(String key, TimeUnit timeUnit) {
        return redisTemplate.getExpire(key, timeUnit);
    }

    /**
     * 将key设置为永久有效
     *
     * @param key
     */
    public void persist(String key) {
        redisTemplate.persist(key);
    }



}
