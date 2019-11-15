package com.demo.websocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author Leo
 */
@SpringBootApplication
@EnableScheduling
public class SpringBootDemoWebsocketApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoWebsocketApplication.class, args);
    }

}
