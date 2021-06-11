package com.xjtu.tdchaindemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
@SpringBootApplication
@RestController
@Configuration
@MapperScan("com.xjtu.tdchaindemo.dao")
public class TdchaindemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(TdchaindemoApplication.class, args);
    }

}
