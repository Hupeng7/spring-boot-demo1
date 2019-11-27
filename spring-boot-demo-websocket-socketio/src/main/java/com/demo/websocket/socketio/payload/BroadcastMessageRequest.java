package com.demo.websocket.socketio.payload;

import lombok.Data;

/**
 * @ClassName BroadcastMessageRequest
 * @Description 广播消息载荷
 * @Author Leo
 * @Date 2019/11/26 16:21
 * @Version 1.0
 */
@Data
public class BroadcastMessageRequest {

    /**
     * 消息内容
     */
    private String message;
}
