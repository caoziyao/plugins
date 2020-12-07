package com.example.api.aopdemo.aspectj;

import org.springframework.stereotype.Component;

/**
 * Description:
 *
 * @author csy
 * @version 1.0.0
 * @since 2020/10/25
 */
@Component
public class UserASPDemo {

    public void say() {
        System.out.println("i am user");
    }

    public static void main(String[] args) {
        UserASPDemo demo = new UserASPDemo();
        demo.say();
    }
}
