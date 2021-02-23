package com.demo.noifelse.demo2;

import com.demo.noifelse.demo1.IPay;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @ClassName PayService3
 * @Description 动态拼接名称
 * @Author H
 * @Date 2021/2/8 18:53
 * @Version 1.0
 */
public class PayService3 implements ApplicationContextAware {
    private ApplicationContext applicationContext;
    private static final String SUFFIX = "Pay";

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public void toPay(String payCode) {
        ((IPay) applicationContext.getBean(getBeanName(payCode))).pay();
    }

    private String getBeanName(String payCode) {
        return payCode + SUFFIX;
    }

}
