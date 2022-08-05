package com.demo.controller;

import com.demo.service.TokenUtilService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName TokenController
 * @Description
 * @Author H
 * @Date 2022/8/4 17:29
 * @Version 1.0
 */
@Slf4j
@RestController
public class TokenController {

    @Autowired
    private TokenUtilService tokenUtilService;

    /**
     * 获取 Token接口
     *
     * @return
     */
    @GetMapping("/token")
    public String getToken() {
        /**
         * 获取用户信息（这里使用功能模拟数据）
         * 注： 这里存储该内容只是举例，起作用为辅助验证，使其验证逻辑更安全，如这里存储用户信息，其目的为：
         *  -1)、使用 "token"验证 Redis中是否存在对应的 Key
         *  -2)、使用"用户信息" 验证 Redis的 Value 是否匹配
         */
        String userInfo = "name,age something...";
        // 获取Token 字符串 并返回
        return tokenUtilService.generateToken(userInfo);
    }

    @PostMapping("/test")
    public String test(@RequestHeader(value = "token") String token) {
        // 获取用户信息(这里使用模拟数据)
        String userInfo = "name,age something...";
        // 根据Token 和与用户相关的信息到Redis 验证是否存在对应的信息
        boolean result = tokenUtilService.validToken(token, userInfo);
        // 根据验证结果响应不同信息
        return result ? "正常调用" : "重复调用";

    }


}
