package com.demo.orm.mybatis.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName Order
 * @Description
 * @Author H
 * @Date 2021/4/9 17:55
 * @Version 1.0
 */
@Data
@Table(name = "order")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order implements Serializable {
    private static final long serialVersionUID = -6408168804528064199L;

    @Id
    private Integer id;

//    private BigDecimal t0DuePrincipal;
//    private BigDecimal t0DueInterest;
//    private BigDecimal t0DueFee3;
//    private BigDecimal t30PaidFee3;

    private Date createTime;

    private Date updateTime;

    private String updateBy;

}
