package com.demo.cache.redis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName User
 * @Description TODO
 * @Author Leo
 * @Date 2020/1/2 17:00
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = 5903297925720621990L;

    /**
     * 主键 id
     */
    private Long id;

    /**
     * 姓名
     */
    private String name;

}
