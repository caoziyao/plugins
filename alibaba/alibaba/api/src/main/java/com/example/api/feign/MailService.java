package com.example.api.feign;

import com.example.api.feign.callback.MailServiceCallback;
import com.example.api.feign.factory.MailServiceCallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2020/10/24
 * <p>
 * fallback = MailServiceCallback.class,
 */
@FeignClient(value = "mail-service", fallbackFactory = MailServiceCallbackFactory.class)
public interface MailService {

    @PostMapping(value = "/mail/send/{to}")
    String send(@PathVariable(value = "to") String to, @RequestParam("subject") String subject, @RequestParam("content") String content);
}
