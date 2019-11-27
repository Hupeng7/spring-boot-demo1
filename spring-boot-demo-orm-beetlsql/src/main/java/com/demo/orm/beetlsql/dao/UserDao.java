package com.demo.orm.beetlsql.dao;

import com.demo.orm.beetlsql.entity.User;
import org.beetl.sql.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

/**
 * @ClassName UserDao
 * @Description TODO
 * @Author Leo
 * @Date 2019/11/27 14:33
 * @Version 1.0
 */
@Component
public interface UserDao extends BaseMapper<User> {
}
