package com.example.springbootdemomytool.beans;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Objects;

/**
 * @ClassName User2
 * @Description
 * @Author Leo
 * @Date 2020/6/19 14:53
 * @Version 1.0
 */
public class User2 implements Externalizable {
    private static final long serialVersionUID = 7060510328838742308L;
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        // 自反性
        if (this == o) return true;
        // 任何对象不等于null，比较是否为同一类型
        if (!(o instanceof User2)) return false;
        // 强制类型转换
        User2 user2 = (User2) o;
        // 比较属性值
        return getAge() == user2.getAge() &&
                getName().equals(user2.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAge());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("name", name)
                .append("age", age)
                .toString();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(name);
        out.writeInt(age);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        name = (String) in.readObject();
        age = in.readInt();
    }
}
