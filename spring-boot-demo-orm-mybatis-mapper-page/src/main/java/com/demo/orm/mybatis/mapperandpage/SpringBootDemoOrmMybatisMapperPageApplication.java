package com.demo.orm.mybatis.mapperandpage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author Leo
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.demo.orm.mybatis.mapperandpage.mapper"})
public class SpringBootDemoOrmMybatisMapperPageApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoOrmMybatisMapperPageApplication.class, args);
    }

}
