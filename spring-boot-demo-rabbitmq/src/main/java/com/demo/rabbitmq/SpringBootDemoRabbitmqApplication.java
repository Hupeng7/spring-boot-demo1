package com.demo.rabbitmq;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.demo.rabbitmq.demo.mapper")
@EnableScheduling
@EnableAsync
public class SpringBootDemoRabbitmqApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoRabbitmqApplication.class, args);
    }

}
