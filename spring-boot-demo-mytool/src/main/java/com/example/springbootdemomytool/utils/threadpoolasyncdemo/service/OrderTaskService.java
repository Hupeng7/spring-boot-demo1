package com.example.springbootdemomytool.utils.threadpoolasyncdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName OrderTaskService
 * @Description TODO
 * @Author Leo
 * @Date 2020/4/8 10:06
 * @Version 1.0
 */
@Service
public class OrderTaskService {

    @Autowired
    private TranTest2Service tranTest2Service;

    // 订单处理任务
    public void orderTask() throws InterruptedException {
        this.cancelOrder();
        tranTest2Service.sendMessage1();
        tranTest2Service.sendMessage2();
    }

    /**
     * 取消订单
     */
    public void cancelOrder() {
        System.out.println("cancel order start----");
        System.out.println("cancel order end----");
    }


}
