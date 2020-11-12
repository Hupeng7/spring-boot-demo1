package com.example.springbootdemomytool.utils.annootationdemo.demo2;

import com.example.springbootdemomytool.beans.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @ClassName TestController
 * @Description
 * https://mp.weixin.qq.com/s/JDLr0a7znKmpFIKIHIWz-A
 * 为了验证注解 @OpLog
 * 需求：记录一下入参，出参，方法执行的时间等
 * 自定义注解 + 切面
 * @Author hup
 * @Date 2020/11/9 10:59
 * @Version 1.0
 */
@RestController
@RequestMapping("/anno-test")
@Slf4j
public class Test3Controller {

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST})
    @OpLog(opType = OpType.QUERY, opItem = "user", opItemIdExpression = "#user.id")
    public Object queryUser(@RequestBody @Valid User user) {
        log.info(user.toString());
        return user.getId();
    }

    @PostMapping("/addUser")
    @OpLog(opType = OpType.ADD, opItem = "user", opItemIdExpression = "#user.id")
    public String addUser(@RequestBody @Valid User user) {
        System.out.println(user.toString());
        log.info("【addUser】 request: {}", user.toString());
        // 如果有参数校验失败，会将错误信息封装成对象组装在BindingResult里
//        for (ObjectError error : bindingResult.getAllErrors()) {
//            // return error.getDefaultMessage();
//            // throw  new APIException(error.getDefaultMessage());
//        }
        return user.getId() + "";
    }


}
