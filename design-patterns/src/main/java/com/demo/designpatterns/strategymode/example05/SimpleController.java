package com.demo.designpatterns.strategymode.example05;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName SimpleController
 * @Description 策略模式 + 简单工厂模式
 * @Author H
 * @Date 2021/1/29 18:32
 * @Version 1.0
 */
@RestController
public class SimpleController {
    @Autowired
    private FormService formService;

    @PostMapping("/form/submit")
    public Object submitForm(@RequestParam String submitType, @RequestParam String formInputJson) {
        FormSubmitRequest request = new FormSubmitRequest();
        request.setSubmitType(submitType);
        return formService.submitForm(request);
    }


}
