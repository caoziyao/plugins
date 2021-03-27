package com.zel.market.request;


import com.zel.commonutils.redis.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 默认的state缓存实现
 *
 */
@Component
public class AuthDefaultStateCache implements AuthStateCache {

    /**
     * 当前实例
     */
    //INSTANCE;

    //private AuthCache authCache;
    @Autowired
    private RedisUtils authCache;

    //AuthDefaultStateCache() {
    //    authCache = new AuthDefaultCache();
    //}

    /**
     * 存入缓存
     *
     * @param key   缓存key
     * @param value 缓存内容
     */
    @Override
    public void cache(String key, String value) {
        authCache.set(key, value);
    }

    /**
     * 存入缓存
     *
     * @param key     缓存key
     * @param value   缓存内容
     * @param timeout 指定缓存过期时间（毫秒）
     */
    @Override
    public void cache(String key, String value, long timeout) {
        authCache.set(key, value, timeout);
    }

    /**
     * 获取缓存内容
     *
     * @param key 缓存key
     * @return 缓存内容
     */
    @Override
    public String get(String key) {
        return (String) authCache.get(key);
    }

    /**
     * 是否存在key，如果对应key的value值已过期，也返回false
     *
     * @param key 缓存key
     * @return true：存在key，并且value没过期；false：key不存在或者已过期
     */
    @Override
    public boolean containsKey(String key) {
        String cacheState = this.get(key);
        return null != cacheState;
        //return authCache.containsKey(key);
    }
}
