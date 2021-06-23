package com.demo.ldap.dao;

import lombok.Data;
import org.springframework.ldap.odm.annotations.*;

import javax.naming.Name;

/**
 * @ClassName Person
 * @Description
 * @Author H
 * @Date 2021/6/21 16:48
 * @Version 1.0
 */
@Data
@Entry(base = "ou=people,dc=didispace,dc=com", objectClasses = "inetOrgPerson")
public class Person {
    @Id
    private Name id;

    @DnAttribute(value = "uid", index = 3)
    private String uid;

    @Attribute(name = "cn")
    private String commonName;

    @Attribute(name = "sn")
    private String userName;

    private String userPassword;

}
