package com.demo.sharding.jdbc.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @ClassName TOrder
 * @Description
 * @Author H
 * @Date 2021/10/11 14:11
 * @Version 1.0
 */
@Data
public class TOrder {
    private Long orderId;
    private Long userId;
    private String orderNo;
    private String createName;
    private BigDecimal price;
}
