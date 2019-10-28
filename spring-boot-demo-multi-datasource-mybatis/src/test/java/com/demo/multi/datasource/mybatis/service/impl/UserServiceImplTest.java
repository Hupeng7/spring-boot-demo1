package com.demo.multi.datasource.mybatis.service.impl;


import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.multi.datasource.mybatis.SpringBootDemoMultiDatasourceMybatisApplicationTests;
import com.demo.multi.datasource.mybatis.model.User;
import com.demo.multi.datasource.mybatis.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @ClassName UserServiceImplTest
 * @Description TODO
 * @Author Leo
 * @Date 2019/10/25 16:12
 * @Version 1.0
 */
@Slf4j
public class UserServiceImplTest extends SpringBootDemoMultiDatasourceMybatisApplicationTests {
    @Autowired
    private UserService userService;

    @Test
    public void addUser() {
        User userMaster = User.builder().name("主库添加").age(20).build();
        userService.addUser(userMaster);

        User userSlave = User.builder().name("从库添加").age(20).build();
        userService.save(userSlave);
    }

    @Test
    public void testListUser() {
        List<User> list = userService.list(new QueryWrapper<>());
        log.info("【list 】= {}", JSONUtil.toJsonStr(list));
    }
}
