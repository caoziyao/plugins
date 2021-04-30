package com.zel.market.app.controller;

import com.google.common.util.concurrent.RateLimiter;
import com.zel.commonutils.client.RequestUtil;
import com.zel.redis.RedisUtils;
import com.zel.market.common.Response;
//import com.zel.market.es.EsClient;
import com.zel.market.app.service.mail.MailService;
import com.zel.market.app.service.user.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.concurrent.TimeUnit;


@RestController
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    private MailService mailService;

    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtils redisUtils;

    // 允许每秒最多400个任务
    public static final RateLimiter rateLimiter = RateLimiter.create(Math.max(4, Runtime.getRuntime().availableProcessors()) * 100);


    @RequestMapping(value = "/test", method = {RequestMethod.GET, RequestMethod.POST})
    public Response index(@RequestParam(required = false, defaultValue = "1") String statType, HttpServletRequest request) {

        String ipAddr = RequestUtil.getIpAddr(request);

        //请求尝试获取令牌，获取不到返回服务器繁忙
        if (!rateLimiter.tryAcquire()) {
            return Response.ok("系统繁忙，请稍后再试！");
        }


        String key = "abctest";
        String o = (String)redisUtils.get(key);
        if (StringUtils.isBlank(o)) {
            o = "cccc" + new Date().getTime();
            redisUtils.set("abctest", o,  20L, TimeUnit.SECONDS);
            System.out.println("set redis:" + o);
        } else {
            System.out.println("from redis:" + o);
        }


        return Response.ok(null);
    }
}
