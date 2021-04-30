package com.zel.market.app.controller.user;

import com.zel.redis.RedisUtils;
import com.zel.market.common.Response;
import com.zel.market.app.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class RegisterController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtils redisUtils;

    @GetMapping("/register")
    // @Validated @RequestBody LoginReqDTO body,
    public Response register(HttpServletRequest request
            , HttpServletResponse response) {
        return null;
    }
}
