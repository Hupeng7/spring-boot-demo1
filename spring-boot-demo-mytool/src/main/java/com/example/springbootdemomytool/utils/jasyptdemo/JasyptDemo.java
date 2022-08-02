package com.example.springbootdemomytool.utils.jasyptdemo;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName JasyptDemo
 * @Description 将配置明文进行加密
 * @Author H
 * @Date 2022/7/7 11:24
 * @Version 1.0
 */
@RestController
public class JasyptDemo {

    @Autowired
    private StringEncryptor encryptor;

    @GetMapping("/jasypt")
    public void testJasypt() {
        String password = "123456";
        String encryptPwd = encryptor.encrypt(password);
        System.out.println("加密: " + encryptPwd);
        System.out.println("解密: " + encryptor.decrypt(encryptPwd));

        System.out.println(encryptor.encrypt("张三"));
        System.out.println(encryptor.encrypt("222333"));
    }

    @Value("${jasypt.diy.name}")
    private String name;
    @Value("${jasypt.diy.num}")
    private String num;

    @GetMapping("/jasypt2")
    public void testJasypt2() {
        System.out.println("name: " + name);
        System.out.println("num: " + num);
    }


}