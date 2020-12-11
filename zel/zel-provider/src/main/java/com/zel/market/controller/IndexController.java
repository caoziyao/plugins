package com.zel.market.controller;

import com.zel.dbmanager.service.UserService;
import com.zel.market.config.Env;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "login")
@RestController
public class IndexController {

    @Autowired
    private UserService userService;

    @ApiOperation(value="login", notes="login", produces="application/json")
    @RequestMapping(value = "/")
    public String index() {
        userService.test();
        // System.out.println(u);
        long  userId = Env.getContext().getUserId();
        String token = Env.getContext().getToken();
        return "response:" + userId + token;
    }
}
