package com.demo.sharding.jdbc.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @ClassName TOrderDto
 * @Description
 * @Author H
 * @Date 2021/10/11 14:14
 * @Version 1.0
 */
@Data
public class TOrderDto {
    private Long orderId;
    private String orderNo;
    private String createName;
    private BigDecimal price;
    private Long userId;
    private Long itemId;
    private String itemName;
}
