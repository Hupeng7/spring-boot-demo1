package com.demo.mybatis.generator.entity;

public class MultiTable {
    private Long id;

    private String name;

    public MultiTable(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public MultiTable() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}