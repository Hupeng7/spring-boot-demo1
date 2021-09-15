package com.demo.encryptionanddecryption.controller;

import com.alibaba.fastjson.JSON;
import com.demo.encryptionanddecryption.annotation.EncryptField;
import com.demo.encryptionanddecryption.annotation.EncryptMethod;
import com.demo.encryptionanddecryption.model.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName EncryptorController
 * @Description
 * @Author H
 * @Date 2021/9/15 11:40
 * @Version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/encryptor/")
public class EncryptorController {
    @Autowired
    private StringEncryptor stringEncryptor;

    public void encrypt(String content) {
        String encryptString = stringEncryptor.encrypt("hello");
        System.out.println("加密后的内容：" + encryptString);
    }

    @EncryptMethod
    @PostMapping(value = "test")
    @ResponseBody
    public Object testEncrypt(@RequestBody UserVo user, @EncryptField String name) {
        return insertUser(user,name);
    }

    private UserVo insertUser(UserVo user, String name) {
        System.out.println("加密后的数据：user" + JSON.toJSONString(user));
        return user;
    }


}
