package com.demo.websocket.task;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Dict;
import cn.hutool.json.JSONUtil;
import com.demo.websocket.common.WebSocketConsts;
import com.demo.websocket.model.Server;
import com.demo.websocket.payload.ServerVO;
import com.demo.websocket.util.ServerUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @ClassName ServerTask
 * @Description TODO
 * @Author Leo
 * @Date 2019/11/22 18:28
 * @Version 1.0
 */
@Component
@Slf4j
public class ServerTask {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Scheduled(cron = "0/2 * * * * ?")
    public void websocket() throws Exception {
        log.info("【推送消息】开始执行：{}", DateUtil.formatDateTime(new Date()));
        Server server = new Server();
        server.copyTo();
        ServerVO serverVO = ServerUtil.wrapServerVO(server);
        Dict dict = ServerUtil.wrapServerDict(serverVO);
        simpMessagingTemplate.convertAndSend(WebSocketConsts.PUSH_SERVER, JSONUtil.toJsonStr(dict));
        log.info("【推送消息】执行结束：{}", DateUtil.formatDateTime(new Date()));
    }

}
