package com.example.springbootdemomytool.utils.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName ScopeTestController
 * @Description Spring的Controller是单例还是多例？怎么保证并发的安全?
 * <p>
 * 相信大家不难发现 ：
 * 单例是不安全的，会导致属性重复使用。
 * 解决方案
 * 1.不要在controller中定义成员变量。
 * 2.万一必须要定义一个非静态成员变量时候，则通过注解@Scope(“prototype”)，将其设置为多例模式。
 * 3.在Controller中使用ThreadLocal变量
 * @Author hup
 * @Date 2020/8/11 13:53
 * @Version 1.0
 */
@Controller
@Scope("prototype")
@RequestMapping("/testScope")
public class ScopeTestController {
    private int num = 0;

    @RequestMapping("/a")
    public void testScope() {
        System.out.println("a接口结果是：" + ++num);
    }

    @RequestMapping("/b")
    public void testScope2() {
        System.out.println("b接口结果是：" + ++num);
    }

}
