package com.example.springbootdemomytool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@EnableRetry
@SpringBootApplication
public class SpringBootDemoMytoolApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoMytoolApplication.class, args);
    }

}
