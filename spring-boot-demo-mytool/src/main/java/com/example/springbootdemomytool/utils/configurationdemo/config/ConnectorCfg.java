package com.example.springbootdemomytool.utils.configurationdemo.config;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import lombok.Data;

/**
 * @ClassName ConnectorCfg
 * @Description TODO
 * @Author Leo
 * @Date 2020/4/2 18:23
 * @Version 1.0
 */
@Data
@XStreamAlias("Connector")
public class ConnectorCfg {
    @XStreamAsAttribute
    private int port = 8080;

    @XStreamAsAttribute
    private String protocol = "HTTP/1.1";

    @XStreamAsAttribute
    String executor;
}
