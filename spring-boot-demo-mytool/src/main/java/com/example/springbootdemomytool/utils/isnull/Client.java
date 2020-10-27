package com.example.springbootdemomytool.utils.isnull;

import java.util.Objects;

/**
 * @ClassName Client
 * @Description
 * @Author hup
 * @Date 2020/10/23 13:49
 * @Version 1.0
 */
public class Client {
    public static void main(String[] args) {
        User user = new User();
        User.School school = new User.School();

        user.setName("hello");

        //
        isNull1(user);

        isNull2(user);

        // 1. 基本调用
        String value1 = OptionalBean.ofNullable(user)
                .getBean(User::getSchool)
                .getBean(User.School::getAdress).get();
        System.out.println(value1);

        // 2. 扩展的 isPresent方法 用法与 Optional 一样
        boolean present = OptionalBean.ofNullable(user)
                .getBean(User::getSchool)
                .getBean(User.School::getAdress).isPresent();
        System.out.println(present);

        // 3. 扩展的 ifPresent 方法
        OptionalBean.ofNullable(user)
                .getBean(User::getSchool)
                .getBean(User.School::getAdress)
                .ifPresent(address -> System.out.println(String.format("地址存在:%s", address)));

        // 4. 扩展的 orElse
        String value2 = OptionalBean.ofNullable(user)
                .getBean(User::getSchool)
                .getBean(User.School::getAdress).orElse("没大学");
        System.out.println(value2);

        // 5. 扩展的 orElseThrow
        try {
            String value3 = OptionalBean.ofNullable(user)
                    .getBean(User::getSchool)
                    .getBean(User.School::getAdress).orElseThrow(() -> new RuntimeException("NPE"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    static boolean isNull1(User user) {
        // 不优雅的判空调用
        if (Objects.nonNull(user) && Objects.nonNull(user.getSchool())) {
            User.School userSc = user.getSchool();
            System.out.println(userSc.getAdress());
            return true;
        }
        return false;
    }

    static boolean isNull2(User user) {
        String value = OptionalBean.ofNullable(user)
                .getBean(User::getSchool)
                .getBean(User.School::getAdress).get();
        System.out.println(value);
        return false;
    }
}
