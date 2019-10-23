package com.demo.dynamic.datasource.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @ClassName User
 * @Description TODO
 * @Author Leo
 * @Date 2019/10/21 18:30
 * @Version 1.0
 */

@Data
@Table(name = "test_user")
public class User implements Serializable {
    private static final long serialVersionUID = 352891444257193149L;

    /**
     * 主键
     */
    @Id
    @Column(name = "`id`")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 姓名
     */
    @Column(name = "`name`")
    private String name;


}
