package com.demo.websocket.socketio.init;

import com.corundumstudio.socketio.SocketIOServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @ClassName ServerRunner
 * @Description websocket 服务器启动
 * @Author Leo
 * @Date 2019/11/26 16:29
 * @Version 1.0
 */
@Component
@Slf4j
public class ServerRunner implements CommandLineRunner {
    @Autowired
    private SocketIOServer server;

    @Override
    public void run(String... args) throws Exception {
        server.start();
        log.info("websocket 服务器启动成功。。。");
    }
}
