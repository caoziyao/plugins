package com.zel.market.service.login;

import com.zel.market.controller.user.dto.LoginVO;
import com.zel.market.exception.BusinessException;
import com.zel.pojo.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {

    /**
     * 登录
     */
    User login(LoginVO loginVo);

    void checkParam(LoginVO loginVo) throws BusinessException;
}
