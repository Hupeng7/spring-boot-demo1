package com.demo.mybatis;

import com.demo.mybatis.model.Order;
import com.demo.mybatis.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootDemoMybatisApplicationTests {

    @Autowired
    private OrderService orderService;

    @Test
    public void addOrder() {
        BigDecimal bigDecimal1 = new BigDecimal(1000);
        BigDecimal bigDecimal2 = new BigDecimal(10);
        Order order = Order.builder().t0DuePrincipal(bigDecimal1).t30PaidFee3(bigDecimal2).build();


    }


}
