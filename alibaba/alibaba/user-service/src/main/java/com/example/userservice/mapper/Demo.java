package com.example.userservice.mapper;

import org.springframework.stereotype.Component;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2020/11/22
 */
@Component
public class Demo implements IDemo {

    public void say() {
        System.out.println("demo");
    }
}
