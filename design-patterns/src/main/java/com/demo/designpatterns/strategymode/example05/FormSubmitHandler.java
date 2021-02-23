package com.demo.designpatterns.strategymode.example05;

import java.io.Serializable;
import java.util.Map;

/**
 * @ClassName FormSubmitHandler
 * @Description 提交表单到处理器
 * @Author H
 * @Date 2021/1/29 17:42
 * @Version 1.0
 */
public interface FormSubmitHandler<R extends Serializable> {

    /**
     * 获得提交类型（返回值也可以使用已经存在的枚举类）
     *
     * @return 提交类型
     */
    String getSubmitType();

    /**
     * 狐狸表单提交请求
     *
     * @param request 请求
     * @return 响应，left为返回给前端的提示信息，right为业务值
     */
    Map<String, R> handleSubmit(FormSubmitRequest request);

}
