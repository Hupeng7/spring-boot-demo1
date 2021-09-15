package com.demo.encryptionanddecryption.model;

import com.demo.encryptionanddecryption.annotation.EncryptField;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName UserVo
 * @Description
 * @Author H
 * @Date 2021/9/15 10:57
 * @Version 1.0
 */
@Data
public class UserVo implements Serializable {
    private static final long serialVersionUID = 5815542656869315995L;

    private Long userId;

    @EncryptField
    private String mobile;

    @EncryptField
    private String address;

    private String age;

}
