package com.demo.logback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author Leo
 */
@SpringBootApplication
@Slf4j
public class SpringBootDemoLogbackApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringBootDemoLogbackApplication.class, args);
        int length = context.getBeanDefinitionNames().length;
        log.trace("Spring boot 启动初始化了 {} 个Bean", length);
        log.debug("Spring boot 启动初始化了 {} 个Bean", length);
        log.info("Spring boot 启动初始化了 {} 个Bean", length);
        log.warn("Spring boot 启动初始化了 {} 个Bean", length);
        log.error("Spring boot 启动初始化了 {} 个Bean", length);
        try {
            int i = 0;
            int j = 1 / i;
        } catch (Exception e) {
            log.error("【SpringBootDemoLogbackApplication】启动异常：", e);
        }
    }

}
