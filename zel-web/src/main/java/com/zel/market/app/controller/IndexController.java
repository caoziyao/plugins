package com.zel.market.app.controller;

import com.zel.pojo.entity.User;
import com.zel.market.common.AppContext;
import com.zel.market.common.Env;
import com.zel.market.app.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2020/12/20
 */
@Controller
public class IndexController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/")
    public String index(Model model, HttpServletRequest request) {
        AppContext context = Env.getContext();
        User user = context.getUser();
        long online = userService.onlineUserNumToday();
        model.addAttribute("user", user);
        model.addAttribute("onlineUserNum", online);

        return "index";
    }

    @GetMapping(value = "/article")
    public String article() {
        return "article";
    }

    @GetMapping(value = "/enote")
    public String enote() {
        return "enote";
    }

    @GetMapping(value = "/canvas")
    public String canvas() {
        return "canvas";
    }


    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }
}
