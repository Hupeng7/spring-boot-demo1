package com.demo.websocket.socketio.config;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName DbTemplate
 * @Description 模拟数据库
 * @Author Leo
 * @Date 2019/11/26 17:39
 * @Version 1.0
 */
@Component
public class DbTemplate {

    public static final ConcurrentHashMap<String, UUID> DB = new ConcurrentHashMap<>();

    /**
     * 获取所有SessionId
     *
     * @return
     */
    public List<UUID> findAll() {
        return CollUtil.newArrayList(DB.values());
    }

    /**
     * 根据userId查询SessionId
     *
     * @param userId
     * @return
     */
    public Optional<UUID> findByUserId(String userId) {
        return Optional.ofNullable(DB.get(userId));
    }

    /**
     * 保存/更新 user_id<->session_id的关系
     *
     * @param userId
     * @param sessionId
     */
    public void save(String userId, UUID sessionId) {
        DB.put(userId, sessionId);
    }

    /**
     * 删除 user_id <-> session_id 的关系
     *
     * @param userId
     */
    public void deleteByUserId(String userId) {
        DB.remove(userId);
    }

}
