package com.example.springbootdemomytool.utils.newobjectdemo;

import java.io.Serializable;
import java.util.Objects;

/**
 * @ClassName Employee
 * @Description
 * @Author hup
 * @Date 2020/10/27 16:40
 * @Version 1.0
 */

public class Employee implements Cloneable, Serializable {
    private static final long serialVersionUID = -6525291848738007127L;

    private String name;

    public Employee() {
        System.out.println("Employee Constructor Called...");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Employee)) {
            return false;
        }
        Employee employee = (Employee) o;
        return Objects.equals(getName(), employee.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Object obj = null;
        try {
            obj = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
