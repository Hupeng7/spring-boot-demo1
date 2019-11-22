package com.demo.exception.handler.controller;

import com.demo.exception.handler.constant.Status;
import com.demo.exception.handler.exception.JsonException;
import com.demo.exception.handler.exception.PageException;
import com.demo.exception.handler.model.ApiResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ClassName TestController
 * @Description TODO
 * @Author Leo
 * @Date 2019/11/22 18:10
 * @Version 1.0
 */
@Controller
public class TestController {

    @GetMapping("/json")
    @ResponseBody
    public ApiResponse jsonException() {
        throw new JsonException(Status.UNKNOWN_ERROR);
    }

    @GetMapping("page")
    public ModelAndView pageException() {
        throw new PageException(Status.UNKNOWN_ERROR);
    }

}
