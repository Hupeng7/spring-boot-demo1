package com.example.springbootdemomytool.beans;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import lombok.Data;

/**
 * @ClassName Connector
 * @Description TODO
 * @Author Leo
 * @Date 2020/4/2 18:05
 * @Version 1.0
 */
@Data
@XStreamAlias("Connector")
public class Connector {
    @XStreamAsAttribute
    private int port = 8080;

    @XStreamAsAttribute
    private String protocol = "HTTP/1.1";

}
