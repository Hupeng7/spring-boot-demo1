package com.demo.websocket.socketio.config;

/**
 * @ClassName Event
 * @Description 事件常量
 * @Author Leo
 * @Date 2019/11/26 16:17
 * @Version 1.0
 */
public interface Event {

    /**
     * 聊天事件
     */
    String CHAT = "chat";

    /**
     * 广播消息
     */
    String BRAODCAST = "broadcast";

    /**
     * 群聊
     */
    String GROUP = "group";

    /**
     * 加入群聊
     */
    String JOIN = "join";


}
