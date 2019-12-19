package com.demo.orm.mybatis.plus.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @ClassName Role
 * @Description TODO
 * @Author Leo
 * @Date 2019/12/18 17:18
 * @Version 1.0
 */
@Data
@TableName("orm_role_mybatis_plus")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class Role extends Model<Role> {

    /**
     * 主键
     */
    private Long id;

    /**
     * 角色名
     */
    private String name;

    /**
     * 主键值，ActiveRecord 模式这个必须有，否则xxById 的方法都将失效！
     * 即使使用 ActiveRecord 不会用到 RoleMapper,RoleMapper 这个接口也必须创建
     *
     * @return
     */
    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
