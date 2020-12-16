package com.demo.xxl.service.jobhandler;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.core.log.XxlJobLogger;
import org.springframework.stereotype.Component;

/**
 * @ClassName BeanMethodJobHandler
 * @Description
 * @Author H
 * @Date 2020/12/15 18:13
 * @Version 1.0
 */
@Component
public class BeanMethodJobHandler {

    @XxlJob("beanMethodJobHandler")
    public ReturnT<String> beanMethodJobHandler(String param) throws Exception {
        XxlJobLogger.log("bean method jobhandler running...");
        return ReturnT.SUCCESS;
    }
}
