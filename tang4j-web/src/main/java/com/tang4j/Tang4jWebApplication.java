package com.tang4j;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = {"com.tang4j.mapper"})
@SpringBootApplication(scanBasePackages = "com.tang4j")
public class Tang4jWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(Tang4jWebApplication.class, args);
    }

}
