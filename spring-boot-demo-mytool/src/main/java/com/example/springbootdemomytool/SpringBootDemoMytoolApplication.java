package com.example.springbootdemomytool;

import com.example.springbootdemomytool.utils.enabledemo.EnableEcho;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author leo
 */
@EnableRetry
@SpringBootApplication
@EnableAsync           // 开启支持异步
@EnableEcho(packages = {"com.example.springbootdemomytool.utils.enabledemo.vo", "com.example.springbootdemomytool.utils.enabledemo.dto"})
@EnableEncryptableProperties
public class SpringBootDemoMytoolApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoMytoolApplication.class, args);
    }

}
