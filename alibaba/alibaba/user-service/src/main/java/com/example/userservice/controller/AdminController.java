package com.example.userservice.controller;

import com.example.userservice.controller.vo.LoginParam;
import com.example.userservice.controller.vo.VoUser;
import com.example.userservice.entity.User;
import com.example.userservice.mapper.UserMapper;
import com.example.userservice.service.UserService;
import gua.commons.DigestTool;
import gua.commons.ToolUtils;
import gua.commons.request.BadRequestException;
import gua.commons.request.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.Duration;

@RestController
@RequestMapping(value = "/user")
public class AdminController {

    @Value("${randomSalt}")
    private String salt;
    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;



    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public User isLogin(String token) {
        String userId = stringRedisTemplate.opsForValue().get(token);
        User user = userService.findById(userId);
        return user;
    }

    @GetMapping(value = "/check")
    public BaseResponse check(HttpServletRequest request) {
        String token = request.getHeader("token");

        String userId = stringRedisTemplate.opsForValue().get(token);

        User user = userService.findById(userId);

        return BaseResponse.ok("ok:" + token + ":" + userId, user);
    }

    /**
     * 登出
     *
     * @param request
     * @return
     */
    @GetMapping(value = "/logout")
    public BaseResponse logout(HttpServletRequest request) {
        String token = request.getHeader("token");
        Boolean delete = stringRedisTemplate.opsForValue().getOperations().delete(token);
        return BaseResponse.ok("ok:" + delete);
    }

    @PostMapping(value = "/login")
    public BaseResponse<VoUser> login(@RequestBody @Valid LoginParam loginParam) {
        String username = loginParam.getUsername();
        String plainPassword = DigestTool.sha1(loginParam.getPassword(), salt, username);
        User user = userService.login(username, plainPassword);
        if (user == null) {
            throw new BadRequestException("用户名或者密码不正确");
        }

        // 保存字符串
        String token = ToolUtils.randomUUIDWithoutDash();
        VoUser vo = new VoUser(user);
        vo.setToken(token);
        stringRedisTemplate.opsForValue().set(token, String.valueOf(user.getId()), Duration.ofMinutes(60));

        return BaseResponse.ok("login ok", vo);
    }

    @PostMapping(value = "/sign")
    public BaseResponse sign(@RequestBody @Valid LoginParam loginParam) {
        String username = loginParam.getUsername();
        String password = loginParam.getPassword();
        User user = userService.login(username, password);
        if (user != null) {
            throw new BadRequestException("用户已经存在");
        }

        String plainPassword = DigestTool.sha1(password, salt, username);

        User u = userService.createUser(username, plainPassword);
        return BaseResponse.ok("sign ok", u);
    }

    @PostMapping(value = "/update")
    public BaseResponse<VoUser> update(HttpServletRequest request, @RequestBody User userParam) {
        String token = request.getHeader("token");
        User u = isLogin(token);
        if (u == null) {
            throw new BadRequestException("请登录");
        }
        u.setUsername(userParam.getUsername());
        u.setAge(userParam.getAge());

        userMapper.updateById(u);

        VoUser v = new VoUser(u);
        return BaseResponse.ok("sign ok", v);
    }
}
