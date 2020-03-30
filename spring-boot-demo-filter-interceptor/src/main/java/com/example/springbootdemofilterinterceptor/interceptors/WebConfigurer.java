package com.example.springbootdemofilterinterceptor.interceptors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName InterceptorConfig
 * @Description TODO
 * @Author Leo
 * @Date 2020/3/30 12:03
 * @Version 1.0
 */
@Configuration  // 一定要加@Configuration 这个注解，在启动的时候在会被加载。
public class WebConfigurer implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 拦截所有请求，通过判断是否有@LoginRequired注解，决定是否需要登录
        registry.addInterceptor(LoginInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(AuthorityInterceptor()).addPathPatterns("/**");
    }

    @Bean
    public LoginInterceptor LoginInterceptor() {
        return new LoginInterceptor();
    }

    @Bean
    public AuthorityInterceptor AuthorityInterceptor() {
        return new AuthorityInterceptor();
    }


}
