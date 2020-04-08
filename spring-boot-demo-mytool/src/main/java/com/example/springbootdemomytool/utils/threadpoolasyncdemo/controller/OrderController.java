package com.example.springbootdemomytool.utils.threadpoolasyncdemo.controller;

import com.example.springbootdemomytool.utils.threadpoolasyncdemo.service.OrderTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName OrderController
 * @Description TODO
 * @Author Leo
 * @Date 2020/4/8 10:38
 * @Version 1.0
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderTaskService orderTaskService;

    /**
     * http://localhost:9090/order/task1
     * cancel order start----
     * cancel order end----
     * 2020-04-08 10:42:53.061  INFO 79444 --- [Async-Service-3] c.e.s.u.t.service.TranTest2Service       : 发送短信方法----1 执行开始
     * 2020-04-08 10:42:53.061  INFO 79444 --- [Async-Service-4] c.e.s.u.t.service.TranTest2Service       : 发送短信方法----2 执行开始
     * 2020-04-08 10:42:55.062  INFO 79444 --- [Async-Service-4] c.e.s.u.t.service.TranTest2Service       : 发送短信方法----2 执行结束
     * 2020-04-08 10:42:58.062  INFO 79444 --- [Async-Service-3] c.e.s.u.t.service.TranTest2Service       : 发送短信方法----1 执行结束
     *
     * 注意事项：
     * 如下方式会使@Async失效
     * 一、异步方法使用static修饰
     * 二、异步类没有使用@Component注解（或其他注解）导致spring无法扫描到异步类
     * 三、异步方法不能与被调用的异步方法在同一个类中
     * 四、类中需要使用@Autowired或@Resource等注解自动注入，不能自己手动new对象
     * 五、如果使用SpringBoot框架必须在启动类中增加@EnableAsync注解
     * @throws InterruptedException
     */
    @GetMapping("/task1")
    public void task1() throws InterruptedException {
        orderTaskService.orderTask();
    }
}
