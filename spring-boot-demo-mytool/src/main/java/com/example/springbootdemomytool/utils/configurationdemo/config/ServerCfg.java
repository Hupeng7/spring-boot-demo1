package com.example.springbootdemomytool.utils.configurationdemo.config;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import lombok.Data;

import java.util.List;

/**
 * @ClassName ServerCfg
 * @Description TODO
 * @Author Leo
 * @Date 2020/4/2 18:24
 * @Version 1.0
 */
@Data
@XStreamAlias("Server")
public class ServerCfg {

    @XStreamAsAttribute
    private int port = 8005;

    @XStreamAsAttribute
    private String shutDown = "SHUTDOWN";

    private List<ServiceCfg> services;
}
