package com.example.springbootdemomytool.utils.annootationdemo.demo01;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName MoonBookConfiguration
 * @Description
 * @Author hup
 * @Date 2020/10/13 11:27
 * @Version 1.0
 */
@Configuration
public class MoonBookConfiguration {

    // 如果一个bean依赖另一个bean，则直接调用对应JavaConfig类中依赖bean的创建方法即可
    // 这里直接调用dependencyService()
    @Bean
    public BookService bookService(){
        return new BookServiceImpl(dependencyService());
    }

//    @Bean
//    public OtherService otherService(){
//        return new OtherServiceImpl(dependencyService());
//    }

    @Bean
    public DependencyService dependencyService() {
        return new DependencyServiceImpl();
    }

//    @Bean
//    public BookService bookService1(){
//        return new BookServiceImpl();
//    }
}
