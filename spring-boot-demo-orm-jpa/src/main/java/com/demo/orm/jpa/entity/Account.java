package com.demo.orm.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @ClassName Account
 * @Description
 * @Author H
 * @Date 2020/12/10 14:28
 * @Version 1.0
 */

@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "t_account")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "user_id", columnDefinition = "varchar(64) not null")
    private String userId;
    private BigDecimal money;
    @Version
    private Integer version;


}
