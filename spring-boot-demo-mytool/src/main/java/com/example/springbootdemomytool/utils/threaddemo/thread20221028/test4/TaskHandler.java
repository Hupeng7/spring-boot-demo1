package com.example.springbootdemomytool.utils.threaddemo.thread20221028.test4;

/**
 * @ClassName TaskHandler
 * @Description
 * @Author H
 * @Date 2022/10/31 14:48
 * @Version 1.0
 */
public class TaskHandler implements TaskCallable<TaskResult> {
    @Override
    public TaskResult callable(TaskResult taskResult) {
        // TODO 拿到结果数据后进一步处理
        System.out.println(taskResult.toString());
        return taskResult;
    }
}
