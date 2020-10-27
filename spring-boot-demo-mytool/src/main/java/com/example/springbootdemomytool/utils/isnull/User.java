package com.example.springbootdemomytool.utils.isnull;

import lombok.Data;

/**
 * @ClassName User
 * @Description
 * @Author hup
 * @Date 2020/10/23 13:46
 * @Version 1.0
 */
@Data
public class User {

    private String name;
    private String gender;
    private School school;

    @Data
    public static class School {
        private String scName;
        private String adress;
    }

}
