package com.zel.market.controller.user;

import com.zel.commonutils.JacksonHelper;
import com.zel.commonutils.RequestUtil;
import com.zel.commonutils.crypto.AESEncrypt;
import com.zel.commonutils.crypto.Md5Utils;
import com.zel.commonutils.redis.RedisUtils;
import com.zel.dbmanager.entity.User;
import com.zel.market.common.Constants;
import com.zel.market.common.Response;
import com.zel.market.common.enumcom.ERedisKey;
import com.zel.market.common.enumcom.EResponseCode;
import com.zel.market.controller.user.dto.LoginReqDTO;
import com.zel.commonutils.TokenUtils;
import com.zel.market.exception.BusinessException;
import com.zel.market.service.user.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;


@Api(description = "login")
@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtils redisUtils;

    private User getDefaultUser() {
        User user = new User();
        user.setId(12L);
        user.setPassword("81dc9bdb52d04dc20036dbd8313ed055");
        user.setUsername("abc");
        return user;
    }

    @ApiOperation(value="login", notes="login", produces="application/json")
    @GetMapping("/login")
    // @Validated @RequestBody LoginReqDTO body,
    public Response index(HttpServletRequest request
            , HttpServletResponse response) throws Exception {
        String username = "abc";
        String password = "1234";

        // User user = userService.getUserByName(username);
        User user = getDefaultUser();
        if(user == null){
            throw new BusinessException(EResponseCode.NOT_REGISTER);
        }

        // 密码判断
        String md5 = Md5Utils.md5(password);
        if(!user.getPassword().equals(md5)){
            throw new BusinessException(EResponseCode.PASSWORD_ERR);
        }

        // 生成并设置TOKEN
        String newToken = new AESEncrypt("random key").encrypt(user.getId() + "-" + System.currentTimeMillis()/1000);
        //        String newToken = TokenUtils.INSTANCE.buildToken(username + password);
        // 设置TOKEN
        redisUtils.set(ERedisKey.USERID.formatKey(user.getId().toString()), JacksonHelper.write(user), 24L, TimeUnit.HOURS);
        // 保存到 cookie 或 header 里面
        RequestUtil.saveCookie(Constants.SESSIONID, newToken, request, response);

        return Response.ok(user);
    }
}
