package com.example.emailservice.controller;

import com.example.emailservice.annotation.KthLog;
import com.example.emailservice.mq.fanout.ProviderFanout;
import com.example.emailservice.mq.helloworld.ProviderHelloWorld;
import com.example.emailservice.mq.routing.ProviderRouting;
import com.example.emailservice.mq.topic.ProviderTopic;
import com.example.emailservice.mq.workqueue.ProviderWorkQueue;
import com.example.emailservice.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

/**
 * Description: 发送邮件
 *
 * @author csy
 * @version 1.0.0
 * @since 2020/10/24
 */
@RestController
@RequestMapping(value = "/mail")
public class MailController {

    @Autowired
    private MailService mailService;

    @Autowired
    private ProviderHelloWorld providerHelloWorld;

    @Autowired
    private ProviderWorkQueue providerWorkQueue;

    @Autowired
    private ProviderFanout providerFanout;

    @Autowired
    private ProviderRouting providerRouting;

    @Autowired
    private ProviderTopic providerTopic;

    @PostMapping(value = "/send/{to}")
    public String send(HttpServletRequest request, @PathVariable(value = "to") String to) {
        System.out.println("email service 收到任务");
        String subject = request.getParameter("subject");
        String content = request.getParameter("content");
        mailService.sendSimpleMail(to, subject, content);
        return "send mail ok";
    }

    @GetMapping(value = "/hello")
    public String hello() {
        providerHelloWorld.sendMessage();
        return "send hello ok";
    }

    @GetMapping(value = "/work")
    public String work() {
        providerWorkQueue.sendMessage();
        return "send work ok";
    }

    @GetMapping(value = "/fanout")
    public String fanout() {
        providerFanout.sendMessage();
        return "send fanout ok";
    }

    @KthLog("这里是日志内容")
    @GetMapping(value = "/route")
    public String route() {
        providerRouting.sendMessage();
        return "send route ok";
    }

    @GetMapping(value = "/topic")
    public String topic() {
        providerTopic.sendMessage();
        return "send topic ok";
    }
}
