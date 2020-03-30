package com.example.springbootdemofilterinterceptor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan("com.example.springbootdemofilterinterceptor")
public class SpringBootDemoFilterInterceptorApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoFilterInterceptorApplication.class, args);
    }

}
