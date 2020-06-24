package com.example.springbootdemomytool.utils.resttemplate;

import com.example.springbootdemomytool.beans.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName Hello
 * @Description TODO
 * @Author Leo
 * @Date 2020/6/23 16:59
 * @Version 1.0
 */
@Configuration
public class Hello {

    @Bean(name = "testHello1")
    public User testHello() {
        User user = new User();
        user.setAccount("zhangsan");
        user.setId(10L);
        return user;
    }

    @Bean(name = "userHello")
    public User testHello2() {
        User user = new User();
        user.setAccount("lisi");
        user.setId(22L);
        return user;
    }
}
