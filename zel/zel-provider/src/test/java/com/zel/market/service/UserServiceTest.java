package com.zel.market.service;

import com.zel.dbmanager.service.UserService;
import com.zel.market.BaseTest;
import com.zel.dbmanager.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


class UserServiceTest extends BaseTest {

    @Autowired
    private UserService userService;

    @Test
    void transactTest() {
//        userService.createUser("abc", "123");
//        User u = userService.findById("1");
//        System.out.println(u);
    }

    @Test
    void createUser() {
    }

    @Test
    void login() {
    }

    @Test
    void findById() {
    }
}