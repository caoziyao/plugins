package com.zel.market.controller;

import com.google.common.util.concurrent.RateLimiter;
import com.zel.market.common.Response;
import com.zel.market.service.mail.MailService;
import com.zel.market.service.user.UserService;
import com.zel.market.jobs.MailTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    private MailService mailService;

    @Autowired
    private UserService userService;

//    @Autowired
//    private MailService mailService;

    // 允许每秒最多10个任务
    public static final RateLimiter rateLimiter = RateLimiter.create(10);

    public String index2() {

        // 请求尝试获取令牌，获取不到返回服务器繁忙
        if (rateLimiter.tryAcquire()) {
            // 正常逻辑
        } else {
            // error
        }
        return "";
    }

    @RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST})
    public Response index(@RequestParam(required = false, defaultValue = "1") String statType) {
        mailService.addTask("helloddddddd");
//        if ("2".equals(statType)) {
//            throw new BusinessException("参数错误5！");
//        }
//        if (StringUtils.isEmpty(statType)) {
//            throw new BusinessException("参数错误！");
//        }
//        IndexVO vo = new IndexVO();
//        UserDTO user = new UserDTO();
//        user.setUserId("123:" + statType);
//        user.setUsername("abc");
//
//        Object all = userService.findTest();

        //vo.setUser(user);
        //vo.setUpdateTime(new Date());

        return Response.ok(null);
    }
}