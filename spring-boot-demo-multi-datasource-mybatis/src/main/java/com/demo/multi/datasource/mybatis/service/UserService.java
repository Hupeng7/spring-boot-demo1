package com.demo.multi.datasource.mybatis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.multi.datasource.mybatis.model.User;

/**
 * @ClassName UserService
 * @Description TODO
 * @Author Leo
 * @Date 2019/10/25 16:08
 * @Version 1.0
 */
public interface UserService extends IService<User> {

    /**
     * 添加User
     *
     * @param user
     */
    void addUser(User user);
}
