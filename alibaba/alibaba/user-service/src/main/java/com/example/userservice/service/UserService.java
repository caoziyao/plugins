package com.example.userservice.service;

import com.example.userservice.entity.User;
import com.example.userservice.mapper.UserMapper;
import gua.commons.DigestTool;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2020/10/25
 */
@Service
public class UserService {

    @Value("${random.salt}")
    private String salt;

    @Autowired
    private UserMapper userMapper;

    @Transactional
    public void transactTest() {
        User user = userMapper.findByUsername("zhangsan");
        User user2 = userMapper.findByUsername("lisi");
        user.setAge(user.getAge()+1);
        user2.setAge(user2.getAge()+1);
        userMapper.updateById(user);
        userMapper.updateById(user2);
    }

    public User createUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        userMapper.insert(user);
        return user;
    }

    /**
     * 校验登录
     * @param username
     * @param password
     */
    public User login(@NonNull String username, @NonNull String password) {
        return userMapper.findByUsernameAndPassword(username, password);
    }

    public User findById(String id) {
        return userMapper.findById(id);
    }

    /**
     * 校验密码是否正确
     * @return
     */
    public boolean passwordMatch(String password, String plainPassword) {
        if (StringUtils.isBlank(password) || StringUtils.isBlank(plainPassword)) {
            return false;
        }
        return plainPassword.equals(DigestTool.sha1(password, salt));
    }
}
