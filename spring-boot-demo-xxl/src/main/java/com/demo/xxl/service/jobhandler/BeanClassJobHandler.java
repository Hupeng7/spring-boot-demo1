package com.demo.xxl.service.jobhandler;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.log.XxlJobLogger;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @ClassName BeanClassJobHandler
 * @Description
 * @Author H
 * @Date 2020/12/15 18:03
 * @Version 1.0
 */

@Component
public class BeanClassJobHandler extends IJobHandler {
    @Override
    public ReturnT<String> execute(String s) throws Exception {
        XxlJobLogger.log("bean class jobhandler running...");
        System.out.println("bean class jobhandler running..." + new Date());
        return ReturnT.SUCCESS;
    }
}
