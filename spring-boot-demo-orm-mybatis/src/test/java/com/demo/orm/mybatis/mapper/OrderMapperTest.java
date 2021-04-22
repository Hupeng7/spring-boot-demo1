package com.demo.orm.mybatis.mapper;

import com.demo.orm.mybatis.SpringBootDemoOrmMybatisApplicationTests;
import com.demo.orm.mybatis.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName OrderMapperTest
 * @Description
 * @Author H
 * @Date 2021/4/12 11:32
 * @Version 1.0
 */
@Slf4j
public class OrderMapperTest extends SpringBootDemoOrmMybatisApplicationTests {

    @Autowired
    private OrderMapper orderMapper;

    @Test
    public void saveOrder() {
        BigDecimal money = new BigDecimal(1000);
//        Order order = Order.builder()
//                .t0DuePrincipal(money)
//                .t0DueInterest(new BigDecimal(10))
//                .t0DueFee3(new BigDecimal(2))
//                .t30PaidFee3(new BigDecimal(2))
//                .updateBy("U" + new Random().nextInt(100))
//                .build();
//        int i = orderMapper.saveOrder(order);
//        Assert.assertEquals(1, i);
    }

    @Test
    public void batchSaveOrder() {
        for (int i = 0; i < 2000; i++) {
            this.saveOrder();
        }
    }

    @Test
    public void updateOrder() {
//        Order order = Order.builder()
//                .t30PaidFee3(new BigDecimal(3))
//                .updateBy("UP1")
//                .id(1)
//                .build();
//        orderMapper.updateOrder(order);
    }

    /**
     * 批量更新
     */
    @Test
    public void batchUpdate() {
        Long startTime = System.currentTimeMillis();
        List<Order> orders = orderMapper.selectOrderLimit(1000);
        Long endTime = System.currentTimeMillis();
        log.info("selectOrderLimit time: {}", endTime - startTime);
        for (int i = 0, length = orders.size(); i < length; i++) {
            Order curOrder = orders.get(i);
            //curOrder.setT30PaidFee3(new BigDecimal(5));
            curOrder.setUpdateBy("UPDATE-5-" + i);
        }
        startTime = System.currentTimeMillis();
        log.info("fori time: {}", startTime - endTime);
        orderMapper.batchUpdate1(orders);
        endTime = System.currentTimeMillis();
        log.info("batch time: {}", endTime - startTime);
    }


}
