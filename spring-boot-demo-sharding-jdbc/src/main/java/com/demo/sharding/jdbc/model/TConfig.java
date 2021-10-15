package com.demo.sharding.jdbc.model;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName TConfig
 * @Description
 * @Author H
 * @Date 2021/10/11 14:09
 * @Version 1.0
 */
@Data
public class TConfig {
    private Long id;
    private String remark;
    private Date createTime;
    private Date lastModifyTime;
}
