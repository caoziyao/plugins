package com.example.api.feign.callback;

import com.example.api.feign.MailService;
import org.springframework.stereotype.Component;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2020/10/24
 */
@Component
public class MailServiceCallback  implements MailService {

    @Override
    public String send(String to, String subject, String content) {
        System.out.println("email 任务放入消费服务");
        return "email任务放入消费服务";
    }
}
