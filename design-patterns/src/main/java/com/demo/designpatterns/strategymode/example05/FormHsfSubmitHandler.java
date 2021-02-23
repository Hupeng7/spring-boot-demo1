package com.demo.designpatterns.strategymode.example05;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Map;


/**
 * @ClassName FormPreviewSubmitHandler
 * @Description 预览模型时的提交
 * @Author H
 * @Date 2021/1/29 17:52
 * @Version 1.0
 */
@Component
public class FormHsfSubmitHandler implements FormSubmitHandler<Serializable> {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public String getSubmitType() {
        return "hsf";
    }

    @Override
    public Map<String, Serializable> handleSubmit(FormSubmitRequest request) {
        logger.info("HSF模式提交：userId={},formInput={}",request.getUserId(),request.getFormInput());
        return null;
    }
}
