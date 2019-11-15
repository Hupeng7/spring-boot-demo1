package com.demo.websocket.payload;

import com.demo.websocket.model.Server;
import com.demo.websocket.payload.server.*;
import lombok.Data;
import org.assertj.core.util.Lists;

import java.util.List;

/**
 * @ClassName ServerVO
 * @Description 服务信息VO
 * @Author Leo
 * @Date 2019/11/7 10:33
 * @Version 1.0
 */
@Data
public class ServerVO {
    List<CpuVO> cpu = Lists.newArrayList();
    List<JvmVO> jvm = Lists.newArrayList();
    List<MemVO> mem = Lists.newArrayList();
    List<SysFileVO> sysFile = Lists.newArrayList();
    List<SysVO> sys = Lists.newArrayList();

    public ServerVO create(Server server) {
        cpu.add(CpuVO.create(server.getCpu()));
        jvm.add(JvmVO.create(server.getJvm()));
        mem.add(MemVO.create(server.getMem()));
        sysFile.add(SysFileVO.create(server.getSysFiles()));
        sys.add(SysVO.create(server.getSys()));
        return null;
    }


}
