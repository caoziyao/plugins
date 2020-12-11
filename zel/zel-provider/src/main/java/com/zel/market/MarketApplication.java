package com.zel.market;

import com.zel.dbmanager.DBManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
public class MarketApplication {

    public static void main(String[] args) {
        DBManager.test();
        SpringApplication.run(MarketApplication.class, args);
    }

}
