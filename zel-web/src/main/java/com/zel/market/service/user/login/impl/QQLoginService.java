package com.zel.market.service.user.login.impl;

import com.zel.market.controller.user.dto.LoginVO;
import com.zel.market.exception.BusinessException;
import com.zel.market.service.user.login.LoginService;
import com.zel.pojo.entity.User;
import org.springframework.stereotype.Service;

@Service
public class QQLoginService implements LoginService {
    @Override
    public User login(LoginVO loginVo) {
        return null;
    }

    @Override
    public void checkParam(LoginVO loginVo) throws BusinessException {

    }
}
