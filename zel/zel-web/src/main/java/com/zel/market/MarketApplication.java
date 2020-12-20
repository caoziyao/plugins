package com.zel.market;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.zel.dbmanager.mapper")
public class MarketApplication {

    public static void main(String[] args) {

        SpringApplication.run(MarketApplication.class, args);
    }

}
