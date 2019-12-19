package com.demo.orm.mybatis.plus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.orm.mybatis.plus.entity.User;
import com.demo.orm.mybatis.plus.mapper.UserMapper;
import com.demo.orm.mybatis.plus.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author Leo
 * @Date 2019/12/18 17:45
 * @Version 1.0
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
