package com.example.springbootdemomytool.utils.mapstructdemo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName Test
 * @Description 编译期动态生成 set/get 代码的class文件，在运行时直接调用该class的 set/get 方法
 * https://blog.csdn.net/qq_44732146/article/details/119968376
 * @Author H
 * @Date 2022/10/10 14:15
 * @Version 1.0
 */
public class Test {

    public static void main(String[] args) {

        Person person = new Person();
        //person.setDescribe("test aaa!!!");
        person.setAge(18);
        person.setName("张三");
        person.setHeight(170.5);
        person.setSource(new BigDecimal("100"));
        person.setCreateTime(new Date());

        PersonDTO personDTO = PersonMapper.INSTANCT.conver(person);

        System.out.println(personDTO);
    //PersonDTO(describe=默认值, id=null, personName=张三, age=18, source=100, height=170.5, createTime=2022-10-10 15:20:13, updateTime=Mon Oct 10 15:20:13 CST 2022)


        String stringM = "userName";

        int remainder = 1;
        int remainder2 = 2;
    }
}
