package com.example.gateway.controller;

import org.springframework.web.bind.annotation.*;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2020/10/24
 */
@RestController
@RequestMapping(value = "/gateway")
public class HelloController {

    /**
     * 拿不到 body 参数？？？
     *
     * @param to
     * @return
     */
    @PostMapping("/mail/send/{to}")
    String send(@PathVariable(value = "to") String to) {
        return "ok";
    }
}
