package com.demo.task.quartz.service;

import com.demo.task.quartz.entity.domain.JobAndTrigger;
import com.demo.task.quartz.entity.form.JobForm;
import com.github.pagehelper.PageInfo;
import org.quartz.SchedulerException;

/**
 * @ClassName JobService
 * @Description TODO
 * @Author Leo
 * @Date 2019/11/2 11:35
 * @Version 1.0
 */
public interface JobService {
    /**
     * 添加并启动定时任务
     *
     * @param form 表单参数{@link JobForm}
     * @throws Exception 异常
     */
    void addJob(JobForm form) throws Exception;

    /**
     * 删除定时任务
     *
     * @param form 表单参数 {@link JobForm}
     * @throws SchedulerException 异常
     */
    void deleteJob(JobForm form) throws SchedulerException;

    /**
     * 恢复定时任务
     *
     * @param form
     * @throws SchedulerException
     */
    void resumeJob(JobForm form) throws SchedulerException;

    /**
     * 暂定定时任务
     *
     * @param form 表单参数 {@link JobForm}
     * @throws SchedulerException 异常
     */
    void pauseJob(JobForm form) throws SchedulerException;

    /**
     * 重新配置定时任务
     *
     * @param form 表单参数 {@link JobForm}
     * @throws Exception 异常
     */
    void cronJob(JobForm form) throws Exception;

    /**
     * 查询定时任务列表
     *
     * @param currentPage 当前页
     * @param pageSize    每页条数
     * @return 定时任务列表
     */
    PageInfo<JobAndTrigger> list(Integer currentPage, Integer pageSize);


}
