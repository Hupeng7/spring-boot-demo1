package com.demo.encryptionanddecryption.process;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @ClassName conf
 * @Description
 * @Author H
 * @Date 2021/9/15 14:14
 * @Version 1.0
 */
@Configuration
public class conf {

    @Bean
    public EnvCopy1 envCopy1(final ConfigurableEnvironment environment) {
        return new EnvCopy1(environment);
    }
}
