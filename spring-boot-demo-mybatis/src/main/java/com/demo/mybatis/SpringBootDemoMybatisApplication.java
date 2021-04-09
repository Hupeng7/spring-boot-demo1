package com.demo.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.demo.mybatis.mapper")
public class SpringBootDemoMybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoMybatisApplication.class, args);
    }

}
