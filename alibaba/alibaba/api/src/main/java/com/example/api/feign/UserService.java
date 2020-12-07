package com.example.api.feign;

import com.example.api.feign.callback.UserServiceCallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2020/10/23
 */
@FeignClient(value = "user-service", fallback = UserServiceCallback.class)
public interface UserService {

    @GetMapping("/user")
    String index();
}
