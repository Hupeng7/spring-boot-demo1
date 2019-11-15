package com.demo.websocket.payload.server;

import com.demo.websocket.model.server.Sys;
import com.demo.websocket.payload.KV;
import lombok.Data;
import org.assertj.core.util.Lists;

import java.util.List;

/**
 * @ClassName SysVO
 * @Description TODO
 * @Author Leo
 * @Date 2019/11/7 10:34
 * @Version 1.0
 */
@Data
public class SysVO {

    List<KV> data = Lists.newArrayList();

    public static SysVO create(Sys sys) {
        SysVO vo = new SysVO();
        vo.data.add(new KV("服务器名称", sys.getComputerName()));
        vo.data.add(new KV("服务器IP", sys.getComputerIp()));
        vo.data.add(new KV("项目路径", sys.getUserDir()));
        vo.data.add(new KV("操作系统", sys.getOsName()));
        vo.data.add(new KV("系统架构", sys.getOsArch()));
        return vo;
    }
}
