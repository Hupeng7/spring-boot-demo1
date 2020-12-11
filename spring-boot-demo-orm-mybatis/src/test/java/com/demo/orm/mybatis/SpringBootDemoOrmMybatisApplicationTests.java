package com.demo.orm.mybatis;

import com.demo.orm.mybatis.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.concurrent.CountDownLatch;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootDemoOrmMybatisApplicationTests {
    @Resource
    private AccountService accountService;

    private CountDownLatch countDownLatch = new CountDownLatch(2);

    @Test
    public void testMoneyOperator(){
        Thread t1 = new Thread(()->{
           accountService.deduction(100, BigDecimal.valueOf(10));
          // countDownLatch.countDown();
        });

        t1.start();

//        Thread t2 = new Thread(()->{
//           accountService.recharge(100,BigDecimal.valueOf(10));
//           countDownLatch.countDown();
//        });
//
//        t2.start();

//        try {
//            countDownLatch.await();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    @Test
    public void contextLoads() {
    }

}
