package com.zel.market.app.filter;

import com.google.common.util.concurrent.RateLimiter;
import com.zel.market.exception.BusinessException;

import java.util.concurrent.TimeUnit;

public class RateLimiterUtils {
    private static final String BASE_PRE_KEY = "rateLimiter:";
    private static final String INCREASE_LUA = "local num = redis.call('INCRBY', KEYS[1],ARGV[3])\nif tonumber(num) == 1 then\n\tredis.call('expire', KEYS[1], ARGV[1])\n\treturn 1\nelseif tonumber(num) > tonumber(ARGV[2]) then\n\treturn 0\nelse \n\treturn 1\nend\n";
    private Long timeInterval;
    private Long maxPermit;
    private String name;

    /**
     * 只能单台服务器限制访问
     *
     * 允许每秒最多 Math.max(4, Runtime.getRuntime().availableProcessors()) * 100 个任务
     * 4核 400/s
     */
    private static final RateLimiter RATE_LIMITER = RateLimiter.create(Math.max(4, Runtime.getRuntime().availableProcessors()) * 100);

    private RateLimiterUtils() {
    }

    private RateLimiterUtils(String name, Long timeInterval, TimeUnit timeUnit, Long maxPermit) {
        this.name = name;
        this.timeInterval = timeUnit.toSeconds(timeInterval);
        this.maxPermit = maxPermit;
    }

    public static RateLimiterUtils build(String name, Long timeInterval, TimeUnit timeUnit, Long maxPermit) {
        boolean illegalParam = timeInterval == null || timeUnit == null || maxPermit == null || timeInterval <= 0L || maxPermit <= 0L;
        if (illegalParam) {
            throw new IllegalArgumentException();
        } else {
            return new RateLimiterUtils(name, timeInterval, timeUnit, maxPermit);
        }
    }

    public boolean tryAcquire(String key) {
        return this.tryAcquire(key, 1L);
    }

    /**
     * 分布式
     * @param key
     * @param permit
     * @return
     */
    private boolean tryAcquire(String key, Long permit) {
        String realKey = this.getFullKey(key);
        //(Long)JedisUtil.getJedisCluster().eval(INCREASE_LUA, Arrays.asList(realKey), Arrays.asList(this.timeInterval.toString(), this.maxPermit.toString(), permit.toString()))
        // 运行 luna 脚本
        //long result = (Long)RedisUtils (INCREASE_LUA, Arrays.asList(realKey), Arrays.asList(this.timeInterval.toString(), this.maxPermit.toString(), permit.toString()));
        long result = 1;
        return result != 0L;
    }

    private String getFullKey(String key) {
        return BASE_PRE_KEY + this.name + ":" + key;
    }

    /**
     * 单台服务器
     * @return
     */
    public static RateLimiter getSingleRateLimiter() {
        return RATE_LIMITER;
    }

    /**
     * 只能单台服务器限制访问
     * @param timeout
     * @param unit
     */
    public static void trySingleAcquire(long timeout, TimeUnit unit) {
        if (!RATE_LIMITER.tryAcquire(timeout, unit)) {
            throw new BusinessException("系统繁忙，请稍后再试");
        }
    }
}
