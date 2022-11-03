package com.example.springbootdemomytool.utils.threaddemo.thread20221028.test4;

/**
 * @ClassName TaskCallableTest
 * @Description 测试回调
 * @Author H
 * @Date 2022/10/31 14:57
 * @Version 1.0
 */
public class TaskCallableTest {
    public static void main(String[] args) {
        TaskCallable<TaskResult> taskCallable = new TaskHandler();
        TaskExecutor taskExecutor = new TaskExecutor(taskCallable, "测试回调任务");
        new Thread(taskExecutor).start();
    }
}
