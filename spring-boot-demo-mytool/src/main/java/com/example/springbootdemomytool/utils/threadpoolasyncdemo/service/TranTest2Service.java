package com.example.springbootdemomytool.utils.threadpoolasyncdemo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @ClassName TranTest2Service
 * @Description TODO
 * @Author Leo
 * @Date 2020/4/8 10:08
 * @Version 1.0
 */
@Service
public class TranTest2Service {

    Logger log = LoggerFactory.getLogger(TranTest2Service.class);

    // 发送提醒短信 1
    //@PostConstruct // 加上该注解项目启动时就执行一次该方法
    @Async("taskExecutor")
    public void sendMessage1() throws InterruptedException {
        log.info("发送短信方法----1 执行开始");
        Thread.sleep(5000); //模拟耗时
        log.info("发送短信方法----1 执行结束");
    }

    // 发送提醒短信2
    //@PostConstruct
    @Async("taskExecutor")
    public void sendMessage2() throws InterruptedException {
        log.info("发送短信方法----2 执行开始");
        Thread.sleep(2000);
        log.info("发送短信方法----2 执行结束");
    }

}
