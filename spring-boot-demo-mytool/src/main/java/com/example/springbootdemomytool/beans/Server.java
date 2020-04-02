package com.example.springbootdemomytool.beans;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import lombok.Data;

import java.util.List;

/**
 * @ClassName Server
 * @Description TODO
 * @Author Leo
 * @Date 2020/4/2 18:05
 * @Version 1.0
 */
@Data
@XStreamAlias("Server")
public class Server {
    @XStreamAsAttribute
    private int port = 8005;

    @XStreamAsAttribute
    private String shutDown = "SHUTDOWN";

    private List<Service> services;
}
