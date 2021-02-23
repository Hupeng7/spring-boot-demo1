package com.demo.noifelse.demo5;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName PayHandlerChain
 * @Description 责任链模式
 * @Author H
 * @Date 2021/2/9 14:10
 * @Version 1.0
 */
@Service
public class PayHandlerChain implements ApplicationContextAware, InitializingBean {
    private ApplicationContext applicationContext;
    private PayHandler headler;

    public void handlerPay(String code) {
        headler.pay(code);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String, PayHandler> beansOfTypeMap = applicationContext.getBeansOfType(PayHandler.class);
        if (beansOfTypeMap == null || beansOfTypeMap.size() == 0) {
            return;
        }

        List<PayHandler> handlers = beansOfTypeMap.values().stream().collect(Collectors.toList());
        for (int i = 0; i < handlers.size(); i++) {
            PayHandler payHandler = handlers.get(i);
            if (i != handlers.size() - 1) {
                payHandler.setNext(handlers.get(i + 1));
            }
        }
        headler = handlers.get(0);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
