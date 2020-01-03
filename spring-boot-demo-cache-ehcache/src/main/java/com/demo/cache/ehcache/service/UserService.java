package com.demo.cache.ehcache.service;

import com.demo.cache.ehcache.entity.User;

/**
 * @ClassName UserService
 * @Description TODO
 * @Author Leo
 * @Date 2020/1/2 16:01
 * @Version 1.0
 */
public interface UserService {
    /**
     * 保存或修改用户
     *
     * @param user 用户对象
     * @return 操作结果
     */
    User saveOrUpdate(User user);

    /**
     * 获取用户
     *
     * @param id key值
     * @return 返回结果
     */
    User get(Long id);

    /**
     * 删除
     *
     * @param id key值
     */
    void delete(Long id);


}
