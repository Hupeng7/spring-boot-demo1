package com.demo.task.quartz.util;

import com.demo.task.quartz.job.base.BaseJob;

/**
 * @ClassName JobUtil
 * @Description TODO
 * @Author Leo
 * @Date 2019/11/2 11:01
 * @Version 1.0
 */
public class JobUtil {

    /**
     * 根据全类名获取Job实例
     *
     * @param className
     * @return
     * @throws Exception
     */
    public static BaseJob getClass(String className) throws Exception {
        Class<?> clazz = Class.forName(className);
        return (BaseJob) clazz.newInstance();
    }
}
