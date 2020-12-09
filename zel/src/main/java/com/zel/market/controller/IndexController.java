package com.zel.market.controller;

import com.zel.market.entity.User;
import com.zel.market.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "生产者进程API接口")
@RestController
public class IndexController {

    @Autowired
    UserService userService;

    @ApiOperation(value="查询单词计数", notes="查询单词计数", produces="application/json")
    @RequestMapping(value = "/index")
    public String index() {
        User u = userService.findById("1");
        System.out.println(u);
        return "response";
    }
}
