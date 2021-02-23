package com.demo.noifelse.demo3;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName PayService4
 * @Description 模板方法判断
 * @Author H
 * @Date 2021/2/9 9:45
 * @Version 1.0
 */
@Service
public class PayService4 implements ApplicationContextAware, InitializingBean {
    private ApplicationContext applicationContext;
    private List<IPay> payList = null;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (payList == null) {
            payList = new ArrayList<>();
            Map<String, IPay> beansOfType = applicationContext.getBeansOfType(IPay.class);
            beansOfType.forEach((key, value) -> payList.add(value));
        }
    }

    public void toPay(String code) {
        for (IPay iPay : payList) {
            if (iPay.support(code)) {
                iPay.pay();
            }
        }
    }

}
