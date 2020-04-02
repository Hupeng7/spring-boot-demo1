package com.example.springbootdemomytool.utils.restfuldemo.beans;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName ResultBean
 * @Description TODO
 * @Author Leo
 * @Date 2020/3/30 18:45
 * @Version 1.0
 */
@Data
public class ResultBean<T> implements Serializable {

    private static final long serialVersionUID = 2775094813042168997L;

    public static final int SUCCESS = 0;
    public static final int FAIL = 1;
    public static final int NO_PERMISSION = 2;
    private int code = SUCCESS;
    private String msg = "success";
    private T data;

    public ResultBean() {
        super();
    }

    public ResultBean(T data) {
        super();
        this.data = data;
    }

    public ResultBean(Throwable e) {
        super();
        this.code = FAIL;
        this.msg = e.toString();
    }


}
