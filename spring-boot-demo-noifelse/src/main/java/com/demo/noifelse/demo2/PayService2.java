package com.demo.noifelse.demo2;

import com.demo.noifelse.demo1.IPay;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName PayService2
 * @Description  使用注解
 * @Author H
 * @Date 2021/2/8 18:44
 * @Version 1.0
 */
@Service
public class PayService2 implements ApplicationListener<ContextRefreshedEvent> {
    private static Map<String, IPay> payMap = null;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        ApplicationContext applicationContext = contextRefreshedEvent.getApplicationContext();
        Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(PayCode.class);

        if (beansWithAnnotation != null) {
            payMap = new HashMap<>();
            beansWithAnnotation.forEach((key, value) -> {
                String bizType = value.getClass().getAnnotation(PayCode.class).value();
                payMap.put(bizType, (IPay) value);
            });
        }
    }

    public void pay(String code) {
        payMap.get(code).pay();
    }
}
