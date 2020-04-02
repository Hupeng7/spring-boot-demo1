package com.example.springbootdemomytool.utils.restfuldemo.controller;

import com.example.springbootdemomytool.utils.restfuldemo.beans.ResultBean;
import com.example.springbootdemomytool.utils.restfuldemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName TestController
 * @Description
 * url:https://github.com/xwjie/PLMCodeTemplate.git
 * @Author Leo
 * @Date 2020/3/31 10:16
 * @Version 1.0
 */
@RestController
@RequestMapping("/aoptest2/")
@Slf4j
@Component
public class TestAopTwoController {
    @Autowired
    private UserService userService;

    @GetMapping("/test2")
    public ResultBean<String> hello() {
        System.out.println("controller aop2test================");
        return new ResultBean<String>("well done 2");
    }

    /**
     * 使用日志示例
     * 不要依赖debug，多依赖日志。
     * 代码开发测试完成之后不要急着提交，先跑一遍看看日志是否看得懂
     *
     * @param id
     * @return
     */
    @DeleteMapping("/user/{id}")
    public ResultBean<Boolean> delete(@PathVariable("id") Long id) {
        // 日志示例
        Long userType = getCurrentUserType(id);

        // 校验通过后打印重要的日志
        log.info("delete config,id:{},userType:{}", id, userType);
        boolean result = false;
        if (userType == 1) {
            System.out.println("do something() 1");
        } else {
            result = true;
            System.out.println("do something() 2");
        }
        // 修改操作需要打印操作结果
        log.info("delete config success,id:{},result:{}", id, result);
        return new ResultBean<Boolean>(result);
    }

    private Long getCurrentUserType(Long id) {
        return id;
    }

    @DeleteMapping("/user/test/{id}")
    public ResultBean<Boolean> deleteTest(@PathVariable("id") Long id) {
        return new ResultBean<>(userService.delete(id));
    }


}
