package com.zel.market.controller;

import com.zel.dbmanager.service.BookService;
import com.zel.dbmanager.service.SSAccountService;
import com.zel.dbmanager.service.UserService;
import com.zel.market.common.Env;
import com.zel.market.utils.HTMLParseUtils;
import com.zel.market.utils.SysLoggers;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(description = "index")
@RestController
public class IndexController {

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @Autowired
    private HTMLParseUtils htmlParseUtils;

    @ApiOperation(value="index", notes="index", produces="application/json")
    @GetMapping(value = "/")
    public String index() {

//        userService.test();
        // System.out.println(u);
        //UserService userService = new UserService();
//        htmlParseUtils.parse();
//        userService.findAll();
//        bookService.findAll();
        //UserService.findAll();
        //BookService.findAll();
        long  userId = Env.getContext().getUserId();
        String token = Env.getContext().getToken();

        return "response:" + userId + token;
    }
}
