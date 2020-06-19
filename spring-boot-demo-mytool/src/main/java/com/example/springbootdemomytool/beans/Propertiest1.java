package com.example.springbootdemomytool.beans;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @ClassName Propertiest1
 * @Description TODO
 * @Author Leo
 * @Date 2020/5/20 14:07
 * @Version 1.0
 */
@Component
@Data
public class Propertiest1 {

    @Value("${code.fail.code.list}")
    private String failCodeStr;

    @Value("${dingding.robot.margin.monitor.atmobiles}")
    private String atmobiles;
}
