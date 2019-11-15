package com.demo.websocket.payload.server;

import com.demo.websocket.model.server.Mem;
import com.demo.websocket.payload.KV;
import lombok.Data;
import org.assertj.core.util.Lists;

import java.util.List;

/**
 * @ClassName MemVO
 * @Description 内存相关信息实体VO
 * @Author Leo
 * @Date 2019/11/7 10:34
 * @Version 1.0
 */
@Data
public class MemVO {
    List<KV> data = Lists.newArrayList();

    public static MemVO create(Mem mem) {
        MemVO vo = new MemVO();
        vo.data.add(new KV("内存总量", mem.getTotal() + "G"));
        vo.data.add(new KV("已用内存", mem.getUsed() + "G"));
        vo.data.add(new KV("剩余内存", mem.getFree() + "G"));
        vo.data.add(new KV("使用率", mem.getUsage() + "%"));
        return vo;
    }

}
