package com.example.springbootdemomytool.utils.apidemo.controller;

import com.example.springbootdemomytool.beans.ResultVO;
import com.example.springbootdemomytool.beans.User;
import com.example.springbootdemomytool.utils.apidemo.service.UserService;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @ClassName UserController
 * @Description
 * https://mp.weixin.qq.com/s/nSGsG43NbnbmEpztJXQ34g
 * 总结
 * 自此整个后端接口基本体系就构建完毕了
 * 通过Validator + 自动抛出异常来完成了方便的参数校验
 * 通过全局异常处理 + 自定义异常完成了异常操作的规范
 * 通过数据统一响应完成了响应数据的规范
 * 多个方面组装非常优雅的完成了后端接口的协调，让开发人员有更多的经历注重业务逻辑代码，轻松构建后端接口
 * @Author Leo
 * @Date 2020/4/13 17:03
 * @Version 1.0
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    private final static Gson GSON = new Gson();

    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    public String addUser(@RequestBody @Valid User user) {
        System.out.println(user.toString());
        log.info("【addUser】 request: {}", user.toString());
        log.info("【addUser】 request: {}", GSON.toJson(user));
        // 如果有参数校验失败，会将错误信息封装成对象组装在BindingResult里
//        for (ObjectError error : bindingResult.getAllErrors()) {
//            // return error.getDefaultMessage();
//            // throw  new APIException(error.getDefaultMessage());
//        }
        return userService.addUser(user);
    }

    @GetMapping("/getUser")
    public ResultVO<User> getUser() {
        User user = new User();
        user.setId(1L);
        user.setAccount("falkdjf");
        user.setPassword("jdioaodfj");
        user.setEmail("da@dad.com");
        return new ResultVO<>(user);
    }

    @GetMapping("/getUser1")
    public User getUser1() {
        User user = new User();
        user.setId(1L);
        user.setAccount("falkdjf");
        user.setPassword("jdioaodfj");
        user.setEmail("da@dad.com");
        return user;
    }

}
