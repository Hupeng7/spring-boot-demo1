package com.example.springbootdemomytool.utils.enabledemo;

import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.util.List;

/**
 * @ClassName EchoBeanPostProcessor
 * @Description @EnableEcho注解 处理
 * @Author Leo
 * @Date 2020/7/6 16:50
 * @Version 1.0
 */
@Data
public class EchoBeanPostProcessor implements BeanPostProcessor {
    private List<String> packages;

    // 该方法在spring容器初始化前执行
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        for (String pack : packages) {
            if (bean.getClass().getName().startsWith(pack)) {
                System.out.println("Spring容器初始化之前 do something");
                System.out.println("echo bean: " + bean.getClass().getName());
            }
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        for (String pack : packages) {
            if (bean.getClass().getName().startsWith(pack)) {
                System.out.println("Spring容器初始化之后 do something");
                System.out.println("echo bean: " + bean.getClass().getName() + " 已获取");
            }
        }
        return bean;
    }
}
