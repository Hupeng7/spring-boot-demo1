package com.demo.springbootdemowar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author Leo
 */
@SpringBootApplication
public class SpringBootDemoWarApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoWarApplication.class, args);
    }

    /**
     * 若需要打成war包 则需要写一个继承类
     * {@link SpringBootServletInitializer} 并重写{@link SpringBootServletInitializer#configure(SpringApplicationBuilder)}
     *
     * @param builder
     * @return
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SpringBootDemoWarApplication.class);
    }
}
