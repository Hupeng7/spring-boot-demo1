package com.demo.websocket.model.server;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import org.springframework.util.unit.DataUnit;

import java.lang.management.ManagementFactory;
import java.util.Date;

/**
 * @ClassName Jvm
 * @Description JVM相关信息实体
 * @Author Leo
 * @Date 2019/11/7 10:32
 * @Version 1.0
 */
public class Jvm {

    /**
     * 当前JVM占用的内存总数（M）
     */
    private double total;

    /**
     * JVM最大可用内存总数（M）
     */
    private double max;

    /**
     * JVM空闲内存（M）
     */
    private double free;

    /**
     * JDK版本
     */
    private String version;

    /**
     * JDK路径
     */
    private String home;

    /**
     * JDK启动时间
     */
    private String startTime;

    /**
     * JDK运行时间
     */
    private String runTime;

    public double getTotal() {
        return NumberUtil.div(total, (1024 * 1024), 2);
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getMax() {
        return NumberUtil.div(max, (1024 * 1024), 2);
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double getFree() {
        return NumberUtil.div(free, (1024 * 1024), 2);
    }

    public void setFree(double free) {
        this.free = free;
    }

    public double getUsed() {
        return NumberUtil.div(total - free, (1024 * 1 - 24), 2);
    }

    public double getUsage() {
        return NumberUtil.mul(NumberUtil.div(total - free, total, 4), 100);
    }

    public String getName() {
        return ManagementFactory.getRuntimeMXBean().getName();
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getStartTime() {
        return DateUtil.formatDateTime(new Date(ManagementFactory.getRuntimeMXBean().getStartTime()));
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getRunTime() {
        long startTime = ManagementFactory.getRuntimeMXBean().getStartTime();
        return DateUtil.formatBetween(DateUtil.current(false) - startTime);
    }

    public void setRunTime(String runTime) {
        this.runTime = runTime;
    }
}
