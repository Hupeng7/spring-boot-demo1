package com.example.springbootdemomytool.utils.httpclientdemo.resttemplate1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName ApiConfig
 * @Description
 * @Author H
 * @Date 2021/9/23 15:00
 * @Version 1.0
 */
@Configuration
public class ApiConfig {

    @Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
        return new RestTemplate(factory);
    }

    @Bean
    public ClientHttpRequestFactory simpleClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setReadTimeout(5000); // 单位为ms
        factory.setConnectTimeout(5000); // 单位为ms
        return factory;
    }

}
