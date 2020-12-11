package com.zel.dbmanager.service;


import com.zel.dbmanager.DBManager;
import com.zel.dbmanager.entity.User;
import com.zel.dbmanager.mapper.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
//import org.springframework.lang.NonNull;
//import org.springframework.stereotype.Service;

import java.util.List;
//import org.springframework.transaction.annotation.Transactional;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2020/10/25
 */

public class UserService extends DBManager {

    //    public static final UserService getInstance() {
//        return Env.getBean("cptaskcount-service");
//    }


    private static UserMapper userMapper = null;
    static {
        SqlSession session = sqlSessionFactory.openSession();
        userMapper = session.getMapper(UserMapper.class);
    }

    //@Transactional
    public void transactTest() {
        User user = userMapper.findByUsername("zhangsan");
        User user2 = userMapper.findByUsername("lisi");
        user.setAge(user.getAge() + 1);
        user2.setAge(user2.getAge() + 1);
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
     *
     * @param username
     * @param password
     */
    public User login(String username, String password) {
        return userMapper.findByUsernameAndPassword(username, password);
    }

    public User findById(String id) {
        if (StringUtils.isEmpty(id)) {
            return null;
        }
        return userMapper.findById(id);
    }

    //@Transactional
    public int updateUser(String id, String username) {

        if (StringUtils.isEmpty(id) || StringUtils.isEmpty(username)) {
            return 0;
        }
        return userMapper.updateUser(id, username);
    }

    public  void findAll() {
        List<User> all = userMapper.findAll();
        for (User user: all) {
            System.out.println(user.toString());
        }
    }
}
