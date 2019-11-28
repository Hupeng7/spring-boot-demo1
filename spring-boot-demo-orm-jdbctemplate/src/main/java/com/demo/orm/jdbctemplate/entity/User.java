package com.demo.orm.jdbctemplate.entity;

import com.demo.orm.jdbctemplate.annotation.Column;
import com.demo.orm.jdbctemplate.annotation.Pk;
import com.demo.orm.jdbctemplate.annotation.Table;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName User
 * @Description 用户实体类
 * @Author Leo
 * @Date 2019/11/27 16:31
 * @Version 1.0
 */
@Data
@Table(name = "orm_user_jdbc")
public class User implements Serializable {
    // private static final long serialVersionUID = 3332258711653008474L;

    /**
     * 主键
     */
    @Pk
    private Long id;

    /**
     * 用户名
     */
    private String name;

    /**
     * 加密后的密码
     */
    private String password;

    /**
     * 加密使用的盐
     */
    private String salt;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号码
     */
    @Column(name = "phone_number")
    private String phoneNumber;

    /**
     * 状态， -1：逻辑删除 0：禁用 1:启用
     */
    private Integer status;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 上次登录时间
     */
    @Column(name = "last_login_time")
    private Date lastLoginTime;

    /**
     * 上次更新时间
     */
    @Column(name = "last_update_time")
    private Date lastUpdateTime;


}
