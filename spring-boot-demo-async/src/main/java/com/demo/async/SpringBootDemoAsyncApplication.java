package com.demo.async;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author Leo
 */
@SpringBootApplication
@EnableAsync
public class SpringBootDemoAsyncApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoAsyncApplication.class, args);
    }

}
