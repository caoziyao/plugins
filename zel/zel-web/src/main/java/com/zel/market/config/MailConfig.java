package com.zel.market.config;

import com.zel.commonutils.crypto.AESEncrypt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2020/4/20
 */
@Configuration
public class MailConfig {
    @Value("${spring.mail.host}")
    private String host;

    @Value("${spring.mail.username}")
    private String user;

    @Value("${spring.mail.password}")
    private String password;

    @Value("${random.key}")
    private String KEY;

    @Bean
    public JavaMailSender mailSender(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        try {
            AESEncrypt des = new AESEncrypt(KEY);
            password = des.decrypt(password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        mailSender.setHost(host);
        // mailSender.setPort(port);
        mailSender.setUsername(user);
        mailSender.setPassword(password);

        return mailSender;
    }
}
