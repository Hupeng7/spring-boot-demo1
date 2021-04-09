package com.demo.mybatis.service.impl;

import com.demo.mybatis.mapper.OrderMapper;
import com.demo.mybatis.model.Order;
import com.demo.mybatis.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName OrderServiceImpl
 * @Description
 * @Author H
 * @Date 2021/4/9 17:58
 * @Version 1.0
 */
@Service
public class OrderServiceImpl  implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public boolean addOrder(Order order) {
        orderMapper.insert(order);
        return false;
    }
}
