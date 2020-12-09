package com.zel.market.service;

import com.zel.market.BaseTest;
import com.zel.market.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


class UserServiceTest extends BaseTest {

    @Autowired
    private UserService userService;

    @Test
    void transactTest() {
//        userService.createUser("abc", "123");
        User u = userService.findById("1");
        System.out.println(u);
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