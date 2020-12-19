package com.zel.market.service;

import com.zel.market.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2020/3/20
 */
class MailServiceImplTest  extends BaseTest{

    @Autowired
    private MailServiceImpl mailService;

    @Test
    void sendSimpleMail() {
    }
}