package com.zel.market.controller;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2020/12/20
 */
@Controller
public class LeIndexController {

    @GetMapping(value = "/")
    public String index() {
        return "index";
    }

    @GetMapping(value = "/article")
    public String article() {
        return "article";
    }
}
