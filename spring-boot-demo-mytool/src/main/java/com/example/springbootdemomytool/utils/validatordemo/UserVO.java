package com.example.springbootdemomytool.utils.validatordemo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

/**
 * @ClassName UserVO
 * @Description
 * @Author H
 * @Date 2020/12/14 15:06
 * @Version 1.0
 */
@Data
public class UserVO {

    // 注解对静态变量不生效
    @NotBlank(message = "性别不能为空")
    private static String sex;

    @NotBlank(message = "姓名不能为空")
    @Size(min = 2, max = 5, message = "姓名长度不规范")
    private String name;

    @NotNull(message = "年龄不能为空")
    @Max(value = 30, message = "年龄超过最大值30")
    @Range(min = 30, max = 60)
    private Integer age;

    @DecimalMax(value = "108.88", message = "超过最大值108.88", inclusive = false)
    private Double price;

    @Past(message = "生日不能大于当前日期")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime birthday;

    @Email(message = "电子邮箱格式不正确")
    private String email;

    @SafeHtml(message = "非法请求参数")
    private String content;

}
