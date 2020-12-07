package com.example.orderservice.feign.callback;

import com.example.orderservice.feign.UserService;
import org.springframework.stereotype.Component;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2020/10/23
 */
@Component
public class UserServiceCallback implements UserService {

    @Override
    public String index() {
        System.out.println("积分调用失败");
        return "积分调用服务失败";
    }
}
