package com.demo.orm.mybatis.mapperandpage.mapper;

import com.demo.orm.mybatis.mapperandpage.entity.User;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;


/**
 * @ClassName UserMapper
 * @Description TODO
 * @Author Leo
 * @Date 2019/12/10 16:27
 * @Version 1.0
 */
@Component
public interface UserMapper extends Mapper<User>, MySqlMapper<User> {
}
