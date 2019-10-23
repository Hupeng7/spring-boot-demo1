package com.demo.dynamic.datasource.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @ClassName DatasourceConfig
 * @Description TODO
 * @Author Leo
 * @Date 2019/10/21 18:35
 * @Version 1.0
 */

@Data
@Table(name = "datasource_config")
public class DatasourceConfig implements Serializable {

    private static final long serialVersionUID = 1951896048617241573L;
    /**
     * 主键
     */
    @Id
    @Column(name = "`id`")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 数据库地址
     */
    @Column(name = "`host`")
    private String host;

    /**
     * 数据库端口
     */
    @Column(name = "port")
    private Integer port;

    /**
     * 数据库用户名
     */
    @Column(name = "`username`")
    private String username;

    /**
     * 数据库密码
     */
    @Column(name = "`password`")
    private String password;

    /**
     * 数据库名称
     */
    @Column(name = "`database`")
    private String database;

    /**
     * 构造JDBC URL
     *
     * @return
     */
    public String buildJdbUrl() {
        return String.format("jdbc:mysql://%s:%s/%s?useUnicode=true&characterEncoding=utf-8&useSSL=false", this.host, this.port, this.database);
    }

}
