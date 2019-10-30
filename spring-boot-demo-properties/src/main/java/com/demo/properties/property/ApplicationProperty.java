package com.demo.properties.property;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @ClassName ApplicationProperty
 * @Description TODO
 * @Author Leo
 * @Date 2019/10/28 14:12
 * @Version 1.0
 */
@Data
@Component
public class ApplicationProperty {
    @Value("${application.name}")
    private String name;
    @Value("${application.version}")
    private String version;
}
