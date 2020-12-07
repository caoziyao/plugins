package com.example.api.controller;

import com.example.api.mq.ISendProvider;
import com.example.api.mq.dto.MqMailDTO;
import com.google.common.collect.ImmutableMap;
import gua.commons.JsonTool;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2020/10/24
 */
@RestController
@RequestMapping(value = "/api")
public class HelloController {

    @Resource
    private ISendProvider iSendProvider;

    @PostMapping("/mail/send/{to}")
    String send(@PathVariable(value = "to") String to, HttpServletRequest request) {
        String subject = request.getParameter("subject");
        String content = request.getParameter("content");
//        mailService.send(to, subject, content);


        MqMailDTO data = MqMailDTO.builder()
                .to(to)
                .subject(subject)
                .content(content)
                .build();

        iSendProvider.send(data);
        return "email ok";
    }
}
