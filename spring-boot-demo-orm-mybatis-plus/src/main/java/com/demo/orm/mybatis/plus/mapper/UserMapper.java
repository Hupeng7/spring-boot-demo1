package com.demo.orm.mybatis.plus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.orm.mybatis.plus.entity.User;
import org.springframework.stereotype.Component;

/**
 * @ClassName UserMapper
 * @Description TODO
 * @Author Leo
 * @Date 2019/12/18 17:36
 * @Version 1.0
 */
@Component
public interface UserMapper extends BaseMapper<User> {
}
