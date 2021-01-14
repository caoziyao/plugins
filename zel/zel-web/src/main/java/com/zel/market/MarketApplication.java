package com.zel.market;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
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
@MapperScan(value = "com.zel.dbmanager.mapper")
//@EnableAutoConfiguration(exclude = {RabbitAutoConfiguration.class})
//@ComponentScan(value ={"com.zel.commonutils.redis", "com.zel.market", "com.zel.mq"})
@ComponentScan(value ={"com.zel.commonutils.redis", "com.zel.market"})
public class MarketApplication {

    public static void main(String[] args) {
        //RabbitAutoConfiguration
        SpringApplication.run(MarketApplication.class, args);
    }

}

    var asm_code = `

        jump @function_define_end
@function_multiply

; 函数结束了，这时候 a1 存的就是 a1*a2 的值
        ; f1 寄存器里面存储的是函数调用前的地址，我们让 f1-2，然后把它取出来, 然后返回
        set2 a3 2
        subtract2 f1 a3 f1
        load_from_register2 f1 a2
        jump_from_register a2

        set2 a2 100

@function_define_end


; a1 是 300, a2 是 10
        set2 a1 300
        set2 a2 10

        set2 a3 14
        add2 pa a3 a3

        ; 这行指令占 3 字节
        save_from_register2 a3 f1

        ; 这行指令占 4 字节
        set2 a3 2

        ; 这行指令占 4 字节
        add2 f1 a3 f1

        ; 跳转到函数  这行指令占 3 字节
        jump @function_multiply

        halt
                `