package com.example.springbootdemodbserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@EnableRetry
@SpringBootApplication
public class SpringBootDemoDbserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoDbserverApplication.class, args);
    }

}
