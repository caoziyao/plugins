package com.zel.market.controller;

import com.zel.dbmanager.entity.User;
import com.zel.market.common.AppContext;
import com.zel.market.common.Env;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2020/12/20
 */
@Controller
public class IndexController {

    @GetMapping(value = "/")
    public String index(Model model) {
        AppContext context = Env.getContext();
        User user = context.getUser();
        model.addAttribute("user", user);
        return "index";
    }

    @GetMapping(value = "/article")
    public String article() {
        return "article";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }
}
