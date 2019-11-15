package com.demo.websocket.payload.server;

import com.demo.websocket.model.server.Jvm;
import com.demo.websocket.payload.KV;
import lombok.Data;
import org.assertj.core.util.Lists;

import java.util.List;

/**
 * @ClassName JvmVO
 * @Description JVM相关信息实体VO
 * @Author Leo
 * @Date 2019/11/7 10:34
 * @Version 1.0
 */
@Data
public class JvmVO {
    List<KV> data = Lists.newArrayList();

    public static JvmVO create(Jvm jvm) {
        JvmVO vo = new JvmVO();
        vo.data.add(new KV("当前JVM占用的内存总数（M）", jvm.getTotal() + "M"));
        vo.data.add(new KV("JVM最大可用内存总数（M）", jvm.getMax() + "M"));
        vo.data.add(new KV("JVM空闲内存（M）", jvm.getFree() + "M"));
        vo.data.add(new KV("JVM使用率", jvm.getUsage() + "%"));
        vo.data.add(new KV("JDK版本", jvm.getVersion()));
        vo.data.add(new KV("JDK路径", jvm.getHome()));
        vo.data.add(new KV("JDK启动时间", jvm.getStartTime()));
        vo.data.add(new KV("JDK运行时间", jvm.getRunTime()));
        return vo;
    }

}
