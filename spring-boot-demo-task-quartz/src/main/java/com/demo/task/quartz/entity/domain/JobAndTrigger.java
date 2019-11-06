package com.demo.task.quartz.entity.domain;

import lombok.Data;

import java.math.BigInteger;

/**
 * @ClassName JobAndTrigger
 * @Description 实体类
 * @Author Leo
 * @Date 2019/11/2 10:43
 * @Version 1.0
 */
@Data
public class JobAndTrigger {
    /**
     * 定时任务名称
     */
    private String jobName;
    /**
     * 定时任务组
     */
    private String jobGroup;
    /**
     * 定时任务全类名
     */
    private String jobClassName;
    /**
     * 触发器名称
     */
    private String triggerName;
    /**
     * 触发器组
     */
    private String triggerGroup;
    /**
     * 重复间隔
     */
    private BigInteger repeatInterval;
    /**
     * 触发次数
     */
    private BigInteger timesTriggered;
    /**
     * cron 表达式
     */
    private String cronExpression;
    /**
     * 市区
     */
    private String timeZoneId;
    /**
     * 定时任务状态
     */
    private String triggerState;

}
