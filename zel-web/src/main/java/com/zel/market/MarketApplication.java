package com.zel.market;


//import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync
@EnableScheduling
@SpringBootApplication
//@MapperScan(value = "com.zel.dbmanager.mapper")
//@EnableAutoConfiguration(exclude = {RabbitAutoConfiguration.class})
//@ComponentScan(value ={"com.zel.commonutils.redis", "com.zel.market", "com.zel.mq"})
@ComponentScan(value ={"com.zel.redis", "com.zel.market", "com.zel.pay", "com.zel.dbjooq"})
public class MarketApplication {

    public static void main(String[] args) {
        //RabbitAutoConfiguration
        SpringApplication.run(MarketApplication.class, args);
    }

}
