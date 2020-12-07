package com.example.api.feign.factory;

import com.example.api.feign.MailService;
import com.example.api.feign.callback.MailServiceCallback;
import feign.hystrix.FallbackFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2020/10/24
 */
@Component
public class MailServiceCallbackFactory implements FallbackFactory<MailService> {

    @Autowired
    private MailServiceCallback mailServiceCallback;

    @Override
    public MailService create(Throwable cause) {
        //打印下异常
//        cause.printStackTrace();
        String error = "邮件调用服务失败 Error:" + cause.getMessage();
        System.out.println(error);
        return mailServiceCallback;
    }
}
