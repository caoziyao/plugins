package com.example.userservice.controller;

import com.example.userservice.entity.User;
import com.example.userservice.mapper.Demo;
import com.example.userservice.mapper.IDemo;
import com.example.userservice.mapper.UserMapper;
import com.example.userservice.service.UserService;
import gua.commons.request.BaseResponse;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2020/10/23
 */
@RestController
public class UserController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private UserService userService;

    @Autowired
    private Registration registration;

    @Autowired
    private UserMapper userMapper;


    @Autowired
    private IDemo demo;

    @GetMapping(value = "/user")
    public String user() {
        // 保存字符串
        stringRedisTemplate.opsForValue().set("aaa", "111");
        String value = stringRedisTemplate.opsForValue().get("aaa");
        String ip = registration.getHost() + ":" + registration.getPort();
        System.out.println("from other:" + ip);
        return "user ok" + ip + ":" + value;
    }

    @GetMapping(value = "/user/test")
    public String testUser() {
        System.out.println("demo:" + demo);
        demo.say();
//        userService.transactTest();
        return "user test ";
    }

    @GetMapping(value = "/user/all")
    public BaseResponse<List<User>> allUser() {
        return BaseResponse.ok("ok", userMapper.findAll());
    }

    @GetMapping(value = "/user/all2")
    public BaseResponse<List<User>> allUser2() {
        List<User> userList = userMapper.selectList(null);
        return BaseResponse.ok("ok2", userList);
    }
}
