package com.demo.sharding.jdbc.model;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @ClassName TOrderItem
 * @Description
 * @Author H
 * @Date 2021/10/11 14:17
 * @Version 1.0
 */
@Data
public class TOrderItem {
    @TableId(value = "item_id")
    private Long itemId;
    private Long orderId;
    private String orderNo;
    private String itemName;
    private BigDecimal price;
}
