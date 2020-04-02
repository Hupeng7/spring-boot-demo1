package com.example.springbootdemomytool.utils.configurationdemo.config;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import lombok.Data;

import java.util.List;

/**
 * @ClassName ServiceCfg
 * @Description TODO
 * @Author Leo
 * @Date 2020/4/2 18:24
 * @Version 1.0
 */
@Data
@XStreamAlias("Service")
public class ServiceCfg {

    @XStreamAsAttribute
    private String name;

    private List<ConnectorCfg> connectors;


}
