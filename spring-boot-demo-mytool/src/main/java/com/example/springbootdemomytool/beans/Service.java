package com.example.springbootdemomytool.beans;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import lombok.Data;

import java.util.List;

/**
 * @ClassName Service
 * @Description TODO
 * @Author Leo
 * @Date 2020/4/2 18:05
 * @Version 1.0
 */
@Data
@XStreamAlias("Service")
public class Service {

    @XStreamAsAttribute
    private String name;

    private List<Connector> connectors;

}
