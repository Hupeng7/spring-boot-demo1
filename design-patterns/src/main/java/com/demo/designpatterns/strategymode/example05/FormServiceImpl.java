package com.demo.designpatterns.strategymode.example05;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * @ClassName FormServiceImpl
 * @Description
 * @Author H
 * @Date 2021/1/29 18:27
 * @Version 1.0
 */
@Service
public class FormServiceImpl implements FormService {
    @Autowired
    private FormSubmitHandlerFactory formSubmitHandlerFactory;

    @Override
    public Object submitForm(FormSubmitRequest request) {
        String submitType = request.getSubmitType();

        // 根据submitType找到对应的提交处理器
        FormSubmitHandler<Serializable> handler = formSubmitHandlerFactory.getHandler(submitType);
        if (handler == null) {
            return "false!!!非法的提交类型" + submitType;
        }
        return "success";
    }
}
