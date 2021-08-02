package com.demo.exception.handler.controller;

import com.demo.exception.handler.constant.Status;
import com.demo.exception.handler.exception.JsonException;
import com.demo.exception.handler.exception.PageException;
import com.demo.exception.handler.model.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

/**
 * @ClassName TestController
 * @Description TODO
 * @Author Leo
 * @Date 2019/11/22 18:10
 * @Version 1.0
 */
@Controller
@Slf4j
public class TestController {

    @GetMapping("/json")
    @ResponseBody
    public ApiResponse jsonException() {
        throw new JsonException(Status.UNKNOWN_ERROR);
    }

    @GetMapping("/page")
    public ModelAndView pageException() {
        throw new PageException(Status.UNKNOWN_ERROR);
    }

    @GetMapping("/hello")
    @ResponseBody
    public ApiResponse hello(@RequestParam("msg") String msg) {
        return ApiResponse.ofSuccess("hello " + msg + "! " + new Date());
    }

    @RequestMapping(value = "/helloworld/{msg}", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse helloWorld(@PathVariable("msg") String msg) {
        log.info("hello " + msg);
        return ApiResponse.ofSuccess("hello " + msg + "!" + new Date());
    }

}
