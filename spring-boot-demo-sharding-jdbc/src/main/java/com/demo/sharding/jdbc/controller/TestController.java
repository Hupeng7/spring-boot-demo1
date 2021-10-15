package com.demo.sharding.jdbc.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.sharding.jdbc.mapper.ConfigRepository;
import com.demo.sharding.jdbc.mapper.OrderItemRepository;
import com.demo.sharding.jdbc.mapper.OrderRepository;
import com.demo.sharding.jdbc.model.TConfig;
import com.demo.sharding.jdbc.model.TOrder;
import com.demo.sharding.jdbc.model.TOrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @ClassName TestController
 * @Description
 * @Author H
 * @Date 2021/10/11 15:03
 * @Version 1.0
 */
@Controller
@RequestMapping("/test")
public class TestController {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private ConfigRepository configRepository;

    @GetMapping(value = "/insertOrder")
    @ResponseBody
    public String insertOrder() {
        for (int i = 0; i < 5; i++) {
            TOrder order = new TOrder();
            order.setOrderNo("A000" + i);
            order.setCreateName("订单" + i);
            order.setUserId(Long.parseLong(i + ""));
            order.setPrice(new BigDecimal("" + i));
            orderRepository.insert(order);
        }
        return "success";
    }

    @GetMapping(value = "config")
    @ResponseBody
    public String config() {
        TConfig tConfig = new TConfig();
        tConfig.setRemark("我是广播表");
        tConfig.setCreateTime(new Date());
        tConfig.setLastModifyTime(new Date());
        configRepository.insert(tConfig);
        return "success";
    }

    @GetMapping(value = "getOneOrder")
    @ResponseBody
    public String getOne(String orderId) {
        return JSON.toJSONString(orderRepository.selectById(Long.parseLong(orderId)));
    }

    @GetMapping(value = "selectOrderAndItemByOrderId")
    @ResponseBody
    public String selectOrderAndItemByOrderId(String orderId) {
        TOrderDto tOrder = new TOrderDto();
        if (!StringUtils.isEmpty(orderId)) {
            tOrder.setOrderId(Long.parseLong(orderId));
        }
        return JSON.toJSONString(orderRepository.selectOrderAndItemByOrderId(tOrder));
    }

    @GetMapping("/orderList")
    @ResponseBody
    public Object list() {
        return orderRepository.selectList(new QueryWrapper<>());
    }

    @GetMapping(value = "selectOrderListPage")
    @ResponseBody
    public List<TOrderDto> selectOrderListPage() {
        return orderRepository.selectOrderListPage();
    }
}
