package com.demo.cache.redis.service;

import com.demo.cache.redis.SpringBootDemoCacheRedisApplicationTests;
import com.demo.cache.redis.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName UserServiceTest
 * @Description TODO
 * @Author Leo
 * @Date 2020/1/2 18:03
 * @Version 1.0
 */
@Slf4j
public class UserServiceTest extends SpringBootDemoCacheRedisApplicationTests {

    @Autowired
    private UserService userService;

    /**
     * 获取两次，查看日志验证缓存
     */
    @Test
    public void getTwice() {
        // 模拟查询id 为1的用户
        User user1 = userService.get(1L);
        log.debug("【user1】 = {}", user1);
        // 再次查询
        User user2 = userService.get(1L);
        log.debug("【user2】 = {}", user2);
        // 查看日志，只打印一次日志，证明缓存生效
    }

    /**
     * 先存，再查询，查看日志验证缓存
     */
    @Test
    public void getAfterSave() {
        for (long i = 4L; i < 14; i++) {
            userService.saveOrUpdate(new User(i, "测试中文" + i));

            User user = userService.get(i);
            log.debug("【user】 = {}", user);
        }
        // 查看日志，只打印保存用户的日志，查看是未触发查询日志，因此缓存生效
    }

    /**
     * 测试删除，查看redis 是否存在缓存数据
     */
    @Test
    public void deleteUser() {
        // 查询一次 ，使redis 中存在缓存数据
        userService.get(1L);
        // 删除，查看redis是否存在缓存数据
        userService.delete(1L);
    }


}
