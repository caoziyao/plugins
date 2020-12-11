package com.zel.market.controller;

//import com.zel.dbmanager.service.UserService;

//import com.zel.dbmanager.service.BookService;
//import com.zel.dbmanager.service.UserService;
import com.zel.market.config.Env;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "index")
@RestController
public class IndexController {

    //@Autowired
    //private UserService userService;

    @ApiOperation(value="index", notes="index", produces="application/json")
    @GetMapping(value = "/")
    public String index() {
//        userService.test();
        // System.out.println(u);
        //UserService.findAll();
        //BookService.findAll();
        long  userId = Env.getContext().getUserId();
        String token = Env.getContext().getToken();

        return "response:" + userId + token;
    }
}
