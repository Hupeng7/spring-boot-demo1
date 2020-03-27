package com.example.springbootdemomytool.utils.aspectdemo.controller;

import com.example.springbootdemomytool.utils.aspectdemo.aspect.WebLog;
import com.example.springbootdemomytool.utils.aspectdemo.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName TestController
 * @Description TODO
 * @Author Leo
 * @Date 2020/3/27 18:04
 * @Version 1.0
 */
@RestController
@Slf4j
public class TestController {

    /**
     * POST 方式接口测试
     *
     * @param user
     * @return
     */
    @PostMapping("/user/login")
    @WebLog(description = "请求了用户登录接口")
    public User userLogin(@RequestBody User user) {
        log.info("user login ...");
        return user;
    }

    /**
     * GET 方式接口测试
     *
     * @return
     */
    @GetMapping("/test")
    public String test() {
        log.info("test Get...");
        return "success";
    }

    /**
     * GET 方式接口测试
     *
     * @param userId
     * @return
     */
    @GetMapping("/user/{id}")
    @WebLog(description = "请求了用户登录接口")
    public String findUserInfo(@PathVariable("id") String userId) {
        log.info("find user info ...");
        return "success";
    }

    /**
     * 单文件上传接口测试
     *
     * @param file
     * @return
     */
    @PostMapping("/file/upload")
    public String testFileUpload(@RequestParam("file") MultipartFile file) {
        log.info("test file upload ...");
        return "success";
    }

    /**
     * 多文件上传接口测试
     *
     * @return
     */
    @PostMapping("/multiFile/upload")
    @WebLog(description = "上传了多个文件")
    public String testMultiFileUpload() {
        log.info("test multi file upload ...");
        return "success";
    }

}
