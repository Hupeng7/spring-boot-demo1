package com.example.springbootdemomytool.utils.threaddemo.thread20221028.test4;

import java.io.Serializable;

/**
 * @ClassName TaskResult
 * @Description 任务执行结果
 * @Author H
 * @Date 2022/10/31 14:45
 * @Version 1.0
 */
public class TaskResult implements Serializable {
    private static final long serialVersionUID = -3306480081988459681L;

    /**
     * 任务状态
     */
    private Integer taskStatus;

    /**
     * 任务消息
     */
    private String taskMessage;

    /**
     * 任务结果数据
     */
    private String taskResult;

    public Integer getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getTaskMessage() {
        return taskMessage;
    }

    public void setTaskMessage(String taskMessage) {
        this.taskMessage = taskMessage;
    }

    public String getTaskResult() {
        return taskResult;
    }

    public void setTaskResult(String taskResult) {
        this.taskResult = taskResult;
    }

    @Override
    public String toString() {
        return "TaskResult{" +
                "taskStatus=" + taskStatus +
                ", taskMessage='" + taskMessage + '\'' +
                ", taskResult='" + taskResult + '\'' +
                '}';
    }
}
