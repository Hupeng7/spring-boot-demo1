package com.example.springbootdemomytool.utils.mapstructdemo;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonMapper {
    PersonMapper INSTANCT = Mappers.getMapper(PersonMapper.class);

    // target是目标字段，source是传参对象的字段
    @Mapping(target = "personName", source = "name")
    // 忽略id，不进行映射
    @Mapping(target = "id", ignore = true)
    // 默认值
    @Mapping(target = "describe", source = "describe", defaultValue = "默认值")
    // 表达式
    @Mapping(target = "updateTime", expression = "java(new java.util.Date())")
    // dateFormat()
    @Mapping(target = "createTime",source = "createTime",dateFormat = "yyyy-MM-dd HH:mm:ss")
    PersonDTO conver(Person person);

}
