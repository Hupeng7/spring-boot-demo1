package com.demo.websocket.payload.server;

import com.demo.websocket.model.server.Cpu;
import com.demo.websocket.payload.KV;
import lombok.Data;
import org.assertj.core.util.Lists;

import java.util.List;

/**
 * @ClassName CpuVO
 * @Description CPU相关信息实体VO
 * @Author Leo
 * @Date 2019/11/7 10:33
 * @Version 1.0
 */
@Data
public class CpuVO {
    List<KV> data = Lists.newArrayList();

    public static CpuVO create(Cpu cpu) {
        CpuVO vo = new CpuVO();
        vo.data.add(new KV("核心数", cpu.getCpuNum()));
        vo.data.add(new KV("CPU总的使用率", cpu.getTotal()));
        vo.data.add(new KV("CPU系统使用率", cpu.getSys() + "%"));
        vo.data.add(new KV("CPU用户使用率", cpu.getUsed() + "%"));
        vo.data.add(new KV("CPU当前等待率", cpu.getWait() + "%"));
        vo.data.add(new KV("CPU当前空闲率", cpu.getFree() + "%"));
        return vo;
    }


}
