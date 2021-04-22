package com.demo.orm.mybatis.mapper;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import com.demo.orm.mybatis.SpringBootDemoOrmMybatisApplicationTests;
import com.demo.orm.mybatis.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @ClassName UserMapperTest
 * @Description TODO
 * @Author Leo
 * @Date 2019/12/10 11:33
 * @Version 1.0
 */
@Slf4j
public class UserMapperTest extends SpringBootDemoOrmMybatisApplicationTests {

    @Autowired
    private UserMapper userMapper;

    /**
     * 测试查询所有
     */
    @Test
    public void selectAllUser() {
        List<User> userList = userMapper.selectAllUser();
        Assert.assertTrue(CollUtil.isNotEmpty(userList));
        log.debug("【userList】 = {}", userList);
    }

    /**
     * 测试根据主键查询单个
     */
    @Test
    public void selectUserById() {
        User user = userMapper.selectUserById(1L);
        Assert.assertNotNull(user);
        log.debug("【user】 = {}", user);
    }

    /**
     * 测试保存
     */
    @Test
    public void saveUser() {
        String salt = IdUtil.fastSimpleUUID();
        User user = User.builder().name("testSave5").password(SecureUtil.md5("123456" + salt)).salt(salt).email("testSave5@qq.com").phoneNumber("13500000005").status(1).lastLoginTime(new DateTime()).createTime(new DateTime()).lastUpdateTime(new DateTime()).build();
        int i = userMapper.saveUser(user);
        Assert.assertEquals(1, i);
    }

    /**
     * 测试根据主键删除
     */
    @Test
    public void deleteById() {
        int i = userMapper.deleteById(1L);
        Assert.assertEquals(1, i);
    }

    @Test
    public void batchUpdate(){


    }

}
