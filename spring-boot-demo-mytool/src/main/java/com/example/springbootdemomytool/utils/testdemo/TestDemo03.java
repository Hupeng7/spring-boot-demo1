package com.example.springbootdemomytool.utils.testdemo;

import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @ClassName TestDemo03
 * @Description
 * @Author H
 * @Date 2021/1/19 18:37
 * @Version 1.0
 */
public class TestDemo03 implements InitializingBean {
    // 初始化
    @Override
    public void afterPropertiesSet() throws Exception {
        // do some initialization work
    }

    // 其他方法初始化
    @PostConstruct
    public void init() {
        // also can do some initialization work
    }

    // 销毁
    @PreDestroy
    public void cleanup() {
        // do some destruction work (like releasing pooled connections)
    }
}
