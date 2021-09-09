package com.example.springbootdemomytool.utils.strategyandreflectdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @ClassName GetPointAdvertisePageReflectFactory
 * @Description
 * @Author H
 * @Date 2021/9/9 17:36
 * @Version 1.0
 */
@Component
public class GetPointAdvertisePageReflectFactory {
    @Autowired
    private ApplicationContext applicationContext;
    private static final Logger LOGGER = LoggerFactory.getLogger(GetPointAdvertisePageReflectFactory.class);

    public GetPointAdvertisePageStrategy getPointAdvertisePageStrategy(String filePathName, String key) {
        GetPointAdvertisePageStrategy getPointAdvertisePageStrategy = null;
        String classPath = null;

        classPath = PropertyUtil.get(filePathName, key);
        if (classPath == null) {
            return null;
        }

        try {
            // 通过反射创建对象
            Class<?> handler = Class.forName(classPath);
            // 这里必须强行纳入spring管理 否则在得到的具体实现类中 如果有通过@Autowired注入的bean 将会报注入失败
            getPointAdvertisePageStrategy = (GetPointAdvertisePageStrategy) applicationContext.getAutowireCapableBeanFactory().createBean(handler, AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE, true);
        } catch (ClassNotFoundException e) {
            LOGGER.error("Failed to reflect the corresponding object through the specified path,filePath:{},key:{}", filePathName, key);
            return null;
        }

        return getPointAdvertisePageStrategy;
    }


}
