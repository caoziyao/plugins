package com.zel.market.controller;

import com.zel.market.utils.TokenUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@Api(description = "login")
@RestController
public class LoginController {

    @ApiOperation(value="login", notes="login", produces="application/json")
    @GetMapping("/login")
    public String index() {
        // System.out.println(u);
        String token = TokenUtils.INSTANCE.buildToken("1213");
        return "token:" + token;
    }
}
