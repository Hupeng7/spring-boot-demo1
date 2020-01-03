package com.demo.cache.ehcache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author Leo
 */
@SpringBootApplication
@EnableCaching
public class SpringBootDemoCacheEhcacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoCacheEhcacheApplication.class, args);
    }

}
