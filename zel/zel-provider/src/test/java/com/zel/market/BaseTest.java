package com.zel.market;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BaseTest {

    @Autowired
    protected WebApplicationContext wac;
    protected MockMvc mockMvc;

    protected MockHttpSession session;
    protected Cookie[] cookies;
    protected HttpHeaders headers;

    /**
     * 这个方法在每个方法执行之前都会执行一遍
     */
    @Before
    public void setup() throws Exception {
        // 初始化MockMvc对象
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        session = new MockHttpSession();
        String token = "1234";
        //拦截器那边会判断用户是否登录，所以这里注入一个 token
        session.setAttribute("token", token);
    }
}
