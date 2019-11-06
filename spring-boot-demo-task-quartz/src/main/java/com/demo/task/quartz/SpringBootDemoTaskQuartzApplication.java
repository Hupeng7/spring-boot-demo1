package com.demo.task.quartz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Leo
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.demo.task.quartz.mapper"})
public class SpringBootDemoTaskQuartzApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoTaskQuartzApplication.class, args);
    }

}
