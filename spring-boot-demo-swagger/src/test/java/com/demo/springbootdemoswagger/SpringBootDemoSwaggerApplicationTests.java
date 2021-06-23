package com.demo.springbootdemoswagger;

import com.demo.swagger.service.HelloService;
import com.demo.swagger.service.HelloServiceProxy;
import com.demo.swagger.service.impl.HelloServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {HelloServiceImpl.class, HelloServiceProxy.class})
public class SpringBootDemoSwaggerApplicationTests {

    @Test
    public void test1() {
        // 目标对象
        HelloService target = new HelloServiceImpl();
        target.say();
        // 代理对象
        HelloServiceProxy proxy = new HelloServiceProxy(target);
        proxy.say();
    }

}
