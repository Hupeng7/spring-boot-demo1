package com.example.springbootdemomytool.beans;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * @ClassName StudentInfo
 * @Description TODO
 * @Author Leo
 * @Date 2020/4/7 16:12
 * @Version 1.0
 */
public class StudentInfo implements Comparable<StudentInfo> {

    private String name;

    //性别 true男 false女
    private Boolean gender;

    private Integer age;

    private Double height;

    private LocalDate birthday;

    private BigDecimal money;

    @Override
    public int compareTo(StudentInfo o) {
        return this.age.compareTo(o.getAge());
    }

    public StudentInfo(String name, Boolean gender, Integer age, Double height, LocalDate birthday) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.height = height;
        this.birthday = birthday;
    }

    public static void printStudents(List<StudentInfo> studentInfos) {
        System.out.println("[姓名]\t\t[性别]\t\t[年龄]\t\t[身高]\t\t[生日]");
        System.out.println("================================================");
        studentInfos.forEach(s -> System.out.println(s.toString()));
        System.out.println(" ");
    }

    @Override
    public String toString() {
        return "StudentInfo{" +
                "name='" + name + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                ", height=" + height +
                ", birthday=" + birthday +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }
}
