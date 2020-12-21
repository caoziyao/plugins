package com.zel.market;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync
@EnableScheduling
@SpringBootApplication
@MapperScan(value = "com.zel.dbmanager.mapper")
@ComponentScan(value ={"com.zel.commonutils.redis", "com.zel.market"})
public class MarketApplication {

    public static void main(String[] args) {

        SpringApplication.run(MarketApplication.class, args);
    }

}
