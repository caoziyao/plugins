package com.zel.market.utils;

import com.zel.market.BaseTest;
import com.zel.commonutils.redis.RedisUtils;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Set;
import java.util.concurrent.TimeUnit;


class RedisUtilsTest extends BaseTest {

    @Autowired
    //private RedisTemplate<String, Object> redisTemplate;
    private StringRedisTemplate redisTemplate;

    @Autowired
    private RedisUtils redisUtils;

    @Test
    public void testRedisUtils() {
        String key = "test-abc";
        redisUtils.del(key);
        boolean r1 = redisUtils.setnx(key, "ddd", 30l, TimeUnit.SECONDS);
        Assert.assertEquals(r1, true);
        boolean r2 = redisUtils.setnx(key, "ddd", 30l, TimeUnit.SECONDS);
        Assert.assertEquals(r2, true);
        redisUtils.del("key");
    }

    @Test
    public void testSetTimeOut() {
        // 30s 过期
        redisTemplate.opsForValue().set("key", "ddd", 30, TimeUnit.SECONDS);
        String abc = redisTemplate.opsForValue().get("key");
        redisTemplate.delete("key");

    }

    /**
     * leftPush(K key, V value)，往 List 左侧插入一个元素，如 从左边往数组中 push 元素
     * rightPush(K key, V value)，往 List 右侧插入一个元素， 如从右边往数组中 push 元素
     * leftPop(K key)，从 List 左侧取出第一个元素，并移除， 如从数组头部获取并移除值
     * rightPop(K key)，从 List 右侧取出第一个元素，并移除， 如从数组尾部获取并移除值
     * rightPop(K key)，从 List 右侧取出第一个元素，并移除， 如从数组尾部获取并移除值
     */
    @Test
    public void testList() {
        redisTemplate.opsForList().leftPush("TestList", "TestLeftPush");
        redisTemplate.opsForList().rightPush("TestList", "TestRightPush");

        Object leftFirstElement = redisTemplate.opsForList().leftPop("TestList");
        System.out.println(leftFirstElement);

        Object rightFirstElement = redisTemplate.opsForList().rightPop("TestList");
        System.out.println(rightFirstElement);
    }

    /**
     * Hash 中新增元素。
     */
    @Test
    public void testHash() {
        redisTemplate.opsForHash().put("TestHash", "FirstElement", "Hello,Redis hash.");
        Assert.assertTrue(redisTemplate.opsForHash().hasKey("TestHash", "FirstElement"));
        Object element = redisTemplate.opsForHash().get("TestHash", "FirstElement");
        Assert.assertEquals("Hello,Redis hash.", element);

        redisTemplate.opsForHash().delete("TestHash", "FirstElement");
        Assert.assertFalse(redisTemplate.opsForHash().hasKey("TestHash", "FirstElement"));
    }

    @Test
    public void testSet() {
        redisTemplate.opsForSet().add("TestSet", "e1", "e2", "e3");
        long size = redisTemplate.opsForSet().size("TestSet");
        Assert.assertEquals(3L, size);

        Set<String> testSet = redisTemplate.opsForSet().members("TestSet");
        System.out.println(testSet);

        redisTemplate.opsForSet().remove("TestSet", "e1", "e2");
        Set testSet2 = redisTemplate.opsForSet().members("TestSet");
        Assert.assertEquals("e3", testSet2.toArray()[0]);
    }

    /**
     * add(K key, V value, double score)	添加元素到变量中同时指定元素的分值。
     * range(K key, long start, long end)	获取变量指定区间的元素。
     * rangeByLex(K key, RedisZSetCommands.Range range)	用于获取满足非 score 的排序取值。这个排序只有在有相同分数的情况下才能使用，如果有不同的分数则返回值不确定。
     * angeByLex(K key, RedisZSetCommands.Range range, RedisZSetCommands.Limit limit)	用于获取满足非 score 的设置下标开始的长度排序取值。
     * add(K key, Set<ZSetOperations.TypedTuple<V>> tuples)	通过 TypedTuple 方式新增数据。
     * rangeByScore(K key, double min, double max)	根据设置的 score 获取区间值。
     * rangeByScore(K key, double min, double max,long offset, long count)	根据设置的 score 获取区间值从给定下标和给定长度获取最终值。
     * rangeWithScores(K key, long start, long end)	获取 RedisZSetCommands.Tuples 的区间值。
     */
    @Test
    public void testZset() {

    }
}