package com.demo.websocket.socketio.payload;

import lombok.Data;

/**
 * @ClassName GroupMessageRequest
 * @Description 群聊消息载荷
 * @Author Leo
 * @Date 2019/11/26 16:23
 * @Version 1.0
 */
@Data
public class GroupMessageRequest {

    /**
     * 消息发送方用户id
     */
    private String fromUid;

    /**
     * 群组id
     */
    private String groupId;

    /**
     * 消息内容
     */
    private String message;
}
