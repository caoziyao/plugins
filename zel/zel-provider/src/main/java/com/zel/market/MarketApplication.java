package com.zel.market;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
public class MarketApplication {

    public static void main(String[] args) {
        com.zel.dbmanager.DBManager.test();
        SpringApplication.run(MarketApplication.class, args);
    }

}
