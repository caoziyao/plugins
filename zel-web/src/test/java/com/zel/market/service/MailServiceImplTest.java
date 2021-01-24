package com.zel.market.service;

import com.zel.market.BaseTest;
import com.zel.market.service.mail.MailServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

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