package com.example.api.controller;

import com.example.api.aopdemo.aspectj.UserASPDemo;
import gua.commons.JsonTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description: index
 *
 * @author csy
 * @version 1.0.0
 * @since 2020/10/24
 */
@RestController
public class IndexController {


    @GetMapping(value = "/")
    public JSONDemo index() {


        String txt = "{\"abc\": 1}";
        JSONDemo demo = JsonTool.loads(txt, JSONDemo.class);
        return demo;
    }

    @GetMapping(value = "/2")
    @Transactional
    public JSONDemo index2() {

        String txt = "{\"abc\": 1}";
        JSONDemo demo = JsonTool.loads(txt, JSONDemo.class);
        return demo;
    }
}
