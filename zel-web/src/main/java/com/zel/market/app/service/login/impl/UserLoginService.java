package com.zel.market.app.service.login.impl;

import com.zel.commonutils.redis.RedisUtils;
import com.zel.dbmanager.mapper.UserMapper;
import com.zel.market.common.enumcom.EResponseCode;
import com.zel.market.app.controller.user.dto.LoginVO;
import com.zel.market.exception.BusinessException;
import com.zel.market.app.service.login.LoginService;
import com.zel.pojo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 默认注册方式：手机号/邮箱+密码注册 、一键登录
 */
@Service
public class UserLoginService implements LoginService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public User login(LoginVO loginVo) {
        String username = loginVo.getUsername();
        String password = loginVo.getPassword();
        User user = userMapper.findByUsernameAndPassword(username, password);
        if (user == null) {
            throw new BusinessException(EResponseCode.NOT_REGISTER);
        }
        return user;
    }

    @Override
    public void checkParam(LoginVO loginVo) throws BusinessException {

    }

    /**
     * 注册
     * @param loginVo
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public User register(LoginVO loginVo) {
        return null;
    }
}
