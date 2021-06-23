package com.demo.websocket.demo01;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @ClassName WebSocketConfig
 * @Description 开启websocket支持配置类
 * @Author H
 * @Date 2021/6/1 18:05
 * @Version 1.0
 */
@Configuration
public class WebSocketNewConfig {

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

}
