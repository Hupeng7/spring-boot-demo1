package com.demo.cache.ehcache.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName User
 * @Description TODO
 * @Author Leo
 * @Date 2020/1/2 16:01
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = 5232385786300625078L;

    /**
     * 主键id
     */
    private Long id;

    /**
     * 姓名
     */
    private String name;



}
