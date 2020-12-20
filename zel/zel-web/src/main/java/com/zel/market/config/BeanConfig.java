package com.zel.market.config;


import com.zel.dbmanager.entity.SSAccount;
//import com.zel.dbmanager.service.BookService;
//import com.zel.dbmanager.service.JobsService;
//import com.zel.dbmanager.service.SSAccountService;
//import com.zel.dbmanager.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring的@Bean注解用于告诉方法，产生一个Bean对象，然后这个Bean对象交给Spring管理。
 * 产生这个Bean对象的方法Spring只会调用一次，随后这个Spring将会将这个Bean对象放在自己的IOC容器中。
 */
@Configuration
public class BeanConfig {

    //@Bean(name = "userService")
    //public UserService userService() {
    //    return new UserService();
    //}
    //
    //@Bean(name = "bookService")
    //public BookService bookService() {
    //    return new BookService();
    //}
    //
    //@Bean(name = "ssAccountService")
    //public SSAccountService ssAccount() {
    //    return new SSAccountService();
    //}
    //
    //@Bean
    //public JobsService jobsService() {
    //    return new JobsService();
    //}
}
