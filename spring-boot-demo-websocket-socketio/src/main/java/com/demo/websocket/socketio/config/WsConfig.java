package com.demo.websocket.socketio.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @ClassName WsConfig
 * @Description WebSocket配置类
 * @Author Leo
 * @Date 2019/11/26 16:15
 * @Version 1.0
 */
@ConfigurationProperties(prefix = "ws.server")
@Data
public class WsConfig {
    /**
     * 端口号
     */
    private Integer port;

    /**
     * host
     */
    private String host;

}
