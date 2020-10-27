package com.example.springbootdemomytool.utils.annootationdemo.demo01;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @ClassName MoonUserConfiguration
 * @Description
 * @Author hup
 * @Date 2020/10/13 11:39
 * @Version 1.0
 */
@Configuration
@Import(MoonBookConfiguration.class)
public class MoonUserConfiguration {


//    @Bean
//    public UserService userService(BookService bookService){
//        //return new BookServiceImpl(bookService);
//        return null;
//    }
}
