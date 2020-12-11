package com.demo.orm.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Leo
 */
@SpringBootApplication
//@EntityScan("com.demo.orm.jpa.entity")
//@EnableJpaRepositories(basePackages = "com.demo.orm.jpa.repository")
public class SpringBootDemoOrmJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoOrmJpaApplication.class, args);
    }

}
