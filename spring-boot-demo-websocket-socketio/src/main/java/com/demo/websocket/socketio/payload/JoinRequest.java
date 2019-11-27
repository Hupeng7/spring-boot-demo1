package com.demo.websocket.socketio.payload;

import lombok.Data;

/**
 * @ClassName JoinRequest
 * @Description 加群载荷
 * @Author Leo
 * @Date 2019/11/26 16:26
 * @Version 1.0
 */
@Data
public class JoinRequest {

    /**
     * 用户id
     */
    private String userId;

    /**
     * 群名称
     */
    private String groupId;
}
