package com.zel.market.controller;

import com.google.common.util.concurrent.RateLimiter;
import com.zel.commonutils.IpUtil;
import com.zel.commonutils.JsonHelper;
import com.zel.commonutils.redis.RedisUtils;
import com.zel.market.common.Response;
//import com.zel.market.es.EsClient;
import com.zel.market.service.mail.MailService;
import com.zel.market.service.user.UserService;
import com.zel.mq.patterndemo.helloworld.ProviderHelloWorld;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
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

    //// 允许每秒最多10个任务
    //public static final RateLimiter rateLimiter = RateLimiter.create(10);
    //
    //public String index2() {
    //
    //    // 请求尝试获取令牌，获取不到返回服务器繁忙
    //    if (rateLimiter.tryAcquire()) {
    //        // 正常逻辑
    //    } else {
    //        // error
    //    }
    //    return "";
    //}

    @RequestMapping(value = "/t", method = {RequestMethod.GET, RequestMethod.POST})
    public Response index(@RequestParam(required = false, defaultValue = "1") String statType, HttpServletRequest request) {

        String ipAddr = IpUtil.getIpAddr(request);


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
