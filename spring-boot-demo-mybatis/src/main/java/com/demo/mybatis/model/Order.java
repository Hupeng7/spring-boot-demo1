package com.demo.mybatis.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName Order
 * @Description
 * @Author H
 * @Date 2021/4/9 17:55
 * @Version 1.0
 */
@Data
@TableName("order")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order implements Serializable {
    private static final long serialVersionUID = -6408168804528064199L;

    @TableId(type = IdType.ID_WORKER)
    private Integer id;

    private BigDecimal t0DuePrincipal;
    private BigDecimal t0DueInterest;
    private BigDecimal t0DueFee3;
    private BigDecimal t30PaidFee3;

    private Date createTime;

    private Date updateTime;

    private String updateBy;

}
