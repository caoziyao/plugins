package com.zel.market.controller.article;

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
public class ArticleIndexController {


    @GetMapping(value = "/article")
    public String index() {
        return "index";
    }
}
