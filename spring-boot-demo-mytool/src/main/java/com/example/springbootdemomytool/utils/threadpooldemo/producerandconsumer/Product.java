package com.example.springbootdemomytool.utils.threadpooldemo.producerandconsumer;

/**
 * @ClassName Product
 * @Description
 * @Author hup
 * @Date 2020/9/15 14:30
 * @Version 1.0
 */
public class Product {
   private String name;

    public Product(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
