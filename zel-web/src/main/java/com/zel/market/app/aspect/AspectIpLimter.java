package com.zel.market.app.aspect;


import com.zel.commonutils.client.RequestUtil;
import com.zel.market.common.Response;
import com.zel.redis.RedisUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * ip限流
 *
 * 算法：可以换成漏桶或令牌桶算法。目前只简单写写
 *
 * 比如某接口设置相同IP10秒内请求5次，超过5次不让访问该接口。
 *
 * 1. 第一次该IP地址存入redis的时候，key值为IP地址,value值为1，设置key值过期时间为10秒。
 * 2. 第二次该IP地址存入redis时，如果key没有过期,那么更新value为2。
 * 3. 以此类推当value已经为5时，如果下次该IP地址在存入redis同时key还没有过期，那么该Ip就不能访问了。
 * 4. 当10秒后，该key值过期，那么该IP地址再进来，value又从1开始，过期时间还是10秒，这样反反复复。
 *
 */
@Aspect
@Component
public class AspectIpLimter {

    @Autowired
    private RedisUtils redisUtils;

    private Logger logger = LoggerFactory.getLogger(AspectIpLimter.class);


    /**
     * getRedisScript 读取脚本工具类
     * 这里设置为Long,是因为ipLimiter.lua 脚本返回的是数字类型
     */
    //private DefaultRedisScript<Long> getRedisScript;

    /**
     * @PostConstruct注解
     * 用途: 被注解的方法，在对象加载完依赖注入后执行。
     *
     * 因为 AspectIpLimter 是单例，所以这里只会执行一次
     */
    @PostConstruct
    public void init() {
        //getRedisScript = new DefaultRedisScript<>();
        //getRedisScript.setResultType(Long.class);
        //getRedisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("ipLimiter.lua")));
        logger.info("IpLimterHandler[分布式限流处理器]脚本加载完成");
    }


    /**
     * time 分钟通过 limit 次
     * @param ip
     * @param time
     * @param limit 单位是秒
     * @return
     */
    public boolean validIpLimit(String ip, int time, int limit) {
        ip = "ip:limit:" + ip;

        Long hasTime = redisUtils.incr(ip, time);
        if (hasTime > limit) {
            logger.error("{} {}分钟内访问了{}次。访问太频繁", ip, time, hasTime);
            return false;
        } else {
            return true;
        }
    }

    /**
     * 环绕
     * 在注解 IpLimiter 的方法都会执行
     *
     */
    @Around("@annotation(ipLimiter)")
    public Object around(ProceedingJoinPoint jp, IpLimiter ipLimiter) throws Throwable {
        logger.debug("环绕前: IpLimterHandler[分布式限流处理器]开始执行限流操作，签名：" + jp.getSignature());

        Signature signature = jp.getSignature();
        if (!(signature instanceof MethodSignature)) {
            throw new IllegalArgumentException("the Annotation @IpLimter must used on method!");
        }

        /**
         * 获取注解参数
         */
        // 获取参数，API百度。原理大概就是 jp 保存了所有的上下文。
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();

        // 获取请求 ip
        String ip = RequestUtil.getIpAddr(request);

        //
        int limit = ipLimiter.limit();
        int time = ipLimiter.time();
        String message = ipLimiter.message();

        /**
         * 执行Lua脚本
         */
        List<String> ipList = new ArrayList<>();
        // 设置key值为注解中的值
        ipList.add(ip);

        boolean result = validIpLimit(ip, time, limit);
        if (result == false) {
            String msg = "由于超过单位时间=" + time + "-允许的请求次数=" + limit + "[触发限流]";
            logger.info(msg);
            // 达到限流返回给前端信息
            return  Response.error(message);
        }


        //执行目标方法proceed
        Object proceed = jp.proceed();


        System.out.println("环绕后");
        System.out.println(proceed);

        return proceed;
    }
}
