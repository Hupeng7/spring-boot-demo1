package com.demo.multi.datasource.mybatis.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.multi.datasource.mybatis.mapper.UserMapper;
import com.demo.multi.datasource.mybatis.model.User;
import com.demo.multi.datasource.mybatis.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author Leo
 * @Date 2019/10/25 16:06
 * @Version 1.0
 */
@Service
@DS("slave")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @DS("master")
    @Override
    public void addUser(User user) {
        baseMapper.insert(user);
    }
}
