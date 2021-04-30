package com.zel.redis.redisson;

import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class RedissonLockTest extends TestCase {

    //@Autowired
    //RedissonLock redissonLock;

    /**
     * 模拟这个是商品库存
     */
    public static volatile Integer TOTAL = 10;


    public String lockDecreaseStock(RedissonLock redissonLock) {
        try{
            // 1. 最常见的使用方法
            redissonLock.lock("lock", 10L);

            // do your business
            log.warn("===获取锁===" + Thread.currentThread().getName());
            if (TOTAL > 0) {
                TOTAL--;
            }

            Thread.sleep(500);
            log.warn("===lock===减完库存后,当前库存===" + TOTAL + "---"  + Thread.currentThread().getName());

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //如果该线程还持有该锁，那么释放该锁。如果该线程不持有该锁，说明该线程的锁已到过期时间，自动释放锁
            if (redissonLock.isHeldByCurrentThread("lock")) {
                log.warn("===lock===释放锁===" + TOTAL + "---"  + Thread.currentThread().getName());
                redissonLock.unlock("lock");
            }
        }


        //redissonLock.lock("lock", 10L);
        //log.warn("===获取锁===" + Thread.currentThread().getName());
        //if (TOTAL > 0) {
        //    TOTAL--;
        //}
        //try {
        //    Thread.sleep(50);
        //} catch (InterruptedException e) {
        //    e.printStackTrace();
        //}
        //log.warn("===lock===减完库存后,当前库存===" + TOTAL + "---"  + Thread.currentThread().getName());
        ////如果该线程还持有该锁，那么释放该锁。如果该线程不持有该锁，说明该线程的锁已到过期时间，自动释放锁
        //if (redissonLock.isHeldByCurrentThread("lock")) {
        //    log.warn("===lock===释放锁===" + TOTAL + "---"  + Thread.currentThread().getName());
        //    redissonLock.unlock("lock");
        //}
        return "==============";
    }

    public String trylockDecreaseStock(RedissonLock redissonLock) {

        try{
            // redissonLock.tryLock("trylock", 5L, 200L)
            if (redissonLock.tryLock("trylock", 10L, 200L)) {
                log.warn("===获取锁===" + Thread.currentThread().getName());
                //业务处理
                if (TOTAL > 0) {
                    TOTAL--;
                }
                Thread.sleep(500L);
                log.warn("===lock===减完库存后,当前库存===" + TOTAL + "---===="  + Thread.currentThread().getName());
            } else {
                log.warn("======[ExecutorRedisson]排队中,请稍后重试" + Thread.currentThread().getName());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            log.warn("===lock===释放锁===" + TOTAL + "---"  + Thread.currentThread().getName());
            redissonLock.unlock("trylock");

        }

        return "===================================";
    }

    @Test
    public void testRead() {
        RedissonManager redissonManager = new RedissonManager();
        RedissonLock redissonLock = new RedissonLock(redissonManager);


        for (int i = 0; i < 20; i += 1) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    lockDecreaseStock(redissonLock);
                }
            }).start();
        }


        lockDecreaseStock(redissonLock);
        lockDecreaseStock(redissonLock);
        lockDecreaseStock(redissonLock);

    }
}