package com.demo.dynamic.datasource.controller;

import com.demo.dynamic.datasource.mapper.UserMapper;
import com.demo.dynamic.datasource.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author Leo
 * @Date 2019/10/23 16:52
 * @Version 1.0
 */

@RestController
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserController {

    private final UserMapper userMapper;


    @GetMapping("/user")
    public List<User> getUserList(){
        return userMapper.selectAll();
    }
}
