package com.demo.orm.mybatis.mapper;

import com.demo.orm.mybatis.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName UserMapper
 * @Description TODO
 * @Author Leo
 * @Date 2019/12/10 10:58
 * @Version 1.0
 */
@Mapper
@Component
public interface UserMapper {

    /**
     * 查询所有用户
     *
     * @return 用户列表
     */
    @Select("SELECT * FROM orm_user_mybatis")
    List<User> selectAllUser();

    /**
     * 根据id查询用户
     *
     * @param id 主键id
     * @return 当前id的用户，不存在则是{@code null}
     */
    @Select("SELECT * FROM orm_user_mybatis WHERE id = #{id}")
    User selectUserById(@Param("id") Long id);

    /**
     * 保存用户
     *
     * @param user 用户
     * @return 成功 -{@code 1} 失败 -{@code 0}
     */
    int saveUser(@Param("user") User user);

    /**
     * 删除用户
     *
     * @param id 主键id
     * @return 成功 -{@code 1} 失败 -{@code 0}
     */
    int deleteById(@Param("id") Long id);

}
