package com.zel.market.controller.user;

import com.zel.market.controller.user.dto.LoginReqDTO;
import com.zel.TokenUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@Api(description = "login")
@RestController
public class LoginController {

    @ApiOperation(value="login", notes="login", produces="application/json")
    @PostMapping("/login")
    public String index(@Validated @RequestBody LoginReqDTO body) {
        String username = body.getUsername();
        String password = body.getPassword();
        String token = TokenUtils.INSTANCE.buildToken(username + password);
        return "token: " + token;
    }
}
