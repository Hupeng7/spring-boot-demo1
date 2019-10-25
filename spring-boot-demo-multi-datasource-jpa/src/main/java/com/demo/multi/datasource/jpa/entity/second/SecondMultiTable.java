package com.demo.multi.datasource.jpa.entity.second;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ClassName SecondMultiTable
 * @Description TODO
 * @Author Leo
 * @Date 2019/10/24 14:40
 * @Version 1.0
 */
@Data
@Entity
@Table(name = "multi_table")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SecondMultiTable {

    /**
     * 主键
     */
    @Id
    private Long id;

    /**
     * 名称
     */
    private String name;
}
