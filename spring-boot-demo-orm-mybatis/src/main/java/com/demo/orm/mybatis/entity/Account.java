package com.demo.orm.mybatis.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import java.math.BigDecimal;

/**
 * @ClassName Account
 * @Description
 * @Author H
 * @Date 2020/12/10 14:28
 * @Version 1.0
 */
@Entity
@Table(name = "t_account")
@Data
public class Account {

    @Id
    private Integer id;
    private String userId;
    private BigDecimal money;
    @Version
    private Integer version;


}
