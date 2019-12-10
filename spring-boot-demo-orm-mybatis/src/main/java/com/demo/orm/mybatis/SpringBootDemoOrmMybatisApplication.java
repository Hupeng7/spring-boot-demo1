package com.demo.orm.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Leo
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.demo.orm.mybatis.mapper"})
public class SpringBootDemoOrmMybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoOrmMybatisApplication.class, args);
    }

}
