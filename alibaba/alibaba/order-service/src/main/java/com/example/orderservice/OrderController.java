package com.example.orderservice;

import com.example.orderservice.feign.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2020/10/23
 */
@RestController
public class OrderController {

    @Autowired
    private UserService userService;

    @Autowired
    private RestTemplate restTemplate;

//    @Resource
//    private IMessageProvider iMessageProvider;

    @GetMapping(value = "/order")
    public String order() {
        String url = "http://user-service/user";
        String result = this.restTemplate.getForObject(url, String.class);
        return "order ok" + result;
    }

//    @Transa
//    @GlobalTransactional
    @GetMapping(value = "/order2")
    public String order2() {
        String rest = userService.index();

        String rest2 = userService.index();
        return "order 2 ok" + rest + "==" + rest2;
    }
}
