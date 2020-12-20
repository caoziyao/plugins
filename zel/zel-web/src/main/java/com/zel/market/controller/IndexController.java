package com.zel.market.controller;

import com.google.common.util.concurrent.RateLimiter;
import com.zel.dbmanager.entity.Jobs;
import com.zel.dbmanager.entity.User;
import com.zel.market.common.Env;
import com.zel.market.common.Response;
import com.zel.market.controller.vo.IndexVO;
import com.zel.market.dto.UserDTO;
import com.zel.market.exception.BusinessException;
import com.zel.market.service.mail.MailService;
import com.zel.market.service.user.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Api(description = "index")
@RestController
public class IndexController {

    @Autowired
    private MailService mailService;

    @Autowired
    private UserService userService;
    

    // 允许每秒最多10个任务
    public static final RateLimiter rateLimiter = RateLimiter.create(10);

    public String index2() {

        // 请求尝试获取令牌，获取不到返回服务器繁忙
        if (rateLimiter.tryAcquire()) {
            // 正常逻辑
        } else {
            // error
        }
        long userId = Env.getContext().getUserId();
        String token = Env.getContext().getToken();

        return "response:" + userId + token;
    }

    @ApiOperation(value = "index", notes = "index", produces = "application/json")
    @RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST})
    public Response index(@RequestParam(required = false, defaultValue = "1") String statType) {
        if ("2".equals(statType)) {
            throw new BusinessException("参数错误5！");
        }
        if (StringUtils.isEmpty(statType)) {
            throw new BusinessException("参数错误！");
        }
        IndexVO vo = new IndexVO();
        UserDTO user = new UserDTO();
        user.setUserId("123:" + statType);
        user.setUsername("abc");

        Object all = userService.findTest();

        //vo.setUser(user);
        //vo.setUpdateTime(new Date());

        return Response.ok(all);
    }
}
