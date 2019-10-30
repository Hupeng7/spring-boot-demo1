package com.demo.properties.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ClassName DeveloperProperty
 * @Description TODO
 * @Author Leo
 * @Date 2019/10/28 14:10
 * @Version 1.0
 */
@Data
@ConfigurationProperties(prefix = "developer")
@Component
public class DeveloperProperty {
    private String name;
    private String website;
    private String qq;
    private String phoneNumber;
}
