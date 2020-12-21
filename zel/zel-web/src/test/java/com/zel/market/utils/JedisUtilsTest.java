package com.zel.market.utils;

import com.zel.market.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;


class JedisUtilsTest extends BaseTest {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private JedisUtils jedisUtils;

    @Test
    public void testString() {
        System.out.println("tewsssssss");
        jedisUtils.deleteKey("a", "b", "a");
    }

    @Test
    public void testLink() {

    }

    @Test
    public void testSet() {

    }

    @Test
    public void testHash() {

    }

    @Test
    public void testZset() {

    }
}