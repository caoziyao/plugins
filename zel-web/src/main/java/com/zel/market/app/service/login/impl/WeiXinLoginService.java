package com.zel.market.app.service.login.impl;

import com.zel.market.app.controller.user.dto.LoginVO;
import com.zel.market.exception.BusinessException;
import com.zel.market.app.service.user.UserService;
import com.zel.market.app.service.login.LoginService;
import com.zel.market.app.service.login.db.UserThird;
import com.zel.pojo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeiXinLoginService implements LoginService {

    @Autowired
    private UserService userService;

    @Override
    public User login(LoginVO loginVo) {
        String openId = loginVo.getOpenId();
        User user = userService.findByOpenId(openId);
        // 写入第三方用户表
        UserThird userThird = new UserThird();
        userThird.setUserId(user.getId());
        userThird.setAppName("weixin");

        userService.insert(userThird);
        return null;
    }

    @Override
    public void checkParam(LoginVO loginVo) throws BusinessException {

    }
}