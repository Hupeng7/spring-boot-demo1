package com.demo.websocket.socketio.payload;

import lombok.Data;

/**
 * @ClassName SingleMessageRequest
 * @Description 私聊消息载荷
 * @Author Leo
 * @Date 2019/11/26 16:27
 * @Version 1.0
 */
@Data
public class SingleMessageRequest {
    /**
     * 消息发送方用户id
     */
    private String fromUid;

    /**
     * 消息接收方用户id
     */
    private String toUid;

    /**
     * 消息内容
     */
    private String message;
}
