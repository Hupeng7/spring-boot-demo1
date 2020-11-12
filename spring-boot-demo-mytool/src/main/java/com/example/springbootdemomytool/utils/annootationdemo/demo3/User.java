package com.example.springbootdemomytool.utils.annootationdemo.demo3;

import javax.validation.constraints.NotNull;

/**
 * @ClassName User
 * @Description
 * @Author hup
 * @Date 2020/11/9 11:48
 * @Version 1.0
 */
public class User {
    private String idempotentNo;

    @NotNull(
            message = "userName can't be null"
    )
    private String userName;

}
