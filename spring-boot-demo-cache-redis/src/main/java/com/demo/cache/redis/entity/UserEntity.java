package com.demo.cache.redis.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName UserEntity
 * @Description
 * @Author H
 * @Date 2021/4/23 18:09
 * @Version 1.0
 */
@Data
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 984992319928272679L;

    private Long id;
    private String guid;
    private String name;
    private String age;
    private Date createTime;

}
