package com.demo.websocket.controller;

import cn.hutool.core.lang.Dict;
import com.demo.websocket.model.Server;
import com.demo.websocket.payload.ServerVO;
import com.demo.websocket.util.ServerUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ServerController
 * @Description TODO
 * @Author Leo
 * @Date 2019/11/15 16:50
 * @Version 1.0
 */
@RestController
@RequestMapping("/server")
public class ServerController {

    @GetMapping
    public Dict serverInfo() throws Exception {
        Server server = new Server();
        server.copyTo();
        ServerVO serverVO = ServerUtil.wrapServerVO(server);
        return ServerUtil.wrapServerDict(serverVO);
    }
}
