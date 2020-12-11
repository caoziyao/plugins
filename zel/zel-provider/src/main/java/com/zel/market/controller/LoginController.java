package com.zel.market.controller;

import com.zel.market.utils.TokenUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @GetMapping("/login")
    public String index() {
        // System.out.println(u);
        String token = TokenUtils.INSTANCE.buildToken("1213");
        return "token:" + token;
    }
}
