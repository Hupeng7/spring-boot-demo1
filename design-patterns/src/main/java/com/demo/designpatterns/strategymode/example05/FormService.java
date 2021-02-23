package com.demo.designpatterns.strategymode.example05;

import org.springframework.lang.NonNull;

/**
 * @ClassName FormService
 * @Description
 * @Author H
 * @Date 2021/1/29 18:26
 * @Version 1.0
 */
public interface FormService {

    Object submitForm(@NonNull FormSubmitRequest request);
}
