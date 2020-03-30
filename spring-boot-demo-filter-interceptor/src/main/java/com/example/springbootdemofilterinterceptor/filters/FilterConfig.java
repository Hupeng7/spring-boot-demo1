package com.example.springbootdemofilterinterceptor.filters;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName FilterConfig
 * @Description TODO
 * @Author Leo
 * @Date 2020/3/30 11:49
 * @Version 1.0
 */
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean registrationBean(){

        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new LogCostFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setName("LogCostFilter");
        registrationBean.setOrder(1);
        return registrationBean;
    }


}
