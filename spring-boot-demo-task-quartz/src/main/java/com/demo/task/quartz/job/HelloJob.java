package com.demo.task.quartz.job;

import cn.hutool.core.date.DateUtil;
import com.demo.task.quartz.job.base.BaseJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;

/**
 * @ClassName HelloJob
 * @Description TODO
 * @Author Leo
 * @Date 2019/11/2 11:21
 * @Version 1.0
 */
@Slf4j
public class HelloJob implements BaseJob {
    @Override
    public void execute(JobExecutionContext context) {
        log.error("Hello Job 执行时间：{}", DateUtil.now());
    }
}
